package cart;

import java.util.*;
//import java.util.regex.Pattern;

public class Cart {

    //Store Shopping cart attributes in Cart class object
    List<String> cart;
    private String item;
    private int num;
    private int index;

    public Cart(List<String> cart) {
        this.cart = cart;
    }

    public void add(String item) {
        //Check if item exists in cart
        if (cart.contains(item)) {
            System.out.printf("%s exists in cart \n", item);
        } else {
            cart.add(item);
            System.out.printf("%s added to cart \n", item);
        }

    }

    //Overload del to accept two parameters (e.g. delete orange(string), delete 4 (index))
    public void del(int index) {
        //Check if index exists in cart
        if (cart.get(index)!= null) {
            System.out.printf("%s removed from cart \n", cart.get(index));
            cart.remove(index);
        } else {
        System.out.printf("%s does not exist in cart \n", item); 
        }
    }

    public void del(String item) {
        //Check if item exists in cart
        if (cart.contains(item)) {
            cart.remove(item);
            System.out.printf("%s removed from cart \n", item);
        } else {
        System.out.printf("%s does not exist in cart \n", item); 
        }
    }

    public void list() {
        //Check if cart is empty
        if (cart.isEmpty()) {
            System.out.println("Cart is empty \n");
        } else {
            //System.out.println("Cart is not empty\n");
            for (int i = 0; i < cart.size(); i++) {
            System.out.printf("%d. %s\n", (i+1), cart.get(i)); 
            }
        } 
    }

    public boolean isNum(String item) {
        //Check if input is a number
        // Rest of code will not run if exception is thrown
        try {
            index = Integer.parseInt(item);
            System.out.println("This is a number"); 
            return true;
        }

        catch (NumberFormatException exc){ 
            System.out.println("This is not a number");
            return false;      
        } 
    }
}

/** Tried pattern.match, constraint by char input length, switched to try block
 * public boolean isNum(String item) {
        //Matches against the regex
        if (Pattern.matches("[a-zA-Z]", "1")) {
            System.out.println("This is not a number");
            return false;
        } else {
            System.out.println("This is a number"); 
        } return true;
    } **/

