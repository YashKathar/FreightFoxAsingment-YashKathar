package com.frightfox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frightfox.dao.InvoiceDao;
import com.frightfox.dao.ItemDao;
import com.frightfox.pojo.InvoiceRequest;
import com.frightfox.pojo.Item;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InvoiceService implements IInvoiceService {
	@Autowired
	private InvoiceDao invoiceDao;
	@Autowired
	private ItemDao itemDao;
	
	@Override
	public InvoiceRequest addInvoiceaddInvoiceRequest(InvoiceRequest invoiceRequest) {	
		itemDao.saveAll(invoiceRequest.getItems());
		InvoiceRequest save = invoiceDao.save(invoiceRequest);
		return save;
	}

}
