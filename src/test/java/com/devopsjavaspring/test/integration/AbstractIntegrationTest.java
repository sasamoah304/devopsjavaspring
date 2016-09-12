package com.devopsjavaspring.test.integration;

import com.devopsjavaspring.backend.persistence.domain.backend.*;
import com.devopsjavaspring.backend.persistence.repositories.PasswordResetTokenRepository;
import com.devopsjavaspring.backend.persistence.repositories.PlanRepository;
import com.devopsjavaspring.backend.persistence.repositories.RoleRepository;
import com.devopsjavaspring.backend.persistence.repositories.UserRepository;
import com.devopsjavaspring.enums.PlansEnum;
import com.devopsjavaspring.enums.RolesEnum;
import com.devopsjavaspring.utils.UserUtils;
import org.junit.Assert;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by stephenasamoah on 9/10/16.
 */
public abstract class AbstractIntegrationTest {


    @Autowired
    protected PlanRepository planRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected UserRepository userRepository;


    @Value("${token.expiration.length.minutes}")
    private int expirationTimeInMinutes;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;


    protected Plan createPlan(PlansEnum plansEnum){
        return new Plan(plansEnum);
    }

    protected Role createRole(RolesEnum rolesEnum){
        return new Role(rolesEnum);
    }

    protected User createUser(String username, String email){
        Plan basicPlan = createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);

        User basicUser = UserUtils.createBasicUser(username, email);
        basicUser.setPlan(basicPlan);

        Role basicRole = createRole(RolesEnum.BASIC);
        roleRepository.save(basicRole);

        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(basicUser, basicRole);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);
        basicUser = userRepository.save(basicUser);

        return basicUser;

    }

    protected User createUser(TestName testName){
        return createUser(testName.getMethodName(), testName.getMethodName() + "@gmail.com");
    }

    // --------Private Methods

    private PasswordResetToken createPasswordResetToken(String token, User user, LocalDateTime now){
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user, now, expirationTimeInMinutes);
        passwordResetTokenRepository.save(passwordResetToken);
        Assert.assertNotNull(passwordResetToken.getId());
        return passwordResetToken;
    }
}
