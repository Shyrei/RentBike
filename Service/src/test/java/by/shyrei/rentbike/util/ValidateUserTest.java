package by.shyrei.rentbike.util;

import by.shyrei.rentbike.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Project RentBike
 * Created on 30.08.2017.
 * author Shyrei Uladzimir
 */
@RunWith(Parameterized.class)
public class ValidateUserTest {
    private User user;

    public ValidateUserTest(User user) {
        this.user = user;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                {new User(1, "Uladzimir", "Shyrei", "login", "pass", new BigDecimal(25), 1)},
                {new User(1, "владимир", "ширей", "vovka5", "a.S_5*", new BigDecimal(15), 1)},
                {new User(1, "Uladzimir", "Shyrei", "login", "pass", new BigDecimal(74), 1)}
        });
    }

    @Test
    public void validate() throws Exception {
        assertNull(ValidateUser.validate(user));
    }

}