package com.example.backend.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "coin", schema = "TradingDashboard")
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer coin_Id ;
    private String symbol ;

    public Integer getCoinId() {
        return coin_Id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setCoinId(Integer coin_Id) {
        this.coin_Id = coin_Id;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
