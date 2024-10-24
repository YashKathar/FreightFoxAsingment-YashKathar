package com.frightfox.controller;

import java.io.File;

import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.frightfox.pojo.InvoiceRequest;
import com.frightfox.service.IInvoiceService;
import com.frightfox.service.IPDFServise;

@RestController
@RequestMapping("/invoicerequest/")
public class InvoiceController {
	
	@Autowired
	private IInvoiceService invoiceRequestService;
	@Autowired
	private IPDFServise pdfService;
	
	/* http://localhost:8080/assingment_1/invoicerequest/addinvoicerequest */
	@PostMapping("addinvoicerequest")
	public ResponseEntity<byte[]> addInvoiceRequest(@RequestBody InvoiceRequest invoiceRequest) {
		try {	
			   File pdfFile = pdfService.generateInvoicePdf(invoiceRequest);
			   byte[] pdfContent = Files.readAllBytes(pdfFile.toPath());
			   HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_PDF);
	            headers.setContentDispositionFormData("attachment", pdfFile.getName());
			   
				InvoiceRequest addInvoiceaddInvoiceRequest = invoiceRequestService.addInvoiceaddInvoiceRequest(invoiceRequest);
			return new ResponseEntity<>(pdfContent, headers,  HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating PDF "+e.getMessage(), e);
		}
	}
}
