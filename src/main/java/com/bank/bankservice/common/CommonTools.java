package com.bank.bankservice.common;

import lombok.Data;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Data
public class CommonTools {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String dateFormat;
    public String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }
    public Date stringToDate(String date) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.parse(date);
    }
}
