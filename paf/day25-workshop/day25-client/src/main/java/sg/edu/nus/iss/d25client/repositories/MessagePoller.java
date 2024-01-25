package sg.edu.nus.iss.d25client.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MessagePoller {

    @Autowired
    private RedisTemplate<String, String> template;

    @Autowired
    private ApplicationArguments appArgs;

    //task1 set app name as acme
    @Async
    public void start() {
        List<String> customerNameArg = appArgs.getNonOptionArgs();
        // System.out.println("Customer name" + customerNameArg);

        //connect to redis, lpush "registration", customerNameArg[0];
        template.opsForList().leftPush("registration", customerNameArg.get(0)); 
    }
    
}
