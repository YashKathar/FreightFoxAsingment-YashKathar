package com.frightfox.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frightfox.pojo.PincodeLocation;

public interface PincodeLocationDao extends JpaRepository<PincodeLocation, Long> {
	PincodeLocation findByPincode(String pincode);
}
