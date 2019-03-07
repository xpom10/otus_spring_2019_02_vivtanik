package ru.otus.messageSystem;

import java.util.Locale;

public interface MessageSystem {

    String getMessage(String code, Object[] args);

    Locale getLocale();
}
