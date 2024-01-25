package vttp2023.batch4.paf.day25emart;

import java.util.Date;
import java.util.LinkedList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp2023.batch4.paf.day25emart.models.LineItem;
import vttp2023.batch4.paf.day25emart.models.PurchaseOrder;

@SpringBootApplication
public class Day25WorkshopApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Day25WorkshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// PurchaseOrder po = new PurchaseOrder("orderId", "name", "address", new Date(), new Date(), new LinkedList<>());
		// System.out.println(po.toString());

		// LineItem li = new LineItem(0, "item", 0);
		// System.out.println(li.toString());
	}


}
