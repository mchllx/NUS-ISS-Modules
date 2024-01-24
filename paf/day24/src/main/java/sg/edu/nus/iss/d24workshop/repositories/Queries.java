package sg.edu.nus.iss.d24workshop.repositories;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import sg.edu.nus.iss.d24workshop.models.OrderDetail;

public class Queries {

    //int orderId, date orderDate, string customerName, string shipAddress, string text, double tax, list<orderdetail>orderDetails
    //int id, string product, double unitprice, int quantity

    public static final String SQL_INSERT_ORDER = """
        insert into orders(order_id, customer_name, ship_address)
            values (?, ?, ?)
    """;

    public static final String SQL_INSERT_ORDER_DETAILS = """
        insert into order_detail(product, quantity, order_id)
            values (?, ?, ?)
    """;
    
}
