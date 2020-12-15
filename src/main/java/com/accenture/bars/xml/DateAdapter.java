package com.accenture.bars.xml;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

 

import javax.xml.bind.annotation.adapters.XmlAdapter;

 

public class DateAdapter extends XmlAdapter<String, LocalDate> {

 

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd");


    @Override
    public LocalDate unmarshal(String date) throws Exception {
        return LocalDate.parse(date, formatter);
    }
 
    @Override
    public String marshal(LocalDate date) throws Exception {
        return date.format(formatter);
    }

 

}
 