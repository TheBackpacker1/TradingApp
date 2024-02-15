package com.example.backend.pp.coin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

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

    @Scheduled(fixedDelayString = "PT1H")
    public void fetchCoinMarketData() {
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-CMC_PRO_API_KEY", apiKey)
                .build();
        Flux<Map> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("start", 1)
                        .queryParam("limit", 5000)
                        .build())
                .retrieve()
                .bodyToFlux(Map.class);
        List<CoinMarketData> coinMarketDataList = new ArrayList<>();
        response.flatMap(jsonResponse -> {
            List<Map<String, Object>> data = (List<Map<String, Object>>) jsonResponse.get("data");
            return Flux.fromIterable(data);
        }).flatMap(map -> {
            CoinMarketData coinMarketData = new CoinMarketData();
            coinMarketData.setCoin_id(Integer.parseInt(map.get("coin_id").toString()));
            coinMarketData.setSyncDate(LocalDateTime.now());
            coinMarketData.setAth(BigDecimal.valueOf(Double.parseDouble(map.get("ath").toString())));
            coinMarketData.setName(map.get("name").toString());
            Map<String, Map<String, BigDecimal>> quote = (Map<String, Map<String, BigDecimal>>) map.get("quote");
            coinMarketData.setPrice(quote.get("USD").get("price"));
            coinMarketData.setAthMarketCap(BigDecimal.valueOf(Double.parseDouble(map.get("ath_mc").toString())));
            coinMarketData.setCirculatingSupply(BigDecimal.valueOf(Double.parseDouble(map.get("circulating_supply").toString())));
            coinMarketData.setTotalSupply(BigDecimal.valueOf(Double.parseDouble(map.get("total_supply").toString())));
            coinMarketData.setMaxSupply(BigDecimal.valueOf(Double.parseDouble(map.get("max_supply").toString())));
            coinMarketDataList.add(coinMarketData);
            return Flux.just(coinMarketData);
        }).blockLast();
       coinMarketDataRepository.saveAll(coinMarketDataList);
    }

    public List<CoinMarketData> getCoinMarketData() {
        return coinMarketDataRepository.findAll();
    }
}