package com.sgu.executer.main.task.three;

import com.sgu.bean.Client;
import com.sgu.dao.CrudDao;
import com.sgu.dao.implementation.ClientDaoImp;
import com.sgu.dao.implementation.ConnectionBuilderImpl;

import java.util.List;

public class MainTaskThreeClient {

    private static CrudDao<Client> clientCrudDao;

    public static void main(String[] args) {
        getByName();
        getAll();
        addClient();
        deleteClient();
        getAll();
    }

    public static void getByName(){
        clientCrudDao = new ClientDaoImp();
        ((ClientDaoImp) clientCrudDao).setConnectionBuilder(new ConnectionBuilderImpl());
        Client client = clientCrudDao.getByName("Evil corp");
        System.out.println("Get one client from DB");
        System.out.println(client);
    }

    public static void getAll(){
        clientCrudDao = new ClientDaoImp();
        ((ClientDaoImp) clientCrudDao).setConnectionBuilder(new ConnectionBuilderImpl());
        List<Client> clients = clientCrudDao.getAll();
        System.out.println("Get all client");
        clients.forEach(System.out::println);
    }

    public static void addClient(){
        Client client = new Client();
        client.setName("Oracle");
        client.setPropertyType("private");
        client.setAddress("silicon valley");
        client.setPhoneNumber("");
        client.setContactPerson("Tim");
        clientCrudDao = new ClientDaoImp();
        ((ClientDaoImp) clientCrudDao).setConnectionBuilder(new ConnectionBuilderImpl());
        int res = clientCrudDao.create(client);
        if(res != 0){
            System.out.println("Oracle client was created");
        }else {
            System.out.println("Error");
        }
    }

    public static void deleteClient(){
        clientCrudDao = new ClientDaoImp();
        ((ClientDaoImp) clientCrudDao).setConnectionBuilder(new ConnectionBuilderImpl());
        int res = clientCrudDao.deleteByName("Some company");
        if(res == 1){
            System.out.println("Some company was deleted");
        }else {
            System.out.println("Error");
        }
    }

}
