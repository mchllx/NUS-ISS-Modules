package sg.edu.nus.iss.d24workshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.d24workshop.models.Order;
import sg.edu.nus.iss.d24workshop.models.OrderDetail;

@SpringBootApplication
public class D24WorkshopApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(D24WorkshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// String order = new Order().toString();
		// String orderDetail = new OrderDetail().toString();
		// System.out.println("Order" + order);
		// System.out.println("Order Detail" + orderDetail);
	}

}
