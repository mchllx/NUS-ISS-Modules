package sg.edu.nus.iss.d24workshop.repositories;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.d24workshop.models.Order;
import sg.edu.nus.iss.d24workshop.services.OrderService;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate template;

    private Logger logger = Logger.getLogger(OrderRepository.class.getName());

    public boolean insertOrder(Order order) throws OrderException { 
        return template.update(Queries.SQL_INSERT_ORDER
            , order.getOrderId(), order.getCustomerName(), order.getShipAddress()) > 0; 
    }
 
}
