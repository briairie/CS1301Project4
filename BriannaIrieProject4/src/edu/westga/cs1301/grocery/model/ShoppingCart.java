package edu.westga.cs1301.grocery.model;

import java.util.ArrayList;

/**
 * Models a shopping cart as an array list of items
 * 
 * @author CS1301 Spring 2020
 *
 */
public class ShoppingCart {

	public static final int RECEIVED = 0;
	public static final int PROCESSING = 1;
	public static final int SHIPPED = 2;
	public static final int DELIVERED = 3;

	private int status;
	private int idNumber;
	private ArrayList<Item> cart;

	/**
	 * Default constructor
	 * 
	 * @preconditions none
	 * @postconditions getStatus() == ShoppingCart.RECEIVED AND size() == 0 AND
	 *                 getIdNumber() == 0
	 */
	public ShoppingCart() {
		this.status = ShoppingCart.RECEIVED;
		this.idNumber = 0;
		this.cart = new ArrayList<Item>();
	}

	/**
	 * One parameter constructor to specify the id number
	 * 
	 * @preconditions idNumber > 0
	 * @postcoditions getStatus() == ShoppingCart.RECEIVED AND size() == 0 AND
	 *                getIdNumber() == idNumber
	 * @param idNumber the id number for the cart
	 */
	public ShoppingCart(int idNumber) {
		if (idNumber <= 0) {
			throw new IllegalArgumentException("id number must be positive");
		}
		this.idNumber = idNumber;
		this.status = ShoppingCart.RECEIVED;
		this.cart = new ArrayList<Item>();
	}

	/**
	 * Returns the number of items in the cart
	 * 
	 * @preconditions none
	 * @postconditions none
	 * @return the number of items
	 */
	public int size() {
		return this.cart.size();
	}

	/**
	 * Gets the status
	 * 
	 * @preconditions none
	 * @postconditions none
	 * @return the cart status
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * Setter for status
	 * 
	 * @preconditions status >= ShoppingCart.RECEVIED AND status <=
	 *                ShoppingCart.DELIVERED
	 * 
	 * @postconditions getStatus() == status
	 * @param status - the new status
	 */
	public void setStatus(int status) {
		if (status < ShoppingCart.RECEIVED || status > ShoppingCart.DELIVERED) {
			throw new IllegalArgumentException("Status not in range");
		}
		this.status = status;
	}

	/**
	 * Gets the id number
	 * 
	 * @preconditions none
	 * @postconditions none
	 * @return the id number
	 */
	public int getIdNumber() {
		return this.idNumber;
	}

	/**
	 * Sets the id number
	 * 
	 * @preconditions id > 0
	 * @postconditions getIdNumber() == id
	 * @param id the new id number
	 */
	public void setIdNumber(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("id must be positive");
		}
		this.idNumber = id;
	}

	/**
	 * Gets the items in the cart
	 * 
	 * @preconditions none
	 * @postconditions none
	 * @return the item array list
	 */
	public ArrayList<Item> getCart() {
		return this.cart;
	}

	/**
	 * Adds an item to the shopping cart. If the id number matches an id number of
	 * an item already in the cart, the item is not added and the quantity of the
	 * item already in the cart is incremented by item.getQuantity()
	 * 
	 * @preconditions item != null
	 * @postconditions size() == size()@prev + 1 if item.getIdNumber() !=
	 *                 getIdNumber() for all items in the cart OR
	 *                 match.getQuantity() == match.getQuantity@prev +
	 *                 item.getQuantity() if match.getIdNumber() ==
	 *                 item.getIdNumber()
	 * @param item - the item to add
	 */
	public void addItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("item cannot be null");
		}
		for (Item current : this.cart) {
			if (item.getIdNumber() == current.getIdNumber()) {
				current.setQuantity(current.getQuantity() + item.getQuantity());
				return;
			}

		}
		this.cart.add(item);
	}

	/**
	 * returns true if an item in the cart has an id number that matches the input
	 * id number and false otherwise
	 * 
	 * @preconditions id >= Item.MINIMUM_ID_NUMBER
	 * @postconditions none
	 * @param id - the id number to search for
	 * @return true if match found, false otherwise
	 */
	public boolean hasItemWithId(int id) {
		if (id < Item.MINIMUM_ID_NUMBER) {
			throw new IllegalArgumentException("Id below minimum");
		}
		for (Item current : this.cart) {
			if (current.getIdNumber() == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes the item in the cart with the input id number
	 * 
	 * @preconditions id >= Item.MINIMUM_ID_NUMBER
	 * @postconditions size() == size()@prev - 1 if match found OR size()@prev
	 *                 otherwise
	 * @param id the id number to search for
	 * @return true if match found and item removed, false otherwise
	 */
	public boolean removeItemWithId(int id) {
		if (id < Item.MINIMUM_ID_NUMBER) {
			throw new IllegalArgumentException("id below minimum");
		}
		Item itemToRemove = null;
		for (Item current : this.cart) {
			if (current.getIdNumber() == id) {
				itemToRemove = current;
			}
		}
		if (itemToRemove != null) {
			this.cart.remove(itemToRemove);
			return true;
		}
		return false;
	}

	/**
	 * Returns the sum of the total quantities for all the items in the cart
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the total quantity
	 */
	public int totalQuantity() {
		int total = 0;
		for (Item current : this.cart) {
			total += current.getQuantity();
		}
		return total;
	}

	/**
	 * Returns the sum of the total cost of all items in the cart
	 * 
	 * @preconditions none
	 * @postconditions none
	 * @return the total cost
	 */
	public double totalCost() {
		double total = 0.0;
		for (Item current : this.cart) {
			total += current.totalCost();
		}
		return total;
	}

	/**
	 * Gets the status string for the current status
	 * 
	 * @preconditions status >= ShoppingCart.RECEVIED AND status <=
	 *                ShoppingCart.DELIVERED
	 * @postconditions none
	 * 
	 * @param status the status int
	 * @return the status string
	 */
	public static String getStatusString(int status) {

		if (status < ShoppingCart.RECEIVED || status > ShoppingCart.DELIVERED) {
			throw new IllegalArgumentException("Status out of range");
		}
		if (status == ShoppingCart.RECEIVED) {
			return "RECEIVED";
		}
		if (status == ShoppingCart.PROCESSING) {
			return "PROCESSING";
		}
		if (status == ShoppingCart.SHIPPED) {
			return "SHIPPED";
		}

		return "DELIVERED";

	}

	@Override
	public String toString() {
		return "Cart " + this.idNumber;
	}
	
	/**
	 * Returns item with the highest quantity
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return The item with highest quantity
	 */
	public Item getHighestQuantityItem() {
		Item highestQuantity = null;
		for (Item currentItem : this.cart) {
			if (highestQuantity == null) {
				highestQuantity = currentItem;
			}
			if (currentItem.getQuantity() > highestQuantity.getQuantity()) {
				highestQuantity = currentItem;
			}
		}
		return highestQuantity;
	}
}
