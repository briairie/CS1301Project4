package edu.westga.cs1301.grocery.test.retailer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Retailer;
import edu.westga.cs1301.grocery.model.ShoppingCart;

public class TestCreateNewCart {

	@Test
	public void testAddOneCart() {
		Retailer retailer = new Retailer();
		retailer.createNewCart();
		assertEquals(1,retailer.size());
		
	}
	
	@Test
	public void testAddThreeCarts() {
		Retailer retailer = new Retailer();
		retailer.createNewCart();
		retailer.createNewCart();
		retailer.createNewCart();
		assertEquals(3,retailer.size());
	}
}
