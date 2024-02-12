package com.example.backend.controller;

import com.example.backend.persistence.CoinMarketData;
import com.example.backend.service.CoinMarketDataService;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class CoinMarketDataController {

    @Autowired
    private CoinMarketDataService coinMarketDataService;

    private static final Logger log = Logger.getLogger(CoinMarketDataController.class.getName());

// ...

    @GetMapping("/coin_market_data")
    public ResponseEntity<List<CoinMarketData>> getCoinMarketData() {
        log.info("Starting to fetch coin market data");
        List<CoinMarketData> coinMarketDataList = coinMarketDataService.getCoinMarketData();
        log.info("Successfully fetched coin market data");
        return ResponseEntity.ok(coinMarketDataList);
    }
}