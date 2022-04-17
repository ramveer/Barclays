package com.store.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.store.dto.TradesRepository;
import com.store.model.Trades;

@Service
@Configuration
public class TradesService {

	@Autowired
	TradesRepository repo;
	
	public ResponseEntity<String> createTradesRecords(Trades trades) throws Exception{
		
		List<Trades>  td=repo.findByTradeIdAndVersionGreaterThan(trades.getTradeId(),trades.getVersion()); 
		if(td.size()==0) {
			trades.setCreatedDate(new Date());
			repo.save(trades);
			return ResponseEntity.status(HttpStatus.CREATED).body("Trades is added successfully.");
		}else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Trade version lower than avialble in DB, not allowed !!"); 
			//throw new Exception("Trade version lower than store data");
		}
		
		 
	}
	  
	 @Bean
	 public RestTemplate restTemplate() {
	     return new RestTemplate();
	 }
	  
	 
}
