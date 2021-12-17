package edu.westga.cs1301.grocery.test.retailer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Retailer;
import edu.westga.cs1301.grocery.model.ShoppingCart;

public class TestGetSizeByStatus {

	@Test
	public void testEmptyRetailer(){
		
		Retailer retailer = new Retailer();
		assertEquals(0,retailer.getSizeByStatus(ShoppingCart.RECEIVED));
	}
	
	@Test
	public void testSinlgeCart() {
		Retailer retailer = new Retailer();
		ShoppingCart cart = new ShoppingCart();
		cart.setStatus(ShoppingCart.DELIVERED);
		retailer.addCart(cart);
		
		assertEquals(0,retailer.getSizeByStatus(ShoppingCart.RECEIVED));
		assertEquals(0,retailer.getSizeByStatus(ShoppingCart.PROCESSING));
		assertEquals(0,retailer.getSizeByStatus(ShoppingCart.SHIPPED));
		assertEquals(1,retailer.getSizeByStatus(ShoppingCart.DELIVERED));
	}
	
	@Test
	public void testMultpleCarts() {
		Retailer retailer = new Retailer();
		ShoppingCart cart1 = new ShoppingCart();
		ShoppingCart cart2 = new ShoppingCart();
		ShoppingCart cart3 = new ShoppingCart();
		ShoppingCart cart4 = new ShoppingCart();
		ShoppingCart cart5 = new ShoppingCart();
		ShoppingCart cart6 = new ShoppingCart();
		ShoppingCart cart7 = new ShoppingCart();
		
		cart1.setStatus(ShoppingCart.PROCESSING);
		cart2.setStatus(ShoppingCart.SHIPPED);
		cart3.setStatus(ShoppingCart.DELIVERED);
		cart4.setStatus(ShoppingCart.SHIPPED);
		cart5.setStatus(ShoppingCart.PROCESSING);
		
		retailer.addCart(cart1);
		retailer.addCart(cart2);
		retailer.addCart(cart3);
		retailer.addCart(cart4);
		retailer.addCart(cart5);
		retailer.addCart(cart6);
		retailer.addCart(cart7);
		
		assertEquals(2,retailer.getSizeByStatus(ShoppingCart.RECEIVED));
		assertEquals(2,retailer.getSizeByStatus(ShoppingCart.PROCESSING));
		assertEquals(2,retailer.getSizeByStatus(ShoppingCart.SHIPPED));
		assertEquals(1,retailer.getSizeByStatus(ShoppingCart.DELIVERED));
	}
}
