package com.example.backend.pp.coin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/coins")
public class CoinController {

    @Autowired
    private CoinService coinService ;


    @GetMapping

    public List<Coin> getAllCoins(){

        return coinService.getAllCoins() ;

    }

    @GetMapping("/{coin_id}")
    public ResponseEntity<Coin> getCoinById(@PathVariable Integer coin_id) {
        Optional<Coin> coinOptional = coinService.getCoinById(coin_id);
        if (coinOptional.isPresent()) {
            return ResponseEntity.ok(coinOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{coin_id}")

    public Coin updateCoin(@PathVariable Integer coin_id ,@RequestBody Coin updatedCoin){
        return coinService.updateCoin(coin_id,updatedCoin) ;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCoin(@RequestBody CoinDTO coinDTO) {

        coinService.addCoin(coinDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Coin added successfully");
    }
  @DeleteMapping("/{coin_id}")

    public ResponseEntity<String>deleteCoin(@PathVariable Integer coin_id) {

      Optional<Coin>optionalCoin=coinService.getCoinById(coin_id);

      if(optionalCoin.isPresent()){
          Coin existingCoin = optionalCoin.get() ;

          coinService.deleteCoin(existingCoin);
          return ResponseEntity.ok("Coin deleted successfully");
      }else {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coin with ID "+coin_id+"not found");

      }


  }

}
