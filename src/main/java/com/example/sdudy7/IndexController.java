package com.example.sdudy7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
public class IndexController {
    @Autowired
    private MessageSource messageSource;
    @RequestMapping(value = "/validator")
    private String validator(@Valid TestEntity testEntity, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuffer buffer = new StringBuffer();
            List<FieldError> fieldErrors= bindingResult.getFieldErrors();
            Locale locale  = LocaleContextHolder.getLocale();
            for (FieldError fieldError:fieldErrors){
                String message =messageSource.getMessage(fieldError,locale);
                buffer.append(fieldError.getField()+":"+message);
            }
           return buffer.toString();
        }
        return "验证通过：年龄"+testEntity.getAge()+"姓名"+testEntity.getName()+"邮箱"+testEntity.getEmail();
    }
}
