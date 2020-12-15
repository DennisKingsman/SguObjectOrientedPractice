package com.sgu.executer.main.task.three;

import com.sgu.bean.Client;
import com.sgu.bean.Credit;
import com.sgu.dao.CrudDependentDao;
import com.sgu.dao.implementation.ConnectionBuilderImpl;
import com.sgu.dao.implementation.CreditDaoImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class MainTaskThreeCredit {

    private static CrudDependentDao<Credit> creditCrudDependentDao;

    public static void main(String[] args) {
        getAll();
        setCloseDate();
        getByForeignKey();
        createDependentEntity();
        getAll();
    }

    public static void getAll(){
        creditCrudDependentDao = new CreditDaoImpl();
        ((CreditDaoImpl) creditCrudDependentDao).setConnectionBuilder(new ConnectionBuilderImpl());
        List<Credit> credits = creditCrudDependentDao.getAll();
        credits.forEach(System.out::println);
    }

    public static void setCloseDate(){
        creditCrudDependentDao = new CreditDaoImpl();
        ((CreditDaoImpl) creditCrudDependentDao).setConnectionBuilder(new ConnectionBuilderImpl());
        int res = ((CreditDaoImpl) creditCrudDependentDao).setCloseDate("Stark industries", null);
        if (res == 1){
            System.out.println("One credit closed");
        }else {
            System.out.println("Error");
        }
    }

    public static void getByForeignKey(){
        creditCrudDependentDao = new CreditDaoImpl();
        ((CreditDaoImpl) creditCrudDependentDao).setConnectionBuilder(new ConnectionBuilderImpl());
        List<Credit> credits = creditCrudDependentDao.getByForeignKey("Stark industries", new Client());
        System.out.println("got by foreign key");
        credits.forEach(System.out::println);
    }

    public static void createDependentEntity(){
        creditCrudDependentDao = new CreditDaoImpl();
        ((CreditDaoImpl) creditCrudDependentDao).setConnectionBuilder(new ConnectionBuilderImpl());
        Credit credit = new Credit();
        BigDecimal debt = BigDecimal.valueOf(5900);
        credit.setTotalDebt(debt);
        credit.setCurrentDebt(debt);
        credit.setOpenDate(LocalDate.now());
        int res = creditCrudDependentDao.createDependentBean(credit, "Oracle", "mortgage");
        if(res == 1){
            System.out.println("Entity added");
        }else {
            System.out.println("Error");
        }
    }
}
