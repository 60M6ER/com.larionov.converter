package com.larionov.converter.entities;

import com.larionov.converter.xmlEntitys.XmlValute;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "valutes")
public class Currency {
    @Id
    private String id;

    private String charCode;
    private int numCode;
    private int nominal;
    private String name;
    private String fullName;

    public Currency() {
    }

    public Currency(XmlValute xmlValute) {
        id = xmlValute.getId();
        charCode = xmlValute.getCharCode();
        numCode = xmlValute.getNumCode();
        nominal = xmlValute.getNominal();
        name = xmlValute.getName();
        fullName = charCode + " (" + name + ")";
    }

    public Currency(String id, String charCode, int numCode, int nominal, String name) {
        this.id = id;
        this.charCode = charCode;
        this.numCode = numCode;
        this.nominal = nominal;
        this.name = name;
        fullName = charCode + " (" + name + ")";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getCharCode() {
        return charCode;
    }

    public int getNumCode() {
        return numCode;
    }

    public int getNominal() {
        return nominal;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;
        Currency valute = (Currency) o;
        return numCode == valute.numCode &&
                Objects.equals(id, valute.id) &&
                Objects.equals(charCode, valute.charCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, charCode, numCode, nominal, name);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id='" + id + '\'' +
                ", charCode='" + charCode + '\'' +
                ", numCode=" + numCode +
                ", nominal=" + nominal +
                ", name='" + name + '\'' +
                ", active=" +
                '}';
    }
}
