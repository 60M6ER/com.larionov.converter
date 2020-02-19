package com.larionov.converter.entities;

import java.util.ArrayList;
import java.util.Date;

public class ConverterForm {
    private String date;
    private ArrayList<Currency> currencies;
    private String currencyLeft;
    private String currencyRight;
    private String valueLeft;
    private String valueRight;

    public void setValueLeft(String valueLeft) {
        this.valueLeft = valueLeft;
    }

    public void setValueLift(String valueLift) {
        this.valueLeft = valueLift;
    }

    public void setValueRight(String valueRight) {
        this.valueRight = valueRight;
    }

    public String getValueLeft() {
        return valueLeft;
    }

    public String getValueRight() {
        return valueRight;
    }

    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public String getCurrencyLeft() {
        return currencyLeft;
    }

    public String getCurrencyRight() {
        return currencyRight;
    }

    public void setCurrencies(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }

    public void setCurrencyLeft(String currencyLeft) {
        this.currencyLeft = currencyLeft;
    }

    public void setCurrencyRight(String currencyRight) {
        this.currencyRight = currencyRight;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ConverterForm{" +
                "date=" + date +
                '}';
    }
}
