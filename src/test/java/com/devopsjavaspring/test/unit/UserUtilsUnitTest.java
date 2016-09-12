package com.devopsjavaspring.test.unit;

import com.devopsjavaspring.utils.UserUtils;
import com.devopsjavaspring.web.controllers.ForgotMyPasswordController;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * Created by stephenasamoah on 9/12/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserUtilsUnitTest {

  @Autowired
  private HttpServletRequest httpServletRequest;


    @Before
    public void init () {
    }

    @Test
    public void testPasswordResetEmailUrlContruction() throws Exception {

        String token = UUID.randomUUID().toString();
        long userId = 123456;

        String expectedUrl = "http://localhost:80" +
                ForgotMyPasswordController.CHANGE_PASSWORD_PATH + "?id=" + userId + "&token" + token;

        String actualUrl = UserUtils.createPasswordResetUrl(httpServletRequest, userId, token);


        Assert.assertEquals(expectedUrl, actualUrl);

    }
}
