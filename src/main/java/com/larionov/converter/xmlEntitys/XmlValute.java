package com.larionov.converter.xmlEntitys;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Valute")
public class XmlValute {
    private String id;
    private int numCode;
    private String charCode;
    private int nominal;
    private String name;
    private String value;

    @XmlAttribute(name = "ID")
    public String getId() {
        return id;
    }
    @XmlElement(name = "NumCode")
    public int getNumCode() {
        return numCode;
    }
    @XmlElement(name = "CharCode")
    public String getCharCode() {
        return charCode;
    }
    @XmlElement(name = "Nominal")
    public int getNominal() {
        return nominal;
    }
    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }
    @XmlElement(name = "Value")
    public String getValue() {
        return value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
