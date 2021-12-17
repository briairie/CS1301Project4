package edu.westga.cs1301.grocery.test.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.ShoppingCart;

public class TestSetIdNumber {

	@Test
	public void testIdZero() {
		ShoppingCart cart = new ShoppingCart();
		
		assertThrows(IllegalArgumentException.class, () -> {
			cart.setIdNumber(0);
		});
	}
	
	@Test
	public void testIdOne() {
		ShoppingCart cart = new ShoppingCart();
		cart.setIdNumber(1);
		assertEquals(1,cart.getIdNumber());
	}
}
