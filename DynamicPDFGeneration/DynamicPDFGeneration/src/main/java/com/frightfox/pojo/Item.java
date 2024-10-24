package com.frightfox.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long itemId;
	 private String name;
     private String quantity;
     private Double rate;
     private Double amount;
   
     
     
}
