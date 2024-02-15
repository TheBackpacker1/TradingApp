package com.example.backend.pp.coin;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="coin_market_data")
public class CoinMarketData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "Coin ID must be greater than 0")
    private int coin_id;


    @Column(name = "name")
    @NotNull(message = "Name  cannot be null")
    private String name ;
    @Column(name = "price")
    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    @Column(name = "sync_date")
    @NotNull(message = "Sync date cannot be null")
    private LocalDateTime sync_date;

    @Column(name = "ath")
    @DecimalMin(value = "0.0", message = "ATH must be a positive number")
    private BigDecimal ath;

    @Column(name = "ath_mc")
    @DecimalMin(value = "0.0", message = "ATH Market Cap must be a positive number")
    private BigDecimal ath_mc;

    @Column(name = "circulating_supply")
    @DecimalMin(value = "0.0", message = "Circulating Supply must be a positive number")
    private BigDecimal circulating_supply;

    @Column(name = "total_supply")
    @DecimalMin(value = "0.0", message = "Total Supply must be a positive number")
    private BigDecimal total_supply;

    @Column(name = "max_supply")
    @DecimalMin(value = "0.0", message = "Max Supply must be a positive number")
    private BigDecimal max_supply;

    public int getCoin_id() {
        return coin_id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getSyncDate() {
        return sync_date;
    }

    public BigDecimal getAth() {
        return ath;
    }

    public BigDecimal getAthMarketCap() {
        return ath_mc;
    }

    public BigDecimal getCirculatingSupply() {
        return circulating_supply;
    }

    public BigDecimal getTotalSupply() {
        return total_supply;
    }

    public BigDecimal getMaxSupply() {
        return max_supply;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCoin_id(int coin_id) {
        this.coin_id = coin_id;
    }

    public void setSyncDate(LocalDateTime sync_date) {
        this.sync_date = sync_date;
    }

    public void setAth(BigDecimal ath) {
        this.ath = ath;
    }

    public void setAthMarketCap(BigDecimal ath_mc) {
        this.ath_mc= ath_mc;
    }

    public void setCirculatingSupply(BigDecimal circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public void setTotalSupply(BigDecimal total_supply) {
        this.total_supply = total_supply;
    }

    public void setMaxSupply(BigDecimal max_supply) {
        this.max_supply = max_supply;
    }

}
