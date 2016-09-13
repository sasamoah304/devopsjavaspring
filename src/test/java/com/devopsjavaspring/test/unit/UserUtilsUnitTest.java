package com.devopsjavaspring.test.unit;

import com.devopsjavaspring.backend.persistence.domain.backend.User;
import com.devopsjavaspring.utils.UserUtils;
import com.devopsjavaspring.web.controllers.ForgotMyPasswordController;
import com.devopsjavaspring.web.domain.frontend.BasicAccountPayload;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.UUID;

/**
 * Created by stephenasamoah on 9/12/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserUtilsUnitTest {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(UserUtilsUnitTest.class);

  @Autowired
  private HttpServletRequest httpServletRequest;

    private PodamFactory podamFactory;


    @Before
    public void init () {
        podamFactory = new PodamFactoryImpl();
    }

    @Test
    public void testPasswordResetEmailUrlContruction() throws Exception {

        String token = UUID.randomUUID().toString();
        long userId = 123456;

        String expectedUrl = "http://localhost:80" +
                ForgotMyPasswordController.CHANGE_PASSWORD_PATH + "?id=" + userId + "&token=" + token;
        String actualUrl = UserUtils.createPasswordResetUrl(httpServletRequest, userId, token);

        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void mapWebUserToDomain() throws Exception {

        BasicAccountPayload webUser = podamFactory.manufacturePojoWithFullData(BasicAccountPayload.class);
        webUser.setEmail("me@example.com");

        User user = UserUtils.fromWebUserToDomainUser(webUser);

        Assert.assertNotNull(user);

        Assert.assertEquals(webUser.getUsername(), user.getUsername());
        Assert.assertEquals(webUser.getPassword(), user.getPassword());
        Assert.assertEquals(webUser.getFirstName(), user.getFirstName());
        Assert.assertEquals(webUser.getLastName(), user.getLastName());
        Assert.assertEquals(webUser.getEmail(), user.getEmail());
        Assert.assertEquals(webUser.getPhoneNumber(), user.getPhoneNumber());
        Assert.assertEquals(webUser.getCountry(), user.getCountry());
        Assert.assertEquals(webUser.getDescription(), user.getDescription());

    }
}
