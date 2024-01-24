package sg.edu.nus.iss.d24workshop.models;

// id int auto_increment,
//     product varchar(64),
//     unit_price decimal(6,2),
//     discount decimal(6,2) default 1.0,
//     quantity int,
//     order_id int not null,
public class OrderDetail {

    private int id;
    private String product;
    private double unitPrice;
    private double discount;
    private int quantity;
    private String orderId;

    @Override
    public String toString() {
        return "OrderDetail [id="
            .concat("" + this.id)
            .concat(", product=")
            .concat("" + this.product)
            .concat(", unitPrice=")
            .concat("" + this.unitPrice)
            .concat(", discount=")
            .concat("" + this.discount)
            .concat(", quantity=")
            .concat("" + this.quantity)
            .concat(", orderId=")
            .concat("" + this.orderId)
            .concat("]");
    }

    public OrderDetail() { 
    }

    public OrderDetail(int id, String product, double unitPrice, double discount, int quantity, String orderId) {
        this.id = id;
        this.product = product;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.quantity = quantity;
        this.orderId = orderId;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    
}
