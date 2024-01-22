package vttp2023.batch4.paf.day23emart.models;

public class LineItem extends PurchaseOrder{

   private int id;
   private String item;
   private int quantity;

   public LineItem() {
   }

   public LineItem(int id, String item, int quantity) {
      this.id = id;
      this.item = item;
      this.quantity = quantity;
   }

   public int getId() { return id; }
   public void setId(int id) { this.id = id; }

   public String getItem() { return item; }
   public void setItem(String item) { this.item = item; }

   public int getQuantity() { return quantity; }
   public void setQuantity(int quantity) { this.quantity = quantity; }

   //"" to convert int to str
   @Override
   public String toString() {
      return "LineItem [id="
         .concat("" + this.id)
         .concat(", item=")
         .concat(this.item)
         .concat(", quantity=")
         .concat("" + this.quantity)
         .concat("]");
   }
}
