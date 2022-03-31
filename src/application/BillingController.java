package application;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.scene.control.*;
import org.controlsfx.control.textfield.TextFields;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BillingController {
	public class items {
		private IntegerProperty sno;
		private IntegerProperty itemCode;
		private StringProperty itemName;
		private StringProperty hsn;
		private IntegerProperty quantity;
		//private StringProperty qtytype;
		private StringProperty unit;
		private FloatProperty price;
		private FloatProperty mrp;
		private FloatProperty discvalue;
		private FloatProperty taxable;
		private FloatProperty sgstRate;
		private FloatProperty sgstAmount;
		private FloatProperty cgstRate;
		private FloatProperty cgstAmount;
		private FloatProperty igstRate;
		private FloatProperty igstAmount;

		// private FloatProperty tax;
		private FloatProperty total;

		public items(Integer sno,Integer itemCode, String itemName, String hsn, Integer quantity,/*String qtytype,*/String unit, Float price,Float mrp,
		             Float discvalue,Float taxable,Float sgstRate, Float sgstAmount, Float cgstRate, Float cgstAmount, Float igstRate,
				     Float igstAmount,/* Float tax, */ Float total) {
			super();
			this.sno = new SimpleIntegerProperty(sno);
			this.itemCode = new SimpleIntegerProperty(itemCode);
			this.itemName = new SimpleStringProperty(itemName);
			this.hsn = new SimpleStringProperty(hsn);
			this.quantity = new SimpleIntegerProperty(quantity);
			//this.qtytype = new SimpleStringProperty(qtytype);
			this.unit = new SimpleStringProperty(unit);
			this.price = new SimpleFloatProperty(price);
			this.mrp = new SimpleFloatProperty(mrp);
			this.discvalue = new SimpleFloatProperty(discvalue);
			this.taxable = new SimpleFloatProperty(taxable);
			this.sgstRate = new SimpleFloatProperty(sgstRate);
			this.sgstAmount = new SimpleFloatProperty(sgstAmount);
			this.cgstRate = new SimpleFloatProperty(cgstRate);
			this.cgstAmount = new SimpleFloatProperty(cgstAmount);
			this.igstRate = new SimpleFloatProperty(igstAmount);
			this.igstAmount = new SimpleFloatProperty(igstRate);
			// this.tax = new SimpleFloatProperty(tax);
			this.total = new SimpleFloatProperty(total);
		}

		public items() {
			super();

		}

		public Float getSgstRate() {
			return sgstRate.get();
		}

		public void setSgstRate(Float sgstRate) {
			this.sgstRate.set(sgstRate);
		}

		public Float getSgstAmount() {
			return sgstAmount.get();
		}

		public void setSgstAmount(Float sgstAmount) {
			this.sgstAmount.set(sgstAmount);
		}

		public Float getCgstRate() {
			return cgstRate.get();
		}

		public void setCgstRate(Float cgstRate) {
			this.cgstRate.set(cgstRate);
		}

		public Float getCgstAmount() {
			return cgstAmount.get();
		}

		public void setCgstAmount(Float cgstAmount) {
			this.cgstAmount.set(cgstAmount);
		}

		public Float getIgstRate(){return igstRate.get();}
		public void setIgstRate(Float igstRate){this.igstRate.set(igstRate);}

		public Float getIgstAmount() {
			return igstAmount.get();
		}

		public void setIgstAmount(Float igstAmount) {
			this.igstAmount.set(igstAmount);
		}

		public Integer getSno(){return sno.get();}
		public void setSno(Integer sno){this.sno.set(sno);}

		public Integer getItemCode() {
			return itemCode.get();
		}

		public void setItemCode(Integer itemCode) {
			this.itemCode.set(itemCode);
		}

		public String getItemName() {
			return itemName.get();
		}

		public void setItemName(String itemName) {
			this.itemName.set(itemName);
		}

		public String getHsn() {
			return hsn.get();
		}

		public void setHsn(String hsn) {
			this.hsn.set(hsn);
		}

		public Integer getQuantity() {
			return quantity.getValue();
		}

		public void setQuantity(Integer quantity) {
			this.quantity.set(quantity);
		}

		/*public String getQtytype(){return qtytype.get();}
		public void setQtytype(String qtytype) {this.qtytype.set(qtytype);}*/

		public String getUnit(){return unit.get(); }
		public void setUnit(String unit){this.unit.set(unit);}

		public Float getPrice() {
			return price.get();
		}

		public void setPrice(Float price) {
			this.price.set(price);
		}

		public  Float getMrp(){return mrp.get();}
		public void setMrp(Float mrp){this.mrp.set(mrp);}

		public Float getDiscvalue(){return discvalue.get();}
		public void setDiscvalue(Float discvalue){this.discvalue.set(discvalue);}

		public Float getTaxable(){return taxable.get();}
		public void setTaxable(Float taxable){this.taxable.set(taxable);}

		/*
		 * public Float getGstRate() { return gstRate.get(); }
		 * 
		 * public void setGstRate(Float gstRate) { this.gstRate.set(gstRate); }
		 * 
		 * public Float getTax() { return tax.get(); }
		 * 
		 * public void setTax(Float tax) { this.tax.set(tax); }
		 */
		public Float getTotal() {
			return total.get();
		}

		public void setTotal(Float total) {
			this.total.set(total);
		}

	}

	// getting connections
	Connection conn = Connections.b2csDBConncetion();
	// ----field handles for billing pane---------
	// customer name and moblie field
	float totalBillableAmount = 0.0f;
	float totalTax = 0.0f;
	float totalCgst = 0f;
	float totalSgst = 0f;
	float totalIgst = 0f;
	float totalDiscvalue = 0f;
	float totalTaxable = 0f;
	float totalcess = 0.0f;
	float sgstRate = 0f;
	float cgstRate = 0f;
	float igstRate = 0f;
	String custname = "";
	String custMob = "";
	String custGstin = "";
	String custAddr = "";
	float cess = 0.0f;
	//int sno = 1;
	Float amountPaid = 0f;
	Float dueAmount = 0f;
	Float advanceAmount = 0f;

	Integer invoiceNo;
	String date = LocalDate.now().toString();

	/*@FXML
	ComboBox<String> modeOfBussiness = new ComboBox<>();*/
	@FXML
	TextField billingCustomerName = new TextField();
	@FXML
	TextField billingMoblie = new TextField();
	@FXML
	TextField billingcustGSTIn = new TextField();
	@FXML
	TextField billingcustAddr = new TextField();
	@FXML
	Label billingAdvanceBalance = new Label();
	@FXML
	Label billingDueBalance = new Label();
	@FXML
	TextField billingAmountPaid = new TextField();
	
	// items field for making bill
	@FXML
	TextField billingItemCode = new TextField();
	@FXML
	TextField billingItemName = new TextField();
	@FXML
	Label billingHSN = new Label();
	@FXML
	TextField billingQuantity = new TextField();
	/*@FXML
	Label billingQuantityLabel = new Label();*/
	@FXML
	Label billingUnit = new Label();
	@FXML
	Label billingPrice = new Label();
	@FXML
	Label billingMrp = new Label();
	@FXML
	Label billingDiscvalue = new Label();
	@FXML
	Label billingTaxable = new Label();
	@FXML
	Label billingCgst = new Label();
	@FXML
	Label billingSgst = new Label();
	@FXML
	Label billingIgst = new Label();
	// gstrate
	/*
	 * @FXML Label billingTax = new Label();
	 */

	@FXML
	Label billingTotalSgst = new Label();
	@FXML
	Label billingTotalCgst = new Label();
	@FXML
	Label billingTotalIgst = new Label();
	@FXML
	Label billingTotalDiscvalue = new Label();
	@FXML
	Label billingTotalTaxable = new Label();
	@FXML
	Label billingdueAmount = new Label();
	@FXML
	Label billingadvanceAmount = new Label();

	//@FXML
//	TextField billingIgst = new TextField();
	@FXML
	Label billingTotal = new Label();
	@FXML
	Button billingAddToTableButton = new Button();

	// field for grand total and save and print button
	@FXML
	Label billinGrandTotal = new Label();
	@FXML
	Button billinSaveAndPrintButton = new Button();
	// ----end of field handles for billing pane---------

	@FXML
	TableView<items> table = new TableView<>();
	@FXML
	TableColumn<items, Integer> collItemSNO = new TableColumn<>();
	@FXML
	TableColumn<items, Integer> collItemId = new TableColumn<>();
	@FXML
	TableColumn<items, String> collItemName = new TableColumn<>();
	@FXML
	TableColumn<items, String> collItemhsn = new TableColumn<>();
	@FXML
	TableColumn<items, String> collItemQty = new TableColumn<>();
	/*@FXML
	TableColumn<items, String> collItemQtytype = new TableColumn<>();*/
	@FXML
	TableColumn<items, String> collItemUnit = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collItemGstRate = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collItemPrice = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collItemMrp = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collItemDiscvalue = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collItemTaxable = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collSgstRate = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collSgstAmount = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collCgstRate = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collCgstAmount = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collIgstRate = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collIgstAmount = new TableColumn<>();
	/*
	 * @FXML TableColumn<items, Float> collItemTax = new TableColumn<>();
	 */
	@FXML
	TableColumn<items, Float> collItemTotal = new TableColumn<>();

	ObservableList<items> tableData = FXCollections.observableArrayList();
	String sql;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	@FXML
	public void initialize() throws SQLException {

		// drop down menu for mode of bussiness
		//String[] mode = { "Bussiness to Bussiness", "Bussiness to Customer(Large)", "Bussiness to Customer(Small)" };
		//modeOfBussiness.getItems().addAll(mode);
		// for fetching customer data form the primary key moblieNumber and the
		// related field
		ObservableList<String> data = FXCollections.observableArrayList();
		sql = "SELECT * FROM customer";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			data.add(rs.getString("customerCustomerName"));
		}

		TextFields.bindAutoCompletion(billingCustomerName, data);

		billingCustomerName.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (data.contains(billingCustomerName.getText().toString())) {
            							
					if (!newValue) {
						if (!billingCustomerName.getText().toString().equals("")) {
							System.out.println(billingCustomerName.getText().toString());

							sql = "SELECT * FROM customer WHERE customerCustomerName = '"
									+ billingCustomerName.getText().toString() + "'";
							try {
								rs = stmt.executeQuery(sql);
								rs.next();
								billingMoblie.setText(rs.getString("customerMobileNumber"));
								billingcustGSTIn.setText(rs.getString("customerGstinNumber"));
								billingcustAddr.setText(rs.getString("customerAdderss"));
								//billingAdvanceBalance.setText(String.valueOf(rs.getFloat("advanceBalance")));
								//advanceAmount = rs.getFloat("advanceBalance");
								billingDueBalance.setText(String.valueOf(rs.getFloat("dueBalance")));
								//dueAmount = rs.getFloat("dueBalance");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} else {
						System.out.println("else part");
					}
				} else {// else part - here we will add the customer to the
						// customer table
						// we will not add here bcoz if we add here as the user
						// step out odf the text field
						// it will add the customer data but we want thid=s
						// customer to be added at the time
						// of billing

				}
			}
		});

		// customer name data
		ObservableList<String> dataCustName = FXCollections.observableArrayList();
		sql = "SELECT * FROM customer";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dataCustName.add(rs.getString("customerMobileNumber"));
		}

		TextFields.bindAutoCompletion(billingMoblie, dataCustName);

		billingMoblie.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (dataCustName.contains(billingMoblie.getText().toString())) {// this

					if (!newValue) {
						if (!billingMoblie.getText().toString().equals("")) {
							System.out.println(billingMoblie.getText().toString());

							sql = "SELECT * FROM customer WHERE customerMobileNumber = '"
									+ billingMoblie.getText().toString() + "'";
							try {
								rs = stmt.executeQuery(sql);
								rs.next();
								billingCustomerName.setText(String.valueOf(rs.getString("customerCustomerName")));
								billingcustGSTIn.setText(rs.getString("customerGstinNumber"));
								billingcustAddr.setText(rs.getString("customerAdderss"));
								//billingAdvanceBalance.setText(String.valueOf(rs.getFloat("advanceBalance")));
								//advanceAmount = rs.getFloat("advanceBalance");
								billingDueBalance.setText(String.valueOf(rs.getFloat("dueBalance")));
								//dueAmount = rs.getFloat("dueBalance");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} else {
						System.out.println("else part");
					}
				} else {// else part - here we will add the customer to the
						// customer table
						// we will not add here bcoz if we add here as the user
						// step out odf the text field
						// it will add the customer data but we want thid=s
						// customer to be added at the time
						// of billing

				}
			}
		});

		// item id data
		ObservableList<Integer> idData = FXCollections.observableArrayList();
		sql = "SELECT * FROM items";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			idData.add(rs.getInt("item_id"));
		}
		TextFields.bindAutoCompletion(billingItemCode, idData);
		billingItemCode.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldVal, Boolean newVal) {
				if (!newVal && !billingItemCode.getText().toString().equals("")) {
					String subSQL = "SELECT * FROM items WHERE item_id = " + billingItemCode.getText().toString();
					System.out.println("sds" + billingItemCode.getText().toString());
					try {
						// float temp = rs.getFloat("gst_rate");

						rs = stmt.executeQuery(subSQL);
						rs.next();
						float temp = rs.getFloat("gst_rate");
						cgstRate = temp / 2;
						sgstRate = temp / 2;
						igstRate = 0;
						//sno = 1;
						billingItemName.setText(rs.getString("item_name"));
						// billingItemName.setEditable(false);
						billingHSN.setText(rs.getString("item_hsn"));
						billingUnit.setText(rs.getString("Unit"));
						//billingQuantityLabel.setText(rs.getString("qty_type"));
						billingQuantity.focusedProperty().addListener(new ChangeListener<Boolean>() {

							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
									Boolean newValue) {
								// TODO Auto-generated method stub
								if (!newValue) {
									try {
										//billingQuantityLabel.setText(rs.getString("qty_type").substring(0, 3));
										billingPrice.setText(String.valueOf(rs.getFloat("cost_price")));
										billingMrp.setText(String.valueOf(rs.getFloat("Selling_price")));
										billingDiscvalue.setText(String.valueOf(rs.getFloat("DiscValue")
										        *Float.parseFloat(billingQuantity.getText().toString())));
										billingTaxable.setText(String.valueOf(rs.getFloat("Taxable")
										        *Float.parseFloat(billingQuantity.getText().toString())));
										billingSgst.setText(String.valueOf(rs.getFloat("sgst")
												* Float.parseFloat(billingQuantity.getText().toString())));

										billingCgst.setText(String.valueOf(rs.getFloat("cgst")
												* Float.parseFloat(billingQuantity.getText().toString())));
										billingIgst.setText(String.valueOf(rs.getFloat("igst")));
										/*
										 * billingTax.setText(
										 * String.valueOf(Float.parseFloat(
										 * billingQuantity.getText().toString())
										 * (rs.getFloat("cgst") +
										 * rs.getFloat("sgst"))));
										 */
										billingTotal.setText(String.valueOf((Float.parseFloat(billingPrice.getText().toString())
														* Float.parseFloat(billingQuantity.getText().toString()))));
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							}
						});
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		// item name data
		ObservableList<String> itemNameData = FXCollections.observableArrayList();
		// generating the itemName data list
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			itemNameData.add(rs.getString("item_name"));
		}
		TextFields.bindAutoCompletion(billingItemName, itemNameData);
		billingItemName.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				// TODO Auto-generated method stub
				if (!arg2 && !billingItemName.getText().toString().equals("")) {
					String subSQL = "SELECT * FROM items WHERE item_name = \'" + billingItemName.getText().toString()
							+ "\'";
					try {
						// float temp = rs.getFloat("gst_rate");

						rs = stmt.executeQuery(subSQL);
						rs.next();
						float temp = rs.getFloat("gst_rate");
						cgstRate = temp / 2;
						sgstRate = temp / 2;
						igstRate = 0;
						//sno = 1;
						billingItemCode.setText(String.valueOf(rs.getInt("item_id")));
						billingItemName.setText(rs.getString("item_name"));
						// billingItemName.setEditable(false);
						billingHSN.setText(rs.getString("item_hsn"));
						//billingQuantityLabel.setText(rs.getString("qty_type"));
						billingUnit.setText(rs.getString("unit"));

						billingQuantity.focusedProperty().addListener(new ChangeListener<Boolean>() {

							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
									Boolean newValue) {
								// TODO Auto-generated method stub
								if (!newValue) {
									try {
										//billingQuantityLabel.setText(rs.getString("qty_type").substring(0, 3));
										billingPrice.setText(String.valueOf(rs.getFloat("cost_price")));
										billingMrp.setText(String.valueOf(rs.getFloat("Selling_price")));
										billingDiscvalue.setText(String.valueOf(rs.getFloat("discvalue")
										        *Float.parseFloat(billingQuantity.getText().toString())));
										billingTaxable.setText(String.valueOf(rs.getFloat("taxable")
										        *Float.parseFloat(billingQuantity.getText().toString())));
										billingSgst.setText(String.valueOf(rs.getFloat("sgst")
												* Float.parseFloat(billingQuantity.getText().toString())));

										billingCgst.setText(String.valueOf(rs.getFloat("cgst")
												* Float.parseFloat(billingQuantity.getText().toString())));
										billingIgst.setText(String.valueOf(rs.getFloat("igst")));
										/*
										 * billingTax.setText(
										 * String.valueOf(Float.parseFloat(
										 * billingQuantity.getText().toString())
										 * (rs.getFloat("cgst") +
										 * rs.getFloat("sgst"))));
										 */
										billingTotal.setText(
												String.valueOf((Float.parseFloat(billingPrice.getText().toString())
														* Float.parseFloat(billingQuantity.getText().toString()))));
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							}
						});
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		//setting listener for amount paid
		billingAmountPaid.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				try {
					amountPaid = (Float.parseFloat(billingAmountPaid.getText().toString()));

				//	        totalBillableAmount = totalBillableAmount + dueAmount;
					// TODO Auto-generated method stub

					/*if (!(Float.parseFloat(billingAmountPaid.getText().toString()) == 0f) ||
							!(Float.parseFloat(billingAmountPaid.getText().toString()) == 0.0f)) {*/

						if ((totalBillableAmount == amountPaid) || (totalBillableAmount != amountPaid)) {
							System.out.println("Amount paid: " + amountPaid);
							System.out.println("total billable amount:" + totalBillableAmount);
							if ((amountPaid - totalBillableAmount) < 0) {
								dueAmount = totalBillableAmount - amountPaid;
								billingdueAmount.setText(String.valueOf(dueAmount));
								System.out.println("due Amount: " + dueAmount + "\n");
							}
							if ((amountPaid - totalBillableAmount) == 0) {
								totalBillableAmount = amountPaid;
								dueAmount = totalBillableAmount - amountPaid;
								advanceAmount = totalBillableAmount - amountPaid;
								billingdueAmount.setText(String.valueOf("0"));
								System.out.println("advance amount:" + amountPaid);
							}
							if ((amountPaid - totalBillableAmount) > 0) {
								billingdueAmount.setText(String.valueOf("0"));
								dueAmount = 0f;
								advanceAmount = amountPaid - totalBillableAmount;
							}
							if (amountPaid == 0) {
								dueAmount = totalBillableAmount;
								System.out.println("d="+dueAmount);
							}
							System.out.println(dueAmount + "/" + amountPaid + "\n");
                                                  
						}

				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                              

			}
                        

		});
               if(billingAmountPaid.isFocused()) {
               billinSaveAndPrintButton.requestFocus();
                } 

	}
       

	public void addItemToTable(ActionEvent event) throws SQLException {
		//subtractItemFromStock();
			int sno = 1;
			sno = sno + table.getItems().size();
			System.out.println("Row:" + sno);

			tableData.add(new items(sno, Integer.parseInt(billingItemCode.getText().toString()),
					billingItemName.getText().toString(), billingHSN.getText().toString(),
					Integer.parseInt(billingQuantity.getText().toString()), billingUnit.getText().toString(),
					Float.parseFloat(billingPrice.getText().toString()), Float.parseFloat(billingMrp.getText().toString()),
					Float.parseFloat(billingDiscvalue.getText().toString()), Float.parseFloat(billingTaxable.getText().toString()),
					sgstRate, Float.parseFloat(billingSgst.getText().toString()), cgstRate, Float.parseFloat(billingCgst.getText().toString()),
					igstRate, Float.parseFloat(billingIgst.getText().toString()),
					Float.parseFloat(billingTotal.getText().toString())));

		table.setItems(tableData);
		collItemSNO.setCellValueFactory(new PropertyValueFactory<items, Integer>("sno"));
		collItemId.setCellValueFactory(new PropertyValueFactory<items, Integer>("itemCode"));
		collItemName.setCellValueFactory(new PropertyValueFactory<items, String>("itemName"));
		collItemhsn.setCellValueFactory(new PropertyValueFactory<items, String>("hsn"));
		collItemQty.setCellValueFactory(new PropertyValueFactory<items, String>("quantity"));
		//collItemQtytype.setCellValueFactory(new PropertyValueFactory<items, String>("qtytype"));
		collItemUnit.setCellValueFactory(new PropertyValueFactory<items, String>("unit"));
		collIgstAmount.setCellValueFactory(new PropertyValueFactory<items, Float>("igstRate"));
		collIgstRate.setCellValueFactory(new PropertyValueFactory<items, Float>("igstAmount"));
		collSgstRate.setCellValueFactory(new PropertyValueFactory<items, Float>("sgstRate"));
		collSgstAmount.setCellValueFactory(new PropertyValueFactory<items, Float>("sgstAmount"));
		collCgstRate.setCellValueFactory(new PropertyValueFactory<items, Float>("cgstRate"));
		collCgstAmount.setCellValueFactory(new PropertyValueFactory<items, Float>("cgstAmount"));
		collItemPrice.setCellValueFactory(new PropertyValueFactory<items, Float>("price"));
		collItemMrp.setCellValueFactory(new PropertyValueFactory<items, Float>("mrp"));
		collItemDiscvalue.setCellValueFactory(new PropertyValueFactory<items, Float>("discvalue"));
		collItemTaxable.setCellValueFactory(new PropertyValueFactory<items, Float>("taxable"));
		collItemTotal.setCellValueFactory(new PropertyValueFactory<items, Float>("total"));

		/// Updating the totalBillableAmount
		totalBillableAmount = totalBillableAmount + Float.parseFloat(billingTotal.getText().toString());
		totalSgst = totalSgst + Float.parseFloat(billingSgst.getText().toString());
		totalCgst = totalCgst + Float.parseFloat(billingCgst.getText().toString());
		totalIgst = totalIgst + Float.parseFloat(billingIgst.getText().toString());
		totalDiscvalue = totalDiscvalue + Float.parseFloat(billingDiscvalue.getText().toString());
		totalTaxable = totalTaxable + Float.parseFloat(billingTaxable.getText().toString());

		//to round up the total billable amount
		BigDecimal bd = new BigDecimal(Float.toString(totalBillableAmount));
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		totalBillableAmount = bd.floatValue();
		//to round up the total billable amount

		billinGrandTotal.setText("Rs. " + String.valueOf(totalBillableAmount));
		billingTotalSgst.setText("Rs. " + String.valueOf(totalSgst));
		billingTotalCgst.setText("Rs. " + String.valueOf(totalCgst));
		billingTotalIgst.setText("Rs. " + String.valueOf(totalIgst));
		billingTotalDiscvalue.setText("Rs. " + String.valueOf(totalDiscvalue));
		billingTotalTaxable.setText("Rs. " + String.valueOf(totalTaxable));

		// totalTax = totalTax +
		// Float.parseFloat(billingTax.getText().toString());
		// totalcess = totalcess +
		// Float.parseFloat(billingTax.getText().toString());
		billingIgst.setText("0.0");// this is to reset the textField of IGST for net item add
		//clearing the field items
		billingItemCode.clear();
		billingItemName.clear();
		billingQuantity.clear();
		//billingQuantityLabel.setText("QtyType");
		billingUnit.setText("Unit");
		billingHSN.setText("HSN");
		billingPrice.setText("Rate");
		billingMrp.setText("MRP");
		billingDiscvalue.setText("Discvalue");
		billingTaxable.setText("Taxable");
		billingSgst.setText("SGST");
		billingCgst.setText("CGST");
		billingIgst.setText("IGST");
		billingTotal.setText("TOTAL");
		table.setEditable(true);
                
                  if(billingAddToTableButton.isFocused()) {
	                  billingItemName.requestFocus();
                    } 
                
	}

	public void saveAndPrintBill(ActionEvent event) throws SQLException {
		/*
		 * if(modeOfBussiness.getValue() == null){ System.out.println("empty");
		 * System.exit(0); }else System.exit(0);
		 */

		totalTax = totalSgst + totalCgst + totalIgst;
		if (!(totalBillableAmount == 0000)) {
			String sql1;
			String sql2;
		/*	
			sql1 = "INSERT INTO selling_invoice (invoice_date,customer_name,cutomer_number,taxable_value,cess,amount) VALUES ('"
					+ LocalDate.now().toString() + "','" + billingCustomerName.getText().toString() + "','"
					+ billingMoblie.getText().toString() + "','" + totalTax + "','" + totalcess + "','"
					+ totalBillableAmount + "')";
	*/		
			custname = billingCustomerName.getText().toString();
			custMob = billingMoblie.getText().toString();
			custGstin = billingcustGSTIn.getText().toString();
			custAddr = billingcustAddr.getText().toString();
			
			
			sql1 = "INSERT INTO selling_invoice (invoice_date,customer_name,cutomer_number,customer_gstin,billing_addr," +
					"total_sgst,total_cgst,total_igst,total_Discvalue,total_Taxable,due_Amount,amount_paid,amount)" +
					" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, LocalDate.now().toString());
			pstmt.setString(2, custname);
			pstmt.setString(3, custMob);
			pstmt.setString(4, custGstin);
			pstmt.setString(5, custAddr);
			pstmt.setFloat(6, totalSgst);
			pstmt.setFloat(7, totalCgst);
			pstmt.setFloat(8, totalIgst);
			pstmt.setFloat(9, totalDiscvalue);
			pstmt.setFloat(10, totalTaxable);
			pstmt.setFloat(11, dueAmount);
			pstmt.setFloat(12, amountPaid);
			pstmt.setFloat(13, totalBillableAmount);
			System.out.println(pstmt.toString());
			pstmt.executeUpdate();

			System.out.println("first stement");
			// now getting the generated invoice number
			sql1 = "SELECT invoice_number FROM selling_invoice ORDER BY invoice_number DESC LIMIT 1";
			rs = stmt.executeQuery(sql1);
			rs.next();
			int invoiceNo = rs.getInt("invoice_number");
			//for getting the formateed date from database
			sql1 = "SELECT DATE_FORMAT(invoice_date, '%d/%m/%Y') FROM selling_invoice WHERE invoice_number = " + invoiceNo;
			System.out.println(sql1);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			rs.next();
			String dateToSend = rs.getString(1);

			sql2 = "INSERT INTO selling_invoice_detail (invoice_number, item_code,s_no, item_name, hsn, quantity, unit ," +
					" price , mrp , discvalue , taxable , cgst_rate, cgst_amount, sgst_rate, sgst_amount, igst_rate , igst_amount ," +
					" total) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql2);
			for (items x : tableData) {

				int code = x.getItemCode();
				int sno = x.getSno();
				String itemname = x.getItemName();
				String hsn = x.getHsn();
				Integer qty = x.getQuantity();
				//String qtytype = x.getQtytype();
				String unit = x.getUnit();
				float price = x.getPrice();
				float mrp = x.getMrp();
				float discvalue = x.getDiscvalue();
				float taxable = x.getTaxable();
				float cgst_rate = x.getCgstRate();
				float cgst_amount = x.getCgstAmount();
				float sgst_rate = x.getSgstRate();
				float sgst_amount = x.getSgstAmount();
				float igst_rate = x.getIgstRate();
				float igst_amount = x.getIgstAmount();
				float total = x.getTotal();

				pstmt.setInt(1, invoiceNo);
				pstmt.setInt(2, code);
				pstmt.setInt(3, sno);
				pstmt.setString(4, itemname);
				pstmt.setString(5, hsn);
				pstmt.setInt(6, qty);
				//pstmt.setString(6, qtytype);
				pstmt.setString(7, unit);
				pstmt.setFloat(8, price);
				pstmt.setFloat(9, mrp);
				pstmt.setFloat(10, discvalue);
				pstmt.setFloat(11, taxable);
				pstmt.setFloat(12, cgst_rate);
				pstmt.setFloat(13, cgst_amount);
				pstmt.setFloat(14, sgst_rate);
				pstmt.setFloat(15, sgst_amount);
				pstmt.setFloat(16, igst_rate);
				pstmt.setFloat(17, igst_amount);
				pstmt.setFloat(18, total);
				pstmt.executeUpdate();
			}

			// opening new window for print
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("print.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				((printBillController) fxmlLoader.getController()).setDataFromBillingController(tableData,
						String.valueOf(totalBillableAmount), String.valueOf(invoiceNo), dateToSend,
						billingCustomerName.getText().toString(), billingMoblie.getText().toString(),totalDiscvalue,totalTaxable,totalSgst,
						totalCgst, totalIgst, custGstin , custAddr);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		cleaning();

		int tableRow = table.getItems().size();
		System.out.println("Value:"+ tableRow);

		for(int Row =0;Row <= tableRow-1;Row++){
			System.out.println("Row:"+Row);
			String qtyTmp = String.valueOf(table.getItems().get(Row).getQuantity());
			Integer id = table.getItems().get(Row).getItemCode();
			System.out.println(qtyTmp);
			String x = qtyTmp;
			String numStr = "";
			float num;
			for(int i=0;i<x.length();i++){
				char charCheck = x.charAt(i);
				if(Character.isDigit(charCheck)){
					numStr+=charCheck;
				}
			}
			num=Float.parseFloat(numStr);
			System.out.println("qty ="+num);

			sql.charAt(0);
			float qtyFromDatabase = 0f;
			String sql1 = "SELECT stock FROM items WHERE item_id = " + id;
			rs = stmt.executeQuery(sql1);
			rs.next();
			qtyFromDatabase = rs.getFloat("stock");

			num = qtyFromDatabase - num;

			sql1 = "UPDATE items SET stock = " + num +" WHERE item_id = "+ id;
			stmt.execute(sql1);
		}

		//Updating Customer DueAmount....
		String name = billingCustomerName.getText().toString();
		String Mobile = billingMoblie.getText().toString();
		float temdue = Float.parseFloat(billingdueAmount.getText().toString());
		float sqldue = 0f;
		String sql1 = "SELECT dueBalance FROM customer WHERE customerCustomerName='"+name+"' AND customerMobileNumber ='" + Mobile+"'";
		rs = stmt.executeQuery(sql1);
		rs.next();
		sqldue = rs.getFloat("dueBalance");
		sqldue = sqldue + temdue;
		sql1 = "UPDATE customer SET dueBalance = " + sqldue +" WHERE customerCustomerName='"+name+"' AND customerMobileNumber ='" + Mobile+"'";
		stmt.execute(sql1);
	}
	private void cleaning() {
		billinGrandTotal.setText("Rs. 0000");
		billingTotalTaxable.setText("0");
		billingTotalDiscvalue.setText("0");
		billingTotalCgst.setText("0");
		billingTotalSgst.setText("0");
		billingTotalIgst.setText("0");


	}
	public void deleteItemFromTable() throws SQLException {
		//reverseAddItemFromStock();
		items del = table.getSelectionModel().getSelectedItem();
		int row = table.getSelectionModel().getSelectedItem().getSno();
		String col = String.valueOf(table.getColumns().get(0));
		System.out.println("del:"+row);
		/// Updating the totalBillableAmount
		totalBillableAmount = totalBillableAmount - del.getTotal();
		billinGrandTotal.setText("Rs. " + String.valueOf(totalBillableAmount));
		
		totalSgst = totalSgst - del.getSgstAmount();
		totalCgst = totalCgst - del.getCgstAmount();
		totalIgst = totalIgst - del.getIgstAmount();
		totalDiscvalue = totalTaxable - del.getDiscvalue();
		totalTaxable = totalTaxable - del.getTaxable();
		billingTotalSgst.setText("Rs. " + String.valueOf(totalSgst));
		billingTotalCgst.setText("Rs. " + String.valueOf(totalCgst));
		billingTotalIgst.setText("Rs. " + String.valueOf(totalIgst));
		billingTotalDiscvalue.setText("Rs. " + String.valueOf(totalDiscvalue));
		billingTotalTaxable.setText("Rs. " + String.valueOf(totalTaxable));

		tableData.remove(del);
		table.refresh();
		/*int row = mtt.getSelectedRows()[0];// returns row position(Here mtt is  name of table)
          model.removeRow(row);
         for(int index=row ;index<model.getRowCount();index++){
        model.setValueAt(index+1, index, 0);*/
	}

	/*public void subtractItemFromStock() throws SQLException{
		String sql1 = null;
		float qtyTmp = Float.valueOf(billingQuantity.getText().toString());
		float qtyFromDatabase = 0f;
		int id = Integer.valueOf(billingItemCode.getText().toString());
		
		sql1 = "SELECT stock FROM items WHERE item_id = " + id  ;
		
		rs = stmt.executeQuery(sql1);
		rs.next();
		qtyFromDatabase = rs.getFloat("stock");
		
		qtyTmp = qtyFromDatabase - qtyTmp;
		//System.out.println("qtytmp final : " + qtyTmp);
		sql1 = "UPDATE items SET stock = " + qtyTmp +" WHERE item_id = "+ id;
		
		stmt.execute(sql1);
		
	}*/

	/*public void reverseAddItemFromStock() throws SQLException{
	
		String sql1;
		String qtyTmp =(table.getSelectionModel().getSelectedItem().getQuantity());
		System.out.println(qtyTmp);
		String x = qtyTmp;
		String numStr = "";
		float num;
		for(int i=0;i<x.length();i++){
			char charCheck = x.charAt(i);
			if(Character.isDigit(charCheck)){
				numStr+=charCheck;
			}
		}
		num=Float.parseFloat(numStr);
		System.out.println(num);
		Integer id = table.getSelectionModel().getSelectedItem().getItemCode();
		sql.charAt(0);
		//float qtyTmp1 = Float.valueOf(billingQuantity.getText().toString());
		float qtyFromDatabase = 0f;
		//float qtyTmp = Float.parseFloat(temp);
		//int id = Integer.valueOf(billingItemCode.getText().toString());
		sql1 = "SELECT stock FROM items WHERE item_id = " + id ;
		rs = stmt.executeQuery(sql1);
		rs.next();
		qtyFromDatabase = rs.getFloat("stock");

		num = qtyFromDatabase + num;
		
		sql1 = "UPDATE items SET stock = " + num +" WHERE item_id = "+ id;
		stmt.execute(sql1);
	}*/
	/*String sql="update items set quantity=quantity-? WHERE itemcode=?";*/

}
