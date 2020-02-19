package com.larionov.converter.services;

import com.larionov.converter.entities.ConverterForm;
import com.larionov.converter.entities.Currency;
import com.larionov.converter.entities.Quote;
import com.larionov.converter.entities.QuoteKey;
import com.larionov.converter.repository.CurrencyRepo;
import com.larionov.converter.repository.ValueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Converter {
    private Quote quoteLeft;
    private Quote quoteRight;
    private Currency currencyLeft;
    private Currency currencyRight;
    private Date currentDate;
    private double valueLeft;
    private double valueRight;



    private SimpleDateFormat dateFormatForm = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private ValueRepo valueRepo;

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private CbrGetService cbrGetService;

    private List<Quote> getQuoteFromDB(String currency) {
        List<Quote> quotes;
        QuoteKey quoteKey = new QuoteKey(currentDate, currency);
        quotes = valueRepo.findByQuoteKey(quoteKey);
        if (quotes.size() == 0) {
            cbrGetService.setDate(currentDate); cbrGetService.updateCurrency();
            quotes = getQuoteFromDB(currency);
        }
        return quotes;
    }

    public void execute(ConverterForm converterForm) {
        try {
            currentDate = dateFormatForm.parse(converterForm.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        quoteLeft = getQuoteFromDB(converterForm.getCurrencyLeft()).get(0);
        quoteRight = getQuoteFromDB(converterForm.getCurrencyRight()).get(0);

        currencyLeft = currencyRepo.findById(converterForm.getCurrencyLeft()).get();
        currencyRight = currencyRepo.findById(converterForm.getCurrencyRight()).get();

        valueLeft = Double.parseDouble(converterForm.getValueLeft());

        valueRight = quoteLeft.getValue() / currencyLeft.getNominal();
        valueRight /= quoteRight.getValue() / currencyRight.getNominal();
        valueRight *= valueLeft;

        converterForm.setValueRight(Double.toString(valueRight));

    }

    public void getStateForm(ConverterForm converterForm) {

    }

}
