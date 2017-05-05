package com.devopsjavaspring.backend.persistence.domain.backend;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by stephenasamoah on 9/9/16.
 */
public class Authority implements GrantedAuthority {

    private final String authority;

    public Authority(String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
