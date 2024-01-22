package vttp2023.batch4.paf.day23emart.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch4.paf.day23emart.models.LineItem;
import vttp2023.batch4.paf.day23emart.repositories.LineItemRepository;

@Service
public class LineItemService {

    @Autowired
    LineItemRepository liRepo;
   
    private Logger logger = Logger.getLogger(LineItemService.class.getName());

    public boolean insertLineItem(List<LineItem> lineItems, String orderId) {
        return liRepo.insertLineItem(lineItems, orderId);
    }
    
}
