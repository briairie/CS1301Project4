package edu.westga.cs1301.grocery.test.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1301.grocery.model.Item;

public class TestTotalCost {

	public static final double MARGIN_OF_ERROR = 0.01;
	
	@Test
	public void testNonZeroUnitPriceAndQuantity() {
		
		int quantity = 123;
		double unitPrice = 39.95;
		
		Item item = new Item("test item",12345,unitPrice,quantity);
		double expected = quantity*unitPrice;
		double actual = item.totalCost();
		
		assertEquals(expected,actual,MARGIN_OF_ERROR);
	}
}
