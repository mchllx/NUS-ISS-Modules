package sg.edu.iss.nus.day22.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.iss.nus.day22.models.Contact;

@Repository
public class BffRepository {

    @Autowired
    private JdbcTemplate template;

    public boolean contactExists(String email) {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_COUNT_EMAIL, email);
        // this statement will always return something, even if email doesn't match
        if (!rs.next())
            return false;

        int emailCount = rs.getInt("email_count"); 
        return emailCount > 0;
    }

    // SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BFF_BY_EMAIL, email);
    // returns true if new row is valid
    // return rs.next();

    public boolean insertContact(Contact c) {

        // insert into bff(email, name, dob, phone, comments)
        return template.update(Queries.SQL_INSERT_BFF,
            c.getEmail(), c.getName(), c.getDate(), c.getPhone(), c.getComments()
        ) >= 1;

    }
    
}
