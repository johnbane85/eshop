package vttp2022.paf.assessment.eshop.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.servlet.http.HttpSession;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.OrderException;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

@RestController
@RequestMapping(path = "/api/eshop", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

	// TODO: Task 3
	@Autowired
	private OrderRepository ordRepo;

	@PostMapping
	public ResponseEntity<String> postOrder(@RequestBody MultiValueMap<String, String> form,
			Model model, HttpSession sess) {

		List<LineItem> lineItems = (List<LineItem>) sess.getAttribute("cart");

		if (null == lineItems) {
			lineItems = new LinkedList<>();
			sess.setAttribute("cart", lineItems);
		}

		System.out.println(">>>>>>>cart:" + lineItems.toString());

		String name = form.getFirst("name");
		String item = form.getFirst("item");
		Integer quantity = Integer.parseInt(form.getFirst("quantity"));
		lineItems.add(new LineItem(item, quantity));

		Order order = new Order();
		order.setName(name);
		order.setLineItems(lineItems);

		try {
			ordRepo.saveOrder(order);
		} catch (OrderException e) {
			e.printStackTrace();
		}

		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		for (LineItem l : lineItems)
			arrBuilder.add(l.toJson());

		return ResponseEntity.ok(arrBuilder.build().toString());
	}

}
