package sg.edu.nus.iss.d25workshop.repository;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

//Poller = send requests periodically for messages
//cannot implement Runnable, Springboot will throw a proxy exception
@Component
public class MessagePoller {
    @Autowired @Qualifier("myredis")
    private RedisTemplate<String, String> template;

    private Logger logger = Logger.getLogger(MessagePoller.class.getName());

    //create a thread
    @Async
    public void start() {
        //implement runnable
        Runnable run = () -> {
            ListOperations<String, String> listOps = template.opsForList();
            //starting message
            listOps.leftPush("sales", "STARTING...");
            logger.info("Start polling sales queue");

            //loop, always read new messages
            while (true) {
                logger.info("Polling...");
                //pops any message received during this interval
                String value = listOps.rightPop("sales", Duration.ofSeconds(10));
                if (((null == value) || ("" == value.trim()))) {
                    logger.info("No data repolling...");
                    continue;
                }
                //process data
                logger.info(">>>>>DATA: %s".formatted(value));
            }
        };
        ExecutorService thrPool = Executors.newFixedThreadPool(1);
        thrPool.submit(run);
    }

}


//     @Autowired @Qualifier("myredis")
//     private RedisTemplate<String, String> template;

//     @Async
//     public void start() {
//         //runs operation
//         Runnable poller = () -> {
//             ListOperations<String, String> orderList = template.opsForList();
//             while (true) {
//                 //BRPOP key, time interval
//                 Optional<String> opt = Optional.ofNullable(
//                     orderList.rightPop("orders", Duration.ofSeconds(5))
//                 );
           
//             //check if opt is empty or null
//             if (opt.isPresent()) {
//                 String data = opt.get();
//                 }
//             }
//         };
//         //start polling thread
//         Executors.newSingleThreadExecutor().execute(poller);

//     }
