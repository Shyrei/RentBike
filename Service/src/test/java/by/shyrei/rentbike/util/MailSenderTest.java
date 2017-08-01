package by.shyrei.rentbike.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Project RentBike
 * Created on 25.07.2017.
 * author Shyrei Uladzimir
 */
public class MailSenderTest {
    @Test
    public void send() throws Exception {
        MailSender mailSender = new MailSender();
        mailSender.send("hi", "text", "lis2002_2002@mail.ru");
    }

}