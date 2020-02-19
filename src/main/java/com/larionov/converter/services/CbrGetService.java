package com.larionov.converter.services;

import com.larionov.converter.entities.Quote;
import com.larionov.converter.entities.Currency;
import com.larionov.converter.repository.CurrencyRepo;
import com.larionov.converter.repository.UserRepo;
import com.larionov.converter.repository.ValueRepo;
import com.larionov.converter.xmlEntitys.XmlQuotes;
import com.larionov.converter.xmlEntitys.XmlValute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class CbrGetService {

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private ValueRepo valueRepo;

    private Logger logger = LoggerFactory.getLogger("application");

    private final String urlQuotesService = "http://www.cbr.ru/scripts/XML_daily.asp";

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private Date date;

    @Autowired
    public CbrGetService(CurrencyRepo currencyRepo) {
        this.currencyRepo = currencyRepo;
    }

    private Date getDate() {
        if (date == null)
            date = new Date();
        return date;
    }

    private String getXmlQuotes() {
        RestTemplate restTemplate = new RestTemplate();
        String xmlAnswer = "";

        String dataParameter = "?date_req=" + dateFormat.format(getDate());

        try {
            xmlAnswer = restTemplate.getForObject(urlQuotesService + dataParameter, String.class);
        } catch (HttpServerErrorException e) {
            logger.error(e.getMessage());
        }
        return xmlAnswer;
    }

    private XmlQuotes getXmlQuotesObject() {
        String xmlAnswerQuotes = getXmlQuotes();

        try {
            InputStream inputStream = new ByteArrayInputStream(xmlAnswerQuotes.getBytes("windows-1251"));
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlQuotes.class);
            Unmarshaller um = jaxbContext.createUnmarshaller();

            XmlQuotes xmlQuotes = (XmlQuotes) um.unmarshal(inputStream);

            return xmlQuotes;

        } catch (JAXBException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public void updateCurrency() {
        ArrayList<Currency> currencies = new ArrayList<Currency>();
        ArrayList<Quote> quotes = new ArrayList<Quote>();

        XmlQuotes xmlQuotes = getXmlQuotesObject();

        try {
            Date dateData = dateFormat.parse(dateFormat.format(date));
            for (XmlValute xmlValute : xmlQuotes.getValutesList()) {
                Currency currency = new Currency(xmlValute);
                currencies.add(currency);
                quotes.add(new Quote(dateData, xmlValute.getValue(), currency.getId()));
            }

            Currency rub = new Currency("R00000",
                    "RUB",
                    810,
                    1,
                    "Российский рубль");
            currencies.add(rub);
            quotes.add(new Quote(dateData, "1", rub.getId()));

            currencyRepo.saveAll(currencies);
            valueRepo.saveAll(quotes);
            currencyRepo.flush();
            valueRepo.flush();
        } catch (ParseException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CbrGetService() {
    }

    public CbrGetService(Date date) {
        this.date = new Date();
    }
}
