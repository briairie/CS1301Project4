package edu.westga.cs1301.grocery.test.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Item;

public class TestSetUnitPrice {

	public static final double MARGIN_OF_ERROR = 0.01;
	@Test
	public void testSetUnitPriceAtZero() {
		
		Item item = new Item("test item",12345,123.0,1);
		
		assertThrows(IllegalArgumentException.class, () -> {
			item.setUnitPrice(0.0);
		});
	}
	
	@Test
	public void testSetUnitPriceOnePenny() {
		
		Item item = new Item("item", Item.MINIMUM_ID_NUMBER, 1.0, 1);
		
		double expected = 0.01;
		item.setUnitPrice(expected);
		double actual = item.getUnitPrice();
		assertEquals(expected,actual,MARGIN_OF_ERROR);
	}
}
