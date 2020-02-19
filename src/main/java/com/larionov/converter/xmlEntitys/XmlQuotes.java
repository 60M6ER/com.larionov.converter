package com.larionov.converter.xmlEntitys;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlQuotes {

    @XmlElement(name = "Valute")
    private ArrayList<XmlValute> valutesList = new ArrayList<XmlValute>();

    public ArrayList<XmlValute> getValutesList() {
        return valutesList;
    }
}
