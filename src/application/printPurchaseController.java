package application;

import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.PurchaseController.items;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class printPurchaseController {
	@FXML
	private AnchorPane printPane;
	@FXML
	private Text companyAddress;
	@FXML
	private Label companyName;
	@FXML
	private Label companyContact;
	@FXML
	private Label companyGstin;
	@FXML
	private Label grandTotal;
	@FXML
	private Label totaltax;
	@FXML
	private Label invoiceNumber;
	@FXML
	private Label invoiceDate;
	@FXML
	private Label custName;
	@FXML
	private Label custNumber;
	@FXML
	private Label custGstin;
	@FXML
	private Label totalIgst;
	@FXML
	private Text billinAddr;
	@FXML
	private Label totalDiscvalue;
	@FXML
	private Label totalTaxable;
	
	@FXML
	private Label totalSgst;
	@FXML
	private Label totalCgst;
	
	
	@FXML
	TableView<items> table = new TableView<>();
	@FXML
	TableColumn<PurchaseController.items, Integer> collItemSNO = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, Integer> collItemId = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, String> collItemName = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, String> collItemhsn = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, String> collItemQty = new TableColumn<>();
	/*@FXML
	TableColumn<BillingController.items, String> collItemQtytype = new TableColumn<>();*/
	@FXML
	TableColumn<PurchaseController.items, String> collItemUnit = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, Float> collItemGstRate = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, Float> collItemPrice = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, Float> collItemMrp = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items,Float> collItemDiscvalue = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, Float> collItemTaxable =new TableColumn<>();
	
	@FXML
	TableColumn<PurchaseController.items, Float> collSgstRate = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, Float> collSgstAmount = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, Float> collCgstRate = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, Float> collCgstAmount = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, Float> collIgstRate = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, Float> collIgstAmount = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, Float> collItemTax = new TableColumn<>();
	@FXML
	TableColumn<PurchaseController.items, Float> collItemTotal = new TableColumn<>();
	
	ObservableList<PurchaseController.items> tableData = FXCollections.observableArrayList();
	
	Connection conn = Connections.b2csDBConncetion();
	String sql;
	Statement stmt;
	ResultSet rs;
	
	
	// Event Listener on Button.onAction
	@FXML
	public void a(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		printNode(printPane);
		//System.exit(0);
	}
	public static void printNode(final AnchorPane node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
	  
		Printer printer = Printer.getDefaultPrinter();
	    PageLayout pageLayout
	        = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);

	    PrinterJob job = PrinterJob.createPrinterJob();

	     node.setPrefSize(pageLayout.getPrintableWidth(), pageLayout.getPrintableHeight());
	    if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
	      boolean success = job.printPage(pageLayout, node);
	      if (success) {
	        job.endJob();
	      }
	    }
	  }
	public void setDataFromPurchaseController(ObservableList<PurchaseController.items> list2, String total, String invoice_no,
	                                          String invoice_date, String dealer_name, String dealer_contact, float Discvalue, float Taxable,
	                                          float Sgst, float Cgst, float Igst, String gstin, String dealer_address) throws SQLException {
		sql = "SELECT * FROM company_details";
		stmt = conn.createStatement();
		stmt.execute(sql);
		rs = stmt.executeQuery(sql);
		rs.next();
		companyName.setText(rs.getString("name"));
		companyAddress.setText(rs.getString("address"));
		companyGstin.setText(rs.getString("gstin"));
		companyContact.setText(rs.getString("contact"));
		this.invoiceDate.setText(invoice_date);
		this.custName.setText(dealer_name);
		this.custNumber.setText(dealer_contact);
		this.custGstin.setText(gstin);
		this.billinAddr.setText(dealer_address);
		totalSgst.setText(String.valueOf(Sgst));
		totalCgst.setText(String.valueOf(Cgst));
		totalIgst.setText(String.valueOf(Igst));
		totalDiscvalue.setText(String.valueOf(Discvalue));
		totalTaxable.setText(String.valueOf(Taxable));

		tableData = list2;
		//////////////////////////////////
		grandTotal.setText(total);

		//totaltax.setText(tax);
		this.invoiceNumber.setText(invoice_no);
		/// aadding the data to the table
		table.setItems(tableData);
		collItemSNO.setCellValueFactory(new PropertyValueFactory<items, Integer>("sno"));
		collItemId.setCellValueFactory(new PropertyValueFactory<items, Integer>("itemCode"));
		collItemName.setCellValueFactory(new PropertyValueFactory<items, String>("itemName"));
		collItemhsn.setCellValueFactory(new PropertyValueFactory<items, String>("hsn"));
		collItemQty.setCellValueFactory(new PropertyValueFactory<items, String>("quantity"));
		//collItemQtytype.setCellValueFactory(new PropertyValueFactory<items, String>("qtytype"));
		collItemUnit.setCellValueFactory(new PropertyValueFactory<items, String>("unit"));
		collIgstAmount.setCellValueFactory(new PropertyValueFactory<items, Float>("igst_rate"));
		collIgstRate.setCellValueFactory(new PropertyValueFactory<items, Float>("igst_amount"));
		collSgstRate.setCellValueFactory(new PropertyValueFactory<items, Float>("sgst_rate"));
		collSgstAmount.setCellValueFactory(new PropertyValueFactory<items, Float>("sgst_amount"));
		collCgstRate.setCellValueFactory(new PropertyValueFactory<items, Float>("cgst_rate"));
		collCgstAmount.setCellValueFactory(new PropertyValueFactory<items, Float>("cgst_amount"));
		collItemPrice.setCellValueFactory(new PropertyValueFactory<items, Float>("price"));
		collItemMrp.setCellValueFactory(new PropertyValueFactory<items, Float>("mrp"));
		collItemDiscvalue.setCellValueFactory(new PropertyValueFactory<items, Float>("discvalue"));
		collItemTaxable.setCellValueFactory(new PropertyValueFactory<items, Float>("taxable"));
		collItemTotal.setCellValueFactory(new PropertyValueFactory<items, Float>("total"));

	}
}
