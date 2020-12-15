package com.sgu.dao.implementation;

import com.sgu.bean.Client;
import com.sgu.dao.ConnectionBuilder;
import com.sgu.dao.CrudDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImp implements CrudDao<Client> {

    private static final String CREATE = "insert into client(name, property_type, address, phone_number, contact_person)" +
            " values (?, ?, ?, ?, ?);";
    private static final String DELETE_BY_NAME = "delete from client where name = ?;";
    private static final String GET_BY_NAME = "select * from client where name = ?;";
    private static final String GET_ALL = "select * from client;";

    private ConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder){
        this.connectionBuilder = connectionBuilder;
    }

    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }

    @Override
    public Client getByName(String name) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME)){
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Client client = new Client();
                client.setName(resultSet.getString("name"));
                client.setPropertyType("property_type");
                client.setAddress("address");
                client.setPhoneNumber("phone_number");
                client.setContactPerson("contact_person");
                return client;
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Client();
    }

    @Override
    public List<Client> getAll() {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                List<Client> clients = new ArrayList<>();
                do {
                    Client client = new Client();
                    client.setName(resultSet.getString("name"));
                    client.setPropertyType("property_type");
                    client.setAddress("address");
                    client.setPhoneNumber("phone_number");
                    client.setContactPerson("contact_person");
                    clients.add(client);
                }while (resultSet.next());
                return clients;
            }else {
                System.out.println("SQL query error");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int create(Client bean) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)){
            int counter = 1;
            preparedStatement.setString(counter++, bean.getName());
            preparedStatement.setString(counter++, bean.getPropertyType());
            preparedStatement.setString(counter++, bean.getAddress());
            preparedStatement.setString(counter++, bean.getPhoneNumber());
            preparedStatement.setString(counter, bean.getContactPerson());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
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
