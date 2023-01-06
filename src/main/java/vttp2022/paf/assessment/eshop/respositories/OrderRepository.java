package vttp2022.paf.assessment.eshop.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.paf.assessment.eshop.models.Order;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

import java.util.Date;
import java.util.UUID;

@Repository
public class OrderRepository {
	// TODO: Task 3

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private LineItemRepository liRepo;

	public boolean insertOrder(Order order) {
		return template.update(SQL_INSERT_ORDER, order.getOrderId(), order.getName(), order.getDeliveryId(),
				order.getOrderDate()) > 0;
	}

	@Transactional(rollbackFor = OrderException.class)
	public void saveOrder(Order order) throws OrderException {
		String orderId = UUID.randomUUID().toString().substring(0, 8);

		Date date = new java.util.Date();

		order.setOrderId(orderId);
		order.setOrderDate(date);

		System.out.printf(">>>> orderId: %s\n", orderId);
		System.out.printf(">>>> Name: %s\n", order.getName());
		System.out.printf(">>>> OrderDate: %s\n", date);
		System.out.printf(">>>> LineItems: %s\n", order.getLineItems());

		boolean inserted = insertOrder(order);

		if (!inserted) {
			throw new OrderException("\"error\": Exception for orderId %s".formatted(orderId));
		}

		liRepo.addLineItems(order.getLineItems(), orderId);

	}

}
