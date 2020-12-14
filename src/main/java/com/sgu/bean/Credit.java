package com.sgu.bean;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Credit {

    private CreditType creditType;
    private Client client;
    private BigDecimal totalDebt;
    private BigDecimal currentDebt;
    private BigDecimal totalFine;
    private LocalDate openDate;
    private LocalDate closeDate;

    public Credit() {
    }

    public Credit(CreditType creditType, Client client, BigDecimal totalDebt, BigDecimal currentDebt, BigDecimal totalFine, LocalDate openDate, LocalDate closeDate) {
        this.creditType = creditType;
        this.client = client;
        this.totalDebt = totalDebt;
        this.currentDebt = currentDebt;
        this.totalFine = totalFine;
        this.openDate = openDate;
        this.closeDate = closeDate;
    }

    public CreditType getCreditType() {
        return creditType;
    }

    public void setCreditType(CreditType creditType) {
        this.creditType = creditType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(BigDecimal totalDebt) {
        this.totalDebt = totalDebt;
    }

    public BigDecimal getCurrentDebt() {
        return currentDebt;
    }

    public void setCurrentDebt(BigDecimal currentDebt) {
        this.currentDebt = currentDebt;
    }

    public BigDecimal getTotalFine() {
        return totalFine;
    }

    public void setTotalFine(BigDecimal totalFine) {
        this.totalFine = totalFine;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

}
