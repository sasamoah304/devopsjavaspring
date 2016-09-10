package com.devopsjavaspring.test.integration;

import com.devopsjavaspring.backend.persistence.domain.backend.Role;
import com.devopsjavaspring.backend.persistence.domain.backend.User;
import com.devopsjavaspring.backend.persistence.domain.backend.UserRole;
import com.devopsjavaspring.backend.service.UserService;
import com.devopsjavaspring.enums.PlansEnum;
import com.devopsjavaspring.enums.RolesEnum;
import com.devopsjavaspring.utils.UserUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by stephenasamoah on 9/9/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Rule
    public TestName testName = new TestName();

    @Test
    public void testcreateNewUser() throws Exception {

        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@gmail.com";

        Set<UserRole> userRoles = new HashSet<>();

        User basicUser = UserUtils.createBasicUser(username, email);
        userRoles.add(new UserRole(basicUser, new Role(RolesEnum.BASIC)));

        User user = userService.createUser(basicUser, PlansEnum.BASIC, userRoles);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
    }
}
