package com.frightfox.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frightfox.pojo.InvoiceRequest;

public interface InvoiceDao extends JpaRepository<InvoiceRequest, Long> {

}
