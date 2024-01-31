package sg.edu.nus.iss.d27lecture.repositories;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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

    public void readById() {
        //inserting manually from spring
        Document toInsert = new Document("name", "Penguin");
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
    
}
