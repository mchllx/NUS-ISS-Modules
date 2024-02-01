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
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;

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

   
    
}
