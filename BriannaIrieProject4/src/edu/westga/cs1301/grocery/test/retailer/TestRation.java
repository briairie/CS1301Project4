package edu.westga.cs1301.grocery.test.retailer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Retailer;
import edu.westga.cs1301.grocery.model.ShoppingCart;
import edu.westga.cs1301.grocery.model.Item;

public class TestRation {

	@Test
	public void testLowId() {
		
		Retailer retailer = new Retailer();
		
		assertThrows(IllegalArgumentException.class, () -> {
			retailer.ration(Item.MINIMUM_ID_NUMBER-1,1);
		});

	}
	
	@Test
	public void testZeroQuantity() {
		
		Retailer retailer = new Retailer();
		
		assertThrows(IllegalArgumentException.class, () -> {
			retailer.ration(Item.MINIMUM_ID_NUMBER,0);
		});

	}
	
	@Test
	public void testOneItemUnderReceived() {
		
		Retailer retailer = new Retailer();
		ShoppingCart cart = new ShoppingCart();
		
		int maxQuantity = 5;
		int idNumber1 = 12345;
		Item item1 = new Item("test item",idNumber1,1.0,10);
		int idNumber2 = 15000;
		Item item2 = new Item("ration item",idNumber2,15.0,2);
		cart.addItem(item1);
		cart.addItem(item2);
		
		retailer.addCart(cart);
		retailer.ration(idNumber2, maxQuantity);
		
		int expected = 2;
		int actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber2, ShoppingCart.RECEIVED);
		assertEquals(expected, actual);
		
