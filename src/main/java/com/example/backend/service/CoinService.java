package com.example.backend.service;

import com.example.backend.model.CoinDTO;
import com.example.backend.persistence.Coin;
import com.example.backend.persistence.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoinService {

    @Autowired
    private CoinRepository coinRepository;

    public CoinService(CoinRepository coinRepository){
        this.coinRepository=coinRepository ;
    }
    public List<Coin> getAllCoins() {

        return coinRepository.findAll();

    }


    public Coin updateCoin(Integer coin_id, Coin updatedCoin) {

        Optional<Coin> optionalCoin = coinRepository.findById(coin_id);

        if (optionalCoin.isPresent()) {

            Coin coin = optionalCoin.get();
            coin.setSymbol(updatedCoin.getSymbol());
            return coinRepository.save(coin);


        } else {

   throw new RuntimeException("Coin with ID"+ coin_id+"not found") ;
        }


    }
    public void addCoin(CoinDTO coinDTO) {
        Coin coin =new Coin() ;
        coin.setSymbol(coinDTO.getSymbol());
        coinRepository.save(coin) ;


    }

    public void deleteCoin(Coin coin){
        coinRepository.delete(coin);
    }

    public Optional<Coin> getCoinById(Integer coin_Id) {
       return coinRepository.findById(coin_Id) ;
    }
}