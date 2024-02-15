package com.example.backend.pp.coin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoinMarketDataController {

    private static final Logger log = LoggerFactory.getLogger(CoinMarketDataController.class);

    @Autowired
    private CoinMarketDataService coinMarketDataService;

    @GetMapping("/coin_market_data")
    public ResponseEntity<CoinMarketDataResponse> getCoinMarketData() {
        log.info("Starting to fetch coin market data");
        List<CoinMarketData> coinMarketDataList = coinMarketDataService.getCoinMarketData();
        log.info("Successfully fetched {} coin market data records", coinMarketDataList.size());
        return ResponseEntity.ok(new CoinMarketDataResponse(coinMarketDataList));
    }
}

class CoinMarketDataResponse {

    private List<CoinMarketData> coinMarketDataList;

    public CoinMarketDataResponse(List<CoinMarketData> coinMarketDataList) {
        this.coinMarketDataList = coinMarketDataList;
    }

    public List<CoinMarketData> getCoinMarketDataList() {
        return coinMarketDataList;
    }

    public void setCoinMarketDataList(List<CoinMarketData> coinMarketDataList) {
        this.coinMarketDataList = coinMarketDataList;
    }
}