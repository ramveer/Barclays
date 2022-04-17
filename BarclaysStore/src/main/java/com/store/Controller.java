package com.store;

import java.util.Calendar;

 
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
import com.store.model.Trades;
import com.store.service.TradesService;

@RestController
public class Controller {
    @Autowired 
	TradesService service;
	
	@PostMapping("v1/api/trades")
	public ResponseEntity<String> tradesTransmission(@Valid @RequestBody Trades trades) throws Exception{ 
		 
		if(trades!=null) { 
			return service.createTradesRecords(trades); 
		 }else {
			return ResponseEntity.badRequest().body("Trades datails are not passed !");
		 } 
	} 
	

}
