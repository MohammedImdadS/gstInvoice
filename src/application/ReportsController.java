package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import application.BillingController.items;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReportsController {
	public class purchaseClass {
		private StringProperty dealerName;
		private StringProperty dealerContact;
		private StringProperty gstin;
		private StringProperty invoice;
		private StringProperty date;
		private FloatProperty amount;
		private FloatProperty discvalue;
		private FloatProperty taxable;
		private FloatProperty dueAmount;
		private FloatProperty paidAmount;

		public purchaseClass(String dealerName,String dealerContact, String gstin, String invoice, String date, Float amount,
		                     Float discvalue,Float taxable,Float dueAmount,Float paidAmount ) {
			super();
			this.dealerName = new SimpleStringProperty(dealerName);
			this.dealerContact = new SimpleStringProperty(dealerContact);
			this.gstin = new SimpleStringProperty(gstin);
			this.invoice = new SimpleStringProperty(invoice);
			this.date = new SimpleStringProperty(date);
			this.amount = new SimpleFloatProperty(amount);
			this.discvalue = new SimpleFloatProperty(discvalue);
			this.taxable = new SimpleFloatProperty(taxable);
			this.dueAmount = new SimpleFloatProperty(dueAmount);
			this.paidAmount = new SimpleFloatProperty(paidAmount);
		}

		public String getDealerName() {
			return dealerName.get();
		}

		public void setDealerName(String dealerName) {
			this.dealerName.set(dealerName);
		}

		public String getDealerContact() {
			return dealerContact.get();
		}
		public void setDealerContact(String dealerContact){this.dealerContact.set(dealerContact);}

		public String getGstin() {
			return gstin.get();
		}

		public void setGstin(String gstin) {
			this.gstin.set(gstin);
		}

		public String getInvoice() {
			return invoice.get();
		}

		public void setInvoice(String invoice) {
			this.invoice.set(invoice);
		}

		public String getDate() {
			return date.get();
		}

		public void setDate(String date) {
			this.date.set(date);
		}

		public Float getAmount() {
			return amount.get();
		}

		public void setAmount(Float amount) {
			this.amount.set(amount);
		}

		public Float getDiscvalue(){return discvalue.get();}
		public void setDiscvalue(Float discvalue){this.discvalue.set(discvalue);}

		public Float getTaxable(){return taxable.get();}
		public void setTaxable(Float taxable){this.taxable.set(taxable);}

		public Float getDueAmount(){return dueAmount.get();}
		public void setDueAmount(Float dueAmount){this.dueAmount.set(dueAmount);}

		public Float getPaidAmount(){return paidAmount.get();}
		public void setPaidAmount(Float paidAmount){this.paidAmount.set(paidAmount);}

	}

	public class sellingClass {
		// here purchaseVariableName Shoud be there but sellingVariableName is
		// there but this cannot be change so will be using this only
		private StringProperty sellingInvoiceNumber;
		private StringProperty sellingInvoiceDate;
		private StringProperty sellingCustomerName;
		private StringProperty sellingCustomerContact;
		private StringProperty sellingDiscValue;
		private StringProperty sellingTaxableAmount;
		private StringProperty sellingDueAmount;
		private StringProperty sellingPaidAmount;
		private StringProperty sellingAmount;

		public sellingClass(String sellingInvoiceNumber, String sellingInvoiceDate, String sellingCustomerName,
				String sellingCustomerContact,String sellingDiscValue, String sellingTaxableAmount,String sellingDueAmount,
				            String sellingPaidAmount, String sellingAmount) {
			super();
			this.sellingInvoiceNumber = new SimpleStringProperty(sellingInvoiceNumber);
			this.sellingInvoiceDate = new SimpleStringProperty(sellingInvoiceDate);
			this.sellingCustomerName = new SimpleStringProperty(sellingCustomerName);
			this.sellingCustomerContact = new SimpleStringProperty(sellingCustomerContact);
			this.sellingDiscValue = new SimpleStringProperty(sellingDiscValue);
			this.sellingTaxableAmount = new SimpleStringProperty(sellingTaxableAmount);
			this.sellingDueAmount = new SimpleStringProperty(sellingDueAmount);
			this.sellingPaidAmount = new SimpleStringProperty(sellingPaidAmount);
			this.sellingAmount = new SimpleStringProperty(sellingAmount);
		}

		public String getSellingInvoiceNumber() {
			return sellingInvoiceNumber.get();
		}

		public void setSellingInvoiceNumber(String sellingInvoiceNumber) {
			this.sellingInvoiceNumber.set(sellingInvoiceNumber);
		}

		public String getSellingInvoiceDate() {
			return sellingInvoiceDate.get();
		}

		public void setSellingInvoiceDate(String sellingInvoiceDate) {
			this.sellingInvoiceDate.set(sellingInvoiceDate);
		}

		public String getSellingCustomerName() {
			return sellingCustomerName.get();
		}

		public void setSellingCustomerName(String sellingCustomerName) {
			this.sellingCustomerName.set(sellingCustomerName);
		}

		public String getSellingCustomerContact() {
			return sellingCustomerContact.get();
		}

		public void setSellingCustomerContact(String sellingCustomerContact) {
			this.sellingCustomerContact.set(sellingCustomerContact);
		}

		public String getSellingDiscValue() {
			return sellingDiscValue.get();
		}

		public void setSellingDiscValue(String sellingDiscValue) {
			this.sellingDiscValue.set(sellingDiscValue);
		}

		public String getSellingTaxableAmount() {
			return sellingTaxableAmount.get();
		}

		public void setSellingTaxableAmount(String sellingTaxableAmount) {
			this.sellingTaxableAmount.set(sellingTaxableAmount);
		}

		public String getSellingDueAmount() {
			return sellingDueAmount.get();
		}

		public void setSellingDueAmount(String sellingDueAmount) {
			this.sellingDueAmount.set(sellingDueAmount);
		}

		public String getSellingPaidAmount() {
			return sellingPaidAmount.get();
		}

		public void setSellingPaidAmount(String sellingPaidAmount) {
			this.sellingPaidAmount.set(sellingPaidAmount);
		}

		public String getSellingAmount() {
			return sellingAmount.get();
		}

		public void setSellingAmount(String sellingAmount) {
			this.sellingAmount.set(sellingAmount);
		}

	}
	//purchase
	@FXML
	private TextField PinvoiceSearch = new TextField();
	@FXML
	private DatePicker Pfrom_date = new DatePicker();
	@FXML
	private DatePicker Pto_date = new DatePicker();
	//selling
	@FXML
	private TextField invoiceSearchBox = new TextField();
	@FXML
	private DatePicker fromDate = new DatePicker();
	@FXML
	private DatePicker toDate = new DatePicker();

	@FXML
	private AnchorPane anchorPane1;
	//purchase
	@FXML
	private TableView<purchaseClass> purchaseTable = new TableView<>();
	@FXML
	private TableColumn<purchaseClass, String> purchaseDealerName = new TableColumn<>();
	@FXML
	private TableColumn<purchaseClass, String> purchaseDealerMobile = new TableColumn<>();
	@FXML
	private TableColumn<purchaseClass, String> purchaseGstin = new TableColumn<>();
	@FXML
	private TableColumn<purchaseClass, String> purchaseInvoice = new TableColumn<>();
	@FXML
	private TableColumn<purchaseClass, String> purchaseDate = new TableColumn<>();
	@FXML
	private TableColumn<purchaseClass, String> purchaseAmount = new TableColumn<>();
	@FXML
	private TableColumn<purchaseClass, Float> purchaseDiscvalue = new TableColumn<>();
	@FXML
	private TableColumn<purchaseClass, Float> purchaseTaxable = new TableColumn<>();
	@FXML
	private TableColumn<purchaseClass, Float> purchaseDueAmount = new TableColumn<>();
	@FXML
	private TableColumn<purchaseClass, Float> purchasePaidAmount = new TableColumn<>();
	//selling
	@FXML
	private TableView<sellingClass> sellingTable = new TableView<>();
	@FXML
	private TableColumn<sellingClass, String> sellingInvoiceNumber = new TableColumn<>();
	@FXML
	private TableColumn<sellingClass, String> sellingInvoiceDate = new TableColumn<>();
	@FXML
	private TableColumn<sellingClass, String> sellingCustomerName = new TableColumn<>();
	@FXML
	private TableColumn<sellingClass, String> sellingCustomerContact = new TableColumn<>();
	@FXML
	private TableColumn<sellingClass, String> sellingDiscValue = new TableColumn<>();
	@FXML
	private TableColumn<sellingClass, String> sellingTaxableAmount = new TableColumn<>();
	@FXML
	private TableColumn<sellingClass, String> sellingDueAmount = new TableColumn<>();
	@FXML
	private TableColumn<sellingClass, String> sellingPaidAmount = new TableColumn<>();
	@FXML
	private TableColumn<sellingClass, String> sellingAmount = new TableColumn<>();

	//purchase
	@FXML
	private Label Pdatanotfound = new Label();
	private Label Pdatanotfounddata = new Label();
	//selling
	@FXML
	private Label datanotfound = new Label();
	private Label datanotfounddate = new Label();

	ObservableList<purchaseClass> purchaseData = FXCollections.observableArrayList();
	ObservableList<sellingClass> sellingData = FXCollections.observableArrayList();

	Connection conn = Connections.b2csDBConncetion();
	String sql;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	@FXML
	public void initialize() throws SQLException {
		// ========================== for
		// purchase=============================================================
		sql = "SELECT * FROM purchase_invoice";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			purchaseData.add(new purchaseClass(rs.getString("dealer_name"),rs.getString("dealer_contact"),
					rs.getString("gstin"), rs.getString("invoice_no"), rs.getString("date"),
					rs.getFloat("amount"), rs.getFloat("total_discvalue"),rs.getFloat("total_taxable"),
					rs.getFloat("due_amount"), rs.getFloat("paid_amount")));
		}
		// setting data to the colomn for purchse data;
		purchaseTable.setItems(purchaseData);

		purchaseDealerName.setCellValueFactory(new PropertyValueFactory<>("dealerName"));
		purchaseDealerMobile.setCellValueFactory(new PropertyValueFactory<>("dealerContact"));
		purchaseGstin.setCellValueFactory(new PropertyValueFactory<>("gstin"));
		purchaseInvoice.setCellValueFactory(new PropertyValueFactory<>("invoice"));
		purchaseDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		purchaseAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		purchaseDiscvalue.setCellValueFactory(new PropertyValueFactory<>("discvalue"));
		purchaseTaxable.setCellValueFactory(new PropertyValueFactory<>("taxable"));
		purchaseDueAmount.setCellValueFactory(new PropertyValueFactory<>("dueAmount"));
		purchasePaidAmount.setCellValueFactory(new PropertyValueFactory<>("paidAmount"));

		//=======for setting date format consistent with mysql database which is yyyy-mm-dd
		Pfrom_date.setConverter(new StringConverter<LocalDate>()
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
		Pto_date.setConverter(new StringConverter<LocalDate>()
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

		// ============================== for selling
		// =====================================================
		sql = "SELECT * FROM selling_invoice";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			sellingData.add(new sellingClass(String.valueOf(rs.getInt("invoice_number")), rs.getString("invoice_date"),
					rs.getString("customer_name"), rs.getString("cutomer_number"),rs.getString("total_Discvalue"),
					rs.getString("total_Taxable"),rs.getString("due_Amount"),rs.getString("amount_paid"),
					String.valueOf(rs.getFloat("amount"))));
		}

		// setting data to the colomn for selling data;
		sellingTable.setItems(sellingData);

		sellingInvoiceNumber.setCellValueFactory(new PropertyValueFactory<>("sellingInvoiceNumber"));
		sellingInvoiceDate.setCellValueFactory(new PropertyValueFactory<>("sellingInvoiceDate"));
		sellingCustomerName.setCellValueFactory(new PropertyValueFactory<>("sellingCustomerName"));
		sellingCustomerContact.setCellValueFactory(new PropertyValueFactory<>("sellingCustomerContact"));
		sellingDiscValue.setCellValueFactory(new PropertyValueFactory<>("sellingDiscValue"));
		sellingTaxableAmount.setCellValueFactory(new PropertyValueFactory<>("sellingTaxableAmount"));
		sellingDueAmount.setCellValueFactory(new PropertyValueFactory<>("sellingDueAmount"));
		sellingPaidAmount.setCellValueFactory(new PropertyValueFactory<>("sellingPaidAmount"));
		sellingAmount.setCellValueFactory(new PropertyValueFactory<>("sellingAmount"));

		//=======for setting date format consistent with mysql database which is yyyy-mm-dd
		fromDate.setConverter(new StringConverter<LocalDate>()
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
		toDate.setConverter(new StringConverter<LocalDate>()
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
		


	}

	public void showDetailedInfoselling(ActionEvent Event) throws SQLException {

		String invoiceNo = sellingTable.getSelectionModel().getSelectedItem().getSellingInvoiceNumber();
		int invoicnumbner = Integer.valueOf(invoiceNo);

		// for getting information about the invoice
		sql = "SELECT * FROM selling_invoice WHERE invoice_number = " + invoiceNo;
		System.out.println(sql);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		rs.next();

		String custName = rs.getString("customer_name");
		String custNumber = rs.getString("cutomer_number");
		String custGstin = rs.getString("customer_gstin");
		String custadde = rs.getString("billing_addr");
		float cgst = rs.getFloat("total_cgst")  ;
		float sgst = rs.getFloat("total_sgst") ;
		float igst = rs.getFloat("total_igst");
		float discvalue = rs.getFloat("total_discvalue");
		float taxable = rs.getFloat("total_taxable");
		float totalBillableAmount = rs.getFloat("amount");
		//float totalTax = rs.getFloat("taxable_value");
		// for getting date in correct format
		sql = "SELECT DATE_FORMAT(invoice_date, '%d/%m/%Y') FROM selling_invoice WHERE invoice_number = " + invoiceNo;
		System.out.println(sql);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		rs.next();
		String date = rs.getString(1);
		// for getting the invoice details
		sql = "SELECT * FROM selling_invoice_detail WHERE invoice_number = " + invoiceNo;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		ObservableList<BillingController.items> tableData = FXCollections.observableArrayList();

		BillingController x = new BillingController();


		while (rs.next()) {
			tableData.add(x.new items(rs.getInt("s_no"),0,rs.getString("item_name"), " ", rs.getInt("quantity"),
					/*rs.getString("qtytype"),*/rs.getString("unit"), rs.getFloat("price"),
					rs.getFloat("mrp"),rs.getFloat("discvalue"),rs.getFloat("taxable"),
					rs.getFloat("cgst_rate"), rs.getFloat("cgst_amount"), rs.getFloat("sgst_rate"),
					rs.getFloat("sgst_amount"), rs.getFloat("igst_rate"),rs.getFloat("igst_amount"), // isgst
					rs.getFloat("total")));
		}
		// System.out.println(tableData.get(1).getItemName());
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("print.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			((printBillController) fxmlLoader.getController()).setDataFromBillingController(tableData,
					String.valueOf(totalBillableAmount), String.valueOf(invoicnumbner), date,
					custName, custNumber, cgst, sgst, igst,discvalue,taxable, custGstin, custadde);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	  public void showDetailedInfoPurchase(ActionEvent Event) throws SQLException{
		  String invoiceNo = purchaseTable.getSelectionModel().getSelectedItem().getInvoice();
		  int invoicenumbner = Integer.valueOf(invoiceNo);
		  sql = "SELECT * FROM purchase_invoice WHERE invoice_no = " + invoiceNo;
		  System.out.println(sql);
		  stmt = conn.createStatement();
		  rs = stmt.executeQuery(sql);
		  rs.next();
		  String dealerName = rs.getString("dealer_name");
		  String dealerNumber = rs.getString("dealer_contact");
		  String dealerGstin = rs.getString("gstin");
		  String dealeraddress = rs.getString("dealer_address");
		  float T_cgst = rs.getFloat("total_cgst")  ;
		  float T_sgst = rs.getFloat("total_sgst") ;
		  float T_igst = rs.getFloat("total_igst");
		  float TDiscvalue = rs.getFloat("total_discvalue");
		  float Ttaxable = rs.getFloat("total_taxable");
		  float grandTotal = rs.getFloat("amount");

		  sql = "SELECT DATE_FORMAT(date, '%d/%m/%Y') FROM purchase_invoice WHERE invoice_no = " + invoiceNo;
		  System.out.println(sql);
		  stmt = conn.createStatement();
		  rs = stmt.executeQuery(sql);
		  rs.next();
		  String invoice_date = rs.getString(1);
		  // for getting the invoice details
		  sql = "SELECT * FROM purchase_invoice_details WHERE invoice_no = " + invoiceNo;
		  stmt = conn.createStatement();
		  rs = stmt.executeQuery(sql);
		  ObservableList<PurchaseController.items> tableData = FXCollections.observableArrayList();

		  PurchaseController x = new PurchaseController();
		  while (rs.next()) {
			  tableData.add(x.new items(rs.getInt("s_no"),rs.getInt("item_code"),
					  rs.getString("item_name"), rs.getString("hsn"),
					  rs.getInt("quantity"),/*rs.getString("qtytype"),*/rs.getString("unit"),
					  rs.getFloat("Percentage"), rs.getFloat("price"), rs.getFloat("MRP"),
					  rs.getFloat("Discvalue"), rs.getFloat("Taxable"), rs.getFloat("cgst_rate"),
					  rs.getFloat("cgst_amount"), rs.getFloat("sgst_rate"), rs.getFloat("sgst_amount"),
					  rs.getFloat("igst_rate"),rs.getFloat("igst_amount"), rs.getFloat("total")));
		  }

		 try {
			 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("printpurchase.fxml"));
			 Parent root1 = (Parent) fxmlLoader.load();
			 Stage stage = new Stage();
			 stage.setScene(new Scene(root1));
			 ((printPurchaseController) fxmlLoader.getController()).setDataFromPurchaseController(tableData,
					 String.valueOf(grandTotal), String.valueOf(invoicenumbner), invoice_date,
					 dealerName, dealerNumber,TDiscvalue,Ttaxable,T_sgst,
					 T_cgst,T_igst, dealerGstin , dealeraddress);
			 stage.show();
		 } catch (Exception e) { e.printStackTrace(); }
		 
	}

	public void searchFromInvoiceNumber() throws SQLException {
		int invoiceNu = Integer.parseInt(invoiceSearchBox.getText().toString());
		sql = "SELECT * FROM selling_invoice WHERE invoice_number = " + invoiceNu;
		System.out.println(sql);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		
		sellingData.clear();
		while (rs.next()) {
			sellingData.add(new sellingClass(String.valueOf(rs.getInt("invoice_number")), rs.getString("invoice_date"),
					rs.getString("customer_name"), rs.getString("cutomer_number"), rs.getString("total_Discvalue"),
					rs.getString("total_Taxable"),rs.getString("due_Amount"),rs.getString("amount_paid"),
					String.valueOf(rs.getFloat("amount"))));
		}
		
		if(sellingData.isEmpty()){
			datanotfound.setVisible(true);
		}
		invoiceSearchBox.clear();
	}

	public void PurchaseInvoiceSearch() throws SQLException{
		int invoiceNu = Integer.parseInt(PinvoiceSearch.getText().toString());
		sql = "SELECT * FROM purchase_invoice WHERE invoice_no = " + invoiceNu;
		System.out.println(sql);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		purchaseData.clear();
		while (rs.next()) {
			purchaseData.add(new purchaseClass(rs.getString("dealer_name"),rs.getString("dealer_contact"),
					rs.getString("gstin"), rs.getString("invoice_no"), rs.getString("date"),
					rs.getFloat("amount"), rs.getFloat("total_discvalue"),rs.getFloat("total_taxable"),
					rs.getFloat("due_amount"), rs.getFloat("paid_amount")));
		}
		if(purchaseData.isEmpty()){
			Pdatanotfound.setVisible(true);
		}
		PinvoiceSearch.clear();
	}

	public void btwDate() throws SQLException {
		

		
		String frmDate = fromDate.getEditor().getText();
		String tDate = toDate.getEditor().getText();
		System.out.println(frmDate +" " + tDate);
		sql = "SELECT * FROM selling_invoice WHERE invoice_date <= ? AND invoice_date >= ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(2, frmDate);
		pstmt.setString(1, tDate);
		System.out.println(pstmt.toString());
		rs = pstmt.executeQuery();
		
		sellingData.clear();
		while (rs.next()) {
			sellingData.add(new sellingClass(String.valueOf(rs.getInt("invoice_number")), rs.getString("invoice_date"),
					rs.getString("customer_name"), rs.getString("cutomer_number"), rs.getString("total_Discvalue"),
					rs.getString("total_Taxable"),rs.getString("due_Amount"),rs.getString("amount_paid"),
					String.valueOf(rs.getFloat("amount"))));
		}
		
		if(sellingData.isEmpty()){
			datanotfounddate.setVisible(true);
		}

	}

	public void PurchaseBtwDate() throws SQLException {
		String frmDate = Pfrom_date.getEditor().getText();
		String tDate = Pto_date.getEditor().getText();
		System.out.println(frmDate +" " + tDate);
		sql = "SELECT * FROM purchase_invoice WHERE date <= ? AND date >= ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(2, frmDate);
		pstmt.setString(1, tDate);
		System.out.println(pstmt.toString());
		rs = pstmt.executeQuery();

		purchaseData.clear();
		while (rs.next()) {
			purchaseData.add(new purchaseClass(rs.getString("dealer_name"),rs.getString("dealer_contact"),
					rs.getString("gstin"), rs.getString("invoice_no"), rs.getString("date"),
					rs.getFloat("amount"), rs.getFloat("total_discvalue"),rs.getFloat("total_taxable"),
					rs.getFloat("due_amount"), rs.getFloat("paid_amount")));
		}
		if(purchaseData.isEmpty()){
			Pdatanotfounddata.setVisible(true);
		}
	}

}
