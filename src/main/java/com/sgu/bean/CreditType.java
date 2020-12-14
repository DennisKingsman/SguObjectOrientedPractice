package com.sgu.bean;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public byte getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(byte interestRate) {
        this.interestRate = interestRate;
    }

    public int getRepaymentPeriodMonth() {
        return repaymentPeriodMonth;
    }

    public void setRepaymentPeriodMonth(int repaymentPeriodMonth) {
        this.repaymentPeriodMonth = repaymentPeriodMonth;
    }

}
