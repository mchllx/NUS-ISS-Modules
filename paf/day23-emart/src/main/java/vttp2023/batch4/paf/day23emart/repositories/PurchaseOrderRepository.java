package vttp2023.batch4.paf.day23emart.repositories;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import vttp2023.batch4.paf.day23emart.models.LineItem;
import vttp2023.batch4.paf.day23emart.models.PurchaseOrder;

@Repository
public class PurchaseOrderRepository {

    @Autowired
    private JdbcTemplate template;

    private Logger logger = Logger.getLogger(PurchaseOrderRepository.class.getName());

    public boolean insertPurchaseOrder(PurchaseOrder po) {
        // string po_id, string name, string address)
        return template.update(Queries.SQL_INSERT_PURCHASE_ORDER
        , po.getOrderId(), po.getName(), po.getAddress()) > 0;
    }

    public PurchaseOrder getByPOId(String orderId) {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BY_PO_ID, orderId);

        //if record does not exist
        if(!rs.next()){
        logger.info("Purchase order does not exist in database");
            return new PurchaseOrder();
        } else {
            logger.info("Purchase order found in database");
            rs.beforeFirst();

            // str name, str address, date createdOn, date lastUpdate, list<li> lineItems
   
            PurchaseOrder po = new PurchaseOrder();
            po.setOrderId(rs.getString("po_id"));
            po.setName(rs.getString("name"));
            po.setAddress(rs.getString("address"));
            po.setCreatedOn(rs.getDate("created_on"));
            po.setLastUpdate(rs.getDate("last_update"));
            return po; 
        }
    }

}
