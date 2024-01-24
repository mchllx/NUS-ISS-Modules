package sg.edu.nus.iss.d24workshop;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.d24workshop.models.Order;
import sg.edu.nus.iss.d24workshop.models.OrderDetail;

public class Utils {

    private static Logger logger = Logger.getLogger(Utils.class.getName());

    //check if session is null
    public static List<OrderDetail> getCart(HttpSession sess) {
        List<OrderDetail> cart = new LinkedList<>();

        if (sess == null) {
            logger.info("Item not found");
        }

        Order order = (Order)sess.getAttribute("order");

        if (order == null) {
            logger.info("No existing order");
        } else {
            logger.info("Retrieving Order" + order);
            cart = order.getOrderDetails();
        }

        return cart; 
    }

    //check if cart is empty
    public static boolean hasCart(HttpSession sess) {
        List<OrderDetail> cart = Utils.getCart(sess);

        if (cart == null) {
			logger.info("Cart not found");
            return false;
		}

        if (cart.isEmpty()) {
            logger.info("Cart is empty");
            return false;
        } else {
            logger.info("Cart found" + cart);
            return true;
        }
    } 

    //get saved order
    public static Order getOrder(HttpSession sess){
        
        if (sess == null) {
            logger.info("Order not found");
            return new Order();

        } else {
            Order order = (Order)sess.getAttribute("order");
            logger.info("Order found" + order); 
            return order;
        } 
    }
}
