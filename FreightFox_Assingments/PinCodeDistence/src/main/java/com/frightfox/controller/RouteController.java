package com.frightfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frightfox.dto.RouteInfoDto;
import com.frightfox.pojo.RouteInfo;
import com.frightfox.service.IRouteInfoService;

@RestController
@RequestMapping("/api/routes")
public class RouteController {
	  @Autowired
	  private IRouteInfoService routeInfoService;
	  
	  @PostMapping
	  public RouteInfo createRoute(@RequestBody RouteInfoDto request) {
	      String apiKey = "YOUR_GOOGLE_MAPS_API_KEY"; // For the key It needs payment to a google so thats the pending work only apiKey
	      return routeInfoService.getRouteInfo(request.getFromPincode(), request.getToPincode(), apiKey);
	  }
}
