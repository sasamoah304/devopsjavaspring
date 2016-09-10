package com.devopsjavaspring.backend.service;

import com.devopsjavaspring.backend.persistence.domain.backend.Plan;
import com.devopsjavaspring.backend.persistence.domain.backend.User;
import com.devopsjavaspring.backend.persistence.domain.backend.UserRole;
import com.devopsjavaspring.backend.persistence.repositories.PlanRepository;
import com.devopsjavaspring.backend.persistence.repositories.RoleRepository;
import com.devopsjavaspring.backend.persistence.repositories.UserRepository;
import com.devopsjavaspring.enums.PlansEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by stephenasamoah on 9/9/16.
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user, PlansEnum plansEnum, Set<UserRole> userRoles){

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        Plan plan = new Plan(plansEnum);
        // It makes sure plan exist in the database
        if(!planRepository.exists(plansEnum.getId())){
            plan = planRepository.save(plan);
        }
        user.setPlan(plan);

        for (UserRole ur : userRoles){
            roleRepository.save(ur.getRole());
        }

        user.getUserRoles().addAll(userRoles);

        user = userRepository.save(user);

        return user;
    }
}
