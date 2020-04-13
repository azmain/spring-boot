package azmain.github.io.domain.covid;

import java.math.BigInteger;

public class CovidDailyDataReport {

    private String country;
    private BigInteger totalCases;
    private BigInteger newCases;
    private BigInteger totalDeaths;
    private BigInteger newDeaths;
    private BigInteger totalRecovered;

    public String getCountry() {
        return country;
    }

    public CovidDailyDataReport setCountry(String country) {
        this.country = country;
        return this;
    }

    public BigInteger getTotalCases() {
        return totalCases;
    }

    public CovidDailyDataReport setTotalCases(BigInteger totalCases) {
        this.totalCases = totalCases;
        return this;
    }

    public BigInteger getNewCases() {
        return newCases;
    }

    public CovidDailyDataReport setNewCases(BigInteger newCases) {
        this.newCases = newCases;
        return this;
    }

    public BigInteger getTotalDeaths() {
        return totalDeaths;
    }

    public CovidDailyDataReport setTotalDeaths(BigInteger totalDeaths) {
        this.totalDeaths = totalDeaths;
        return this;
    }

    public BigInteger getNewDeaths() {
        return newDeaths;
    }

    public CovidDailyDataReport setNewDeaths(BigInteger newDeaths) {
        this.newDeaths = newDeaths;
        return this;
    }

    public BigInteger getTotalRecovered() {
        return totalRecovered;
    }

    public CovidDailyDataReport setTotalRecovered(BigInteger totalRecovered) {
        this.totalRecovered = totalRecovered;
        return this;
    }
}
