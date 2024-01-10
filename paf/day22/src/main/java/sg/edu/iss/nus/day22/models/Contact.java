package sg.edu.iss.nus.day22.models;

import java.sql.Timestamp;
import java.sql.Date;

public class Contact {

    private String email;
    private String name;
    private Date date;
    private String phone;
    private String comments;
    private Timestamp lastUpdate;

    public Contact() {

    }
    
    public Contact(String email, String name, Date date, String phone, String comments, Timestamp lastUpdate) {
        this.email = email;
        this.name = name;
        this.date = date;
        this.phone = phone;
        this.comments = comments;
        this.lastUpdate = lastUpdate;
    }
    @Override
    public String toString() {
        return "Contact [email=" + email + ", name=" + name + ", date=" + date + ", phone=" + phone + ", comments="
                + comments + ", lastUpdate=" + lastUpdate + "]";
    }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    public Timestamp getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(Timestamp lastUpdate) { this.lastUpdate = lastUpdate; }
    
}
