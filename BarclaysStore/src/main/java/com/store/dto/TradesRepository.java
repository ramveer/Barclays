package com.store.dto;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.store.model.Trades;

@Repository
public interface TradesRepository extends JpaRepository<Trades, Integer>{

	   @Transactional
	   @Modifying
	   @Query(value="update Trades set expired='Y' where maturityDate<?1")
	   int updateExpiredByMaturityDate(Date today);
	   
//	   @Query(value="select tradeId from Trades where tradeId=?1 and version>?2")
//	   Trades findByTradeIdAndVersion(String tradeId,int vesrion);
	   
		public List<Trades> findByTradeIdAndVersionGreaterThan(String tradeId,int vesrion);
		 
}
