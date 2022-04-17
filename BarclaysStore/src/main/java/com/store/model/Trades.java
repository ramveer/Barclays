package com.store.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.springframework.http.HttpStatus;

import com.store.exception.CustomException;
  
@Entity
@IdClass(CompositeKey.class)
public class Trades {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	int id;
	
	@Id
	private String tradeId;
	@Id
	private int version;
	@Column(nullable=false)
	private String counterPartyId;
	@Column(nullable=false)
	private String bookId;
	 
	@Column(nullable=false)
	private Date maturityDate;
	@Column(nullable=false)
	private Date createdDate;
	@Column(nullable=false)
	private char expired;
	
	public Trades() {
		
	}
	
	public Trades(String tradeId, int version, String counterPartyId, String bookId, Date maturityDate,
			Date createdDate, char expired) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.createdDate = createdDate;
		this.expired = expired;
	}
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getCounterPartyId() {
		return counterPartyId;
	}
	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public Date getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(Date maturityDate) throws Exception {
		Date today=new Date();
		if(maturityDate.before(today)) {
			throw new CustomException("Maturity Date should be greater than Today Date",HttpStatus.BAD_REQUEST);
		}else {
		 this.maturityDate = maturityDate;
		}
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public char getExpired() {
		return expired;
	}
	public void setExpired(char expired) {
		this.expired = expired;
	}

	
	
}


class CompositeKey implements Serializable{
 
	private String tradeId;
 
	private int version;
	
}