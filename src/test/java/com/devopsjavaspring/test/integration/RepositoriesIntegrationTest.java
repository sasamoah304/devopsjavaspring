package com.devopsjavaspring.test.integration;

import com.devopsjavaspring.backend.persistence.domain.backend.Plan;
import com.devopsjavaspring.backend.persistence.domain.backend.Role;
import com.devopsjavaspring.backend.persistence.domain.backend.User;
import com.devopsjavaspring.backend.persistence.domain.backend.UserRole;
import com.devopsjavaspring.backend.persistence.repositories.PlanRepository;
import com.devopsjavaspring.backend.persistence.repositories.RoleRepository;
import com.devopsjavaspring.backend.persistence.repositories.UserRepository;
import com.devopsjavaspring.backend.service.UserSecurityService;
import com.devopsjavaspring.enums.PlansEnum;
import com.devopsjavaspring.enums.RolesEnum;
import com.devopsjavaspring.utils.UserUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(RepositoriesIntegrationTest.class);

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSecurityService userSecurityService;

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

        User basicUser = createUser();

        User newlyCreatedUser = userRepository.findOne(basicUser.getId());
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId() != 0);
        Assert.assertNotNull(newlyCreatedUser.getPlan());
        Assert.assertNotNull(newlyCreatedUser.getPlan().getId());

        Set<UserRole> newlyCreatedUserUserRoles = newlyCreatedUser.getUserRoles();
        for (UserRole ur : newlyCreatedUserUserRoles){
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }

    }

//    @Test
//    public void testDeleteUser() throws Exception {
//        User basicUser = createUser();
//        userRepository.delete(basicUser.getId());
//
//    }

    @Test
    public void testUserExist() throws Exception {

        String expectedResult = "Kofi";

        User basicUser = createUser();
        LOG.debug("Expected Results for User is {}", userSecurityService.loadUserByUsername(basicUser.getUsername()).getUsername());
        String actualResults = String.valueOf(userSecurityService.loadUserByUsername(basicUser.getUsername()).getUsername());
        Assert.assertEquals("Expect username to equal Kofi", expectedResult, actualResults);
    }

    private User createUser(){
        Plan basicPlan = createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);

        User basicUser = UserUtils.createBasicUser();
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

}
