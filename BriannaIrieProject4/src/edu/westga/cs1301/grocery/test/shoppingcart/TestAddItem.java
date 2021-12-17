package edu.westga.cs1301.grocery.test.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Item;
import edu.westga.cs1301.grocery.model.ShoppingCart;

public class TestAddItem {

	@Test
	public void testAddNullItem() {
		
		ShoppingCart cart = new ShoppingCart();
		
		assertThrows(IllegalArgumentException.class, () -> {
			cart.addItem(null);
		});
	}
	
	@Test
	public void testAddSingleItem() {
		ShoppingCart cart = new ShoppingCart();
		
		Item item = new Item("test item",12345,1.0,4);
		cart.addItem(item);
		assertEquals(1,cart.size());
	}
	
	@Test
	public void testAddThreeDistinctItems() {
		ShoppingCart cart = new ShoppingCart();
		Item item1 = new Item("test item1",12345,1.0,1);
		Item item2 = new Item("test item2",12346,1.0,1);
		Item item3 = new Item("test item3",12347,1.0,1);
		
		cart.addItem(item1);
		cart.addItem(item2);
		cart.addItem(item3);
		
		assertEquals(3,cart.size());
		
	}
	
	@Test
	public void testAddThreeDistinctItemsAndMatchFirstId() {
		ShoppingCart cart = new ShoppingCart();
		Item item1 = new Item("test item1",12345,1.0,1);
		Item item2 = new Item("test item2",12346,1.0,1);
		Item item3 = new Item("test item3",12347,1.0,1);
		Item item4 = new Item("test item1",12345,1.0,4);
		
		cart.addItem(item1);
		cart.addItem(item2);
		cart.addItem(item3);
		cart.addItem(item4);
		
		assertEquals(3,cart.size());
		
	}
	
	@Test
	public void testAddThreeDistinctItemsAndMatchMiddleId() {
		ShoppingCart cart = new ShoppingCart();
		Item item1 = new Item("test item1",12345,1.0,1);
		Item item2 = new Item("test item2",12346,1.0,1);
		Item item3 = new Item("test item3",12347,1.0,1);
		Item item4 = new Item("test item1",12345,1.0,4);
		
		cart.addItem(item2);
		cart.addItem(item1);
		cart.addItem(item3);
		cart.addItem(item4);
		
		assertEquals(3,cart.size());
		
	}
	
	@Test
	public void testAddThreeDistinctItemsAndMatchLastId() {
		ShoppingCart cart = new ShoppingCart();
		Item item1 = new Item("test item1",12345,1.0,1);
		Item item2 = new Item("test item2",12346,1.0,1);
		Item item3 = new Item("test item3",12347,1.0,1);
		Item item4 = new Item("test item1",12345,1.0,4);
		
		cart.addItem(item3);
		cart.addItem(item2);
		cart.addItem(item1);
		cart.addItem(item4);
		
		assertEquals(3,cart.size());
		
	}
	@Test
	public void testAddFourItemsThatMatchId() {
		ShoppingCart cart = new ShoppingCart();
		Item item1 = new Item("test item1",12345,1.0,1);
		Item item2 = new Item("test item2",12345,1.0,2);
		Item item3 = new Item("test item3",12345,1.0,3);
		Item item4 = new Item("test item1",12345,1.0,4);
		
		cart.addItem(item3);
		cart.addItem(item2);
		cart.addItem(item1);
		cart.addItem(item4);
		
		assertEquals(1,cart.size());
		assertEquals(10,cart.totalQuantity());
		
	}
}
