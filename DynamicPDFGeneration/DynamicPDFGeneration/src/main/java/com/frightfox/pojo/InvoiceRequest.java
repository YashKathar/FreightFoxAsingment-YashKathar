package com.frightfox.pojo;



import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class InvoiceRequest {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long invoiceRequestId; 
		private String seller;
	    private String sellerGstin;
	    private String sellerAddress;
	    private String buyer;
	    private String buyerGstin;
	    private String buyerAddress;
	    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	    @JoinColumn(name = "fk_invoiceRequest_id", referencedColumnName = "invoiceRequestId")
	    private List<Item> items;
}
