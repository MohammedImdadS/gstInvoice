package application;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.BillingController.items;
import application.CustomerController.Customer;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;




public class ItemsController {

	public class items {
		private IntegerProperty itemId;
		private StringProperty name;
		private StringProperty hsnNo;
		//private StringProperty qtyType;
		private StringProperty unit;
		private FloatProperty gstRate;
		private FloatProperty cgst;
		private FloatProperty sgst;
		private FloatProperty igst;
		private FloatProperty stock;
		private FloatProperty costPrice;
		private FloatProperty sellingPrice;
		private FloatProperty discValue;
		private FloatProperty taxable;

		public items() {
			super();
		}

		public items(int itemId, String name, String hsnNo,/* String qtyType,*/String unit, Float gstRate, Float cgst, Float sgst,
				Float igst, Float stock, Float costPrice, Float sellingPrice,Float discValue,Float taxable) {
			super();
			this.itemId = new SimpleIntegerProperty(itemId);
			this.name = new SimpleStringProperty(name);
			this.hsnNo = new SimpleStringProperty(hsnNo);
			//this.qtyType = new SimpleStringProperty(qtyType);
			this.unit = new SimpleStringProperty(unit);
			this.gstRate = new SimpleFloatProperty(gstRate);
			this.cgst = new SimpleFloatProperty(cgst);
			this.sgst = new SimpleFloatProperty(sgst);
			this.igst = new SimpleFloatProperty(igst);
			this.stock = new SimpleFloatProperty(stock);
			this.costPrice = new SimpleFloatProperty(costPrice);
			this.sellingPrice = new SimpleFloatProperty(sellingPrice);
			this.discValue = new SimpleFloatProperty(discValue);
			this.taxable = new SimpleFloatProperty(taxable);
		}

		public int getItemId() {
			return itemId.get();
		}

		public void setItemId(int itemId) {
			this.itemId.set(itemId);
		}

		public String getName() {
			return name.get();
		}

		public void setName(String name) {
			this.name.set(name);
		}

		public String getHsnNo() {
			return hsnNo.get();
		}

		public void setHsnNo(String hsnNo) {
			this.hsnNo.set(hsnNo);
		}

		/*public String getQtyType() {
			return qtyType.get();
		}

		/*public void setQtyType(String qtyType) {
			this.qtyType.set(qtyType);
		}*/

		public String getUnit(){return unit.get();}

		public void setUnit(String unit){this.unit.set(unit);}

		public Float getGstRate() {
			return gstRate.get();
		}

		public void setGstRate(Float gstRate) {
			this.gstRate.set(gstRate);
		}

		public Float getCgst() {
			return cgst.get();
		}

		public void setCgst(Float cgst) {
			this.cgst.set(cgst);
		}

		public Float getSgst() {
			return sgst.get();
		}

		public void setSgst(Float sgst) {
			this.sgst.set(sgst);
		}

		public Float getIgst() {
			return igst.get();
		}

		public void setIgst(Float igst) {
			this.igst.set(igst);
		}

		public Float getStock() {
			return stock.get();
		}

		public void setStock(Float stock) {
			this.stock.set(stock);
			;
		}

		public Float getCostPrice() {
			return costPrice.get();
		}

		public void setCostPrice(Float costPrice) {
			this.costPrice.set(costPrice);
		}

		public Float getSellingPrice() {
			return sellingPrice.get();
		}

		public void setSellingPrice(Float sellingPrice) {
			this.sellingPrice.set(sellingPrice);
		}

		public Float getDiscValue(){return discValue.get();}

		public void setDiscvalue(Float discValue){this.discValue.set(discValue);}

		public Float getTaxable(){return taxable.get();}

		public void setTaxable(Float taxable){this.taxable.set(taxable);}

	}

