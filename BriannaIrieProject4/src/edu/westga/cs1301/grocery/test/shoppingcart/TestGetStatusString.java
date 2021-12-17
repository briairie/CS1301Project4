package edu.westga.cs1301.grocery.test.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.ShoppingCart;

public class TestGetStatusString {

	@Test
	public void testLowStatus() {
			
		assertThrows(IllegalArgumentException.class, () -> {
			ShoppingCart.getStatusString(ShoppingCart.RECEIVED-1);
		});
	}
	
	@Test
	public void testHighStatus() {
			
		assertThrows(IllegalArgumentException.class, () -> {
			ShoppingCart.getStatusString(ShoppingCart.DELIVERED+1);
		});
	}
	
	@Test
	public void testReceived() {
		assertEquals("RECEIVED",ShoppingCart.getStatusString(ShoppingCart.RECEIVED));
	}
	
	@Test
	public void testProcessing() {
		assertEquals("PROCESSING",ShoppingCart.getStatusString(ShoppingCart.PROCESSING));
	}
	
	@Test
	public void testShipped() {
		assertEquals("SHIPPED",ShoppingCart.getStatusString(ShoppingCart.SHIPPED));
	}
	
	@Test
	public void testDelivered() {
		assertEquals("DELIVERED",ShoppingCart.getStatusString(ShoppingCart.DELIVERED));
	}
}
