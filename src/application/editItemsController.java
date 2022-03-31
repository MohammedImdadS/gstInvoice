package application;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class editItemsController {
	@FXML
	private AnchorPane EditItem;
	@FXML
	private TextField itemName;
	@FXML
	private TextField itemHSN;

	@FXML
	private TextField stock;
	@FXML
	private TextField sgstAmount;
	@FXML
	private TextField cgstAmount;
	@FXML
	private TextField igstAmount;
	@FXML
	private TextField costPrice;
	@FXML
	private TextField sellingPrice;
	@FXML
	private TextField discValue;
	@FXML
	private TextField taxable;
	@FXML
	private ComboBox<String> gstRate;
	//@FXML
	//private ComboBox<String> qtyTyp;
	@FXML
	private ComboBox<String> unit;

	int itemId = 0;
	String sql ="";
	Connection conn = Connections.b2csDBConncetion();
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	// Event Listener on Button.onAction
	@FXML
	public void updateDetails(ActionEvent event) throws SQLException {

		sql = "UPDATE items SET item_name = ?, item_hsn = ?, Unit = ?, gst_rate = ?, cgst = ?, sgst = ?, igst = ?, stock = ?, cost_price = ?," +
				"selling_price = ?,DiscValue = ?,Taxable = ? WHERE items.item_id = " + itemId;
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, itemName.getText().toString());
		pstmt.setString(2, itemHSN.getText().toString());
		//pstmt.setString(3, qtyTyp.getSelectionModel().getSelectedItem().toString());
		pstmt.setString(3, unit.getSelectionModel().getSelectedItem().toString());
		pstmt.setFloat(4, Float.valueOf(gstRate.getSelectionModel().getSelectedItem().toString()));
		pstmt.setFloat(5, Float.valueOf(cgstAmount.getText().toString()));
		pstmt.setFloat(6, Float.valueOf(sgstAmount.getText().toString()));
		pstmt.setFloat(7, Float.valueOf(igstAmount.getText().toString()));
		pstmt.setFloat(8, Float.valueOf(stock.getText().toString()));
		pstmt.setFloat(9, Float.valueOf(costPrice.getText().toString()));
		pstmt.setFloat(10, Float.valueOf(sellingPrice.getText().toString()));
		pstmt.setFloat(11, Float.valueOf(discValue.getText().toString()));
		pstmt.setFloat(12,Float.valueOf(taxable.getText().toString()));
		pstmt.executeUpdate();

		Stage stage = (Stage)  EditItem.getScene().getWindow();
		stage.close();
		
	}
	
	public void getItemId(int intID) throws SQLException{
		itemId = intID;
		/*String[] qty = { "Half-Ltr", "One-Ltr", "Four-Ltr","Novalty", "Bulk", "1/2-ltr", "1-Ltr",
		"4-Ltr" };
		qtyTyp.getItems().setAll(qty);*/

		String[] units = {"Box","Pieces"};
		unit.getItems().addAll(units);

		String[] gst = { "0.0", "0.25", "5.0", "12.0", "18.0", "28.0" };
		gstRate.getItems().setAll(gst);

		sql = "SELECT * FROM items WHERE item_id = " + intID + " LIMIT 1";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		rs.next();
		itemName.setText(rs.getString("item_name"));
		itemHSN.setText(rs.getString("item_hsn"));
		stock.setText(String.valueOf(rs.getFloat("stock")));
		sgstAmount.setText(String.valueOf(rs.getFloat("sgst")));
		cgstAmount.setText(String.valueOf(rs.getFloat("cgst")));
		igstAmount.setText(String.valueOf(rs.getFloat("igst")));
		costPrice.setText(String.valueOf(rs.getFloat("cost_price")));
		sellingPrice.setText(String.valueOf(rs.getFloat("selling_price")));
		discValue.setText(String.valueOf(rs.getFloat("DiscValue")));
		taxable.setText(String.valueOf(rs.getFloat("Taxable")));
		gstRate.getSelectionModel().select(rs.getString("gst_rate"));
		//qtyTyp.getSelectionModel().select(rs.getString("qty_type"));
		unit.getSelectionModel().select(rs.getString("Unit"));

		
		
	}
}
