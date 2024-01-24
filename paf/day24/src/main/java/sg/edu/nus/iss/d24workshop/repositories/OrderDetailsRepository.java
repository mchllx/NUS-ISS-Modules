package sg.edu.nus.iss.d24workshop.repositories;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.d24workshop.models.OrderDetail;

@Repository
public class OrderDetailsRepository {

    @Autowired
    private JdbcTemplate template;

    private Logger logger = Logger.getLogger(OrderDetailsRepository.class.getName());

    public boolean insertOrderDetails(List<OrderDetail> orderDetails, String orderId)
        throws OrderException {
        // a list of string item, int quantity, string po_id, //id auto increment)
    
        int count = 0;
    
        for (OrderDetail od: orderDetails) {
            //update() returns an int of records affected
            int inserted = template.update(Queries.SQL_INSERT_ORDER_DETAILS, od.getProduct(), od.getQuantity(), orderId);
            count += inserted;

            if (count > 1) {
                logger.info("Transaction error");
                throw new OrderException("Error occured in processing order");
            }
            
        }
    
        logger.info(String.format("===Updated Record: %d, Total Records: %d===", count, orderDetails.size())); 
        
        //>0 is true
        return count == orderDetails.size();
        }
    
}
