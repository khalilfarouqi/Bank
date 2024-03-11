package com.bank.bankservice.common.config;

import com.bank.bankservice.common.CommonTools;
import com.bank.bankservice.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.*;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.util.Date;

@Configurable
@AllArgsConstructor
public class ModelMapperConfig {
    private CommonTools tools;

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        Converter<Date, String> dateToStringConverter = new AbstractConverter<>(){
            @Override
            public String convert(Date date){
                return tools.dateToString(date);
            }
        };
        Converter<String, Date> stringToDateConverter = new AbstractConverter<>(){
            @SneakyThrows
            @Override
            public Date convert(String s){
                try {
                    return tools.stringToDate(s);
                }catch (ParseException e){
                    throw new BusinessException(String.format("the date %s doesn't respect the format %s", s, tools.getDateFormat()));
                }
            }
        };
        modelMapper.addConverter(dateToStringConverter);
        modelMapper.addConverter(stringToDateConverter);
        return modelMapper;
    }
}
