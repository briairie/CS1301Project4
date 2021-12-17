package edu.westga.cs1301.grocery.test.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Item;
import edu.westga.cs1301.grocery.model.ShoppingCart;

public class TestRemoveItemWithId {

	@Test
	public void testEmptyCart() {
		ShoppingCart cart = new ShoppingCart();
		
		assertFalse(cart.removeItemWithId(12345));
	}
	
	@Test
	public void testSingletonMatch() {
		ShoppingCart cart = new ShoppingCart();
		
		Item item = new Item("test item",12345,1.0,4);
		cart.addItem(item);
		assertTrue(cart.removeItemWithId(12345));
		assertEquals(0,cart.size());
	}
	
	@Test
	public void testSingletonNoMatch() {
		ShoppingCart cart = new ShoppingCart();
		
		Item item = new Item("test item",12345,1.0,4);
		cart.addItem(item);
		assertFalse(cart.removeItemWithId(12346));
		assertEquals(1,cart.size());
	}
	
	@Test
	public void testMultipleNoMatch() {
		ShoppingCart cart = new ShoppingCart();
		Item item1 = new Item("test item1",12345,1.0,1);
		Item item2 = new Item("test item2",12346,1.0,1);
		Item item3 = new Item("test item3",12347,1.0,1);
		
		cart.addItem(item1);
		cart.addItem(item2);
		cart.addItem(item3);
		
		assertFalse(cart.hasItemWithId(12340));
		assertEquals(3,cart.size());
		
	}
	
	@Test
	public void testMultipleMatchFirst() {
		ShoppingCart cart = new ShoppingCart();
		Item item1 = new Item("test item1",12345,1.0,1);
		Item item2 = new Item("test item2",12346,1.0,1);
		Item item3 = new Item("test item3",12347,1.0,1);
		
		cart.addItem(item1);
		cart.addItem(item2);
		cart.addItem(item3);
		
		assertTrue(cart.removeItemWithId(12345));
		assertEquals(2,cart.size());
		
	}
	
	@Test
	public void testMultipleMatchMiddle() {
		ShoppingCart cart = new ShoppingCart();
		Item item1 = new Item("test item1",12345,1.0,1);
		Item item2 = new Item("test item2",12346,1.0,1);
		Item item3 = new Item("test item3",12347,1.0,1);
		
		cart.addItem(item1);
		cart.addItem(item2);
		cart.addItem(item3);
		
		assertTrue(cart.removeItemWithId(12346));
		assertEquals(2,cart.size());
		
	}
	
	@Test
	public void testMultipleMatchLast() {
		ShoppingCart cart = new ShoppingCart();
		Item item1 = new Item("test item1",12345,1.0,1);
		Item item2 = new Item("test item2",12346,1.0,1);
		Item item3 = new Item("test item3",12347,1.0,1);
		
		cart.addItem(item1);
		cart.addItem(item2);
		cart.addItem(item3);
		
		assertTrue(cart.removeItemWithId(12347));
		assertEquals(2,cart.size());
		
	}
}
