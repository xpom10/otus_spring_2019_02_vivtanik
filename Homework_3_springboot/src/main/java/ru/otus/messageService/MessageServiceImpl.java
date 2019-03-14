package ru.otus.messageService;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class MessageServiceImpl implements MessageService {

    private MessageSource messageSource;
    private String locale;

    public MessageServiceImpl(MessageSource messageSource, String locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    @Override
    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, getLocale());
    }

    @Override
    public String getMessage(String code) {
        return getMessage(code, null);
    }

    @Override
    public Locale getLocale() {
        return Locale.forLanguageTag(locale);
    }
}
