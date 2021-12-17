package edu.westga.cs1301.grocery.test.retailer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Retailer;
import edu.westga.cs1301.grocery.model.ShoppingCart;
import edu.westga.cs1301.grocery.model.Item;

public class TestGetTotalCostByStatus {

	public static final double MARGIN_OF_ERROR = 0.01;
	
	@Test
	public void testLowStatus() {
		
		Retailer retailer = new Retailer();
		
		assertThrows(IllegalArgumentException.class, () -> {
			retailer.getTotalCostByStatus(ShoppingCart.RECEIVED-1);
		});
	}
	
	@Test
	public void testHighStatus() {
		
		Retailer retailer = new Retailer();
		
		assertThrows(IllegalArgumentException.class, () -> {
			retailer.getTotalCostByStatus(ShoppingCart.DELIVERED+1);
		});
	}
	
	@Test
	public void testEmptyOrders() {
		
		Retailer retailer = new Retailer();
		
		double expected = 0.0;
		double actual = retailer.getTotalCostByStatus(ShoppingCart.RECEIVED);
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.PROCESSING);
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.SHIPPED);
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.DELIVERED);
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		
	}
	
	@Test
	public void testOneCart() {
		
		Retailer retailer = new Retailer();
		ShoppingCart cart = new ShoppingCart();
		Item item = new Item("test item",12345,15.0,2);
		cart.addItem(item);
		
		retailer.addCart(cart);
		
		double expected;
		double actual;
		
		actual = retailer.getTotalCostByStatus(ShoppingCart.RECEIVED);
		expected = 30.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.PROCESSING);
		expected = 0.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.SHIPPED);
		expected = 0.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.DELIVERED);
		expected = 0.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		
		cart.setStatus(ShoppingCart.PROCESSING);
		actual = retailer.getTotalCostByStatus(ShoppingCart.PROCESSING);
		expected = 30.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.RECEIVED);
		expected = 0.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.SHIPPED);
		expected = 0.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.DELIVERED);
		expected = 0.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		
		cart.setStatus(ShoppingCart.SHIPPED);
		actual = retailer.getTotalCostByStatus(ShoppingCart.SHIPPED);
		expected = 30.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.RECEIVED);
		expected = 0.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.PROCESSING);
		expected = 0.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.DELIVERED);
		expected = 0.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		
		cart.setStatus(ShoppingCart.DELIVERED);
		actual = retailer.getTotalCostByStatus(ShoppingCart.DELIVERED);
		expected = 30.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.RECEIVED);
		expected = 0.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.SHIPPED);
		expected = 0.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		actual = retailer.getTotalCostByStatus(ShoppingCart.PROCESSING);
		expected = 0.0;
		assertEquals(expected,actual,MARGIN_OF_ERROR);
			
	}
	
	@Test
	public void testMultipleCarts() {
		
		Retailer retailer = new Retailer();
		
		ShoppingCart cart1 = new ShoppingCart();
		ShoppingCart cart2 = new ShoppingCart();
		ShoppingCart cart3 = new ShoppingCart();
		ShoppingCart cart4 = new ShoppingCart();
		ShoppingCart cart5 = new ShoppingCart();
		ShoppingCart cart6 = new ShoppingCart();
		
		Item item1 = new Item("test item",12345,15.0,2);
		Item item2 = new Item("test item",12346,16.0,2);
		Item item3 = new Item("test item",12347,17.0,2);
		Item item4 = new Item("test item",12348,18.0,2);
		Item item5 = new Item("test item",12349,19.0,2);
		Item item6 = new Item("test item",12350,20.0,2);
		
		cart1.addItem(item1);
		cart2.addItem(item2);
		cart3.addItem(item3);
		cart4.addItem(item4);
		cart5.addItem(item5);
		cart6.addItem(item6);
		
		
		cart1.setStatus(ShoppingCart.PROCESSING);
		cart2.setStatus(ShoppingCart.DELIVERED);
		cart3.setStatus(ShoppingCart.SHIPPED);
		cart4.setStatus(ShoppingCart.PROCESSING);
		cart5.setStatus(ShoppingCart.DELIVERED);
		cart6.setStatus(ShoppingCart.SHIPPED);
		
		retailer.addCart(cart1);
		retailer.addCart(cart2);
		retailer.addCart(cart3);
		retailer.addCart(cart4);
		retailer.addCart(cart5);
		retailer.addCart(cart6);
		
		double actual;
		double expected;
		
		expected = 2*(15.0 + 18.0);
		actual = retailer.getTotalCostByStatus(ShoppingCart.PROCESSING);
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		
		expected = 2*(16.0 + 19.0);
		actual = retailer.getTotalCostByStatus(ShoppingCart.DELIVERED);
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		
		expected = 2*(17.0 + 20.0);
		actual = retailer.getTotalCostByStatus(ShoppingCart.SHIPPED);
		assertEquals(expected,actual,MARGIN_OF_ERROR);
		
		
	}
	
}
