package edu.westga.cs1301.grocery.test.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Item;
import edu.westga.cs1301.grocery.model.ShoppingCart;

public class TestTotalQuantity {

	@Test
	public void testEmptyCart() {
		ShoppingCart cart = new ShoppingCart();
		assertEquals(0,cart.totalQuantity());
	}
	
	@Test
	public void testSingleItem() {
		ShoppingCart cart = new ShoppingCart();
		
		Item item = new Item("test item",12345,1.0,4);
		cart.addItem(item);
		assertEquals(4,cart.totalQuantity());
	}
	
	@Test
	public void testMultipleNoMatch() {
		ShoppingCart cart = new ShoppingCart();
		Item item1 = new Item("test item1",12345,1.0,5);
		Item item2 = new Item("test item2",12346,1.0,12);
		Item item3 = new Item("test item3",12347,1.0,19);
		
		cart.addItem(item1);
		cart.addItem(item2);
		cart.addItem(item3);
		
		assertEquals(36,cart.totalQuantity());
		
	}
}
