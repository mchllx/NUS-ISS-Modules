package bankacc;

import java.time.LocalDateTime;
//import java.io.*;
import java.util.*;

public class BankAcc extends Main {

private String date;

private String customerName;
private String accNumber;
private Float balance;
private Boolean status = false;
private String accDate;
private String closeDate;

//These attributes are read only, thus no setters here
public String getCustomerName() { return customerName; }
public String getAccNumber() { return accNumber; }

//These attributes are read and write, thus getter and setters here to modify values outside of this class
public Float getBalance() { return balance; } public void setBalance(Float balance) { }
public Boolean getStatus() { return status; } public void setStatus(Boolean status) {}
public String getAccDate() { return accDate; } public void setAccDate (String accDate) {}
public String getCloseDate() { return closeDate; } public void setCloseDate(String closeDate) {}

    //Constructors to call values easily
    public BankAcc(Boolean status, String customerName, String accNumber, Float balance, String accDate, String closeDate) {
        this.status = status;
        this.customerName = customerName;
        this.accNumber = accNumber;
        this.balance = balance;
        this.accDate = accDate;
        this.closeDate = closeDate;
    }

    public BankAcc(Float balance) {
        this.balance = balance;
        balance = 0f;
    }
    

    public BankAcc(String customerName, Float balance) {
       this.customerName = customerName;
       this.balance = balance; 
    }

    public void newAccount() {
        System.out.println("Account does not exist, create new account? [y/n]");
        switch(line) {
             case "y":
                while(true) {
                    System.out.println("Enter name");
                    status = true;
                    customerName = line;
                    accNumber = UUID.randomUUID().toString().substring(0, 8);
                    balance = 0f; 
                    LocalDateTime currDateTime = LocalDateTime.now();
                    accDate = currDateTime.toString();
                    System.out.printf("Account created successfully");
                    break;
                }
                
             case "n": 
                System.out.println("Thank you for banking with us");
                break;

            default:
                System.out.println("Error, please enter y/n");
                break;
        }
    }

    public void checkStatus() { 
        if (status = false) { 
            newAccount();
        }
        else {
            LocalDateTime currDateTime = LocalDateTime.now();
            accDate = currDateTime.toString();
            System.out.printf("Account opened: %s", accDate);  
            } 
        }

    //Deposit funds
    public void depositFunds(float deposit) {
        if (status = false) {
            System.out.println("Account is closed");
        }
        else if ((status = true) && (deposit > 0)) {
            LocalDateTime currDateTime = LocalDateTime.now();
            date = currDateTime.toString();
            balance += deposit;
            System.out.printf("New balance is $%.2f %s\n", balance, date);
        }
        else {
            throw new IllegalArgumentException("Invalid value");
        }
     }

     //Withdraw funds
     public void withdrawFunds(float withdraw) { 
        if (status = false) {
            System.out.println("Bank account is closed");
        }
        else if ((status = true) && (balance - withdraw <0)) {
            throw new IllegalArgumentException("Insufficient funds");
        }
         else if ((status = true) && !(balance - withdraw <0) && (withdraw > 0)) {
            LocalDateTime currDateTime = LocalDateTime.now();
            date = currDateTime.toString();
            balance -= withdraw;
             System.out.printf("Remaining balance is $%.2f %s\n", balance, date);
        }
        else {
            throw new IllegalArgumentException("Invalid value");
        }
     }
 }