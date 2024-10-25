package com.frightfox.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.frightfox.dao.PincodeLocationDao;
import com.frightfox.dao.WetherInfoDao;
import com.frightfox.pojo.NominatimResponse;
import com.frightfox.pojo.PincodeLocation;
import com.frightfox.pojo.WetherInfo;

import jakarta.transaction.Transactional; 

@Service
@Transactional
public class WetherService implements IWetherService {
	
	    @Value("${openweathermap.api.key}")
	    private String WEATHER_API_KEY;

	   
	    private final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/search?postalcode=%s&country=India&format=json";
	    
	    @Autowired
	    private PincodeLocationDao pincodeLocationRepository;
	    
	    @Autowired
	    private WetherInfoDao weatherInfoRepository;

	   
	    
	@Override
	public WetherInfo getWeatherInfo(String pincode, String forDate) {
		
		Optional<WetherInfo> cachedWeather = Optional.ofNullable(weatherInfoRepository.findByPincodeAndDate(pincode, forDate));
		
		 if (cachedWeather.isPresent()) {
	            return cachedWeather.get();
	       }
		 
		 	PincodeLocation location = pincodeLocationRepository.findByPincode(pincode);
	        
		 	if (location == null) {
		 		System.out.println("in location is  null "+pincode);
		 		 location = getCoordinatesByPostalCode(pincode);  
	        }
		 	
		 	 if (location == null) {
		         throw new RuntimeException("Location not found for postal code: " + pincode);
		     }
		 	
		 	String weatherData = fetchWeatherData(location.getLatitude(), location.getLongitude(), forDate);
		 	
		 	 WetherInfo weatherInfo = new WetherInfo();
		     weatherInfo.setPincode(pincode);
		     if(weatherData.length() > 300) {
		    	 
		    	 weatherInfo.setWeatherData(weatherData.substring(0, 200));
		     }
		     weatherInfo.setDate(forDate);
		     weatherInfoRepository.save(weatherInfo);
		     return weatherInfo;
	}

	@Override
	public String fetchWeatherData(double lat, double lon, String forDate) {
		 RestTemplate restTemplate = new RestTemplate();
	        String url = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&date=%s&appid=%s",
	                lat, lon, forDate, WEATHER_API_KEY);
	        return restTemplate.getForObject(url, String.class);
	}
	

	@Override
	public PincodeLocation getCoordinatesByPostalCode(String pincode) {
		String url = String.format(NOMINATIM_URL, pincode);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<NominatimResponse>> response =restTemplate.exchange(
	            url,
	            HttpMethod.GET,
	            null,
	            new ParameterizedTypeReference<List<NominatimResponse>>() {}
	        );	
		 List<NominatimResponse> locations = response.getBody();
		
		 
		 if (locations != null && !locations.isEmpty()) {
			 
	            NominatimResponse location = locations.get(0); // Get the first result
	             PincodeLocation pincodeLocation = new PincodeLocation(pincode, Double.parseDouble(location.getLat()), Double.parseDouble(location.getLon()));
	             return pincodeLocation;
		 }
		 else {
	            throw new RuntimeException("Location not found for postal code: " + pincode);
	        }
	}

}
