package com.sgu.executer.main.task.two;

import com.sgu.bean.Client;
import com.sgu.bean.Credit;
import com.sgu.bean.CreditStorage;
import com.sgu.dao.CrudDao;
import com.sgu.dao.implementation.ClientDaoImp;
import com.sgu.dao.implementation.ConnectionBuilderImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.sgu.utils.BeansUtils.initCreditStorage;

public class MainTaskTwo {

    private static final String CREDIT_STORAGE_XML = "src/main/resources/creditStorage.xml";
    private static CrudDao<Client> clientDao;

    public static void main(String[] args) {
        //xml serialize
        CreditStorage creditStorage = initCreditStorage();
        serializeToFile(creditStorage);

        //xml deserialize
        List<Credit> credits = deserializeFromFile();
        credits.forEach(System.out::println);

    }

    private static void serializeToFile(CreditStorage creditStorage) {
        try {
            JAXBContext context = JAXBContext.newInstance(CreditStorage.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(creditStorage, System.out);
            marshaller.marshal(creditStorage, new File(CREDIT_STORAGE_XML));
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Credit> deserializeFromFile(){
        try {
            JAXBContext context = JAXBContext.newInstance(CreditStorage.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CreditStorage creditStorage = (CreditStorage) unmarshaller.unmarshal(new InputStreamReader(
                    new FileInputStream(CREDIT_STORAGE_XML), StandardCharsets.UTF_8));

            return creditStorage.getCredits();
        } catch (FileNotFoundException | JAXBException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

}
