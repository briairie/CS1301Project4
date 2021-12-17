package edu.westga.cs1301.grocery.model;

import java.text.NumberFormat;

/**
 * A data class to model a shopping cart item
 * 
 * @author CS1301 Spring 2020
 *
 */
public class Item {

	public static final int MINIMUM_ID_NUMBER = 10000;
	private String description;
	private int idNumber;
	private double unitPrice;
	private int quantity;

	/**
	 * Four parameter constructor
	 * 
	 * @preconditions description != null AND !description.isEmpty() AND 
	 * 				  idNumber >= Item.MINIMUM_ID_NUMBER AND 
	 *  			  unitPrice > 0.0 AND 
	 *  			  quantity > 0
	 * 
	 * @postconditions getDescription() == description AND 
	 * 				   getIdNumber() == idNumber AND
	 *                 getUnitPrice() == unitPrice AND 
	 *                 getQuantity() == quantity
	 * 
	 * @param description - the item's description
	 * @param idNumber    - the item's unique id number
	 * @param unitPrice   - the unit price of the item
	 * @param quantity    - the current quantity of the item
	 */
	public Item(String description, int idNumber, double unitPrice, int quantity) {
		if (description == null) {
			throw new IllegalArgumentException("description cannot be null");
		}
		if (description.isEmpty()) {
			throw new IllegalArgumentException("description cannot be empty");
		}
		if (idNumber < MINIMUM_ID_NUMBER) {
			throw new IllegalArgumentException("id number below minimum ");
		}
		if (unitPrice <= 0.0) {
			throw new IllegalArgumentException("unit price must be positive");
		}
		if (quantity <= 0) {
			throw new IllegalArgumentException("quantity must be positive");
		}
		this.description = description;
		this.idNumber = idNumber;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	/**
	 * Gets the unit price
	 * @preconditions none
	 * @postconditions none
	 * @return the unit price
	 */
	public double getUnitPrice() {
		return this.unitPrice;
	}

	/**
	 * Sets the unit price
	 * 
	 * @preconditions unitPrice > 0.0
	 * @postconditions getUnitPrice() == unitPrice
	 * @param unitPrice the new unit price
	 */
	public void setUnitPrice(double unitPrice) {
		if (unitPrice <= 0.0) {
			throw new IllegalArgumentException("Unit price must be positive");
		}
		this.unitPrice = unitPrice;
	}

	/**
	 * Gets the quantity
	 * @preconditions none
	 * @postconditions none
	 * @return the quantity
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * Sets the quantity
	 * 
	 * @preconditions quantity > 0
	 * @postconditions getQuantity() == quantity
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity must be positive");
		}
		this.quantity = quantity;
	}

	/**
	 * Gets the description
	 * @preconditions none
	 * @postconditions none
	 * @return the item description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the id number
	 * @preconditions none
	 * @postconditions none
	 * @return the id number
	 */
	public int getIdNumber() {
		return this.idNumber;
	}

	/**
	 * returns the total cost as unit price * quantity
	 * 
	 * @preconditions none
	 * @postconditions none
	 * @return the total cost
	 */
	public double totalCost() {
		return this.unitPrice * this.quantity;
	}

	@Override
	public String toString() {
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		String output = this.description + " " +
				this.idNumber + " " + this.quantity + " " +
				fmt.format(this.unitPrice) + " " +
				fmt.format(this.totalCost());
		return output;
	}
}
