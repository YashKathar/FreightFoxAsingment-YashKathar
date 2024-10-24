package com.frightfox.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.frightfox.dao.RouteInfoDao;
import com.frightfox.pojo.MapResponse;
import com.frightfox.pojo.RouteInfo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RouteInfoService implements IRouteInfoService {
	@Autowired
	private RouteInfoDao routeInfoDao;
	
	@Value("${GOOGLE.MAPS.API.URL}")
	private String mapUrl;
	
	@Override
	public RouteInfo getRouteInfo(String fromPincode, String toPincode, String apiKey) {
		Optional<RouteInfo> cachedRoute = Optional.ofNullable(routeInfoDao.findByFromPincodeAndToPincode(fromPincode, toPincode));
		
		  if (cachedRoute.isPresent()) {
	            return cachedRoute.get();
	        }
		  String url = String.format(mapUrl, fromPincode, toPincode, apiKey);
		  RestTemplate restTemplate = new RestTemplate();
		  MapResponse response = restTemplate.getForObject(url, MapResponse.class);
		  
		  RouteInfo routeInfo = new RouteInfo();
	       routeInfo.setFromPincode(fromPincode);
	       routeInfo.setToPincode(toPincode);
	       
	       routeInfo.setDistance(response.getRoutes().get(0).getLegs().get(0).getDistance().getText());
	       routeInfo.setDuration(response.getRoutes().get(0).getLegs().get(0).getDuration().getText());
	       routeInfo.setRoute(response.getRoutes().get(0).getSummary());
	       
	       

		return routeInfoDao.save(routeInfo);
	}

}
