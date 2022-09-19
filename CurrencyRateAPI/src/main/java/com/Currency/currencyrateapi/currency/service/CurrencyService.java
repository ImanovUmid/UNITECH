package com.Currency.currencyrateapi.currency.service;

import com.Currency.currencyrateapi.currency.entity.CurrencyRateEntity;
import com.Currency.currencyrateapi.currency.entity.LogsCurrencyEntity;
import com.Currency.currencyrateapi.currency.repository.CurrencyRateRepository;
import com.Currency.currencyrateapi.currency.repository.LogsCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.transaction.Transactional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CurrencyService {
    private final CurrencyRateRepository currencyRateRepository;
    private final LogsCurrencyRepository logsCurrencyRepository;

    @Autowired
    public CurrencyService(CurrencyRateRepository currencyRateRepository, LogsCurrencyRepository logsCurrencyRepository) {
        this.currencyRateRepository = currencyRateRepository;
        this.logsCurrencyRepository = logsCurrencyRepository;
    }

     public Double checkCurrency(String val1, String val2) throws ParserConfigurationException, IOException, SAXException {
        Date maxDate = null;
        Double result = null;
        LogsCurrencyEntity logsCurrencyEntity = new LogsCurrencyEntity();
        Long check = currencyRateRepository.findCurrCheck();
        if (check == 0d) {
            updateCurrency();
        }

        CurrencyRateEntity currencyRateRepositoryCurrencyValueBy1Entity = currencyRateRepository.findCurrencyValueBy1(val1);
        CurrencyRateEntity currencyRateRepositoryCurrencyValueBy2Entity = currencyRateRepository.findCurrencyValueBy2(val2);
        if (currencyRateRepositoryCurrencyValueBy1Entity != null && currencyRateRepositoryCurrencyValueBy2Entity != null) {
            maxDate = logsCurrencyRepository.findLastDate(currencyRateRepositoryCurrencyValueBy1Entity.getCurrencyCode());
            logsCurrencyEntity.setCheckDate(new Date());
            if (maxDate != null) {
                long seconds = (logsCurrencyEntity.getCheckDate().getTime()-maxDate.getTime())/1000;

                if (seconds >= 60) {
                    updateCurrency();
                    currencyRateRepositoryCurrencyValueBy1Entity = currencyRateRepository.findCurrencyValueBy1(val1);
                    currencyRateRepositoryCurrencyValueBy2Entity = currencyRateRepository.findCurrencyValueBy2(val2);
                }
            }
            logsCurrencyEntity.setCurrency(currencyRateRepositoryCurrencyValueBy1Entity.getCurrencyCode());
            logsCurrencyRepository.save(logsCurrencyEntity);
            result = currencyRateRepositoryCurrencyValueBy1Entity.getCbarCurr() / currencyRateRepositoryCurrencyValueBy2Entity.getCbarCurr();
        } else {
            throw new IllegalStateException("Your Currency is null");
        }
        return result;
    }

    @Transactional
    public String updateCurrency() throws ParserConfigurationException, IOException, SAXException {
        Date today = new Date();
        String d1 = new SimpleDateFormat("dd.MM.yyyy").format(today).toString();
        String URL = "https://www.cbar.az/currencies/" + d1 + ".xml";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(URL);


        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("Valute");
        boolean azncheck = false;
        for (int i = 0; i < nodeList.getLength(); i++) {

           if (!azncheck) {
                CurrencyRateEntity currazn = currencyRateRepository.findCurrencyValueBy1("AZN");
                if (currazn == null) {
                    azncheck = true;
                    currazn = new CurrencyRateEntity();
                    currazn.setCurrencyDate(new Date());
                    currazn.setCurrencyCode("AZN");
                    currazn.setCurrencyName("1 Azərbaycan manatı");
                    currazn.setCbarCurr(1d);
                    currencyRateRepository.save(currazn);
                }
            }
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    CurrencyRateEntity curr = currencyRateRepository.findCurrencyValueBy1(elem.getAttribute("Code"));

                    if (curr == null) {

                        curr = new CurrencyRateEntity();
                        curr.setCurrencyDate(new Date());
                        curr.setCurrencyCode(elem.getAttribute("Code"));
                        curr.setCurrencyName(elem.getElementsByTagName("Name").item(0).getTextContent());
                        curr.setCbarCurr(Double.parseDouble(elem.getElementsByTagName("Value").item(0).getTextContent()));
                        currencyRateRepository.save(curr);
                    } else {
                        curr.setCurrencyDate(new Date());
                        curr.setCurrencyCode(elem.getAttribute("Code"));
                        curr.setCurrencyName(elem.getElementsByTagName("Name").item(0).getTextContent());
                        curr.setCbarCurr(Double.parseDouble(elem.getElementsByTagName("Value").item(0).getTextContent()));
                    }

                }
            }
            return "Success";
        }
    }