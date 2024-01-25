package vttp2023.batch4.paf.day25emart.services;

import java.io.ByteArrayInputStream;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2023.batch4.paf.day25emart.models.PurchaseOrder;
import vttp2023.batch4.paf.day25emart.repositories.LineItemRepository;
import vttp2023.batch4.paf.day25emart.repositories.PurchaseOrderException;
import vttp2023.batch4.paf.day25emart.repositories.PurchaseOrderRepository;

@Service
public class PurchaseOrderSubscriber implements MessageListener {

    @Autowired
    private PurchaseOrderRepository poRepo;

    @Autowired
    private PlatformTransactionManager txMgr;

    @Autowired
    private LineItemRepository liRepo;

    private Logger logger = Logger.getLogger(PurchaseOrderSubscriber.class.getName());

    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] data = message.getBody();

        logger.info("====Received message====" + data);
       
        //read byte -> jsonobj
        JsonReader reader = Json.createReader(new ByteArrayInputStream(data));
        JsonObject obj = reader.readObject();

        //convert jsonobj(DB) -> pojo
        PurchaseOrder po = PurchaseOrder.fromJSON(obj);

        //checks for transactions, rollbacks when transaction exception is thrown
        TransactionStatus tx = txMgr.getTransaction(null);
        
        //generate ID
        String poId = UUID.randomUUID().toString().substring(0, 8);
        po.setOrderId(poId);

        logger.info("Total records:" + po.getLineItems().size());

        try {
            boolean result = poRepo.insertPurchaseOrder(po) 
            & liRepo.insertLineItem(po.getLineItems(), poId);
            //throws exceptions if contains, rollbacks @Transactional(rollbackfor=...)
            txMgr.commit(tx);

        } catch (PurchaseOrderException e1) {
            logger.info("======Rolling back transaction=====\n");
            e1.printStackTrace();

        }
    }

    
}
