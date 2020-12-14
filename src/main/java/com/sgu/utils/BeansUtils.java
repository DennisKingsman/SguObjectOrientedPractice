package com.sgu.utils;

import com.sgu.bean.Client;
import com.sgu.bean.Credit;
import com.sgu.bean.CreditStorage;
import com.sgu.bean.CreditType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BeansUtils {

    public static final int DEFAULT_CAPACITY = 5;
    private static final byte ZERO = 0;
    private static final byte CONSUMER_RATE = 22;
    private static final byte MORTGAGE_RATE = 12;
    private static final int CONSUMER_REPAYMENT_PERIOD = 4;
    private static final int MORTGAGE_REPAYMENT_PERIOD = 10;
    private static final int CONSUMER_COEFFICIENT = 100;
    private static final int MORTGAGE_COEFFICIENT = 1000;
    private static final BigDecimal INIT_DEBT = BigDecimal.valueOf(10666.45);
    private static final String COMPANY = "company ";
    private static final String ADDRESS = "address ";
    private static final String NAME = "name ";

    private BeansUtils() {

    }

    public static CreditStorage initCreditStorage() {
        List<CreditType> types = initCreditTypes();
        List<Credit> credits = new ArrayList<>();
        for (int i = 0; i < DEFAULT_CAPACITY; ++i) {
            Credit credit = makeCredit(types, i);
            credits.add(credit);
        }
        CreditStorage result = new CreditStorage();
        result.setCredits(credits);
        return result;
    }

    private static Credit makeCredit(List<CreditType> types, int index) {
        Credit credit = new Credit();
        int flag = index % 2;
        if (flag == 0) {
            credit.setTotalDebt(INIT_DEBT);
            BigDecimal residual = BigDecimal.valueOf(CONSUMER_COEFFICIENT * index);
            credit.setCurrentDebt(INIT_DEBT.subtract(residual));
        } else {
            BigDecimal totalDebt = INIT_DEBT.multiply(BigDecimal.valueOf(MORTGAGE_COEFFICIENT));
            credit.setTotalDebt(totalDebt);
            BigDecimal residual = BigDecimal.valueOf(MORTGAGE_COEFFICIENT * index);
            credit.setCurrentDebt(totalDebt.subtract(residual));
        }

        credit.setCreditType(types.get(flag));
        credit.setTotalFine(BigDecimal.valueOf(ZERO));
        credit.setOpenDate(LocalDate.now());
        credit.setCloseDate(null);
        credit.setClient(makeClient(index));
        return credit;
    }

    private static Client makeClient(int index) {
        Client client = new Client();
        client.setName(COMPANY + index);
        client.setAddress(ADDRESS + index);
        client.setPropertyType(getProperty(index % 2));
        client.setContactPerson(NAME + index);
        client.setPhoneNumber("some number");
        return client;
    }

    private static String getProperty(int validator) {
        switch (validator) {
            case 0:
                return "state";
            case 1:
                return "private";
            default:
                return "corporate";
        }
    }

    public static List<CreditType> initCreditTypes() {
        List<CreditType> creditTypes = new ArrayList<>();
        CreditType consumer = new CreditType();
        consumer.setName("consumer");
        consumer.setConditions("have a job");
        consumer.setInterestRate(CONSUMER_RATE);
        consumer.setRepaymentPeriodMonth(CONSUMER_REPAYMENT_PERIOD);
        creditTypes.add(consumer);

        CreditType mortgage = new CreditType();
        mortgage.setName("mortgage");
        mortgage.setConditions("be stupid");
        mortgage.setInterestRate(MORTGAGE_RATE);
        mortgage.setRepaymentPeriodMonth(MORTGAGE_REPAYMENT_PERIOD);
        creditTypes.add(mortgage);
        return creditTypes;
    }

}
