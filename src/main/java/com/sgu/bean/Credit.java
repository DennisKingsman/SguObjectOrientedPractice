package com.sgu.bean;

import com.sgu.utils.LocalDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlRootElement(name = "credit")
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

    @XmlElement(name = "creditType")
    public void setCreditType(CreditType creditType) {
        this.creditType = creditType;
    }

    public Client getClient() {
        return client;
    }

    @XmlElement(name = "client")
    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getTotalDebt() {
        return totalDebt;
    }

    @XmlElement
    public void setTotalDebt(BigDecimal totalDebt) {
        this.totalDebt = totalDebt;
    }

    public BigDecimal getCurrentDebt() {
        return currentDebt;
    }

    @XmlElement
    public void setCurrentDebt(BigDecimal currentDebt) {
        this.currentDebt = currentDebt;
    }

    public BigDecimal getTotalFine() {
        return totalFine;
    }

    @XmlElement
    public void setTotalFine(BigDecimal totalFine) {
        this.totalFine = totalFine;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    @XmlElement
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    @XmlElement
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    @Override
    public String toString() {
        return "Credit{" + "client='" + client + '\'' +
                ", creditType='" + creditType + '\'' +
                ", totalDebt='" + totalDebt + '\'' +
                ", currentDebt='" + currentDebt + '\'' +
                ", totalFine='" + totalFine + '\'' +
                ", openDate='" + openDate + '\'' +
                ", closeDate='" + closeDate + '\'' +
                '}';
    }

}