	@FXML
	Label itemsID = new Label();
	int mItemsID = 0;
	@FXML
	TextField itemsItemName = new TextField();
	String mItemsItemName = "";
	// items field for making bill
	@FXML
	TextField itemsHSNNumber = new TextField();
	String mItemsHSNNumber = "";
	//@FXML
	//ChoiceBox<String> qtyType = new ChoiceBox<>();
	//String mQtyType = "";
	@FXML
	ChoiceBox<String> unit = new ChoiceBox<>();
	String mUnit = "";
	@FXML
	ChoiceBox<String> gstRate = new ChoiceBox<>();
	float mGstRate = 0;
	@FXML
	Label itemsCGST = new Label();
	float mItemsCGST = 0;
	@FXML
	Label itemsSGST = new Label();
	float mItemsSGST = 0;
	@FXML
	Label itemsIGST = new Label();
	float mItemsIGST = 0;
	@FXML
	Label itemsDiscValue = new Label();
	float mItemsDiscvalue = 0;

	@FXML
	Label itemsTaxable = new Label();
	float mItemsTaxable = 0;

	@FXML
	TextField itemsStock = new TextField();
	float mItemsStock = 0;
	@FXML
	TextField itemsCostPrice = new TextField();
	float mItemsCostPrice = 0;
	@FXML
	TextField itemsSellingPrice = new TextField();
	float mItemsSellingPrice = 0;
	@FXML
	Button itemsAddItems = new Button();
	// field for grand total and save and print button
	@FXML
	Label billinGrandTotal = new Label();
	@FXML
	Button billinSaveAndPrintButton = new Button();

	@FXML
	TableView<items> table = new TableView<>();
	@FXML
	TableColumn<items, Integer> collmItemsID = new TableColumn<>();
	@FXML
	TableColumn<items, String> collName = new TableColumn<>();
	@FXML
	TableColumn<items, String> collHSNNumber = new TableColumn<>();
	@FXML
	TableColumn<items, String> collQtyType = new TableColumn<>();
	@FXML
	TableColumn<items, String> collUnit = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collGstRate = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collCGST = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collSGST = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collIGST = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collStock = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collCostPrice = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collSellingPrice = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collDiscValue = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collTaxable = new TableColumn<>();

	ObservableList<items> data = FXCollections.observableArrayList();

	@FXML
	public void initialize() throws SQLException {
		// Array of quantity
		/*String[] qty = { "Half-Ltr", "One-Ltr", "Four-Ltr","Novalty", "Bulk", "1/2-ltr", "1-Ltr",
				"4-Ltr" };
		qtyType.getItems().addAll(qty);*/

		String[] units = {"Box","Pieces"};
		unit.getItems().addAll(units);

		String[] gst = { "0.0", "0.25", "5.0", "12.0", "18.0", "28.0" };
		gstRate.getItems().addAll(gst);

		// sql part
		String sql = "SELECT * FROM items";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			data.add(new items(rs.getInt("item_id"), rs.getString("item_name"), rs.getString("item_hsn"),
					/*rs.getString("qty_type"),*/rs.getString("unit"), rs.getFloat("gst_rate"),
					rs.getFloat("cgst"), rs.getFloat("sgst"),
					rs.getFloat("igst"), rs.getFloat("stock"), rs.getFloat("cost_price"),
					rs.getFloat("Selling_price"),rs.getFloat("DiscValue"),rs.getFloat("taxable")));
		}

