package by.shyrei.rentbike.dao;

import by.shyrei.rentbike.entity.User;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Project RentBike
 * Created on 13.07.2017.
 * author Shyrei Uladzimir
 */
public class UserDaoTest {
    UserDao dao = new UserDao();

    @Test
    public void createEntity() throws Exception {
        String s = "50.1";
        BigDecimal bal = new BigDecimal(s);
        System.out.println(bal);
    }



}