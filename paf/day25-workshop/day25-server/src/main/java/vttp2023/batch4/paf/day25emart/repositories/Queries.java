package vttp2023.batch4.paf.day25emart.repositories;

public class Queries {

    public static final String SQL_INSERT_PURCHASE_ORDER = """
        insert into purchase_order(po_id, name, address)
        values (?, ?, ?)
        """;

    public static final String SQL_INSERT_LINE_ITEM = """
        insert into line_item(item, quantity, po_id)
        values (?, ?, ?)
        """;

    public static final String SQL_SELECT_BY_PO_ID = """
        select po_id from purchase_order
        where po_id like ?
        """;

}
