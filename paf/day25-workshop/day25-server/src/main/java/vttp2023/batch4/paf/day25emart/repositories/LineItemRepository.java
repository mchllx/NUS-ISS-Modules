package vttp2023.batch4.paf.day25emart.repositories;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2023.batch4.paf.day25emart.models.LineItem;
import vttp2023.batch4.paf.day25emart.models.PurchaseOrder;

@Repository
public class LineItemRepository {

    @Autowired
    private JdbcTemplate template;

    private Logger logger = Logger.getLogger(LineItemRepository.class.getName());

    public boolean insertLineItem(List<LineItem> lineItems, String orderId)
        throws PurchaseOrderException {
    // a list of string item, int quantity, string po_id, //id auto increment)

    int count = 0;

    for (LineItem li: lineItems) {
        //update() returns an int of records affected
        int inserted = template.update(Queries.SQL_INSERT_LINE_ITEM, li.getItem(), li.getQuantity(), orderId);

        count += inserted;

        //update if successfully, don't update if even 1 record fails to insert
        if (count > 1) {
            logger.info("Error occurred processing order:" + li.getItem());
            throw new PurchaseOrderException("Invalid transaction error");
        }
    }

    logger.info(String.format("Updated Record: %d, Total Records: %d", count, lineItems.size())); 
    
    //>0 is true
    return count == lineItems.size();
    }

}
