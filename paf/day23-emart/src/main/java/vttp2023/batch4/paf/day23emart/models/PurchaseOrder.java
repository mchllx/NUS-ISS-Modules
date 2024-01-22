package vttp2023.batch4.paf.day23emart.models;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedList;

public class PurchaseOrder {

   private String orderId;

   @NotEmpty(message = "Name is required")
   @Size(min=3, max=20, message="Between 3 and 20 characters")
   private String name;

   @NotEmpty(message="Address is required")
   @Email(message="Invalid email format, example@gmail.com")
   private String address;

   private Date createdOn;
   private Date lastUpdate;

   private List<LineItem> lineItems = new LinkedList<>();

   public PurchaseOrder() {
   } 

	public PurchaseOrder(String orderId, String name, String address, Date createdOn, Date lastUpdate,
         List<LineItem> lineItems) {
      this.orderId = orderId;
      this.name = name;
      this.address = address;
      this.createdOn = createdOn;
      this.lastUpdate = lastUpdate;
      this.lineItems = lineItems;
   }

   public String getOrderId() { return orderId; }
   public void setOrderId(String orderId) { this.orderId = orderId; }

   public String getName() { return name; }
   public void setName(String name) { this.name = name; }

   public String getAddress() { return address; }
   public void setAddress(String address) { this.address = address; }

   public Date getCreatedOn() { return createdOn; }
   public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; }

   public Date getLastUpdate() { return lastUpdate; }
   public void setLastUpdate(Date lastUpdate) { this.lastUpdate = lastUpdate; }

	public List<LineItem> getLineItems() { return lineItems; }
	public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }

   @Override
   public String toString() {
      return "PurchaseOrder [orderId="
         .concat("" + this.orderId)
         .concat(", name=")
         .concat(this.name)
         .concat(", address=")
         .concat("" + this.address)
         .concat(", createdOn=")
         .concat("" + this.createdOn)
         .concat(", lastUpdate=")
         .concat("" + this.lastUpdate)
         .concat(", lineItems=")
         .concat("" + this.lineItems.toString())
         .concat("]");
   }
}
