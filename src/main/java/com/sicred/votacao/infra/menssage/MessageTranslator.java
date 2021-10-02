package com.sicred.votacao.infra.menssage;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageTranslator {

    private final MessageSource messageSource;

    public MessageTranslator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key, String... arguments) {
        try {
            return messageSource.getMessage(key, arguments, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return key;
        }

    }
}
