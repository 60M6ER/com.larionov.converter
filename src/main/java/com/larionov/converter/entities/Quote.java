package com.larionov.converter.entities;

import com.larionov.converter.xmlEntitys.XmlValute;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "values")
public class Quote {
    @EmbeddedId
    private QuoteKey quoteKey;
    private double value;

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public Quote() {
    }

    public Quote(Date date, String value, String currency) {
        //this.date = date;
        this.value = Double.parseDouble(value.replace(",", "."));
        //this.currency = "q" + currency;
        this.quoteKey = new QuoteKey(date, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quote)) return false;
        Quote value1 = (Quote) o;
        return Double.compare(value1.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Value{" +
                "date=" +
                ", value=" + value +
                ", valute=" +
                '}';
    }
}
