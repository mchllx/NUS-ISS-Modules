package bankacc;

import java.io.*;
//import java.util.*;
import java.time.LocalDateTime;

public class Main {

    public static String line;

    public static void main(String[]args){
  
       Console cons = System.console();

        if (cons == null) {
            System.out.println("No console available");
            return;
        }
        boolean stop = false;
        BankAcc acc = new BankAcc("fred", 0f);
        TransactionHandler records = new TransactionHandler();

        while (!stop) {
            line = cons.readLine("> ");

            //System.out.printf("read:%s ", line);

            String[] terms = line.split(" ", 2);
            String cmd = terms[0];
            String amount;

            switch (cmd) {
                case "help":
                    System.out.println("deposit, withdraw, view, balance, stop");
                    break;

                case "deposit":
                    amount = (terms[1] + "f");
                    acc.depositFunds(Float.parseFloat(amount));
                    Transaction t1 = new Transaction(1, Float.parseFloat(terms[1] + "f"), LocalDateTime.now(), "Deposit");
                    records.addTransaction(t1);
                    break;

                case "withdraw":
                    amount = (terms[1] + "f");
                    acc.withdrawFunds(Float.parseFloat(amount));
                    Transaction t2 = new Transaction(1, Float.parseFloat(terms[1] + "f"), LocalDateTime.now(), "Deposit");
                    records.addTransaction(t2); 
                    break;

                case "view":
                    records.viewTransactions();
                    break; 

                case "balance":
                    System.out.printf("Your current balance: $%.2f", acc.getBalance());
                    break;

                case "status":
                    acc.checkStatus();
                    break;

                case "stop":
                    stop = true;
                    break; 

            default:
            System.out.println("Error, type help for list of commands");

            } 
        }
        System.out.println("Thank you for banking with us"); 
    }
}
