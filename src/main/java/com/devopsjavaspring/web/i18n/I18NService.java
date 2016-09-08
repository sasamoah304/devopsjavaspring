package com.devopsjavaspring.web.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by stephenasamoah on 9/7/16.
 */
@Service
public class I18NService {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(I18NService.class);

    @Autowired
    private MessageSource messageSource;

    /**
     * Return a message for the given id and the default locale in the session context
     * @param messageId The key to the messages resource file
     **/
    public String getMessage(String messageId){
        LOG.info("Returning i18n text for messageId {}", messageId);
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(messageId, locale);
    }

    /**
     * Returns a message for the given message id and locale
     * @param messageId The key to the message resource file
     * @param locale The Locale
     **/
    public String getMessage(String messageId, Locale locale){
        LOG.info("Returning i18n text for messageId {}", messageId);
        return messageSource.getMessage(messageId, null, locale);

    }
}
