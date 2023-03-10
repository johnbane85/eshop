package vttp2022.paf.assessment.eshop.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

// DO NOT CHANGE THIS CLASS
public class LineItem {

	private String item;
	private Integer quantity;

	public LineItem(String item, Integer quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public JsonObject toJson() {
		return Json.createObjectBuilder()
				.add("item", item)
				.add("quantity", quantity)
				.build();
	}

}
