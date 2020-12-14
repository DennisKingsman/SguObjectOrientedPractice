package com.sgu.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "client")
public class Client {

    private String name;
    private String propertyType;
    private String address;
    private String phoneNumber;
    private String contactPerson;

    public Client() {
    }

    public Client(String name, String propertyType, String address, String phoneNumber, String contactPerson) {
        this.name = name;
        this.propertyType = propertyType;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.contactPerson = contactPerson;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "clientName")
    public void setName(String name) {
        this.name = name;
    }

    public String getPropertyType() {
        return propertyType;
    }

    @XmlElement
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @XmlElement
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    @XmlElement
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Override
    public String toString() {
        return "Client{" + "name='" + name + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                '}';
    }

}
