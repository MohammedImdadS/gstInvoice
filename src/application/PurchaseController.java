package application;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import application.ItemsController.items;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import javax.management.loading.MLetContent;

public class PurchaseController {
	Connection conn = Connections.b2csDBConncetion();
	// Top level textField field

	public class items {
		private IntegerProperty sno;
		private IntegerProperty itemCode;
		private StringProperty itemName;
		private StringProperty hsn;
		private IntegerProperty quantity;
		//private StringProperty qtytype;
		private StringProperty unit;
		private FloatProperty percent;
		private FloatProperty gstAmount;
		private FloatProperty price;
		private FloatProperty mrp;
		private FloatProperty discvalue;
		private FloatProperty taxable;
		private FloatProperty cgst_rate;
		private FloatProperty cgst_amount;
		private FloatProperty sgst_rate;
		private FloatProperty sgst_amount;
		private FloatProperty igst_rate;
		private FloatProperty igst_amount;
		private FloatProperty total;

		public items(Integer sno,Integer itemCode, String itemName, String hsn, Integer quantity,/*String qtytype,*/String unit,Float percent,/*Float gstAmount,*/
		             Float price,Float mrp,Float discvalue,Float taxable,Float cgst_rate,Float cgst_amount,Float sgst_rate,Float sgst_amount,
		             Float igst_rate, Float igst_amount, Float total) {
			super();
			this.sno = new SimpleIntegerProperty(sno);
			this.itemCode = new SimpleIntegerProperty(itemCode);
			this.itemName = new SimpleStringProperty(itemName);
			this.hsn = new SimpleStringProperty(hsn);
			this.quantity = new SimpleIntegerProperty(quantity);
			//this.qtytype = new SimpleStringProperty(qtytype);
			this.unit = new SimpleStringProperty(unit);
			this.percent = new SimpleFloatProperty(percent);
			//this.gstAmount = new SimpleFloatProperty(gstAmount);
			this.price = new SimpleFloatProperty(price);
			this.mrp = new SimpleFloatProperty(mrp);
			this.discvalue = new SimpleFloatProperty(discvalue);
			this.taxable = new SimpleFloatProperty(taxable);
			this.cgst_rate = new SimpleFloatProperty(cgst_rate);
			this.cgst_amount = new SimpleFloatProperty(cgst_amount);
			this.sgst_rate = new SimpleFloatProperty(sgst_rate);
			this.sgst_amount = new SimpleFloatProperty(sgst_amount);
			this.igst_rate = new SimpleFloatProperty(igst_rate);
			this.igst_amount = new SimpleFloatProperty(igst_amount);
			this.total = new SimpleFloatProperty(total);
		}

		public items() {
			super();

		}

		public Integer getSno(){return sno.get();}
		public void setSno(Integer sno) {this.sno.set(sno);}

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

		/*public String getQtytype() {
			return qtytype.get();
		}

		public void setQtytype(String qtytype) {
			this.qtytype.set(qtytype);
		}*/

		public String getUnit() {
			return unit.get();
		}
		public void setUnit(String unit) {
			this.unit.set(unit);
		}

		public Float getPercent(){return percent.floatValue();}
		public void setPercent(Float percent){this.percent.set(percent);}

		/*public Float getgstAmount() {
			return gstAmount.floatValue();
		}
		public void setgstAmount(Float gstAmount) {
			this.gstAmount.set(gstAmount);
		}*/

		public Float getPrice() {
			return price.floatValue();
		}
		public void setPrice(Float price) {
			this.price.set(price);
		}

		public Float getMrp(){return mrp.floatValue();}
		public void setMrp(Float mrp) {
			this.mrp.set(mrp);
		}

		public Float getDiscvalue(){return discvalue.floatValue();}
		public void setDiscvalue(Float discvalue) {
			this.discvalue.set(discvalue);
		}

		public Float getTaxable(){return taxable.floatValue();}
		public void setTaxable(Float taxable) {
			this.taxable.set(taxable);
		}

