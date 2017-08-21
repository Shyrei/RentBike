package by.shyrei.rentbike.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Project RentBike
 * Created on 16.08.2017.
 * author Shyrei Uladzimir
 */
public class MailSenderTest {
    private final static String MAIL_TO = "salon-minsk@mail.ru";
    private final static String SUBJECT = "Test mailSender method";
    private final static String TEXT = "If you see this text - mailSender test is okey!";

    @Test
    public void send() throws Exception {
        MailSender.send(SUBJECT, TEXT, MAIL_TO);
    }
}