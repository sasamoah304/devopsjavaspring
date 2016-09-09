package com.devopsjavaspring.test.integration;

import com.devopsjavaspring.backend.persistence.domain.backend.Plan;
import com.devopsjavaspring.backend.persistence.domain.backend.Role;
import com.devopsjavaspring.backend.persistence.domain.backend.User;
import com.devopsjavaspring.backend.persistence.domain.backend.UserRole;
import com.devopsjavaspring.backend.persistence.repositories.PlanRepository;
import com.devopsjavaspring.backend.persistence.repositories.RoleRepository;
import com.devopsjavaspring.backend.persistence.repositories.UserRepository;
import com.devopsjavaspring.enums.PlansEnum;
import com.devopsjavaspring.enums.RolesEnum;
import com.devopsjavaspring.utils.UsersUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by stephenasamoah on 9/9/16.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = DevopsjavaspringApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoriesIntegrationTest {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

//    private static final int BASIC_PLAN_ID = 1;
//    private static final int BASIC_ROLE_ID = 1;

    @Before
    public void init(){
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void testCreateNewPlan() throws Exception {
        String expectedPlanType = "Basic";
        Plan basicPlan = createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);
        Plan retrievedPlan = planRepository.findOne(PlansEnum.BASIC.getId());
        Assert.assertNotNull(retrievedPlan);
        Assert.assertEquals("Expected plan type should be Basic", expectedPlanType, retrievedPlan.getName());

    }

    @Test
    public void testCreateNewRole() throws Exception{
        String expectedResult = "ROLE_BASIC";
        Role role = createRole(RolesEnum.BASIC);
        roleRepository.save(role);
        Role retrieveRole = roleRepository.findOne(RolesEnum.BASIC.getId());
        Assert.assertNotNull(retrieveRole);
        Assert.assertEquals("Role name should equal ROLE_BASIC", expectedResult, retrieveRole.getName());
    }

    private Plan createPlan(PlansEnum plansEnum){
        return new Plan(plansEnum);
    }

    private Role createRole(RolesEnum rolesEnum){
        return new Role(rolesEnum);
    }

    @Test
    public void createNewUser() throws Exception {
        Plan basicPlan = createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);

        User basicUser = UsersUtils.createBasicUser("Kofi", "ko@me.com");
        basicUser.setPlan(basicPlan);

        Role basicRole = createRole(RolesEnum.BASIC);

        Set<UserRole> userRoles = new HashSet<>();

        UserRole userRole = new UserRole(basicUser, basicRole);
        userRole.setUser(basicUser);
        userRole.setRole(basicRole);

        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);

        for (UserRole ur : userRoles){
            roleRepository.save(ur.getRole());
        }

        basicUser = userRepository.save(basicUser);
        User newlyCreatedUser = userRepository.findOne(basicUser.getId());

        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId() != 0);
        Assert.assertNotNull(newlyCreatedUser.getPlan());
        Assert.assertNotNull(newlyCreatedUser.getPlan().getId());

        Set<UserRole> newlyCreatedUserUserRoles = newlyCreatedUser.getUserRoles();
        for (UserRole ur : newlyCreatedUserUserRoles){
            Assert.assertNotNull(ur.getRole());
//            Assert.assertNotNull(ur.getRole().getId());
        }

    }

}
