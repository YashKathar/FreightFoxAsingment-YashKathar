package com.frightfox.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frightfox.pojo.RouteInfo;

public interface RouteInfoDao extends JpaRepository<RouteInfo, Long> {
	RouteInfo findByFromPincodeAndToPincode(String fromPincode, String toPincode);
}
