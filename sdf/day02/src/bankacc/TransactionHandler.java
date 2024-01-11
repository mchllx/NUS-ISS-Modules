package bankacc;

import java.util.*;

public class TransactionHandler {
    
    //An arraylist is used as new values/transactions can be stores in indexes directly
    private List<Transaction> transactionList = new ArrayList<>();

    public void viewTransactions() {
        if (transactionList.isEmpty()) {
            System.out.println("No transactions");
        }
        else {
            System.out.println(transactionList);
        }

        /**transactionList.stream()
           .forEachOrdered(System.out::println);

        for (int i = 0; i < transactionList.size(); i++) {
            String results = (transactionList.get(i).toString());
            System.out.println(results);
            }
            
        for (Transactions i : transactionList) {
            System.out.println(i);
        **/

        
        }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction); 
    }
}
    
    
