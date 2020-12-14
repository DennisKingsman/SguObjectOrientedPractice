package com.sgu.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "creditType")
public class CreditType {

    private String name;
    private String conditions;
    private byte interestRate;
    private int repaymentPeriodMonth;

    public CreditType() {
    }

    public CreditType(String name, String conditions, byte interestRate, int repaymentPeriodMonth) {
        this.name = name;
        this.conditions = conditions;
        this.interestRate = interestRate;
        this.repaymentPeriodMonth = repaymentPeriodMonth;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "creditTypeName")
    public void setName(String name) {
        this.name = name;
    }

    public String getConditions() {
        return conditions;
    }

    @XmlElement
    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public byte getInterestRate() {
        return interestRate;
    }

    @XmlElement
    public void setInterestRate(byte interestRate) {
        this.interestRate = interestRate;
    }

    public int getRepaymentPeriodMonth() {
        return repaymentPeriodMonth;
    }

    @XmlElement
    public void setRepaymentPeriodMonth(int repaymentPeriodMonth) {
        this.repaymentPeriodMonth = repaymentPeriodMonth;
    }

    @Override
    public String toString() {
        return "CreditType{" + "name='" + name + '\'' +
                ", conditions='" + conditions + '\'' +
                ", interestRate='" + interestRate + '\'' +
                ", repaymentPeriodMonth='" + repaymentPeriodMonth + '\'' +
                '}';
    }

}
