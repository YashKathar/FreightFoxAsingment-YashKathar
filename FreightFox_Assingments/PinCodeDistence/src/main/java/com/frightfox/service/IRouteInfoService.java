package com.frightfox.service;

import com.frightfox.pojo.RouteInfo;

public interface IRouteInfoService {
	RouteInfo getRouteInfo(String fromPincode, String toPincode, String apiKey);
}
