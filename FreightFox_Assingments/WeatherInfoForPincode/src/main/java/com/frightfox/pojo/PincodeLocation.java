package com.frightfox.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PincodeLocation {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		
		private String pincode;
	    private Double latitude;
	    private Double longitude;
	    
	    public PincodeLocation(String pincode, Double latitude, Double longitude) {
	    	this.pincode = pincode; 
	    	this.latitude = latitude;
	    	this.longitude = longitude;
	    }
}
 