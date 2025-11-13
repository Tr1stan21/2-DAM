package org.example.prueba1.util;

import java.time.LocalDate;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateUtil extends XmlAdapter<String, LocalDate>{
    @Override
    public LocalDate unmarshal(String v) {
        return LocalDate.parse(v);
    }

    @Override
    public String marshal(LocalDate v) {
        return v.toString();
    }

}
