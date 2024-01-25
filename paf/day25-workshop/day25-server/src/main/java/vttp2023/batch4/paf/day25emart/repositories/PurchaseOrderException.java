package vttp2023.batch4.paf.day25emart.repositories;

public class PurchaseOrderException extends Exception { 
    public PurchaseOrderException() {
        super();
    }

    public PurchaseOrderException(String msg){
        super(msg);
    }
}
