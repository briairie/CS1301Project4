package edu.westga.cs1301.grocery.test.retailer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Retailer;
import edu.westga.cs1301.grocery.model.ShoppingCart;

public class TestOneParameterConstructor {
	@Test
	public void ShouldNotAllowNullName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Retailer(null);
		});
	}
	
	@Test
	public void ShouldNotAllowEmptyName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Retailer("");
		});
	}
	
	@Test
	public void ShouldCreateValidRetailer() {
		Retailer f21 = new Retailer("Forever 21");
		
		assertEquals("Forever 21", f21.getName());
	}
	
}
