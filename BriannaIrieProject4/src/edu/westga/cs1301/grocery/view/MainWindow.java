package edu.westga.cs1301.grocery.view;

import java.text.NumberFormat;

import edu.westga.cs1301.grocery.model.Item;
import edu.westga.cs1301.grocery.model.ShoppingCart;
import edu.westga.cs1301.grocery.view.format.OutputFormatter;
import edu.westga.cs1301.grocery.model.Retailer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * CodeBehind To Handle Processing for the MainWindow
 *
 * @author CS1301
 * @version Spring 2020
 */
public class MainWindow {

	@FXML
	private TextField retailerName;
	@FXML
	private ComboBox<Retailer> retailers;
	@FXML
	private ComboBox<ShoppingCart> carts;
	@FXML
	private TextField itemDescription;
	@FXML
	private TextField itemIdNumber;
	@FXML
	private TextField itemQuantity;
	@FXML
	private TextField itemUnitPrice;
	@FXML
	private ToggleGroup statusGroup;
	@FXML
	private RadioButton receivedButton;
	@FXML
	private RadioButton processingButton;
	@FXML
	private RadioButton shippedButton;
	@FXML
	private RadioButton deliveredButton;
	@FXML
	private TextArea output;
	private Retailer selectedRetailer;
	private ShoppingCart selectedCart;
	private OutputFormatter formatter;

	/**
	 * Creates the main window and gets it ready to use.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public MainWindow() {
		this.formatter = new OutputFormatter();

	}

	/**
	 * Displays a popup message box with the provided title and message
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param title   text to be displayed in the title bar of the message box
	 * @param message text to be displayed in the body of the message box
	 * 
	 */
	private void displayPopUp(String title, String message) {
		Alert popup = new Alert(AlertType.INFORMATION);
		popup.setTitle("Information");
		popup.setHeaderText(title);
		popup.setContentText(message);
		popup.showAndWait();
	}

	/**
	 * Create a new Retailer and add it to the MainWindow::retailers dropdown list
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @param event - the JavaFX event which triggers a call to the method
	 */
	@FXML
	public void createRetailer(ActionEvent event) {
		String name = this.retailerName.getText();
		Retailer retailer = new Retailer(name);
		this.retailers.getItems().add(retailer);
	}

	public int getNumberOfRetailers() {
		return retailers.getItems().size();
	}

	@FXML
	public void retailerSelected(ActionEvent event) {

		this.selectedRetailer = this.retailers.getValue();
		showSelectedRetailerCarts();
	}
	
	private void showSelectedRetailerCarts() {
		if (this.selectedRetailer != null) {
			this.carts.setItems(FXCollections.observableArrayList(this.selectedRetailer.getOrders()));
		}
	}

	public void cartSelected(ActionEvent event) {
		this.selectedCart = this.carts.getValue();
	}

	/**
	 * Calls createNewCart for the selected Retailer
	 * 
	 * @preconditions getNumberOfRetailer() != 0
	 * @postconditions selectedRetailer.size() == selectedRetailer.size()@prev + 1
	 * 
	 * @param event - the JavaFX event which triggers a call to the method
	 */
	public void createShoppingCart(ActionEvent event) {
		if (this.getNumberOfRetailers() == 0) {
			this.displayPopUp("No Retailers!", "There are no retailers to create a shopping cart for.");
			return;
		}
		if (this.retailers.getValue() == null) {
			this.displayPopUp("No Retailer selected!", "Please select a retailer to add a shopping cart to.");
			return;
		}
		
		this.retailers.getValue().createNewCart();	
		showSelectedRetailerCarts();
		
	}

	/**
	 * Creates and adds a grocery Item to the selected Shopping Cart
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param event - the JavaFX event which triggers a call to the method
	 */
	@FXML
	public void addItem(ActionEvent event) {
		if(this.selectedCart == null) {
			this.displayPopUp("No Cart Selected!", "Please select a cart to add items to.");
			return;
		}
		
		String description = this.itemDescription.getText();
		int idNumber = Integer.parseInt(this.itemIdNumber.getText());
		double unitPrice = Double.parseDouble(this.itemUnitPrice.getText());
		int quantity = Integer.parseInt(this.itemQuantity.getText());
		
		this.selectedCart.addItem(new Item(description, idNumber, unitPrice, quantity));
	}

	/**
	 * Display the currently selected Shopping Cart in the MainWindow::output
	 * TextArea.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param event - the JavaFX event which triggers a call to the method
	 */
	@FXML
	public void displaySelectedCart(ActionEvent event) {
		if (selectedCart == null) {
			this.displayPopUp("No Cart Selected!", "Please select a cart to display.");
			return;
		}
		
		String displayString = this.formatter.formatCart(selectedRetailer, selectedCart);
		this.output.setText(displayString);
	}