		public Float getCgst_rate(){return cgst_rate.floatValue();}
		public void setCgst_rate(Float cgst_rate) { this.cgst_rate.set(cgst_rate); }

		public Float getCgst_amount(){return cgst_amount.floatValue();}
		public void setCgst_amount(Float cgst_amount) { this.cgst_amount.set(cgst_amount);}

		public Float getSgst_rate(){return sgst_rate.floatValue();}
		public void setSgst_rate(Float sgst_rate){this.sgst_rate.set(sgst_rate);}

		public Float getSgst_amount() { return sgst_amount.floatValue();}
		public void setSgst_amount(Float sgst_amount) { this.sgst_amount.set(sgst_amount);}

		public Float getIgst_rate(){return igst_rate.floatValue();}
		public  void setIgst_rate(Float igst_rate){this.igst_rate.set(igst_rate);}

		public Float getIgst_amount(){return igst_amount.floatValue();}
		public void setIgst_amount(Float igst_amount) {
			this.igst_amount.set(igst_amount);
		}

		public Float getTotal() {
			return total.floatValue();
		}
		public void setTotal(Float total) {
			this.total.set(total);
		}

	}

	int sno = 0;
	float gandTotal = 0.0f;
	float cgstRate = 0.0f;
	float sgstRate = 0.0f;
	float igstRate = 0.0f;
	float totalTax = 0.0f;
	float totalCgst = 0f;
	float totalSgst = 0f;
	float totalIgst = 0f;
	float totalDiscvalue = 0f;
	float totalTaxable = 0f;
	Float amountPaid = 0f;
	Float dueAmount = 0f;
	Float advanceAmount = 0f;



	@FXML
	TextField purchaseDealerName = new TextField();
	@FXML
	TextField purchaseGSTINNumber = new TextField();
	@FXML
	TextField purchaseInvoiceNumber = new TextField();
	@FXML
	TextField purchaseDealerAddress = new TextField();
	@FXML
	TextField purchaseDealerContact = new TextField();
	@FXML
	DatePicker datePicker = new DatePicker();
	

	@FXML
	TextField itemCode = new TextField();
	@FXML
	TextField itemName = new TextField();
	@FXML
	Label hsn = new Label();
	@FXML
	TextField quantity = new TextField();
	/*@FXML
	ComboBox<String> qtyChoose = new ComboBox<>();*/
	@FXML
	ComboBox<String> unit = new ComboBox<>();
	@FXML
	TextField gstAmount = new TextField();
	float tempGST = 0;
	@FXML
	TextField percentage = new TextField();
	float tempPercentage = 0;
	@FXML
	Label price = new Label();
	float tempPrice = 0;
	@FXML
	Label mrp = new Label();
	float tempMrp = 0;
	@FXML
	Label discvalue = new Label();
	float tempDiscvalue = 0;
	@FXML
	Label taxable = new Label();
	float tempTaxable = 0;
	@FXML
	Label cgstAmount = new Label();
	float tempCgst_Amount = 0;
	@FXML
	Label sgstAmount = new Label();
	float tempSgst_Amount = 0;
	@FXML
	TextField igstAmount = new TextField();
	float tempIgst_Amount = 0;
	@FXML
	Label total = new Label();
	float tempTotal = 0;

	// END of top level field
	@FXML
	TextField DueAmount = new TextField();
	@FXML
	TextField PaidAmount = new TextField();
	@FXML
	Label PurchasetotalSGST = new Label();
	@FXML
	Label PurchasetotalCGST = new Label();
	@FXML
	Label PurchasetotalIGST = new Label();
	@FXML
	Label PurchasetotalDiscvalue = new Label();
	@FXML
	Label PurchasetotalTaxable = new Label();
        @FXML
        Button AddButton = new Button();
        @FXML
        Button SaveButton = new Button();

	@FXML
	Label purchaseGrandTotal = new Label();
	Button purchareButton = new Button();

