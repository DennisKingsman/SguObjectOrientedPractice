package com.sgu.dao.implementation;

import com.sgu.bean.Client;
import com.sgu.bean.Credit;
import com.sgu.bean.CreditType;
import com.sgu.dao.ConnectionBuilder;
import com.sgu.dao.CrudDependentDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreditDaoImpl implements CrudDependentDao<Credit> {

    private static final String CREATE = "insert into credit(credit_type_name, client_name, total_debt, current_debt, open_date) values \n" +
            "(?, ?, ?, ?, ?);";
    private static final String DELETE_BY_NAME = "delete from credit where client_name = ?;";
    private static final String GET_BY_CLIENT_NAME = "select * from credit where client_name = ?;";
    private static final String GET_BY_CREDIT_TYPE_NAME = "select * from credit where credit_type_name = ?;";
    private static final String GET_ALL = "select * from credit;";
    private static final String UPDATE_SET_CLOSE_DATE = "update credit set current_debt = 0, close_date = ? where client_name = ? and credit_type_name = ?;";
    private static final String UPDATE_SET_CLOSE_DATE_WITH_ONE_PARAM = "update credit set current_debt = 0, close_date = ? where client_name = ?;";

    private ConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder){
        this.connectionBuilder = connectionBuilder;
    }

    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }

    /**
     * @see CreditDaoImpl use getByForeignKey method
     * @param name not valid
     * @return NULL
     */
    @Override
    public Credit getByName(String name) {
        return null;
    }

    @Override
    public List<Credit> getAll() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                List<Credit> credits = new ArrayList<>();
                do {
                    Credit credit = getFromResultSet(resultSet);
                    credits.add(credit);
                }while (resultSet.next());
                return credits;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * u can use method with one param
     * @param clientName required
     * @param creditTypeName not required
     * @return updated row count
     */
    public int setCloseDate(String clientName, String creditTypeName){
        String stmt;
        boolean nullable = creditTypeName == null;
        if(nullable){
            stmt = UPDATE_SET_CLOSE_DATE_WITH_ONE_PARAM;
        }else {
            stmt = UPDATE_SET_CLOSE_DATE;
        }
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(stmt)){
            Date closeDate = Date.valueOf(LocalDate.now());
            int counter = 1;
            preparedStatement.setDate(counter++, closeDate);
            preparedStatement.setString(counter++, clientName);
            if(!nullable) {
                preparedStatement.setString(counter, creditTypeName);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private Credit getFromResultSet(ResultSet resultSet){
        try {
            Credit credit = new Credit();
            credit.setResponsibleClientName(resultSet.getString("credit_type_name"));
            credit.setCreditTypeName(resultSet.getString("client_name"));
            credit.setTotalDebt(resultSet.getBigDecimal("total_debt"));
            credit.setCurrentDebt(resultSet.getBigDecimal("current_debt"));
            credit.setTotalFine(resultSet.getBigDecimal("total_fine"));
            Date openDate = resultSet.getDate("open_date");
            credit.setOpenDate(openDate.toLocalDate());
            Date closeDate = resultSet.getDate("close_date");
            credit.setCloseDate(closeDate.toLocalDate());
            return credit;
        } catch (SQLException e) {
            System.out.println("Error in getFromResultSet method");
        }
        return new Credit();
    }

    /**
     * Credit has two foreign keys to query of
     * use fake object one of them to validate method
     * @param key key name
     * @param object input new Client or new CreditType to validate queries
     * @return credits by foreign key
     */
    @Override
    public List<Credit> getByForeignKey(String key, Object object) {
        String stmt;
        if(object.getClass() == Client.class){
            stmt = GET_BY_CLIENT_NAME;
        }else if(object.getClass() == CreditType.class){
            stmt = GET_BY_CREDIT_TYPE_NAME;
        }else {
            return new ArrayList<>();
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(stmt)){
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                List<Credit> credits = new ArrayList<>();
                do {
                    Credit credit = getFromResultSet(resultSet);
                    credits.add(credit);
                }while (resultSet.next());
                return credits;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int createDependentBean(Credit bean, String responsibleClientName, String creditTypeName) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)){
            int counter = 1;
            preparedStatement.setString(counter++, creditTypeName);
            preparedStatement.setString(counter++, responsibleClientName);
            preparedStatement.setBigDecimal(counter++, bean.getTotalDebt());
            preparedStatement.setBigDecimal(counter++, bean.getCurrentDebt());
            Date openDate = Date.valueOf(LocalDate.now());
            preparedStatement.setDate(counter,openDate);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteByName(String clientName) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_NAME)){
            preparedStatement.setString(1, clientName);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

}
