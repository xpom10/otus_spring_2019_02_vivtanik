package ru.otus.messageSystem;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class MessageImpl implements MessageSystem {

    private MessageSource messageSource;
    private String locale;

    public MessageImpl(MessageSource messageSource, String locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    @Override
    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, getLocale());
    }

    @Override
    public Locale getLocale() {
        return Locale.forLanguageTag(locale);
    }
}
