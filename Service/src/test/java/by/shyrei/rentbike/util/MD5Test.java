package by.shyrei.rentbike.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Project RentBike
 * Created on 15.07.2017.
 * author Shyrei Uladzimir
 */
public class MD5Test {

    @Test
    public void md5Encode() throws Exception {
        String pass = "123";
        System.out.println(pass.hashCode());
        System.out.println(MD5.md5Encode(pass));
    }


}