package com.example.lukekramer.assignment6.login;

import com.example.lukekramer.assignment6.domain.login.LoginFactory;
import com.example.lukekramer.assignment6.entity.Login;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lukekramer on 08/05/16.
 */
public class LoginFactoryTest {

    @Test
    public void testFactory() throws Exception {

        LoginFactory factory = new LoginFactory();

        Login login = factory.createLogin(1,"LukeKram","DarthVader");


        Assert.assertEquals(1,login.getUserid());
        Assert.assertSame("LukeKram",login.getUsername());
        Assert.assertSame("DarthVader",login.getPassword());

    }

    @Test
    public void testFactoryUpdate() throws Exception {

        LoginFactory factory = new LoginFactory();

        Login login = factory.createLogin(1,"LukeKram","DarthVader");

        login = new Login.Builder()
                .copy(login)
                .Password("Skywalker")
                .build();

        Assert.assertEquals(1,login.getUserid());
        Assert.assertSame("LukeKram",login.getUsername());
        Assert.assertSame("Skywalker",login.getPassword());

    }
}
