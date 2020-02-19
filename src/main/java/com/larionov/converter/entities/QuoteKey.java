package com.larionov.converter.entities;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class QuoteKey implements Serializable {

    private Date date;

    private String currency;

    public Date getDate() {
        return date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public QuoteKey() {
    }

    public QuoteKey(Date date, String currency) {
        this.date = date;
        this.currency = currency;
    }
}
