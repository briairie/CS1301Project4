package edu.westga.cs1301.grocery.view.format;

import java.text.NumberFormat;

import edu.westga.cs1301.grocery.model.Item;
import edu.westga.cs1301.grocery.model.Retailer;
import edu.westga.cs1301.grocery.model.ShoppingCart;

/**
 * Formats output to be displayed in the UI.
 * 
 * @author	CS1301
 * @version Spring 2020
 */
public class OutputFormatter {
	
	/**
	 * Returns a String giving the data members of an Item 
	 * format:
	 * 
	 * ITEM <description>, <idNumber>, <unit price>, <quantity>, <total cost>
	 * 
	 * @precondition item != null
	 * @postcondition none
	 *
	 * @param item The Item to format
	 * @return Full string representation of the item
	 */
	public String formatItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("item cannot be null");
		}
		String description = item.getDescription();
		int idNumber = item.getIdNumber();
		double unitPrice = item.getUnitPrice();
		int quantity = item.getQuantity();
		double totalCost = item.totalCost();
		
		return String.format("ITEM %s, %d, $%.2f, %d, $%.2f", description, idNumber, unitPrice, quantity, totalCost);
	}
	
	/**
	 * Returns a String giving the content of a ShoppingCart 
	 * 
	 * @precondition retailer != null AND cart != null
	 * @postcondition none
	 *
	 * @param retailer The retailer for the cart
	 * @param cart The ShoppingCart to format
	 * @return Full string representation of the cart
	 */
	public String formatCart(Retailer retailer, ShoppingCart cart) {
		if (retailer == null) {
			throw new IllegalArgumentException("retailer cannot be null");
		}
		if (cart == null) {
			throw new IllegalArgumentException("cart cannot be null");
		}
		String retailerName = retailer.getName();
		int cartIdNumber = cart.getIdNumber();
		String status = ShoppingCart.getStatusString(cart.getStatus());
		String topLine =String.format("RETAILER: %s CART ID: %d STATUS: %s", retailerName, cartIdNumber, status)+ System.lineSeparator();
		
		String items = "";
		for (Item currentItem : cart.getCart()) {
			items += this.formatItem(currentItem) + System.lineSeparator();
		}
		
		int totalQuantity = cart.totalQuantity();
		double totalCost = cart.totalCost();
		String lastLine= String.format("TOTAL QUANTITY: %d TOTAL COST: $%.2f", totalQuantity, totalCost);
		return topLine + items + lastLine;
	}
	
	/**
	 * Returns a string giving the number of orders with the given status and total cost of them
	 * 
	 * @precondition retailer != null
	 * @postcondition none
	 *
	 * @param retailer The retailer with the orders
	 * @param status The status of the orders we want
	 * @return String giving us information on the orders with given status
	 */
	public String formatRetailerTotalCostByStatus(Retailer retailer, int status) {
		String retailerLine = "RETAILER: " + retailer.getName() + System.lineSeparator();
		String statusLine = "STATUS: " + ShoppingCart.getStatusString(status) + System.lineSeparator();
		String numOfCarts = "NUMBER OF CARTS: " + retailer.getSizeByStatus(status) + System.lineSeparator();
		String totalCost = "TOTAL COST: $" + retailer.getTotalCostByStatus(status);
		return retailerLine + statusLine + numOfCarts + totalCost;
	}
	
	public String formatItemWithHighestQuantity(Item item) {
		String title = "Item With Highest Total Quantity" + System.lineSeparator();
		String description = "Description: " + item.getDescription() + System.lineSeparator();
		String totalQuantity = "Total Quantity: " + item.getQuantity();
		
		return title + description + totalQuantity;
	}

}
