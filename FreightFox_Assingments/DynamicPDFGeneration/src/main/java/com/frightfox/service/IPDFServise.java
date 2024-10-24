package com.frightfox.service;

import java.io.File;

import com.frightfox.pojo.InvoiceRequest;

public interface IPDFServise {
	 File generateInvoicePdf(InvoiceRequest invoiceRequest) throws Exception;
}
