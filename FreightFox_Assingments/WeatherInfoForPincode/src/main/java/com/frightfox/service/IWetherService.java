package com.frightfox.service;

import com.frightfox.pojo.PincodeLocation;
import com.frightfox.pojo.WetherInfo;

public interface IWetherService {
	WetherInfo getWeatherInfo(String pincode, String forDate);
	String fetchWeatherData(double lat, double lon, String forDate);
	PincodeLocation getCoordinatesByPostalCode(String pincode);
}
