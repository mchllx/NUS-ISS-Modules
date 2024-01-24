package sg.edu.nus.iss.d24workshop.models;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

// order_id int auto_increment,
// order_date date not null,
// customer_name varchar(128) not null,
// ship_address varchar(128) not null,
// notes text,
// tax decimal(6,2) default 0.05,

// orderdetail
// id int auto_increment,
//     product varchar(64),
//     unit_price decimal(6,2),
//     discount decimal(6,2) default 1.0,
//     quantity int,
//     order_id int not null,
public class Order  {

    private int orderId;
    private Date orderDate;

    @NotEmpty(message="Name is a required field")
    @Size(min=3, max=20, message="Between 3 and 20 characters")
    private String customerName;

    @NotEmpty(message="Address is a required field")
    private String shipAddress;

    private String text;
    private Double tax;
    private List<OrderDetail> orderDetails;

    @Override
    public String toString() {
        return "Order [orderId="
            .concat("" + this.orderId)
            .concat(", orderDate=")
            .concat("" + this.orderDate)
            .concat(", customerName=")
            .concat("" + this.customerName)
            .concat(", shipAddress=")
            .concat("" + this.shipAddress)
            .concat(", text=")
            .concat("" + this.text)
            .concat(", tax=")
            .concat("" + this.tax)
            .concat(", orderDetails=")
            .concat("" + this.orderDetails)
            .concat("]");
    }

    public Order() {
    }

    public Order(int orderId, Date orderDate, String customerName, String shipAddress, String text, Double tax,
            List<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.shipAddress = shipAddress;
        this.text = text;
        this.tax = tax;
        this.orderDetails = orderDetails;
    }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getShipAddress() { return shipAddress; }
    public void setShipAddress(String shipAddress) { this.shipAddress = shipAddress; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Double getTax() { return tax; }
    public void setTax(Double tax) { this.tax = tax; }
    public List<OrderDetail> getOrderDetails() { return orderDetails; }
    public void setOrderDetails(List<OrderDetail> orderDetails) { this.orderDetails = orderDetails; } 
}
