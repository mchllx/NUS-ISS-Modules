package sg.edu.nus.iss.d27lecture.repositories;

import java.io.BufferedReader;
import java.sql.Array;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Update.Position;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import sg.edu.nus.iss.d27lecture.services.PersonService;

@Repository
public class PersonRepository {

    @Autowired
    private MongoTemplate template;

    @Autowired
    private PersonService personSvc;

    //db.persons.insert(
//    {
//        _id: 1, name: "Xiao Ming", age: 20, gender: "M", hobbies: [ "food", "electronics"]
//    }
//)

    public void insertOne() {
        //inserting manually from spring
        Document toInsert = new Document()
            .append("name", "Penguin");
        // System.out.println(toInsert);
        Document newDoc = template.insert(toInsert, "persons");

        ObjectId id = newDoc.getObjectId("_id");
        // System.out.println(id);

        List<Document> docs = new LinkedList<>();
        docs.add(toInsert);

        Collection<Document> newDocs = template.insert(docs, "persons");

        // //inserting json obj into mongodb collection
        // template.insert(docs, "persons");

    }

//     db.persons.insertMany(
//     [
//         { name: "Xiao Ming", age: 20, gender: "M", hobbies: [ "food", "investments"] },
//         { name: "Xiao Hua", age: 32, gender: "M", hobbies: [ "soccer", "running"] },
//         { name: "Xiao Mei", age: 40, gender: "F", hobbies: [ "shopping", "basketball"] },
//         { name: "Xiao Li", age: 18, gender: "F", hobbies: [ "studying", "sleeping"] },
//         { name: "Fred", age: 56, gender: "M", hobbies: [ "eating"] }
//     ]
// )
    public void insertMany(String fileName) {

        // Document doc1 = personSvc.createDoc("Xiao Ming", 20, "M", new String[]{"food", "electronics"});
        // Document doc2 = personSvc.createDoc("Xiao Hua", 32, "M", new String[]{"soccer", "running"});
        // Document doc3 = personSvc.createDoc("Xiao Mei", 40, "F", new String[]{"shopping", "basketball"});
        // Document doc4 = personSvc.createDoc("Xiao Li", 18, "F", new String[]{"studying", "sleeping"});
        // Document doc5 = personSvc.createDoc("Fred", 56, "M", new String[]{"eating", "cartoons"});

        template.insert(personSvc.readCSV(fileName), "persons");

    }

    //db.persons.deleteOne(
//    { name: "Xiao Ming"},
//    { name:
//        {$exists: true}}
//)
    public void deleteOne() {
        Query query = Query
            .query(Criteria.where("name").is("Xiao Ming"));

        DeleteResult del = template.remove(query, "persons");

    }

    public void deleteMany() {
        Query query = Query
            .query(Criteria.where("_id").exists(true));

        // DeleteResult del = template.remove(query, "persons");

        List<Document> deleted = template.findAllAndRemove(query, Document.class,  "persons");
        System.out.println(">>> deleted" + deleted);
    }

    // db.persons.updateOne(
    //     { name: "Xiao Hua" },
    //     {
    //         $set: {gender: "M"},
    //         $inc: { "age": 1},
    //         $unset: {gender: ""},
    //         $set: {status: "single"},
    //         $set: {nickname: "Hua hua"}, 
    //     }
    // )
    public void updateOne() {
        Query query = Query
            .query(Criteria.where("name").is("Xiao Hua"));
        
        Update updateOps = new Update()
            .inc("age", 1)
            .unset("gender")
            .set("status", "single")
            .set("nickname", "Hua hua");
        
        UpdateResult updateResult = template.updateMulti(query, updateOps, Document.class, "persons");
        System.out.printf("Documents updated: %d\n", updateResult.getModifiedCount());

    }

//     db.persons.updateOne(
//     {  name: "Xiao Mei" },
//     {
//         $pop: {hobbies: -1},
//         $push: {hobbies: "skating"},
    
//     }
// )
    public void updateHobbyForOne() {
        Query query = Query
            .query(Criteria.where("name").is("Xiao Mei"));

        Update updateOps = new Update()
            .pop("hobbies", Update.Position.FIRST)
            .push("hobbies", "painting");

        UpdateResult updateResult = template.updateFirst(query, updateOps, Document.class, "persons");
        System.out.printf("Documents updated: %d\n", updateResult.getModifiedCount());
    }

//     db.persons.updateOne(
//     { name: "Maybelline" },
//     { $set: {name: "Xiao Mei", age: 40,
//       gender: "F", hobbies: ["painting", "skating"]
//     }},
//     { upsert:true }
// )
    public void upsertOne() {
        Query query = Query
            .query(Criteria
            .where("name")
            .is("Maybelline")
            .and("gender")
            .is("Others"));
        
        Update updateOps = new Update()
            .set("name", "Xiao Mei")
            .set("age", 40)
            .set("gender", "F")
            .push("hobbies").each("painting", "skating");

        UpdateResult updateResult = template.upsert(query, updateOps, "persons");
        System.out.printf("Documents updated: %d\n", updateResult.getModifiedCount());

    }

//     db.persons.find(
//     {
//         $text: {
//             $search: "xiao mei"
//         }
//     }

// )
    public void search() {
        TextCriteria txt = TextCriteria
            .forDefaultLanguage()
            .matchingPhrase("xiao mei")
            .caseSensitive(false);

        TextQuery query = TextQuery.queryText(txt);

        List<Document> results = template.find(query, Document.class, "persons");
        System.out.println("Search" + results);

    }

//     db.persons.find(
//     {
//         $text: {
//             $search: "xiao mei"
//         }
//     },
//     {
//         score: {
//             $meta: "textScore"
//         }
//     }
// )
    public void searchWithScore() {
        TextCriteria txt = TextCriteria
            .forDefaultLanguage()
            .matchingPhrase("xiao mei")
            .caseSensitive(false);
        
        TextQuery query = TextQuery.queryText(txt)
            .sortByScore()
            .includeScore("score");
        
        List<Document> results = template.find(query, Document.class, "persons");
        System.out.println("Search with score" + results);

    }

    
}
