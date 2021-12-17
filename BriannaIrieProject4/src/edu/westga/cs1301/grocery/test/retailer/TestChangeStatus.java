package edu.westga.cs1301.grocery.test.retailer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Retailer;
import edu.westga.cs1301.grocery.model.ShoppingCart;

public class TestChangeStatus {

	@Test
	public void testZeroId() {
		
		Retailer retailer = new Retailer();
		assertThrows(IllegalArgumentException.class, () -> {
			retailer.changeStatus(0,ShoppingCart.RECEIVED);
		});
	}
	
	@Test
	public void testLowStatus() {
		Retailer retailer = new Retailer();
		assertThrows(IllegalArgumentException.class, () -> {
			retailer.changeStatus(1,ShoppingCart.RECEIVED-1);
		});
	}
	
	@Test
	public void testHighStatus() {
		Retailer retailer = new Retailer();
		assertThrows(IllegalArgumentException.class, () -> {
			retailer.changeStatus(1,ShoppingCart.DELIVERED+1);
		});
	}
	
	@Test
	public void testSingleCartNoMatch() {
		Retailer retailer = new Retailer();
		ShoppingCart cart = new ShoppingCart(3);
		retailer.addCart(cart);
		retailer.changeStatus(1,ShoppingCart.DELIVERED);
		assertEquals(1,retailer.getSizeByStatus(ShoppingCart.RECEIVED));
		assertEquals(0,retailer.getSizeByStatus(ShoppingCart.DELIVERED));
		
	}
	
	@Test
	public void testSingleCartMatch() {
		Retailer retailer = new Retailer();
		ShoppingCart cart = new ShoppingCart(3);
		retailer.addCart(cart);
		retailer.changeStatus(3,ShoppingCart.DELIVERED);
		assertEquals(0,retailer.getSizeByStatus(ShoppingCart.RECEIVED));
		assertEquals(1,retailer.getSizeByStatus(ShoppingCart.DELIVERED));
		
	}
	
	@Test
	public void testSingleMultipleCartsMatchFirst() {
		Retailer retailer = new Retailer();
		ShoppingCart cart1 = new ShoppingCart(1);
		ShoppingCart cart2 = new ShoppingCart(2);
		ShoppingCart cart3 = new ShoppingCart(3);
		retailer.addCart(cart1);
		retailer.addCart(cart2);
		retailer.addCart(cart3);
		retailer.changeStatus(1,ShoppingCart.DELIVERED);
		assertEquals(2,retailer.getSizeByStatus(ShoppingCart.RECEIVED));
		assertEquals(1,retailer.getSizeByStatus(ShoppingCart.DELIVERED));
		
	}
	
	@Test
	public void testSingleMultipleCartsMatchMiddle() {
		Retailer retailer = new Retailer();
		ShoppingCart cart1 = new ShoppingCart(1);
		ShoppingCart cart2 = new ShoppingCart(2);
		ShoppingCart cart3 = new ShoppingCart(3);
		retailer.addCart(cart1);
		retailer.addCart(cart2);
		retailer.addCart(cart3);
		retailer.changeStatus(2,ShoppingCart.DELIVERED);
		assertEquals(2,retailer.getSizeByStatus(ShoppingCart.RECEIVED));
		assertEquals(1,retailer.getSizeByStatus(ShoppingCart.DELIVERED));
		
	}
	
	@Test
	public void testSingleMultipleCartsMatchLast() {
		Retailer retailer = new Retailer();
		ShoppingCart cart1 = new ShoppingCart(1);
		ShoppingCart cart2 = new ShoppingCart(2);
		ShoppingCart cart3 = new ShoppingCart(3);
		retailer.addCart(cart1);
		retailer.addCart(cart2);
		retailer.addCart(cart3);
		retailer.changeStatus(3,ShoppingCart.DELIVERED);
		assertEquals(2,retailer.getSizeByStatus(ShoppingCart.RECEIVED));
		assertEquals(1,retailer.getSizeByStatus(ShoppingCart.DELIVERED));
		
	}
}
