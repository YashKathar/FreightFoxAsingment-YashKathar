package com.frightfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.frightfox.pojo.WetherInfo;
import com.frightfox.service.IWetherService;

@RestController
@RequestMapping("/api/weather")
public class WetherController {
	@Autowired
	private  IWetherService weatherService;

   
	/* http://localhost:8080/api/weather?pincode=411014&forDate=2020-10-15 */
    @GetMapping
    public WetherInfo getWeather(@RequestParam String pincode, @RequestParam String forDate) {
    	System.out.println(pincode);
    	System.out.println(forDate);
        return weatherService.getWeatherInfo(pincode, forDate);
    }
}