	// tabel and collmun datafiled
	@FXML
	TableView<items> table = new TableView<>();
	@FXML
	TableColumn<items, Integer> collSNO = new TableColumn<>();
	@FXML
	TableColumn<items, Integer> collmItemCode = new TableColumn<>();
	@FXML
	TableColumn<items, String> collName = new TableColumn<>();
	@FXML
	TableColumn<items, String> collHSNNumber = new TableColumn<>();
	@FXML
	TableColumn<items, String> collQtyType = new TableColumn<>();
	@FXML
	TableColumn<items, String> collUnit = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collPercent = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collPrice = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collMrp = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collDiscValue = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collTaxable = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collgst = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collCgstRate = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collCgstAmount = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collSgstRate = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collSgstAmount = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collIgstRate = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collIgstAmount = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collTotal = new TableColumn<>();

	ObservableList<items> tableData = FXCollections.observableArrayList();

	String sql;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	@FXML
	public void initialize() throws SQLException {

//   date format change
		
		datePicker.setConverter(new StringConverter<LocalDate>()
		{
		    private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");

		    @Override
		    public String toString(LocalDate localDate)
		    {
		        if(localDate==null)
		            return "";
		        return dateTimeFormatter.format(localDate);
		    }

		    @Override
		    public LocalDate fromString(String dateString)
		    {
		        if(dateString==null || dateString.trim().isEmpty())
		        {
		            return null;
		        }
		        return LocalDate.parse(dateString,dateTimeFormatter);
		    }
		});

		// Array of quantity
		/*String[] qty = { "Half-Ltr", "One-Ltr", "Four-Ltr","Novelty", "Bulk", "1/2-ltr", "1-Ltr",
				"4-Ltr" };
		qtyChoose.getItems().addAll(qty);*/
		String[] units = {"Box","Pieces"};
		unit.getItems().addAll(units);


		// dealer details
		ObservableList<String> dealerNameData = FXCollections.observableArrayList();
		sql = "SELECT * FROM dealer";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dealerNameData.add(rs.getString("dealer_company_name"));
		}
		TextFields.bindAutoCompletion(purchaseDealerName, dealerNameData);
		purchaseDealerName.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if(dealerNameData.contains(purchaseDealerName.getText().toString())) {
					if (!newValue) {
						if (!purchaseDealerName.getText().toString().equals("")) {
							System.out.println(purchaseDealerName.getText().toString());
							sql = "SELECT * FROM dealer WHERE dealer_company_name = '"
									+ purchaseDealerName.getText().toString() + "'";
							try {
						      /*String subSQL = "SELECT dealer_company_gstn FROM dealer WHERE dealer_company_name = '"
								+ purchaseDealerName.getText().toString() + "'";*/
								//stmt = conn.createStatement();
								rs = stmt.executeQuery(sql);
								rs.next();
								purchaseGSTINNumber.setText(rs.getString("dealer_company_gstn"));
								purchaseDealerAddress.setText(rs.getString("dealer_company_addr"));
								purchaseDealerContact.setText(rs.getString("dealer_company_contact"));

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}

			}
		});

