package edu.westga.cs1301.grocery.test.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Item;

public class TestConstructor {

	public static final double MARGIN_OF_ERROR = 0.01;
	@Test
	public void testNullDescription() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Item(null, Item.MINIMUM_ID_NUMBER, 1.0, 1);
		});
	}
	
	@Test
	public void testEmptyDescription() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Item("", Item.MINIMUM_ID_NUMBER, 1.0, 1);
		});
	}
	
	@Test
	public void testIdNumberBelowMin() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Item("item", Item.MINIMUM_ID_NUMBER-1, 1.0, 1);
		});
	}
	
	@Test
	public void testIdNumberAtMin() {
		
		Item item = new Item("item", Item.MINIMUM_ID_NUMBER, 1.0, 1);
		assertEquals(Item.MINIMUM_ID_NUMBER,item.getIdNumber());
	}
	
	@Test
	public void testUnitPriceAtZero() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Item("item", Item.MINIMUM_ID_NUMBER, 0.0, 1);
		});
	}
	
	@Test
	public void testUnitPriceOnePenny() {
		
		Item item = new Item("item", Item.MINIMUM_ID_NUMBER, 0.01, 1);
		assertEquals(0.01,item.getUnitPrice(),MARGIN_OF_ERROR);
	}
	
	@Test
	public void testQuantityAtZero() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Item("item", Item.MINIMUM_ID_NUMBER, 1.0, 0);
		});
	}
	
	@Test
	public void testQuantityOne() {
		
		Item item = new Item("item", Item.MINIMUM_ID_NUMBER, 1.0, 1);
		assertEquals(1,item.getQuantity());
		assertEquals("item",item.getDescription());
		assertEquals(Item.MINIMUM_ID_NUMBER,item.getIdNumber());
		assertEquals(1.0,item.getUnitPrice(),MARGIN_OF_ERROR);
	}
}
