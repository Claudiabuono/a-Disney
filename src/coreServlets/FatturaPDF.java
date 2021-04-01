package coreServlets;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import com.itextpdf.text.BadElementException;
//import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import coreModels.beans.FatturaBean;
import coreModels.model.FatturaModel;
import coreModels.model.FatturaModelDM;
import coreModels.model.FatturaModelDS;

/**
 * Servlet implementation class FatturaPDF
 */
@WebServlet("/FatturaPDF")
public class FatturaPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	static boolean isDataSource = false;
	static FatturaModel model;
	
	static {
		if (isDataSource) {
			model = new FatturaModelDS();
		} else {
			model = new FatturaModelDM();
		}
	}
	
	 private static Font bigFont  = new Font(Font.FontFamily.TIMES_ROMAN, 18,  Font.BOLD);
	// private static Font redFont  = new Font(Font.FontFamily.TIMES_ROMAN, 12,  Font.NORMAL, BaseColor.RED);
	 private static Font subFont  = new Font(Font.FontFamily.TIMES_ROMAN, 16,  Font.BOLD);
//	 private static Font smallBold  = new Font(Font.FontFamily.TIMES_ROMAN, 12,  Font.BOLD);

    
    public FatturaPDF() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Boolean isUser = (Boolean) session.getAttribute("isUser"), isAdmin = (Boolean) session.getAttribute("isAdmin");
		
		try{
			int id= Integer.parseInt(request.getParameter("id"));
			FatturaBean fattura= null;
			if(isUser == null ? false : isUser.booleanValue() ){
				coreModels.beans.Registered user = (coreModels.beans.Registered) session.getAttribute("user");
				fattura= model.retrieveInvoice(user, id);
			}else if(isAdmin == null ? false : isAdmin.booleanValue()){
				String login= request.getParameter("search");
				
				System.out.println(login);
				coreModels.beans.Registered user = new  coreModels.beans.Registered();
				user.setLogin(login);
				fattura= model.retrieveInvoice(user, id);		
			}
			  
				 response.setContentType("application/pdf");
				 
				 generatePDF(fattura, response.getOutputStream());
		}
		catch (NumberFormatException | SQLException s ){
			s.printStackTrace();
		}
	}
	
	private static void generatePDF(coreModels.beans.FatturaBean fattura, OutputStream outStream) {
		String fileName = "fattura"+fattura.getCod()+".pdf";
		creaFile(fattura, fileName);  
		File downloadFile = new File(fileName);
	    FileInputStream inStream;
		try {
			inStream = new FileInputStream(downloadFile);
			// obtains response's output stream
		    
		    byte[] buffer = new byte[4096];
		    int bytesRead = -1;
		         
		    while ((bytesRead = inStream.read(buffer)) != -1) {
		        outStream.write(buffer, 0, bytesRead);
		     }
		    
		    inStream.close();
		    outStream.close();
		    downloadFile.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	}
	
	private static void  creaFile(coreModels.beans.FatturaBean fattura, String fileName){
		try {
		   Document document = new Document();
		   PdfWriter.getInstance(document, new FileOutputStream(fileName));
		   document.open();
		   aggiungiMetaDati(document);
		   aggiungiContenuto(document, fattura);
		   document.close();
	 } catch (Exception e) {
		   e.printStackTrace();
		  }
	}
	
	 private static void aggiungiMetaDati(Document document) {
		  document.addTitle("Fattura");
		  document.addSubject("Fattura");
		  document.addKeywords("Java, PDF, iText");
		  document.addAuthor("Disney Store");
		/*  document.addCreator("Luigi Bianchi");*/
		 }
		 
		 private static void aggiungiContenuto(Document document, coreModels.beans.FatturaBean fattura) throws DocumentException {
		 
			Paragraph p = new Paragraph();
			coreModels.beans.Registered registrato = fattura.getUser();
			SimpleDateFormat format = new SimpleDateFormat ("dd-MM-yyyy");
			/*intestazione fattura*/
			
			p.add(new Paragraph(registrato.getCognome() +" " +registrato.getName(), bigFont));
			
			  Paragraph paragraph = new Paragraph("Data: " +format.format(fattura.getDate().getTime()));
			  paragraph.setAlignment(Element.ALIGN_RIGHT);
			  p.add(paragraph);
			aggiungiLineaVuota(p, 1);
				
			/*indirizzo*/
			p.add(new Paragraph(fattura.getShipping().toString(), subFont));
			aggiungiLineaVuota(p, 2);
				
		  // Aggiungiamo una tabella
			creaTabella(p, fattura);
			aggiungiLineaVuota(p, 2);
		  
			document.add(p);
		 }
		 
		 private static void creaTabella(Paragraph p ,coreModels.beans.FatturaBean fattura) throws BadElementException {
		  PdfPTable tabella = new PdfPTable(8);
		  // tabella.setBorderColor(BaseColor.GRAY);
		  // tabella.setPadding(4);
		  // tabella.setSpacing(4);
		  // tabella.setBorderWidth(1);
		 
		  PdfPCell c1 = new PdfPCell(new Phrase("Codice prodotto"));
		  c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  c1.setGrayFill(0.8f);
		  tabella.addCell(c1);
		 
		  c1 = new PdfPCell(new Phrase("Prodotto"));
		  c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  c1.setGrayFill(0.8f);
		  tabella.addCell(c1);
		 
		  c1 = new PdfPCell(new Phrase("Descrizione"));
		  c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  c1.setGrayFill(0.8f);
		  tabella.addCell(c1);
		  tabella.setHeaderRows(1);
		  
		  c1 = new PdfPCell(new Phrase("Quantità"));
		  c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  c1.setGrayFill(0.8f);
		  tabella.addCell(c1);
		  tabella.setHeaderRows(1);
		  
		  c1 = new PdfPCell(new Phrase("Prezzo"));
		  c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  c1.setGrayFill(0.8f);
		  tabella.addCell(c1);
		  tabella.setHeaderRows(1);
		  
		  c1 = new PdfPCell(new Phrase("Sconto"));
		  c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  c1.setGrayFill(0.8f);
		  tabella.addCell(c1);
		  tabella.setHeaderRows(1);
		 
		  c1 = new PdfPCell(new Phrase("Importo"));
		  c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  c1.setGrayFill(0.8f);
		  tabella.addCell(c1);
		  tabella.setHeaderRows(1);
		  
		  c1 = new PdfPCell(new Phrase("Iva"));
		  c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  c1.setGrayFill(0.8f);
		  tabella.addCell(c1);
		  tabella.setHeaderRows(1);
		  
		  java.util.List<coreModels.beans.Order> list = fattura.getProdotti();
		  for(int i=0; i<list.size(); i++)
		  {
			  tabella.addCell(""+list.get(i).getProduct().getCode());
			  tabella.addCell(list.get(i).getProduct().getName());
			  tabella.addCell(list.get(i).getProduct().getDescription());
			  tabella.addCell(""+list.get(i).getQty());
			  tabella.addCell(list.get(i).getProduct().getPrice().toString());
			  tabella.addCell(""+list.get(i).getProduct().getDiscount());
			  tabella.addCell(list.get(i).getProduct().getPricewithIva().toString());
			  tabella.addCell(""+list.get(i).getProduct().getIva());
		  }
		 
		  p.add(tabella);
		  	
		  Paragraph paragraph = new Paragraph("Totale: " +fattura.getTotal().toString());
		  paragraph.setAlignment(Element.ALIGN_RIGHT);
		  p.add(paragraph);
		  
		 
		 }
		 
		 private static void aggiungiLineaVuota(Paragraph paragraph, int number) {
		  for (int i = 0; i < number; i++) {
		   paragraph.add(new Paragraph(" "));
		  }
		 }
		


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
