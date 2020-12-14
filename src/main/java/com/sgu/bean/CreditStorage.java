package com.sgu.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "com.sgu")
public class CreditStorage {


    private List<Credit> credits;

    public List<Credit> getCredits() {
        return credits;
    }

    @XmlElementWrapper(name = "credits")
    @XmlElement(name = "credit")
    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

}
