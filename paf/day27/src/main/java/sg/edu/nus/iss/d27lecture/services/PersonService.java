package sg.edu.nus.iss.d27lecture.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    public Document createDoc(String name, Integer age, String gender, String hobbies) {
        
        Document doc = new Document()
            .append("_id", new ObjectId())
            .append("name", name)
            .append("age", age)
            .append("gender", gender)
            .append("hobbies", hobbies);

        return doc;

    }

    public Map<String, List<Document>> readCSV(String fileName) {

        Map<String, List<Document>> map = new HashMap<>();

        try {
            System.out.println("Starting");
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            map = br.lines()
            .skip(1)
            .map(s -> s.split(","))
            .peek(s -> System.out.println(s[0]+s[1]+s[2]+s[3]+s[4]))
            .map(s -> new Document()
                .append("_id", s[0])
                .append("name", s[1])
                .append("age", s[2])
                .append("gender", s[3])
                .append("hobbies", s[4]))
            .collect(Collectors.groupingBy(d -> d.getString("_id")));
            System.out.println(">>>End of stream");

        } catch (IOException e1) {
            e1.printStackTrace();
            System.out.println(">>>Invalid file");
        }

        return map;
            

       

        // List<Document> docs = new LinkedList<>();
        // List<String> lines = new LinkedList<>();
 
        // try {
        //     FileReader fr = new FileReader(fileName);
        //     BufferedReader br = new BufferedReader(fr);
        //     String line;
            
        //     //{hobbies: {soccer, investments}}
        //     while ((line = br.readLine()) != null) {
        //         lines.add(line);
        //         // System.out.println(line);
        //     }

        //     System.out.println(lines);
        //     System.out.println(">>>End of file");

        // } catch (IOException e1) {
        //     e1.printStackTrace();
        //     System.out.println(">>>Invalid file");
        // }
        //     Map<String, List<Document>> map = lines.stream()
        //         .peek(s -> System.out.println(s))
        //         .skip(1)
        //         .map(s -> s.split(","))
        //         // .peek(s -> System.out.println(s[0] + s[1] + s[2] + s[3]))
        //         .map(s -> new Document()
        //             .append("_id", new ObjectId())
        //             .append("name", s[0])
        //             .append("age", s[1])
        //             .append("gender", s[2])
        //             .append("hobbies", s[3]))
        //         .collect(Collectors.groupingBy(d -> d.getString("name")));
            
        //     return map;
 
        } 
    }
    
