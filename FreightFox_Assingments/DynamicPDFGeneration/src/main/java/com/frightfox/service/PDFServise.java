package com.frightfox.service;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.frightfox.pojo.InvoiceRequest;
import com.frightfox.pojo.Item;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PDFServise implements IPDFServise {
	
	@Value("${pdf.storage.path}")
	private String pdfStoragePath;
	
	@Override
	public File generateInvoicePdf(InvoiceRequest invoiceRequest) throws Exception {
		
		String pdfFileName = pdfStoragePath + invoiceRequest.hashCode()+ ".pdf";
		File pdfFile = new File(pdfFileName);
		
		if(pdfFile.exists()) {
			return pdfFile;
		}
		
		new File(pdfStoragePath).mkdirs();
		
		ITextRenderer renderer = new ITextRenderer();
		
		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder.append("<!DOCTYPE html>");
		htmlBuilder.append("<html lang=\"en\">");
		htmlBuilder.append("<head>");
		htmlBuilder.append("<meta charset=\"UTF-8\"/>");  // Self-closing tag
		htmlBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>"); // Self-closing tag
		htmlBuilder.append("<title>Invoice</title>");
		htmlBuilder.append("<style>");
		htmlBuilder.append("table { width: 100%; border-collapse: collapse; }");
		htmlBuilder.append("table, th, td { border: 1px solid black; padding: 10px; text-align: left; }");
		htmlBuilder.append("th, td { padding: 10px; }");
		htmlBuilder.append(".note { font-size: 12px; }");
		htmlBuilder.append("</style>");
		htmlBuilder.append("</head>");
		htmlBuilder.append("<body>");
		htmlBuilder.append("<h2>Invoice</h2>");
		htmlBuilder.append("<table>");
		htmlBuilder.append("<tr>");
		htmlBuilder.append("<td><strong>Seller:</strong><br/>")
		           .append(invoiceRequest.getSeller()).append("<br/>")
		           .append(invoiceRequest.getSellerAddress()).append("<br/>")
		           .append("GSTIN: ").append(invoiceRequest.getSellerGstin()).append("</td>");
		htmlBuilder.append("<td><strong>Buyer:</strong><br/>")
		           .append(invoiceRequest.getBuyer()).append("<br/>")
		           .append(invoiceRequest.getBuyerAddress()).append("<br/>")
		           .append("GSTIN: ").append(invoiceRequest.getBuyerGstin()).append("</td>");
		htmlBuilder.append("</tr>");
		htmlBuilder.append("</table>");
		htmlBuilder.append("<table>");
		htmlBuilder.append("<thead>");
		htmlBuilder.append("<tr>");
		htmlBuilder.append("<th>Item</th><th>Quantity</th><th>Rate</th><th>Amount</th>");
		htmlBuilder.append("</tr>");
		htmlBuilder.append("</thead>");
		htmlBuilder.append("<tbody>");

		for (Item item : invoiceRequest.getItems()) {
		    htmlBuilder.append("<tr>");
		    htmlBuilder.append("<td>").append(item.getName()).append("</td>");
		    htmlBuilder.append("<td>").append(item.getQuantity()).append(" Nos</td>");
		    htmlBuilder.append("<td>").append(item.getRate()).append("</td>");
		    htmlBuilder.append("<td>").append(item.getAmount()).append("</td>");
		    htmlBuilder.append("</tr>");
		}
		htmlBuilder.append("</tbody>");
		htmlBuilder.append("</table><br/>");
		htmlBuilder.append("</body>");
		htmlBuilder.append("</html>");
		
		renderer.setDocumentFromString(htmlBuilder.toString());
		renderer.layout();
		FileOutputStream os = new FileOutputStream(pdfFile);
		renderer.createPDF(os);
		os.close();
		
		return pdfFile;
	}

}
