package com.sgu.executer.main.task.three;

import com.sgu.bean.CreditType;
import com.sgu.dao.CrudDao;
import com.sgu.dao.implementation.ConnectionBuilderImpl;
import com.sgu.dao.implementation.CreditTypeDaoImpl;

import java.util.List;

public class MainTaskThreeCreditType {

    private static CrudDao<CreditType> creditTypeCrudDao;

    public static void main(String[] args) {
        getAllTypes();
        addCreditType();
        getAllTypes();
    }

    public static void getAllTypes(){
        creditTypeCrudDao = new CreditTypeDaoImpl();
        ((CreditTypeDaoImpl) creditTypeCrudDao).setConnectionBuilder(new ConnectionBuilderImpl());
        List<CreditType> creditTypes = creditTypeCrudDao.getAll();
        creditTypes.forEach(System.out::println);
    }

    public static void addCreditType(){
        CreditType creditType = new CreditType();
        creditType.setName("auto cred");
        creditType.setConditions("have a job");
        creditType.setInterestRate((byte) 18);
        creditType.setRepaymentPeriodMonth(12 * 6);
        creditTypeCrudDao = new CreditTypeDaoImpl();
        ((CreditTypeDaoImpl) creditTypeCrudDao).setConnectionBuilder(new ConnectionBuilderImpl());
        int res = creditTypeCrudDao.create(creditType);
        if(res == 1){
            System.out.println("Auto cred successfully added");
        }else {
            System.out.println("Error");
        }
    }

}
