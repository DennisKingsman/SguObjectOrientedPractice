package com.sgu.dao.implementation;

import com.sgu.bean.CreditType;
import com.sgu.dao.ConnectionBuilder;
import com.sgu.dao.CrudDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditTypeDaoImpl implements CrudDao<CreditType> {

    private static final String CREATE = "insert into credit_type(name, conditions, interest_rate, repayment_period_month) " +
            "values (?, ?, ?, ?);";
    private static final String DELETE_BY_NAME = "delete from credit_type where name = ?;";
    private static final String GET_BY_NAME = "select * from credit_type where name = ?;";
    private static final String GET_ALL = "select * from credit_type;";

    private ConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder){
        this.connectionBuilder = connectionBuilder;
    }

    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }

    @Override
    public CreditType getByName(String name) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME)){
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                CreditType creditType = new CreditType();
                creditType.setName(resultSet.getString("name"));
                creditType.setConditions(resultSet.getString("conditions"));
                creditType.setInterestRate(resultSet.getByte("interest_rate"));
                creditType.setRepaymentPeriodMonth(resultSet.getInt("repayment_period_month"));
                return creditType;
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new CreditType();
    }

    @Override
    public List<CreditType> getAll() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                List<CreditType> creditTypes = new ArrayList<>();
                do{
                    CreditType creditType = new CreditType();
                    creditType.setName(resultSet.getString("name"));
                    creditType.setConditions(resultSet.getString("conditions"));
                    creditType.setInterestRate(resultSet.getByte("interest_rate"));
                    creditType.setRepaymentPeriodMonth(resultSet.getInt("repayment_period_month"));
                    creditTypes.add(creditType);
                }while (resultSet.next());
                return creditTypes;
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int create(CreditType bean) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)){
            int counter = 1;
            preparedStatement.setString(counter++, bean.getName());
            preparedStatement.setString(counter++, bean.getConditions());
            preparedStatement.setInt(counter++, bean.getInterestRate());
            preparedStatement.setInt(counter, bean.getRepaymentPeriodMonth());
            return preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteByName(String name) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_NAME)){
            preparedStatement.setString(1, name);
            return preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

}
