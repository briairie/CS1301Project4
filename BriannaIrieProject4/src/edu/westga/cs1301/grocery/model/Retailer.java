package edu.westga.cs1301.grocery.model;

import java.util.ArrayList;

/**
 * Models a online retailer
 * 
 * @author CS1301 Spring 2020
 *
 */
public class Retailer {

	private int nextIdNumber;
	private ArrayList<ShoppingCart> orders;
	private String name;

	/**
	 * Default Constructor
	 * 
	 * @preconditions none
	 * @postconditions size() == 0
	 * 
	 */
	public Retailer() {
		this.orders = new ArrayList<ShoppingCart>();
		this.nextIdNumber = 1;
	}
	
	/**
	 * Sets name to the given name
	 * 
	 * @precondition name != null && !name.isEmpty
	 * @postcondition size() == 0
	 *
	 * @param name the name of the retailer
	 */
	public Retailer(String name) {
		if(name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be null or empty");
		}
		this.orders = new ArrayList<ShoppingCart>();
		this.nextIdNumber = 1;
		this.name = name;
	}
	
	/**
	 * Gets the name of retailer
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return name of retailer
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the name of retailer
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return name of retailer
	 */
	public String toString() {
		return this.getName();
	}

	/**
	 * Creates and adds a new ShoppingCart with a unique id number
	 * 
	 * @preconditions none
	 * @postconditions size() == size()@prev + 1
	 */
	public void createNewCart() {
		ShoppingCart cart = new ShoppingCart();
		cart.setIdNumber(this.nextIdNumber);
		this.nextIdNumber++;
		this.addCart(cart);
	}

	/**
	 * Gets the array list of orders
	 * 
	 * @preconditions none
	 * @postconditions none
	 * @return the array list of orders
	 */
	public ArrayList<ShoppingCart> getOrders() {
		return this.orders;
	}

	/**
	 * Returns the number of orders
	 * 
	 * @return the number of orders
	 */
	public int size() {
		return this.orders.size();
	}

	/**
	 * Adds a shopping cart to the list of orders
	 * 
	 * @proconditions cart != null
	 * @postconditions size() == size()@prev + 1
	 * @param cart the cart to add
	 */
	public void addCart(ShoppingCart cart) {
		if (cart == null) {
			throw new IllegalArgumentException("cart cannot be null");
		}
		this.orders.add(cart);
	}

	/**
	 * Changes the status of the cart matching the id number
	 * 
	 * @precondition idNumber > 0 AND status >0 ShoppingCart.RECEIVED AND status <=
	 *               ShoppingCart.DELIVERED
	 * @postconditions cart status updated if id match found
	 * @param idNumber - the id number to search for
	 * @param status   - the new status
	 */
	public void changeStatus(int idNumber, int status) {
		if (idNumber <= 0) {
			throw new IllegalArgumentException("id number out of range");
		}
		if (status < ShoppingCart.RECEIVED || status > ShoppingCart.DELIVERED) {
			throw new IllegalArgumentException("status out of range");
		}
		for (ShoppingCart current : this.orders) {
			if (current.getIdNumber() == idNumber) {
				current.setStatus(status);
			}
		}
	}

	/**
	 * Gets the total quantity of all items matching idNumber from all cart with the
	 * given status
	 * 
	 * @preconditions idNumber >= Item.MINIMUM_ID_NUMBER AND status >0
	 *                ShoppingCart.RECEIVED AND status <= ShoppingCart.DELIVERED
	 * @param idNumber - the id number to search for
	 * @param status   - the status
	 * @return the total quantity of all items in carts with given status
	 */
	public int getTotalQuantityByIdNumberAndStatus(int idNumber, int status) {
		if (idNumber < Item.MINIMUM_ID_NUMBER) {
			throw new IllegalArgumentException("idNumber below Item minimum");
		}
		if (status < ShoppingCart.RECEIVED || status > ShoppingCart.DELIVERED) {
			throw new IllegalArgumentException("cart status out of range");
		}

		int total = 0;
		for (ShoppingCart cart : this.orders) {
			if (cart.getStatus() == status) {
				for (Item item : cart.getCart()) {
					if (item.getIdNumber() == idNumber) {
						total += item.getQuantity();
					}
				}
			}
		}
		return total;
	}

	/**
	 * Gets the sum of the total cost of all carts with the given status
	 * 
	 * @preconditions status >0 ShoppingCart.RECEIVED AND status <=
	 *                ShoppingCart.DELIVERED
	 * @param status the input status
	 * @return the total cost of all carts with the given status
	 */
	public double getTotalCostByStatus(int status) {
		if (status < ShoppingCart.RECEIVED || status > ShoppingCart.DELIVERED) {
			throw new IllegalArgumentException("input not in of range");
		}
		double total = 0.0;
		for (ShoppingCart current : this.orders) {
			if (current.getStatus() == status) {
				total += current.totalCost();
			}
		}
		return total;
	}

	/**
	 * Reduces the item quantity to maxQuantity for items matching the idNumber in
	 * carts of status ShoppingCart.RECEIVED or ShoppingCart.PROCESSING
	 * 
	 * @preconditions idNumber >= Item.MINIMUM_ID_NUMBER AND maxQuantity > 0
	 * @postconditions item.getQuantity() <= maxQuantity for item in cart where
	 *                 item.getIdNumber() == idNumber and cart.getStatus() ==
	 *                 ShoppingCart.RECEIVED OR cart.getStatus() ==
	 *                 ShoppingCart.PROCESSING
	 * @param idNumber    - the idNumber to search for
	 * @param maxQuantity - the max quantity of the item for each cart
	 */
	public void ration(int idNumber, int maxQuantity) {
		if (idNumber < Item.MINIMUM_ID_NUMBER) {
			throw new IllegalArgumentException("id Number below Item minimum");
		}
		if (maxQuantity < 1) {
			throw new IllegalArgumentException("Max Quantity must be positive");
		}
		for (ShoppingCart cart : this.orders) {
			if (cart.getStatus() == ShoppingCart.RECEIVED || cart.getStatus() == ShoppingCart.PROCESSING) {
				for (Item item : cart.getCart()) {
					if (item.getIdNumber() == idNumber && item.getQuantity() > maxQuantity) {
						item.setQuantity(maxQuantity);
					}
				}
			}
		}
	}

	public int getSizeByStatus(int status) {
		if (status < ShoppingCart.RECEIVED || status > ShoppingCart.DELIVERED) {
			throw new IllegalArgumentException("input status out of range");
		}
		int size = 0;
		for (ShoppingCart cart : this.orders) {
			if (cart.getStatus() == status) {
				size++;
			}
		}
		return size;
	}

}
