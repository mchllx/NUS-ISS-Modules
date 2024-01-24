package sg.edu.nus.iss.d24workshop.services;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.d24workshop.repositories.OrderRepository;
import sg.edu.nus.iss.d24workshop.models.Order;
import sg.edu.nus.iss.d24workshop.models.OrderDetail;
import sg.edu.nus.iss.d24workshop.repositories.OrderDetailsRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OrderDetailsRepository odRepo;

    private Logger logger = Logger.getLogger(OrderService.class.getName());

    public boolean insertOrder(Order order) {
        return orderRepo.insertOrder(order);
    }

    public boolean insertOrderDetail(List<OrderDetail> orderDetails, String orderId) {
        return odRepo.insertOrderDetails(orderDetails, orderId);
    }

    public String generateId(Order order) {
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        logger.info("Order ID generated" + orderId); 

    return orderId;
    }
    
    public boolean hasOrderId(Order order) {
        if (orderRepo.insertOrder(order) == false) {
            logger.info("Order ID exists");
            return true;
        } else {
            logger.info("Order ID available");
            return false;
        }
    }
    
}