	/**
	 * Changes the status of the selected cart
	 * 
	 * @precondition the selected cart should not be null
	 * @postcondition none
	 * 
	 * @param event - the JavaFX event which triggers a call to the method
	 */
	@FXML
	public void changeCartStatus(ActionEvent event) {
		if (this.selectedCart == null) {
			this.displayPopUp("No Cart Selected!", "Please select a cart to change it's status.");
			return;
		}	
		if (this.getSelectedStatus() == -1) {
			this.displayPopUp("No Status Selected!", "Please select a status.");
			return;
		}
		
		this.selectedCart.setStatus(getSelectedStatus());
	}

	/**
	 * Displays the total cost of the carts from the selected retailer in the text
	 * area
	 * 
	 * @preconditions selected retailer is not null
	 * @postconditions none
	 * 
	 * @param event - the JavaFX event which triggers a call to the method
	 */
	@FXML
	public void displayTotalCostByStatus(ActionEvent event) {
		if(this.selectedRetailer == null) {
			displayPopUp("No Retailer Selected!", "Please selected a retailer to display");
			return;
		}
		if (this.getSelectedStatus() == -1) {
			this.displayPopUp("No Status Selected!", "Please select a status.");
			return;
		}
		
		String displayString = formatter.formatRetailerTotalCostByStatus(selectedRetailer, this.getSelectedStatus());
		this.output.setText(displayString);
	}
	
	private int getSelectedStatus() {
		if (this.receivedButton.isSelected()) {
			return ShoppingCart.RECEIVED;
		} else if (this.processingButton.isSelected()) {
			return ShoppingCart.PROCESSING;
		} else if (this.shippedButton.isSelected()) {
			return ShoppingCart.SHIPPED;
		} else if (this.deliveredButton.isSelected()) {
			return ShoppingCart.DELIVERED;
		} else {
			return -1;
		}
	}
	
	/**
	 * Removes item from selected cart and displays whether the remove occurred or did not occur
	 * 
	 * @precondition selectedCart != null
	 * @postcondition selectedCart.size() == selectedCart.size()@prev - 1 if removed
	 *
	 * @param event - the JavaFX event which triggers a call to the method
	 */
	@FXML
	public void removeItemById(ActionEvent event) {
		if(this.selectedCart == null) {
			this.displayPopUp("No Cart Selected!", "Please select a cart");
			return;
		}
		int idNumber = Integer.parseInt(this.itemIdNumber.getText());
		boolean isRemoved = this.selectedCart.removeItemWithId(idNumber);
		String outputText = "";
		if(isRemoved) {
			outputText = String.format("Item %d has been removed", idNumber);
		} else {
			outputText = String.format("Item %d has not been removed", idNumber);
		}
		this.output.setText(outputText);
	}

	/**
	 * Rations the given item in every cart in every retailer
	 * 
	 * @precondition getNumberOfRetailers != 0
	 * @postcondition none
	 *
	 * @param event - the JavaFX event which triggers a call to the method
	 */
	@FXML
	public void rationItem(ActionEvent event) {
		if (this.getNumberOfRetailers() == 0) {
			this.displayPopUp("No Retailers!", "There are no retailers to ration items for.");
			return;
		}
		int idNumber = Integer.parseInt(this.itemIdNumber.getText());
		int maxQuantity = Integer.parseInt(this.itemQuantity.getText());
		
		for(Retailer currentRetailer : this.retailers.getItems()) {
			currentRetailer.ration(idNumber, maxQuantity);
		}
	}
	
	/**
	 * Displays item that has the highest total quantity summed over all shopping carts for all retailers.
	 * 
	 * @precondition retailer != 0
	 * @postcondition none
	 *
	 * @param event - the JavaFX event which triggers a call to the method
	 */
	@FXML
	public void findItemWithHighestQuantity(ActionEvent event) {
		if (this.getNumberOfRetailers() == 0) {
			this.displayPopUp("No Retailers!", "Please add retailers");
			return;
		}
		
		ShoppingCart tempCart = getAllItems();
		
		if (tempCart.size() == 0) {
			this.output.setText("There are no items in any of the carts.");
			return;
		}
		
		Item highestQuantity = tempCart.getHighestQuantityItem();
		
		String outputText = formatter.formatItemWithHighestQuantity(highestQuantity);
		this.output.setText(outputText);
	}

	private ShoppingCart getAllItems() {
		ShoppingCart tempCart = new ShoppingCart();
		for (Retailer currentRetailer : this.retailers.getItems()) {
			for (ShoppingCart currentCart : currentRetailer.getOrders()) {
				for (Item currentItem : currentCart.getCart()) {
					tempCart.addItem(currentItem);
				}
			}
		}
		return tempCart;
	}

}
