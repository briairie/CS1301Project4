package edu.westga.cs1301.grocery.test.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Item;

public class TestSetQuantity {

	@Test
	public void testSetQuantityAtZero() {
		
		Item item = new Item("test item", 12345, 1.23, 10);
		
		assertThrows(IllegalArgumentException.class, () -> {
			item.setQuantity(0);
		});
	}
	
	@Test
	public void testSetQuantityOne() {
		
		Item item = new Item("item", Item.MINIMUM_ID_NUMBER, 123.0, 10);
		int expected = 1;
		item.setQuantity(expected);
		int actual = item.getQuantity();
		assertEquals(expected, actual);
		
	}
}
