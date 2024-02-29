package com.example.backend;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import jakarta.servlet.http.HttpSession;

public class Utils {

    private static Logger logger = Logger.getLogger(Utils.class.getName());

    public static final String BEAN_REDIS = "myredis";

    //check if session is null
    // public static List<LineItem> getCart(HttpSession sess) {
    //     List<LineItem> cart = new LinkedList<>();

    //     if (sess == null) {
    //         logger.info("Item not found");
    //     }

    //     PurchaseOrder po = (PurchaseOrder)sess.getAttribute("po");

    //     if (po == null) {
    //         logger.info("Purchase order is empty");
    //     } else {
    //         logger.info("Retrieving Purchase Order" + po);
    //         cart = po.getLineItems();
    //     }

    //     return cart; 
    // }

    // //check if cart is empty
    // public static boolean hasCart(HttpSession sess) {
    //     List<LineItem> cart = Utils.getCart(sess);

    //     if (cart == null) {
	// 		logger.info("Cart not found");
    //         return false;
	// 	}

    //     if (cart.isEmpty()) {
    //         logger.info("Cart is empty");
    //         return false;
    //     } else {
    //         logger.info("Cart found" + cart);
    //         return true;
    //     }
    // } 

    // //get saved purchase order
    // public static PurchaseOrder getPO(HttpSession sess){
        
    //     if (sess == null) {
    //         logger.info("Purchase order not found");
    //         return new PurchaseOrder();

    //     } else {
    //         PurchaseOrder po = (PurchaseOrder)sess.getAttribute("po");
    //         logger.info("Purchase order found" + po); 
    //         return po;
    //     } 
    // }
}
