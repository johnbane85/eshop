package vttp2022.paf.assessment.eshop.respositories;

public class Queries {
  public static final String SQL_SELECT_CUSTOMER_BY_NAME = "select name,address,email from customers where name = ?";

  public static String SQL_INSERT_ORDER = "insert into estore(order_id, name, deliveryId, orderDate) values (?, ?, ?, ?)";

  public static String SQL_INSERT_LINE_ITEM = "insert into lineitems(item, quantity, order_id) values (?, ?, ?)";
}
