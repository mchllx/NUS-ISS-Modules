package sg.edu.nus.iss.d25workshop.services;

import java.io.ByteArrayInputStream;

//use redis.connection
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

//MessageListener returns a byte[]
//@Service or @Component
@Component
public class SalesSubscribe implements MessageListener {
   
    @Override
    public void onMessage(Message msg, byte[] pattern) {
        String txt = new String(msg.getBody());
        System.out.printf(">>>> FROM subscription: %s\n", txt);

    }

}
