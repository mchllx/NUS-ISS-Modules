package cart;
import java.util.*;
import java.io.Console;

public class CartApplication {

	public static void main(String[] args) {
	// TODO: Figure out how to compile with this structure
	//String name = "michelle";
	//System.out.printf("This file is successfully compiled into a JAR, %s", name);
	
	Console cons = System.console();

	if (cons == null) {
		System.out.printf("System is unavailable, please try again later");
	}

	String name = cons.readLine("What is your name?");
	System.out.printf("Welcome to your shopping cart, %s\n", name);

	System.out.println("Press HELP for a list of commands, press EXIT to exit this program");

	Map<String, List<String>> users = new HashMap<>();
	String[] commands = {"list", "add", "delete", "save", "help", "exit"};
	List<String> cart = new LinkedList<>();

	boolean exit = false;

	Scanner scan = new Scanner(System.in);

	while (!exit) {	
		String command = scan.next();
		String item = scan.nextLine();	

		System.out.printf(">>>%s\n", command);
		switch (command.trim().toLowerCase()) {
			case "list":
				if (cart.isEmpty()) {
					System.out.printf(">>>%s :: Your cart is empty\n", command);
					break;
				}
				System.out.printf(">>>%s :: %s\n", command, cart.toString());
				break;

			case "add":
				if (item.isBlank()) {
					System.out.printf(">>>%s :: No items selected\n", command);
					break;
				}

				String[] items = item.split(",");
				for (String i : items) {
					String newItem = i.trim();

					if (cart.contains(newItem)) {
						System.out.printf(">>>%s :: You have %s in your cart\n", command, newItem);
						continue;
					}
					
					System.out.printf(">>>%s :: Adding %s to %s\n", command, newItem, cart.toString());
					cart.add(newItem);
					System.out.printf(">>>%s :: Added to cart %s\n", command, cart.toString());
				}	
				break;

			case "delete":
				if (item.isBlank()) {
					System.out.printf(">>>%s :: No items selected\n", command);
					break;
				}
				int index = -2;

				try {
					index = Integer.parseInt(item.trim())-1;
				} catch (NumberFormatException e) {
					System.out.printf(">>>%s :: Invalid, please enter a number, stack:%s\n", command, Arrays.asList(e.getStackTrace()).toString());
					break;
				}

				if (!cart.contains(cart.get(index))) {
					System.out.printf(">>>%s :: %s does not exist in cart", command, item);
					break;
				}
				System.out.printf(">>>%s :: Deleting %s from %s\n", command, item, cart.toString());
				cart.remove(index);
				
				System.out.printf(">>>%s ::  Deleted from cart, cart: %s\n", command, cart.toString());
				break;
			
			case "save":
				System.out.printf(">>>%s :: Saving %s's cart: %s\n", command, name, cart.toString());
				users.put(name, cart);
				break;

			case "help":
				System.out.printf(">>>%s :: %s\n", command, Arrays.asList(commands).toString());
				break;

			case "exit":
				System.out.printf(">>>%s :: %b, Exiting cart\n", command, exit);
				exit = true;
				break;

			case "users":
				System.out.printf(">>>%s ::  Viewing users %s\n", command, users.toString());
				break;

			default:
				System.out.printf(">>>%s is an invalid command\n", command);
			}
		}
		cart.clear();
		scan.close();
	}	
}
