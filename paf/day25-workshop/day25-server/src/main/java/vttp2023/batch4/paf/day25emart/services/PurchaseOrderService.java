package vttp2023.batch4.paf.day25emart.services;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2023.batch4.paf.day25emart.models.PurchaseOrder;
import vttp2023.batch4.paf.day25emart.repositories.LineItemRepository;
import vttp2023.batch4.paf.day25emart.repositories.PurchaseOrderException;
import vttp2023.batch4.paf.day25emart.repositories.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

    @Autowired @Qualifier("myredis")
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    PurchaseOrderRepository poRepo;

    @Autowired @Qualifier("poPubSub")
    private ChannelTopic topic;
    
    private Logger logger = Logger.getLogger(PurchaseOrderService.class.getName());

    public boolean createPurchaseOrderManualTx(PurchaseOrder po) {
        //returns str channel, obj msg
        redisTemplate.convertAndSend(topic.getTopic(), po.toJSON().toString());
        return false;
    }

    //redis list = array
    public String[] getAllRegisteredCustomers() {
        return redisTemplate.opsForList()
            .range("registration", 0, -1)
            .toArray(new String[0]);
    } 

    public PurchaseOrder generateId(PurchaseOrder po) {
            po.setOrderId(UUID.randomUUID().toString().substring(0, 8));
            logger.info("Purchase order generated" + po.getOrderId()); 

        return po; 
    }

    public boolean hasOrderId(PurchaseOrder po) {
        PurchaseOrder existingPO = poRepo.getByPOId(po.getOrderId());
        String existingID = existingPO.getOrderId();

        if (existingID == po.getOrderId()) {
            logger.info("Duplicated order ID");
            return true;
        } else {
            logger.info("Purchase order ID does not exist");
            return false;
        }
    }

    public boolean insertPurchaseOrder(PurchaseOrder po) {
        return poRepo.insertPurchaseOrder(po);
    }

}
