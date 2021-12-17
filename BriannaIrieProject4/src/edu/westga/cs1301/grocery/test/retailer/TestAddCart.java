package edu.westga.cs1301.grocery.test.retailer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Retailer;
import edu.westga.cs1301.grocery.model.ShoppingCart;

public class TestAddCart {

	@Test
	public void testAddNullCart() {
		
		Retailer retailer = new Retailer();
		
		assertThrows(IllegalArgumentException.class, () -> {
			retailer.addCart(null);
		});
	}
	
	@Test
	public void testAddMultipleCarts() {
		
		Retailer retailer = new Retailer();
		
		ShoppingCart cart1 = new ShoppingCart();
		ShoppingCart cart2 = new ShoppingCart();
		ShoppingCart cart3 = new ShoppingCart();
		
		retailer.addCart(cart1);
		retailer.addCart(cart2);
		retailer.addCart(cart3);
		assertEquals(3,retailer.size());
		
	}
}