		expected = 10;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber1, ShoppingCart.RECEIVED);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testOneItemOverReceived() {
		
		Retailer retailer = new Retailer();
		ShoppingCart cart = new ShoppingCart();
		
		int maxQuantity = 5;
		int idNumber1 = 12345;
		Item item1 = new Item("test item",idNumber1,1.0,10);
		int idNumber2 = 15000;
		Item item2 = new Item("ration item",idNumber2,15.0,10);
		cart.addItem(item1);
		cart.addItem(item2);
		
		retailer.addCart(cart);
		retailer.ration(idNumber2, maxQuantity);
		
		int expected = maxQuantity;
		int actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber2, ShoppingCart.RECEIVED);
		assertEquals(expected, actual);
		
		expected = 10;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber1, ShoppingCart.RECEIVED);
		assertEquals(expected, actual);
		
	}
		
	@Test
	public void testOneItemUnderProcessing() {
		
		Retailer retailer = new Retailer();
		ShoppingCart cart = new ShoppingCart();
		
		int maxQuantity = 5;
		int idNumber1 = 12345;
		Item item1 = new Item("test item",idNumber1,1.0,10);
		int idNumber2 = 15000;
		Item item2 = new Item("ration item",idNumber2,15.0,2);
		cart.addItem(item1);
		cart.addItem(item2);
		
		cart.setStatus(ShoppingCart.PROCESSING);
		retailer.addCart(cart);
		retailer.ration(idNumber2, maxQuantity);
		
		int expected = 2;
		int actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber2, ShoppingCart.PROCESSING);
		assertEquals(expected, actual);
		
		expected = 10;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber1, ShoppingCart.PROCESSING);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testOneItemOverProcessing() {
		
		Retailer retailer = new Retailer();
		ShoppingCart cart = new ShoppingCart();
		
		int maxQuantity = 5;
		int idNumber1 = 12345;
		Item item1 = new Item("test item",idNumber1,1.0,10);
		int idNumber2 = 15000;
		Item item2 = new Item("ration item",idNumber2,15.0,15);
		cart.addItem(item1);
		cart.addItem(item2);
		
		cart.setStatus(ShoppingCart.PROCESSING);
		retailer.addCart(cart);
		retailer.ration(idNumber2, maxQuantity);
		
		int expected = maxQuantity;
		int actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber2, ShoppingCart.PROCESSING);
		assertEquals(expected, actual);
		
		expected = 10;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber1, ShoppingCart.PROCESSING);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testOneItemUnderShipped() {
		
		Retailer retailer = new Retailer();
		ShoppingCart cart = new ShoppingCart();
		
		int maxQuantity = 5;
		int idNumber1 = 12345;
		Item item1 = new Item("test item",idNumber1,1.0,10);
		int idNumber2 = 15000;
		Item item2 = new Item("ration item",idNumber2,15.0,2);
		cart.addItem(item1);
		cart.addItem(item2);
		
		cart.setStatus(ShoppingCart.SHIPPED);
		retailer.addCart(cart);
		retailer.ration(idNumber2, maxQuantity);
		
		int expected = 2;
		int actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber2, ShoppingCart.SHIPPED);
		assertEquals(expected, actual);
		
		expected = 10;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber1, ShoppingCart.SHIPPED);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testOneItemOverShipped() {
		
		Retailer retailer = new Retailer();
		ShoppingCart cart = new ShoppingCart();
		
		int maxQuantity = 5;
		int idNumber1 = 12345;
		Item item1 = new Item("test item",idNumber1,1.0,10);
		int idNumber2 = 15000;
		Item item2 = new Item("ration item",idNumber2,15.0,15);
		cart.addItem(item1);
		cart.addItem(item2);
		
		cart.setStatus(ShoppingCart.SHIPPED);
		retailer.addCart(cart);
		retailer.ration(idNumber2, maxQuantity);
		
		int expected = 15;
		int actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber2, ShoppingCart.SHIPPED);
		assertEquals(expected, actual);
		
		expected = 10;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber1, ShoppingCart.SHIPPED);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testOneItemUnderDelivered() {
		
		Retailer retailer = new Retailer();
		ShoppingCart cart = new ShoppingCart();
		
		int maxQuantity = 5;
		int idNumber1 = 12345;
		Item item1 = new Item("test item",idNumber1,1.0,10);
		int idNumber2 = 15000;
		Item item2 = new Item("ration item",idNumber2,15.0,2);
		cart.addItem(item1);
		cart.addItem(item2);
		
		cart.setStatus(ShoppingCart.DELIVERED);
		retailer.addCart(cart);
		retailer.ration(idNumber2, maxQuantity);
		
		int expected = 2;
		int actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber2, ShoppingCart.DELIVERED);
		assertEquals(expected, actual);
		
		expected = 10;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber1, ShoppingCart.DELIVERED);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testOneItemOverDelivered() {
		
		Retailer retailer = new Retailer();
		ShoppingCart cart = new ShoppingCart();
		
		int maxQuantity = 5;
		int idNumber1 = 12345;
		Item item1 = new Item("test item",idNumber1,1.0,10);
		int idNumber2 = 15000;
		Item item2 = new Item("ration item",idNumber2,15.0,15);
		cart.addItem(item1);
		cart.addItem(item2);
		
		cart.setStatus(ShoppingCart.DELIVERED);
		retailer.addCart(cart);
		retailer.ration(idNumber2, maxQuantity);
		
		int expected = 15;
		int actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber2, ShoppingCart.DELIVERED);
		assertEquals(expected, actual);
		
		expected = 10;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber1, ShoppingCart.DELIVERED);
		assertEquals(expected, actual);
		
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
		
		int maxQuantity = 1;
		int idNumber2 = 15000;
		Item item7 = new Item("test item",idNumber2,1.0,2);
		Item item8 = new Item("test item",idNumber2,1.0,4);
		Item item9 = new Item("test item",idNumber2,1.0,6);
		Item item10 = new Item("test item",idNumber2,1.0,8);
		Item item11 = new Item("test item",idNumber2,1.0,10);
		Item item12 = new Item("test item",idNumber2,1.0,12);
		
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
		
		retailer.ration(idNumber2, maxQuantity);
		int actual;
		int expected;
		
		expected = 0;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber2,ShoppingCart.RECEIVED);
		assertEquals(expected,actual);
		
		expected = 2;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber2,ShoppingCart.PROCESSING);
		assertEquals(expected,actual);
		
		expected = 14;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber2,ShoppingCart.SHIPPED);
		assertEquals(expected,actual);
		
		expected = 18;
		actual = retailer.getTotalQuantityByIdNumberAndStatus(idNumber2,ShoppingCart.DELIVERED);
		assertEquals(expected,actual);
		
		
	}
	
}
