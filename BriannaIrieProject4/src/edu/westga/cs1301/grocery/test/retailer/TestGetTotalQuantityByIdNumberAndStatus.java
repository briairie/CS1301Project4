package edu.westga.cs1301.grocery.test.retailer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Retailer;
import edu.westga.cs1301.grocery.model.ShoppingCart;
import edu.westga.cs1301.grocery.model.Item;


public class TestGetTotalQuantityByIdNumberAndStatus {

	public static final double MARGIN_OF_ERROR = 0.0;
	
	@Test
	public void testLowId() {
		
		Retailer retailer = new Retailer();
		
		assertThrows(IllegalArgumentException.class, () -> {
			retailer.getTotalQuantityByIdNumberAndStatus(Item.MINIMUM_ID_NUMBER-1,ShoppingCart.RECEIVED);
		});

	}
	
	@Test
	public void testLowStatus() {
		
		Retailer retailer = new Retailer();
		
		assertThrows(IllegalArgumentException.class, () -> {
			retailer.getTotalQuantityByIdNumberAndStatus(1,ShoppingCart.RECEIVED-1);
		});
	}
	
	@Test
	public void testHighStatus() {
		
		Retailer retailer = new Retailer();
		
		assertThrows(IllegalArgumentException.class, () -> {
			retailer.getTotalQuantityByIdNumberAndStatus(Item.MINIMUM_ID_NUMBER,ShoppingCart.DELIVERED+1);
		});
	}
	
	@Test
	public void testEmptyOrders() {
		
		Retailer retailer = new Retailer();
		
		int expected = 0;
		int actual = retailer.getTotalQuantityByIdNumberAndStatus(Item.MINIMUM_ID_NUMBER,ShoppingCart.RECEIVED);
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(Item.MINIMUM_ID_NUMBER,ShoppingCart.PROCESSING);
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(Item.MINIMUM_ID_NUMBER,ShoppingCart.SHIPPED);
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(Item.MINIMUM_ID_NUMBER,ShoppingCart.DELIVERED);
		assertEquals(expected,actual);
		
	}
	
	@Test
	public void testOneCartOneItem() {
		
		Retailer retailer = new Retailer();
		ShoppingCart cart = new ShoppingCart();
		
		int idNumber = 12345;
		Item item = new Item("test item",idNumber,15.0,2);
		cart.addItem(item);
		
		retailer.addCart(cart);
		
		int expected;
		int actual;
		
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.RECEIVED);
		expected = 2;
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.PROCESSING);
		expected = 0;
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.SHIPPED);
		expected = 0;
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.DELIVERED);
		expected = 0;
		assertEquals(expected,actual);
		
		cart.setStatus(ShoppingCart.PROCESSING);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.PROCESSING);
		expected = 2;
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.RECEIVED);
		expected = 0;
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.SHIPPED);
		expected = 0;
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.DELIVERED);
		expected = 0;
		assertEquals(expected,actual);
		
		cart.setStatus(ShoppingCart.SHIPPED);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.SHIPPED);
		expected = 2;
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.RECEIVED);
		expected = 0;
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.PROCESSING);
		expected = 0;
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.DELIVERED);
		expected = 0;
		assertEquals(expected,actual);
		
		cart.setStatus(ShoppingCart.DELIVERED);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.DELIVERED);
		expected = 2;
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.RECEIVED);
		expected = 0;
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.SHIPPED);
		expected = 0;
		assertEquals(expected,actual);
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.PROCESSING);
		expected = 0;
		assertEquals(expected,actual);
			
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
		
		int idNumber = 15000;
		Item item7 = new Item("test item",idNumber,1.0,2);
		Item item8 = new Item("test item",idNumber,1.0,4);
		Item item9 = new Item("test item",idNumber,1.0,6);
		Item item10 = new Item("test item",idNumber,1.0,8);
		Item item11 = new Item("test item",idNumber,1.0,10);
		Item item12 = new Item("test item",idNumber,1.0,12);
		
		cart1.addItem(item1);
		cart2.addItem(item2);
		cart3.addItem(item3);
		cart4.addItem(item4);
		cart5.addItem(item5);
		cart6.addItem(item6);
		
		cart1.addItem(item7);
		cart2.addItem(item8);
		cart3.addItem(item9);
		cart4.addItem(item10);
		cart5.addItem(item11);
		cart6.addItem(item12);
		
		
		cart1.setStatus(ShoppingCart.PROCESSING);
		cart2.setStatus(ShoppingCart.SHIPPED);
		cart3.setStatus(ShoppingCart.DELIVERED);
		cart4.setStatus(ShoppingCart.PROCESSING);
		cart5.setStatus(ShoppingCart.SHIPPED);
		cart6.setStatus(ShoppingCart.DELIVERED);
		
		
		retailer.addCart(cart1);
		retailer.addCart(cart2);
		retailer.addCart(cart3);
		retailer.addCart(cart4);
		retailer.addCart(cart5);
		retailer.addCart(cart6);
		
		
		int actual;
		int expected;
		
		expected = 10;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.PROCESSING);
		assertEquals(expected,actual);
		
		expected = 14;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.SHIPPED);
		assertEquals(expected,actual);
		
		expected = 18;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber,ShoppingCart.DELIVERED);
		assertEquals(expected,actual);
		
		
	}
	
}
