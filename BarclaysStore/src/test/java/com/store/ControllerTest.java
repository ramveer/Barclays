package com.store;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.store.dto.TradesRepository;
import com.store.model.Trades;
import com.store.service.TradesService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BarclaysStoreApplicationTests.class)
@SpringBootTest
public class ControllerTest {

	@InjectMocks
	Controller tradeController;

	TestRestTemplate restTemplate = new TestRestTemplate();
	 
	
	@AfterEach
	public void afterTest() { 
		System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++\n\n\n\n\n\n\n"); 
	}

	@Test 
	@Order(1) 
	public void testTradesTransmission() throws Exception {
		Trades trades = new Trades("T1", 1, "CP-1", "B1",new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2023"), new Date(), 'N');
		ResponseEntity<String> tradesEntity = restTemplate.postForEntity("http://localhost:8080/v1/api/trades", trades,String.class);
		assertEquals(tradesEntity.getStatusCode(), HttpStatus.CREATED);
		assertEquals(tradesEntity.getBody(), "Trades is added successfully.");
 
		Trades trades3 = new Trades("T2", 2, "CP-2", "B1", new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2023"), new Date(), 'N');
		ResponseEntity<String> tradesEntity3 = restTemplate.postForEntity("http://localhost:8080/v1/api/trades",trades3, String.class);
		assertEquals(tradesEntity3.getStatusCode(), HttpStatus.CREATED);
		assertEquals(tradesEntity3.getBody(), "Trades is added successfully.");

		Trades trades4 = new Trades("T3", 3, "CP-3", "B3", new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2023"), new Date(), 'N');
		ResponseEntity<String> tradesEntity4 = restTemplate.postForEntity("http://localhost:8080/v1/api/trades",trades4, String.class);
		assertEquals(tradesEntity4.getStatusCode(), HttpStatus.CREATED);
		assertEquals(tradesEntity4.getBody(), "Trades is added successfully.");
	}
	
	//Passing same records
	@Test
	@Order(2)
	public void testTradesTransmission2() throws Exception {
		Trades trades = new Trades("T1", 1, "CP-1", "B1", new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2023"), new Date(), 'N');
		ResponseEntity<String> tradesEntity = restTemplate.postForEntity("http://localhost:8080/v1/api/trades", trades,String.class);
		assertEquals(tradesEntity.getStatusCode(), HttpStatus.CREATED);
		assertEquals(tradesEntity.getBody(), "Trades is added successfully.");

		Trades trades2 = new Trades("T1", 1, "CP-1", "B1", new SimpleDateFormat("dd/MM/yyyy").parse("25/04/2023"), new Date(), 'N');
		ResponseEntity<String> tradesEntity2 = restTemplate.postForEntity("http://localhost:8080/v1/api/trades",trades2, String.class);
		assertEquals(tradesEntity2.getStatusCode(), HttpStatus.CREATED);
		assertEquals(tradesEntity2.getBody(), "Trades is added successfully.");
 
	}
	
	//Passing same TradeId and Version but diff other data
	 @Test
	 @Order(3)
	 public void testTradesTransmission3() throws Exception {
			Trades trades = new Trades("T1", 1, "CP-1", "B1", new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2023"), new Date(), 'N');
			ResponseEntity<String> tradesEntity = restTemplate.postForEntity("http://localhost:8080/v1/api/trades", trades,String.class);
			assertEquals(tradesEntity.getStatusCode(), HttpStatus.CREATED);
			assertEquals(tradesEntity.getBody(), "Trades is added successfully.");

			Trades trades2 = new Trades("T1", 1, "CP-2", "B2", new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2023"), new Date(), 'N');
			ResponseEntity<String> tradesEntity2 = restTemplate.postForEntity("http://localhost:8080/v1/api/trades",trades2, String.class);
			assertEquals(tradesEntity2.getStatusCode(), HttpStatus.CREATED);
			assertEquals(tradesEntity2.getBody(), "Trades is added successfully.");
	  
	}
	 
	 //Passing version lower than store in data==>400
	 @Test
	 @Order(4)
	 public void testTradesTransmission4() throws Exception {
		 Trades trades3 = new Trades("T2", 2, "CP-2", "B1", new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2023"), new Date(), 'N');
			ResponseEntity<String> tradesEntity3 = restTemplate.postForEntity("http://localhost:8080/v1/api/trades",trades3, String.class);
			assertEquals(tradesEntity3.getStatusCode(), HttpStatus.CREATED);
			assertEquals(tradesEntity3.getBody(), "Trades is added successfully.");
			
			Trades trades = new Trades("T2", 1, "CP-1", "B1", new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2023"), new Date(), 'N');
			ResponseEntity<String> tradesEntity = restTemplate.postForEntity("http://localhost:8080/v1/api/trades", trades,String.class);
			assertEquals(tradesEntity.getStatusCode(), HttpStatus.BAD_REQUEST); 
			assertEquals(tradesEntity.getBody(), "Trade version lower than avialble in DB, not allowed !!"); 
	 
		}
	 
	 //Passing Muturity date lower than current date==>400
	 @Test
	 @Order(5)
	 public void testMaturityDateLowerThanCurrDate() throws Exception {
			Trades trades = new Trades("T4", 4, "CP-4", "B1", new SimpleDateFormat("dd/MM/yyyy").parse("10/04/2022"), new Date(), 'N');
			ResponseEntity<String> tradesEntity = restTemplate.postForEntity("http://localhost:8080/v1/api/trades", trades,String.class);
			assertEquals(tradesEntity.getStatusCode(), HttpStatus.BAD_REQUEST); 
			assertEquals(tradesEntity.getBody(), "Maturity Date should be greater than Today Date"); 
	 
		} 
	 
	 
	 

}
