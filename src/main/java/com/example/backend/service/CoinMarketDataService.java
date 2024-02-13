package com.example.backend.service;

import com.example.backend.persistence.CoinMarketData;
import com.example.backend.persistence.CoinMarketDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CoinMarketDataService {

    @Autowired
    private CoinMarketDataRepository coinMarketDataRepository;

    @Value("${fb685498-6401-4c7a-9320-1d6f1c42f5bf}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<CoinMarketData> fetchCoinMarketData() {
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        List<CoinMarketData> coinMarketDataList = new ArrayList<>();
        int start = 1;
        int limit = 5000;
        boolean hasMore = true;
        while (hasMore) {
            ResponseEntity<String> response = restTemplate.exchange(url + "?start=" + start + "&limit=" + limit, HttpMethod.GET, entity, String.class);
            Map<String, Object> jsonResponse = objectMapper.convertValue(response.getBody(), Map.class);
            List<Map<String, Object>> data = (List<Map<String, Object>>) jsonResponse.get("data");
            if (data.size() < limit) {
                hasMore = false;
            }
            for (Map<String, Object> map : data) {
                CoinMarketData coinMarketData = new CoinMarketData();
                coinMarketData.setCoin_id(Integer.parseInt(map.get("coin_id").toString()));
                coinMarketData.setSyncDate(LocalDateTime.now());
                coinMarketData.setAth(new BigDecimal(map.get("ath").toString()));
                coinMarketData.setAthMarketCap(new BigDecimal(map.get("ath_market_cap").toString()));
                coinMarketData.setCirculatingSupply(new BigDecimal(map.get("circulating_supply").toString()));
                coinMarketData.setTotalSupply(new BigDecimal(map.get("total_supply").toString()));
                coinMarketData.setMaxSupply(new BigDecimal(map.get("max_supply").toString()));
                coinMarketDataList.add(coinMarketData);
            }
            start += limit;
            try {
                Thread.sleep(60000 / 10); // Sleep for 60 seconds to avoid hitting the rate limit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        coinMarketDataRepository.saveAll(coinMarketDataList);
        return coinMarketDataList;
    }

    public List<CoinMarketData> getCoinMarketData() {
        return coinMarketDataRepository.findAll();
    }
}