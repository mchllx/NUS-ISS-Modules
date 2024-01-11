package bankacc;

import java.time.LocalDateTime;

public class Transaction {

    @Override
    public String toString() {
        return "Transaction{" +
            "ID=" + transactionID +
            ", Amount=" + amount +
            ", Date=" + currDateTime +
            ", Description='" + description + '\'' +
            '}';
    } 

    private int transactionID;
    private float amount;
    private LocalDateTime currDateTime = LocalDateTime.now();
    private String description;

    public Transaction (int transactionID, float amount, LocalDateTime currDateTime, String description) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.currDateTime = currDateTime;
        this.description = description;
    }
}