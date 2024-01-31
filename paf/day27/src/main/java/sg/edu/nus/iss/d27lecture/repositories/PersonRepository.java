package sg.edu.nus.iss.d27lecture.repositories;

import java.sql.Array;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.management.Query;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

    @Autowired
    private MongoTemplate template;

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
        // Document newDoc = template.insert(toInsert, "persons");

        // ObjectId id = newDoc.getObjectId("_id");
        // System.out.println(id);

        List<Document> docs = new LinkedList<>();
        docs.add(toInsert);

        Collection<Document> newDocs = template.insert(docs, "persons");

        // //inserting json obj into mongodb collection
        // template.insert(docs, "persons");

    }

//     db.persons.insertMany(
//     [
//         { name: "Xiao Ming", age: 20, gender: "M", hobbies: [ "food", "electronics"] },
//         { name: "Xiao Hua", age: 32, gender: "M", hobbies: [ "soccer", "running"] },
//         { name: "Xiao Mei", age: 40, gender: "F", hobbies: [ "shopping", "basketball"] },
//         { name: "Xiao Li", age: 18, gender: "F", hobbies: [ "studying", "sleeping"] },
//         { name: "Fred", age: 56, gender: "M", hobbies: [ "eating", "cartoons"] }
//     ]
// )
    public void insertMany() {
        ObjectId id = new ObjectId();
        String[] hobbies = {"food", "electronics"};
        
        Document docs = new Document()
            .append("_id", id)
            .append("name", "Xiao Ming")
            .append("age", "20")
            .append("gender", "M")
            .append("hobbies", hobbies);
        
        template.insert(docs, "persons");

    }
    
}
