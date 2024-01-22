package vttp2023.batch4.paf.day23emart.services;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch4.paf.day23emart.models.PurchaseOrder;
import vttp2023.batch4.paf.day23emart.repositories.LineItemRepository;
import vttp2023.batch4.paf.day23emart.repositories.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

    @Autowired
    PurchaseOrderRepository poRepo;

    private Logger logger = Logger.getLogger(PurchaseOrderService.class.getName());

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
