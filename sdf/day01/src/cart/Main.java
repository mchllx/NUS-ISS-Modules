package cart;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[]args) {

        System.out.println("Welcome to your shopping cart");

        Console cons = System.console();

        //Check if console exists
            if (cons == null) {
            System.out.println ("Console does not exist");
            }

        //Instantiate List cart and class object Cart to access fields and methods
        List<String> cart = new LinkedList<>();
        Cart Cart = new Cart(cart);

        boolean stop = false;
 
        while (!stop) {

             //Console reads input (string) by line
            String line = cons.readLine();

            //Split input by white space and commas
            String[] terms = line.split(" ");
            String cmd = terms[0];

            switch(cmd) {
                case "help":
                System.out.println("Use add, del, list, exit");
                break;

                case "add":
                Cart.add(terms[1]);
                break;

                case "del":
                if (Cart.isNum(terms[1]) == true) {
                    int index = Integer.parseInt(terms[1]);
                    Cart.del(index - 1);
                    break;
                } else {
                    Cart.del(terms[1]);
                    break;
                }
                    
                case "list":
                Cart.list();
                break;
                
                case "exit":
                stop = true;
                System.out.println("Thank you for shopping with us");
                break;

                default:
                System.out.println("Use help for acceptable commands");
            }
        }
    }
}