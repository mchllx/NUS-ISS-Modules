package vttp2023.batch4.paf.day25emart.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2023.batch4.paf.day25emart.models.LineItem;
import vttp2023.batch4.paf.day25emart.repositories.LineItemRepository;
import vttp2023.batch4.paf.day25emart.repositories.PurchaseOrderException;

@Service
public class LineItemService {

    @Autowired
    LineItemRepository liRepo;
   
    private Logger logger = Logger.getLogger(LineItemService.class.getName());

    @Transactional(rollbackFor=PurchaseOrderException.class)
    public boolean insertLineItem(List<LineItem> lineItems, String orderId) throws PurchaseOrderException {
            return liRepo.insertLineItem(lineItems, orderId); 
    }
}
