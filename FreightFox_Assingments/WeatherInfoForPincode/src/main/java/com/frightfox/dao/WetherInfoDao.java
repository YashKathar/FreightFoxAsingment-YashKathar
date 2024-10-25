package com.frightfox.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frightfox.pojo.WetherInfo;

public interface WetherInfoDao extends JpaRepository<WetherInfo, Long> {
	WetherInfo findByPincodeAndDate(String pincode, String date);
}
