package application;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

import application.BillingController.items;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
//import javafx.print.PageLayout;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
 
import javax.swing.*;

public class printBillController {
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
	protected Label totalCgst;


	@FXML
	public TableView<items> table = new TableView<>();
	@FXML
	TableColumn<BillingController.items, Integer> collItemSNO = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Integer> collItemId = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, String> collItemName = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, String> collItemhsn = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, String> collItemQty = new TableColumn<>();
	/*@FXML
	TableColumn<BillingController.items, String> collItemQtytype = new TableColumn<>();*/
	@FXML
	TableColumn<BillingController.items, String> collItemUnit = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collItemGstRate = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collItemPrice = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collItemMrp = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items,Float> collItemDiscvalue = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collItemTaxable =new TableColumn<>();

	@FXML
	TableColumn<BillingController.items, Float> collSgstRate = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collSgstAmount = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collCgstRate = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collCgstAmount = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collIgstRate = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collIgstAmount = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collItemTax = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collItemTotal = new TableColumn<>();

	ObservableList<BillingController.items> tableData = FXCollections.observableArrayList();

	Connection conn = Connections.b2csDBConncetion();
	String sql;
	Statement stmt;
	ResultSet rs;


	// Event Listener on Button.onAction
	/*@FXML
	public void a(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		printNode(printPane);
		//System.exit(0);
            
	}
	public static void printNode(final AnchorPane node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

	       /* Printer printer = Printer.getDefaultPrinter();
	        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
		double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
		double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
		node.getTransforms().add(new Scale(scaleX, scaleY));

	    PrinterJob job = PrinterJob.createPrinterJob();

	     node.setPrefSize(pageLayout.getPrintableWidth(), pageLayout.getPrintableHeight());
	    if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
	      boolean success = job.printPage(pageLayout, node);
	      if (success) {
	        job.endJob();
	      }
	    }*/
		/*Printer printer = Printer.getDefaultPrinter(); //get the default printer
		javafx.print.PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM); //create a pagelayout.  I used Paper.NA_LETTER for a standard 8.5 x 11 in page.
		PrinterJob job = PrinterJob.createPrinterJob();//create a printer job

		if (job.showPrintDialog(node.getScene().getWindow()))// this is very useful it allows you to save the file as a pdf instead using all of your printer's paper. A dialog box pops up, allowing you to change the "name" option from your default printer to Adobe pdf.
		{
			double pagePrintableWidth = pageLayout.getPrintableWidth(); //this should be 8.5 inches for this page layout.
			double pagePrintableHeight = pageLayout.getPrintableHeight();// this should be 11 inches for this page layout.

			node.prefHeightProperty().bind(Bindings.size(node.getChildren()).multiply(1));// If your cells' rows are variable size you add the .multiply and play with the input value until your output is close to what you want. If your cells' rows are the same height, I think you can use .multiply(1). This changes the height of your tableView to show all rows in the table.
			node.minHeightProperty().bind(node.prefHeightProperty());//You can probably play with this to see if it's really needed.  Comment it out to find out.
			node.maxHeightProperty().bind(node.prefHeightProperty());//You can probably play with this to see if it' really needed.  Comment it out to find out.

			double scaleX = pagePrintableWidth / node.getBoundsInParent().getWidth();//scaling down so that the printing width fits within the paper's width bound.
			double scaleY = scaleX; //scaling the height using the same scale as the width. This allows the writing and the images to maintain their scale, or not look skewed.
			double localScale = scaleX; //not really needed since everything is scaled down at the same ratio. scaleX is used thoughout the program to scale the print out.

			double numberOfPages = Math.ceil((node.getPrefHeight() * localScale) / pagePrintableHeight);//used to figure out the number of pages that will be printed.


			//System.out.println("pref Height: " + tblvMain.getPrefHeight());
			//System.out.println("number of pages: " + numberOfPages);


			node.getTransforms().add(new Scale(scaleX, (scaleY)));//scales the printing. Allowing the width to say within the papers width, and scales the height to do away with skewed letters and images.
			node.getTransforms().add(new Translate(0, 0));// starts the first print at the top left corner of the image that needs to be printed

			//Since the height of what needs to be printed is longer than the paper's heights we use gridTransfrom to only select the part to be printed for a given page.
			Translate gridTransform = new Translate();
			node.getTransforms().add(gridTransform);

			//now we loop though the image that needs to be printed and we only print a subimage of the full image.
			//for example: In the first loop we only pint the printable image from the top down to the height of a standard piece of paper. Then we print starting from were the last printed page ended down to the height of the next page. This happens until all of the pages are printed.
			// first page prints from 0 height to -11 inches height, Second page prints from -11 inches height to -22 inches height, etc.
			for (int i = 0; i < numberOfPages; i++) {
				gridTransform.setY(-i * (pagePrintableHeight / localScale));
				job.printPage(pageLayout, node);
			}

			job.endJob();//finally end the printing job.
		}

	}*/
        

