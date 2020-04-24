package com.nrbswift.spring4web.security;

import com.nrbswift.spring4web.dao.AppUser;
import com.nrbswift.spring4web.dao.AppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    AppUserDao appUserDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthenticationToken token = (CustomAuthenticationToken) authentication;
        AppUser appUser = appUserDao.findUserByUsername(token.getName());

        if (appUser == null || (!appUser.getPassword().equalsIgnoreCase(token.getCredentials().toString())
                || !token.getMake().equalsIgnoreCase("babor"))) {
            throw new BadCredentialsException("The Credentials are Invalid");
        }

        return new CustomAuthenticationToken(appUser, appUser.getPassword(), appUser.getAuthorities(), token.getMake());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.equals(authentication);
    }
}
