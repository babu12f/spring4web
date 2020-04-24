package com.nrbswift.spring4web.security;

import com.nrbswift.spring4web.dao.AppUser;
import com.nrbswift.spring4web.dao.AppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AppUserDao appUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserDao.findUserByUsername(username);

        return new User(appUser.getUsername(),appUser.getPassword(),
                        AuthorityUtils.createAuthorityList());
    }
}
