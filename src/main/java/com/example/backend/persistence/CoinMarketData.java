package com.example.backend.persistence;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="coin_market_data")
public class CoinMarketData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coin_id ;

    @Column(name = "sync_date")
    private LocalDateTime syncDate;

    @Column(name = "ath")
    private BigDecimal ath;

    @Column(name = "ath_mc")
    private BigDecimal athMc;

    @Column(name = "circulating_supply")
    private BigDecimal circulatingSupply;

    @Column(name = "total_supply")
    private BigDecimal totalSupply;

    @Column(name = "max_supply")
    private BigDecimal maxSupply;

    public int getCoin_id() {
        return coin_id;
    }

    public LocalDateTime getSyncDate() {
        return syncDate;
    }

    public BigDecimal getAth() {
        return ath;
    }

    public BigDecimal getAthMc() {
        return athMc;
    }

    public BigDecimal getCirculatingSupply() {
        return circulatingSupply;
    }

    public BigDecimal getTotalSupply() {
        return totalSupply;
    }

    public BigDecimal getMaxSupply() {
        return maxSupply;
    }

    public void setCoin_id(int coin_id) {
        this.coin_id = coin_id;
    }

    public void setSyncDate(LocalDateTime syncDate) {
        this.syncDate = syncDate;
    }

    public void setAth(BigDecimal ath) {
        this.ath = ath;
    }

    public void setAthMc(BigDecimal athMc) {
        this.athMc = athMc;
    }

    public void setCirculatingSupply(BigDecimal circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public void setTotalSupply(BigDecimal totalSupply) {
        this.totalSupply = totalSupply;
    }

    public void setMaxSupply(BigDecimal maxSupply) {
        this.maxSupply = maxSupply;
    }
}
