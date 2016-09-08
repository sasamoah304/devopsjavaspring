package com.devopsjavaspring.backend.service;

import com.devopsjavaspring.web.domain.frontend.FeedbackPojo;
import org.springframework.mail.SimpleMailMessage;

/**
 * Contract for email service
 * Created by stephenasamoah on 9/8/16.
 */
public interface EmailService {

    /**
     * Sends an email with the content in the Feedback Pojo
     * @param feedbackPojo The feedback Pojo
     */
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo);

    /**
     * Sends an email with the content of the Simple Mail Message object.
     * @param message The object containing the email content
     */
    public void sendGenericEmailMessage(SimpleMailMessage message);


}