		table.setItems(data);
		collmItemsID.setCellValueFactory(new PropertyValueFactory<items, Integer>("itemId"));
		collmItemsID.setSortType(TableColumn.SortType.ASCENDING);
		collName.setCellValueFactory(new PropertyValueFactory<items, String>("name"));
		collHSNNumber.setCellValueFactory(new PropertyValueFactory<items, String>("hsnNo"));
		//collQtyType.setCellValueFactory(new PropertyValueFactory<items, String>("qtyType"));
		collUnit.setCellValueFactory(new PropertyValueFactory<items,String>("unit"));
		collGstRate.setCellValueFactory(new PropertyValueFactory<items, Float>("gstRate"));
		collCGST.setCellValueFactory(new PropertyValueFactory<items, Float>("cgst"));
		collSGST.setCellValueFactory(new PropertyValueFactory<items, Float>("sgst"));
		collIGST.setCellValueFactory(new PropertyValueFactory<items, Float>("igst"));
		collStock.setCellValueFactory(new PropertyValueFactory<items, Float>("stock"));
		collCostPrice.setCellValueFactory(new PropertyValueFactory<items, Float>("costPrice"));
		collSellingPrice.setCellValueFactory(new PropertyValueFactory<items, Float>("sellingPrice"));
		collDiscValue.setCellValueFactory(new PropertyValueFactory<items, Float>("discValue"));
		collTaxable.setCellValueFactory(new PropertyValueFactory<items, Float>("taxable"));
		
	}

	// getting connections
	Connection conn = Connections.b2csDBConncetion();

	// button Functionality
	public void itemsAdditems(ActionEvent event) throws SQLException {

		// mItemsID = 10320;//itemsID.getText().toString();
		mItemsItemName = itemsItemName.getText().toString();
		mItemsHSNNumber = itemsHSNNumber.getText().toString();
		//mQtyType = qtyType.getSelectionModel().getSelectedItem().toString();
		mUnit = unit.getSelectionModel().getSelectedItem().toString();
		mGstRate = Float.parseFloat(gstRate.getSelectionModel().getSelectedItem().toString());
		// mItemsCGST
		// mItemsSGST
		mItemsStock = Float.parseFloat(itemsStock.getText().toString());
		mItemsCostPrice = Float.parseFloat(itemsCostPrice.getText().toString());
		mItemsSellingPrice = Float.parseFloat(itemsSellingPrice.getText().toString());
		mItemsDiscvalue = (mItemsCostPrice * (mGstRate) / (mGstRate+100)) ;
		BigDecimal bd = new BigDecimal(Float.toString(mItemsDiscvalue));
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		mItemsDiscvalue = bd.floatValue();

		mItemsTaxable = (mItemsCostPrice * (100) / (mGstRate+100)) ;
		BigDecimal bd1 = new BigDecimal(Float.toString(mItemsTaxable));
		bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
		mItemsTaxable =  bd1.floatValue();

		mItemsCGST = (mItemsDiscvalue  /2 );
		mItemsSGST = (mItemsDiscvalue  / 2);
		mItemsIGST = 0;

		String sql = "INSERT INTO items (item_name,item_hsn,unit,gst_rate,cgst,sgst,igst,stock,cost_price," +
				"Selling_price,discValue,taxable) VALUES (\'"
				// + mItemsID + "','"
				+ mItemsItemName + "','" + mItemsHSNNumber + "','" /*+ mQtyType + "','" */+ mUnit + "','" + mGstRate + "','" + mItemsCGST
				+ "','" + mItemsSGST + "','" + mItemsIGST + "','" + + mItemsStock + "','" +mItemsCostPrice + "','" + mItemsSellingPrice
				+"','"+ mItemsDiscvalue + "','"  +mItemsTaxable + "\')";

		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);

		items a = new items(mItemsID, mItemsItemName, mItemsHSNNumber,/* mQtyType,*/mUnit, mGstRate, mItemsCGST, mItemsSGST,
				mItemsIGST, mItemsStock, mItemsCostPrice, mItemsSellingPrice,mItemsDiscvalue,mItemsTaxable);
		data.add(a);

		itemsItemName.clear();
		itemsHSNNumber.clear();
		itemsCostPrice.clear();
		itemsSellingPrice.clear();
		if(billinSaveAndPrintButton.isFocused()) {
			itemsItemName.requestFocus();
		}

	}

	public void deleteItems() throws SQLException{
		//deleting from the database
		String sql;
		Statement stmt;
		ResultSet  rs;
		if(table.getSelectionModel().getSelectedItem().getItemId()==0){
			//if we are trying to delete the very resent entry then we will update data list
			 sql = "SELECT * FROM items";
			 stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			data.clear();
			while(rs.next()){
				data.add(new items(rs.getInt("item_id"),
						rs.getString("item_name"),
						rs.getString("item_hsn"),
						//rs.getString("qty_type"),
						rs.getString("unit"),
						rs.getFloat("gst_rate"),
						rs.getFloat("cgst"),
						rs.getFloat("sgst"),
						rs.getFloat("igst"),
						rs.getFloat("stock"),
						rs.getFloat("cost_price"), 
						rs.getFloat("Selling_price"),
						rs.getFloat("discValue"),
						rs.getFloat("taxable")));
			}
		}else{
		
		int id = table.getSelectionModel().getSelectedItem().getItemId() ;
		sql = "DELETE FROM items WHERE item_id = " + id;
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		}
		//updating ilst after deleting
		
		
		 sql = "SELECT * FROM items";
		 stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		data.clear();
		while(rs.next()){
			data.add(new items(rs.getInt("item_id"),
					rs.getString("item_name"),
					rs.getString("item_hsn"),
					//rs.getString("qty_type"),
					rs.getString("unit"),
					rs.getFloat("gst_rate"),
					rs.getFloat("cgst"),
					rs.getFloat("sgst"),
					rs.getFloat("igst"),
					rs.getFloat("stock"),
					rs.getFloat("cost_price"), 
					rs.getFloat("Selling_price"),
					rs.getFloat("discValue"),
					rs.getFloat("taxable")));
		}
		
		
		stmt.close();
			
	}
	
	public void editItems() throws SQLException, IOException{
		
		int itemId = table.getSelectionModel().getSelectedItem().getItemId() ;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editItems.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		((editItemsController) fxmlLoader.getController()).getItemId(itemId);
		stage.show();
	}

	public void refreshTableData() throws SQLException{
		// sql part	
				data.clear();
				String sql = "SELECT * FROM items";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					data.add(new items(rs.getInt("item_id"), rs.getString("item_name"), rs.getString("item_hsn"),
							/*rs.getString("qty_type"),*/rs.getString("unit"), rs.getFloat("gst_rate"), rs.getFloat("cgst"), rs.getFloat("sgst"),
							rs.getFloat("igst"), rs.getFloat("stock"), rs.getFloat("cost_price"),
							rs.getFloat("Selling_price"),rs.getFloat("discValue"),rs.getFloat("taxable")));
				}

				
				table.setItems(data);
				collmItemsID.setCellValueFactory(new PropertyValueFactory<items, Integer>("itemId"));
				collmItemsID.setSortType(TableColumn.SortType.ASCENDING);
				collName.setCellValueFactory(new PropertyValueFactory<items, String>("name"));
				collHSNNumber.setCellValueFactory(new PropertyValueFactory<items, String>("hsnNo"));
				//collQtyType.setCellValueFactory(new PropertyValueFactory<items, String>("qtyType"));
				collUnit.setCellValueFactory(new PropertyValueFactory<items, String>("unit"));
				collGstRate.setCellValueFactory(new PropertyValueFactory<items, Float>("gstRate"));
				collCGST.setCellValueFactory(new PropertyValueFactory<items, Float>("cgst"));
				collSGST.setCellValueFactory(new PropertyValueFactory<items, Float>("sgst"));
				collIGST.setCellValueFactory(new PropertyValueFactory<items, Float>("igst"));
				collStock.setCellValueFactory(new PropertyValueFactory<items, Float>("stock"));
				collCostPrice.setCellValueFactory(new PropertyValueFactory<items, Float>("costPrice"));
				collSellingPrice.setCellValueFactory(new PropertyValueFactory<items, Float>("sellingPrice"));
				collDiscValue.setCellValueFactory(new PropertyValueFactory<items, Float>("discValue"));
				collTaxable.setCellValueFactory(new PropertyValueFactory<items, Float>("taxable"));
				
				stmt.close();
				rs.close();
		
	}

}
