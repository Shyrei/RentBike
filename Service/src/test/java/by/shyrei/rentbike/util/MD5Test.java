package by.shyrei.rentbike.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;


import static org.junit.Assert.*;

/**
 * Project RentBike
 * Created on 16.08.2017.
 * author Shyrei Uladzimir
 */
@RunWith(Parameterized.class)
public class MD5Test {
    private String expected;
    private String password;

    public MD5Test(String expected, String password) {
        this.expected = expected;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"e10adc3949ba59abbe56e057f20f883e", "123456"},
                {"30cca83a42ab4fbdf2d951b7fccf7115", "passBikeTest33"}
        });
    }

    @Test
    public void md5Encode() throws Exception {
        String actual = MD5.md5Encode(password);
        assertEquals(expected, actual);
    }
}