		ObservableList<String> dealerGstinData = FXCollections.observableArrayList();
		sql = "SELECT * FROM dealer";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dealerGstinData.add(rs.getString("dealer_company_gstn"));
		}
		TextFields.bindAutoCompletion(purchaseGSTINNumber, dealerGstinData);
		//System.out.println(" " + dealerNameData.get(1));
		purchaseGSTINNumber.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if (dealerGstinData.contains(purchaseGSTINNumber.getText().toString())) {
					if (!newValue) {
						if (!purchaseGSTINNumber.getText().toString().equals("")) {
							System.out.println(purchaseGSTINNumber.getText().toString());
							sql = "SELECT * FROM dealer WHERE dealer_company_gstn = '"
									+ purchaseGSTINNumber.getText().toString() + "'";
							try {
								rs = stmt.executeQuery(sql);
								rs.next();
								purchaseDealerName.setText(rs.getString("dealer_company_name"));
								purchaseDealerAddress.setText(rs.getString("dealer_company_addr"));
								purchaseDealerContact.setText(rs.getString("dealer_company_contact"));
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		});

		// seting the data for item code //items detalis ///
		ObservableList<Integer> idData = FXCollections.observableArrayList();
		sql = "SELECT * FROM items";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			idData.add(rs.getInt("item_id"));
		}
		TextFields.bindAutoCompletion(itemCode, idData);
		itemCode.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldVal, Boolean newVal) {
				if (!newVal && !itemCode.getText().toString().equals("")) {
					String subSQL = "SELECT * FROM items WHERE item_id = " + itemCode.getText().toString();
					try {
						rs = stmt.executeQuery(subSQL);
						rs.next();
						itemName.setText(rs.getString("item_name"));
						hsn.setText(rs.getString("item_hsn"));
						unit.setValue(rs.getString("unit"));
						//qtyChoose.setValue(rs.getString("qty_type"));
						mrp.setText(String.valueOf(rs.getFloat("selling_price")));

						gstAmount.setText(String.valueOf(rs.getFloat("gst_rate")));

						percentage.focusedProperty().addListener(new ChangeListener<Boolean>() {

							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
									Boolean newValue) {
								// TODO Auto-generated method stub
								if (!newValue) {
									try {
										tempMrp = Float.parseFloat(mrp.getText().toString());
										tempPercentage = Float.parseFloat((percentage.getText().toString()));
										tempPrice = (tempMrp - (tempMrp * (tempPercentage) / 100 ) );
										BigDecimal tmp = BigDecimal.valueOf(tempPrice);
										price.setText(String.valueOf(tmp));

									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							}
						});

						quantity.focusedProperty().addListener(new ChangeListener<Boolean>() {

							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
									Boolean newValue) {
								// TODO Auto-generated method stub
								if (!newValue) {
									try {
										total.setText(String.valueOf(Float.parseFloat(quantity.getText())
												* Float.parseFloat(price.getText())));
										tempTotal = Float.parseFloat(total.getText().toString());
										tempGST = Float.parseFloat(gstAmount.getText().toString());
										tempDiscvalue = (tempTotal * (tempGST) / (tempGST+100));
										tempTaxable = (tempTotal * (100) / (tempGST + 100));
										tempCgst_Amount = (tempDiscvalue / 2);
										tempSgst_Amount = (tempDiscvalue / 2);
										tempIgst_Amount = 0;
										cgstRate = tempGST/2;
										sgstRate = tempGST/2;
										igstRate = 0;
										discvalue.setText(String.valueOf(tempDiscvalue));
										taxable.setText(String.valueOf(tempTaxable));
										cgstAmount.setText(String.valueOf(tempCgst_Amount));
										sgstAmount.setText(String.valueOf(tempSgst_Amount));
										//igstAmount.setText(String.valueOf(tempIgst_Amount));
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							}
						});
						igstAmount.focusedProperty().addListener(new ChangeListener<Boolean>(){

							@Override
							public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
								//  TODO Auto-generated method stub
								if(!arg2){
									if(Float.parseFloat(igstAmount.getText().toString()) == 0f || Float.parseFloat(igstAmount.getText().toString()) == 0.0f){

									}else{
										sgstAmount.setText("0.0");
										cgstAmount.setText("0.0");
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

		// seting the data for item name //items detalis ///
		ObservableList<String> itemNameData = FXCollections.observableArrayList();
		//sql = "SELECT * FROM items";
		//stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			itemNameData.add(rs.getString("item_name"));
		}
		TextFields.bindAutoCompletion(itemName, itemNameData);
		itemName.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if (!arg2 && !itemName.getText().toString().equals("")) {
					String subSQL = "SELECT * FROM items WHERE item_name =  \'" + itemName.getText().toString()
							+ "\'";
					try {
						rs = stmt.executeQuery(subSQL);
						rs.next();
						itemCode.setText(String.valueOf(rs.getInt("item_id")));
						hsn.setText(rs.getString("item_hsn"));
						unit.setValue(rs.getString("unit"));
						//qtyChoose.setValue(rs.getString("qty_type"));
						mrp.setText(String.valueOf(rs.getFloat("selling_price")));

						gstAmount.setText(String.valueOf(rs.getFloat("gst_rate")));
						//igstAmount.setText(String.valueOf(rs.getFloat("igst")));
						percentage.focusedProperty().addListener(new ChangeListener<Boolean>() {

							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							                    Boolean newValue) {
								// TODO Auto-generated method stub
								if (!newValue) {
									try {
										tempMrp = Float.parseFloat(mrp.getText().toString());
										tempPercentage = Float.parseFloat((percentage.getText().toString()));
										tempPrice = (tempMrp - (tempMrp * (tempPercentage) / 100 ) );
										price.setText(String.valueOf(tempPrice));

									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							}
						});

						quantity.focusedProperty().addListener(new ChangeListener<Boolean>() {

							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							                    Boolean newValue) {
								// TODO Auto-generated method stub
								if (!newValue) {
									try {
										total.setText(String.valueOf(Float.parseFloat(quantity.getText())
												* Float.parseFloat(price.getText())));
										tempTotal = Float.parseFloat(total.getText().toString());
										tempGST = Float.parseFloat(gstAmount.getText().toString());
										tempDiscvalue = (tempTotal * (tempGST) / (tempGST+100));
										BigDecimal bd = new BigDecimal(Float.toString(tempDiscvalue));
										bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
										tempDiscvalue = bd.floatValue();
										tempTaxable = (tempTotal * (100) / (tempGST + 100));
										BigDecimal tx = new BigDecimal(Float.toString(tempTaxable));
										tx = tx.setScale(2, BigDecimal.ROUND_HALF_UP);
										tempTaxable = tx.floatValue();
										tempCgst_Amount = (tempDiscvalue / 2);
										tempSgst_Amount = (tempDiscvalue / 2);
										tempIgst_Amount = 0;
										cgstRate = tempGST/2;
										sgstRate = tempGST/2;
										igstRate = 0;
										discvalue.setText(String.valueOf(tempDiscvalue));
										taxable.setText(String.valueOf(tempTaxable));
										cgstAmount.setText(String.valueOf(tempCgst_Amount));
										sgstAmount.setText(String.valueOf(tempSgst_Amount));
										igstAmount.setText(String.valueOf(tempIgst_Amount));
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							}
						});
						igstAmount.focusedProperty().addListener(new ChangeListener<Boolean>(){

							@Override
							public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
								//  TODO Auto-generated method stub
								if(!arg2){
									if(Float.parseFloat(igstAmount.getText().toString()) == 0f || Float.parseFloat(igstAmount.getText().toString()) == 0.0f){

									}else{
										sgstAmount.setText("0.0");
										cgstAmount.setText("0.0");
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
		PaidAmount.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				amountPaid = (Float.parseFloat(PaidAmount.getText().toString()));
				//	        totalBillableAmount = totalBillableAmount + dueAmount;
				// TODO Auto-generated method stub

				/*if(!(Float.parseFloat(PaidAmount.getText().toString()) == 0f) ||
						!(Float.parseFloat(PaidAmount.getText().toString()) == 0.0f)){*/

					if((gandTotal == amountPaid) ||(gandTotal != amountPaid)){
						System.out.println("Amount paid: " + amountPaid);
						System.out.println("total purchase amount:" + gandTotal);
						if((amountPaid - gandTotal) < 0 ){
							dueAmount = gandTotal - amountPaid;
							DueAmount.setText(String.valueOf(dueAmount));
							System.out.println("due Amount: " + dueAmount + "\n");
						}if((amountPaid - gandTotal) == 0){
							gandTotal = amountPaid;
							dueAmount = gandTotal - amountPaid;
							advanceAmount = gandTotal - amountPaid ;
							DueAmount.setText(String.valueOf("0"));
							System.out.println("advance amount:" + amountPaid);
						}if((amountPaid - gandTotal) > 0){
							DueAmount.setText(String.valueOf("0"));
							dueAmount = 0f;
							advanceAmount = amountPaid - gandTotal;
						}
						if(amountPaid == 0){
							dueAmount = gandTotal;
						}
						System.out.println(dueAmount + "/" + amountPaid + "\n");


					}
				}

		});
	}

	public void addItemsToTable(ActionEvent event) throws SQLException {
		//addItemToStock();
		// here same class is used wich is used for billingController
		sno =1;
		sno = sno+ table.getItems().size();
		System.out.println(sno);
		tableData.add(new items(sno,Integer.parseInt(itemCode.getText().toString()), itemName.getText().toString(), hsn.getText().toString(),
				Integer.parseInt(quantity.getText().toString()),
				unit.getSelectionModel().getSelectedItem().toString(),Float.parseFloat(percentage.getText().toString()),
				Float.parseFloat(price.getText().toString()),Float.parseFloat(mrp.getText().toString()),
				Float.parseFloat(discvalue.getText().toString()),Float.parseFloat(taxable.getText().toString()),
				cgstRate,Float.parseFloat(cgstAmount.getText().toString()),sgstRate,Float.parseFloat(sgstAmount.getText().toString()),
				igstRate,Float.parseFloat(igstAmount.getText().toString()),
				Float.parseFloat(total.getText().toString())));
		table.setItems(tableData);

		collSNO.setCellValueFactory(new PropertyValueFactory<>("sno"));
		collmItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
		collName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		collHSNNumber.setCellValueFactory(new PropertyValueFactory<>("hsn"));
		collQtyType.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		collUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
		collPercent.setCellValueFactory(new PropertyValueFactory<>("percent"));
		collPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		collMrp.setCellValueFactory(new PropertyValueFactory<>("mrp"));
		collDiscValue.setCellValueFactory(new PropertyValueFactory<>("discvalue"));
		collTaxable.setCellValueFactory(new PropertyValueFactory<>("taxable"));
		collCgstRate.setCellValueFactory(new PropertyValueFactory<>("cgst_rate"));
		collCgstAmount.setCellValueFactory(new PropertyValueFactory<>("cgst_amount"));
		collSgstRate.setCellValueFactory(new PropertyValueFactory<>("sgst_rate"));
		collSgstAmount.setCellValueFactory(new PropertyValueFactory<>("sgst_amount"));
		collIgstRate.setCellValueFactory(new PropertyValueFactory<>("igst_rate"));
		collIgstAmount.setCellValueFactory(new PropertyValueFactory<>("igst_amount"));
		collTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

		/// Updating the grandTotal
		gandTotal = gandTotal + Float.parseFloat(total.getText().toString());
		purchaseGrandTotal.setText("Rs. " + gandTotal);
		totalSgst =totalSgst + Float.parseFloat(sgstAmount.getText().toString());
		totalCgst = totalCgst + Float.parseFloat(cgstAmount.getText().toString());
		totalIgst = totalIgst + Float.parseFloat(igstAmount.getText().toString());
		totalDiscvalue = totalDiscvalue + Float.parseFloat(discvalue.getText().toString());
		totalTaxable = totalTaxable + Float.parseFloat(taxable.getText().toString());


		//billinGrandTotal.setText("Rs. " + String.valueOf(totalBillableAmount));
		PurchasetotalSGST.setText("Rs. " + String.valueOf(totalSgst));
		PurchasetotalCGST.setText("Rs. " + String.valueOf(totalCgst));
		PurchasetotalIGST.setText("Rs. " + String.valueOf(totalIgst));
		PurchasetotalDiscvalue.setText("Rs. " + String.valueOf(totalDiscvalue));
		PurchasetotalTaxable.setText("Rs. " + String.valueOf(totalTaxable));

		itemCode.clear();
		itemName.clear();
		hsn.setText("HSN");
		quantity.clear();
		//unit.cancelEdit();
		percentage.clear();
		price.setText("Rate");
		mrp.setText("MRP");
		discvalue.setText("Discvalue");
		taxable.setText("Taxable");
		cgstAmount.setText("CGST");
		sgstAmount.setText("SGST");
		gstAmount.clear();
		igstAmount.clear();
		total.setText("Total");
                
                if(AddButton.isFocused()) {
	                itemName.requestFocus();
                }

	}



	public void savePurchaseDetails() throws SQLException {

		String sql1;
		String dealer_name = purchaseDealerName.getText().toString();
		String dealer_address = purchaseDealerAddress.getText().toString();
		String dealer_contact = purchaseDealerContact.getText().toString();
		String gstin = purchaseGSTINNumber.getText().toString();
		String invoice_no = purchaseInvoiceNumber.getText().toString();
		String invoice_date = datePicker.getEditor().getText();
		float amount = gandTotal;
		float gst_rate = tempGST;
		float disc = totalDiscvalue;
		float tax = totalTaxable;
		float cgst1 = totalCgst;
		float sgst1 = totalSgst;
		float igst1 = totalIgst;
		float due = dueAmount;
		float paid = amountPaid;

		sql1 = "INSERT INTO purchase_invoice (dealer_name,dealer_address,dealer_contact,invoice_no,date,gstin,gst," +
				"total_discvalue,total_taxable,total_cgst,total_sgst,total_igst,due_amount,paid_amount,amount)" +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql1);
		pstmt.setString(1, dealer_name);
		pstmt.setString(2, dealer_address);
		pstmt.setString(3, dealer_contact);
		pstmt.setString(4, invoice_no);
		pstmt.setString(5, invoice_date);
		pstmt.setString(6, gstin);
		pstmt.setFloat(7, gst_rate);
		pstmt.setFloat(8, disc);
		pstmt.setFloat(9, tax);
		pstmt.setFloat(10, cgst1);
		pstmt.setFloat(11, sgst1);
		pstmt.setFloat(12, igst1);
		pstmt.setFloat(13, due);
		pstmt.setFloat(14, paid);
		pstmt.setFloat(15, amount);

		System.out.println(pstmt.toString());
		pstmt.executeUpdate();

		System.out.println("first stement");

		sql = "INSERT INTO purchase_invoice_details (s_no,invoice_no, item_name, item_code, hsn, quantity,Unit,Percentage, price,MRP,DiscValue,Taxable," +
				"cgst_rate,cgst_amount,sgst_rate,sgst_amount,igst_rate, igst_amount, total) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		pstmt = conn.prepareStatement(sql);

		for (items x : tableData) {


			String sno = String.valueOf(x.getSno());
			String itemName = x.getItemName();
			String itemCode = String.valueOf(x.getItemCode());
			String hsn = x.getHsn();
			Integer quantity = x.getQuantity();
			String unit = x.getUnit();
			float percent =x.getPercent();
			float price = x.getTotal();
			float mrp = x.getMrp();
			float discvalue = x.getDiscvalue();
			float taxable = x.getTaxable();
			float cgst_rate = x.getCgst_rate();
			float cgst_amount = x.getCgst_amount();
			float sgst_rate = x.getSgst_rate();
			float sgst_amount = x.getSgst_amount();
			float igst_rate = x.getIgst_rate();
			float igst_amount = x.getIgst_amount();
			float total = x.getTotal();

			pstmt.setString(1, sno);
			pstmt.setString(2, invoice_no);
			pstmt.setString(3, itemName);
			pstmt.setString(4, itemCode);
			pstmt.setString(5, hsn);
			pstmt.setInt(6, quantity);
			pstmt.setString(7, unit);
			pstmt.setFloat(8, percent);
			pstmt.setFloat(9, price);
			pstmt.setFloat(10, mrp);
			pstmt.setFloat(11, discvalue);
			pstmt.setFloat(12, taxable);
			pstmt.setFloat(13, cgst_rate);
			pstmt.setFloat(14, cgst_amount);
			pstmt.setFloat(15, sgst_rate);
			pstmt.setFloat(16, sgst_amount);
			pstmt.setFloat(17, igst_rate);
			pstmt.setFloat(18, igst_amount);
			pstmt.setFloat(19, total);

			pstmt.executeUpdate();

		}
		// opening new window for print
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("printpurchase.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			((printPurchaseController) fxmlLoader.getController()).setDataFromPurchaseController(tableData,
					String.valueOf(gandTotal),invoice_no, invoice_date,
					dealer_name, dealer_contact,totalDiscvalue,totalTaxable,totalSgst,
					totalCgst, totalIgst, gstin , dealer_address);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			System.out.println(num);

			sql.charAt(0);
			float qtyFromDatabase = 0f;
			String sql2 = "SELECT stock FROM items WHERE item_id = " + id;
			rs = stmt.executeQuery(sql2);
			rs.next();
			qtyFromDatabase = rs.getFloat("stock");

			num = qtyFromDatabase + num;

			sql2 = "UPDATE items SET stock = " + num +" WHERE item_id = "+ id;
			stmt.execute(sql2);
		}
		purchaseDealerName.clear();
		purchaseGSTINNumber.clear();
		purchaseInvoiceNumber.clear();
		purchaseDealerAddress.clear();
		purchaseDealerContact.clear();
		purchaseGrandTotal.setText("Rs.0000");

		PurchasetotalTaxable.setText("0");
		PurchasetotalDiscvalue.setText("0");
		PurchasetotalCGST.setText("0");
		PurchasetotalSGST.setText("0");
		PurchasetotalIGST.setText("0");
		
		itemCode.clear();
		itemName.clear();
		hsn.setText("HSN");
		quantity.clear();
		//unit.cancelEdit();
		percentage.setPromptText("pct%");
		price.setText("Rate");
		mrp.setText("MRP");
		discvalue.setText("Discvalue");
		taxable.setText("Taxable");
		cgstAmount.setText("CGST");
		sgstAmount.setText("SGST");
		gstAmount.setPromptText("GST");
		igstAmount.setText("IGST");
		total.setText("Total");
		DueAmount.setText("0");
		PaidAmount.setText("0");
		//tableData.clear();

                    if(SaveButton.isFocused()) {
                        purchaseDealerName.requestFocus();
                    }

	}
	public void deleteItemFromTable() throws SQLException {
		//reverseAddItemFromStock();
		items del = table.getSelectionModel().getSelectedItem();

		gandTotal = gandTotal - del.getTotal();
		purchaseGrandTotal.setText("Rs. " + gandTotal);
		totalSgst = totalSgst - del.getSgst_amount();
		totalCgst = totalCgst - del.getCgst_amount();
		totalIgst = totalIgst - del.getIgst_amount();
		totalDiscvalue = totalDiscvalue - del.getDiscvalue();
		totalTaxable = totalTaxable - del.getTaxable();
		sgstAmount.setText("Rs. " + String.valueOf(totalSgst));
		cgstAmount.setText("Rs. " + String.valueOf(totalCgst));
		igstAmount.setText("Rs. " + String.valueOf(totalIgst));
		discvalue.setText("Rs. " + String.valueOf(totalDiscvalue));
		taxable.setText("Rs. " + String.valueOf(totalTaxable));
		tableData.remove(del);
	}
	
	/*public void addItemToStock() throws SQLException{
		String sql1 = null;
		String sql2;
		float qtyFromDatabase = 0f;
		int id = Integer.valueOf(itemCode.getText().toString());
		float qtyTmp = Float.valueOf(quantity.getText().toString());

		sql2 = "SELECT stock FROM items WHERE item_id = " + id  ;

		rs = stmt.executeQuery(sql2);
		rs.next();
		qtyFromDatabase = rs.getFloat("stock");

		qtyTmp = qtyFromDatabase + qtyTmp;

		sql1 = "UPDATE items SET stock = " +qtyTmp +" WHERE item_id = "+ id;
		stmt = conn.createStatement();
		stmt.executeUpdate(sql1);
		
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

		num = qtyFromDatabase - num;

		sql1 = "UPDATE items SET stock = " + num +" WHERE item_id = "+ id;
		stmt.execute(sql1);
	}*/


}