	public void setDataFromBillingController(ObservableList<BillingController.items> list, String total, String invoiceNo,
	                                         String invoiceDate, String custName, String custNumber,float discvalue,float taxable,
	                                         float sgst, float cgst, float igst, String custGstin, String biilingAddr  ) throws SQLException{
		//first we wll set the company name and data for the header
		sql = "SELECT * FROM company_details";
		stmt = conn.createStatement();
		stmt.execute(sql);
		rs = stmt.executeQuery(sql);
		rs.next();
		companyName.setText(rs.getString("name"));
		companyAddress.setText(rs.getString("address"));
		companyGstin.setText(rs.getString("gstin"));
		companyContact.setText(rs.getString("contact"));
		this.invoiceDate.setText(invoiceDate);
		this.custName.setText(custName);
		this.custNumber.setText(custNumber);
		this.custGstin.setText(custGstin);
		this.billinAddr.setText(biilingAddr);
		totalSgst.setText(String.valueOf(sgst));
		totalCgst.setText(String.valueOf(cgst));
		totalIgst.setText(String.valueOf(igst));
		totalDiscvalue.setText(String.valueOf(discvalue));
		totalTaxable.setText(String.valueOf(taxable));

		tableData = list;
		//////////////////////////////////
		grandTotal.setText(total);

		//totaltax.setText(tax);
		this.invoiceNumber.setText(invoiceNo);
		/// aadding the data to the table
		table.setItems(tableData);
		collItemSNO.setCellValueFactory(new PropertyValueFactory<>("sno"));
		collItemId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
		collItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		collItemhsn.setCellValueFactory(new PropertyValueFactory<>("hsn"));
		collItemQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		//collItemQtytype.setCellValueFactory(new PropertyValueFactory<items, String>("qtytype"));
		collItemUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
		collIgstAmount.setCellValueFactory(new PropertyValueFactory<>("igstRate"));
		collIgstRate.setCellValueFactory(new PropertyValueFactory<>("igstAmount"));
		collSgstRate.setCellValueFactory(new PropertyValueFactory<>("sgstRate"));
		collSgstAmount.setCellValueFactory(new PropertyValueFactory<>("sgstAmount"));
		collCgstRate.setCellValueFactory(new PropertyValueFactory<>("cgstRate"));
		collCgstAmount.setCellValueFactory(new PropertyValueFactory<>("cgstAmount"));
		collItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		collItemMrp.setCellValueFactory(new PropertyValueFactory<>("mrp"));
		collItemDiscvalue.setCellValueFactory(new PropertyValueFactory<>("discvalue"));
		collItemTaxable.setCellValueFactory(new PropertyValueFactory<>("taxable"));
		collItemTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }
        
        
         public void report(ActionEvent event) throws SQLException, JRException {


		String number = invoiceNumber.getText();
		System.out.println("invoice no="+number);
                String date = invoiceDate.getText();
                String customerName = custName.getText();
                String customerAddress = billinAddr.getText();
                String customerContact = custNumber.getText();
                String customerGST = custGstin.getText();
                String totalAmount = grandTotal.getText();
                String TotalInWords = Currency.convertToIndianCurrency(totalAmount);

		try {
			JasperDesign jd = JRXmlLoader.load("C:\\Users\\MD\\Documents\\NetBeansProjects\\MD INVOICE\\src\\application\\ReportJasper.jrxml");

			sql = "SELECT * FROM selling_invoice_detail";
			Map<String, Object> conditions = new HashMap<>();
			conditions.put("invoice_no", number);
                        conditions.put("invoice_Date",date);
                        conditions.put("custName",customerName);
                        conditions.put("custAddress",customerAddress);
                        conditions.put("custContact",customerContact);
                        conditions.put("custGST",customerGST);
                        conditions.put("TotalWords", TotalInWords);
                        conditions.put("totalAmount",totalAmount);
                        
			JRDesignQuery newQuery = new JRDesignQuery();
			newQuery.setText(sql);
			jd.setQuery(newQuery);

			JasperReport jr = JasperCompileManager.compileReport(jd);
			JasperPrint jp = JasperFillManager.fillReport(jr, conditions, conn);
			JasperViewer.viewReport(jp);
		}catch (JRException e){
			JOptionPane.showMessageDialog(null,e);
		}





	}

   


}
