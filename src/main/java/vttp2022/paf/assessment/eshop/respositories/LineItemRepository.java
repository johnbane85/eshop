package vttp2022.paf.assessment.eshop.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

import java.util.List;

@Repository
public class LineItemRepository {

  @Autowired
  private JdbcTemplate template;

  public void addLineItems(Order Order) {
    addLineItems(Order.getLineItems(), Order.getOrderId());
  }

  public void addLineItems(List<LineItem> lineItems, String orderId) {

    List<Object[]> data = lineItems.stream().map(li -> {
      Object[] l = new Object[3];
      l[0] = li.getItem();
      l[1] = li.getQuantity();
      l[2] = orderId;
      return l;
    }).toList();

    template.batchUpdate(SQL_INSERT_LINE_ITEM, data);

  }
}
