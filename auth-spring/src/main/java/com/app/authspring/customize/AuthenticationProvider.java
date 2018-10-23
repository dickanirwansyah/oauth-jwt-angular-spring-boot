package com.app.authspring.customize;

import com.app.authspring.exception.CredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

    @Autowired
    private CustomUserDetails customUserDetails;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken token)
            throws AuthenticationException {

       if (token.getCredentials() == null && userDetails.getPassword().isEmpty() && userDetails.getPassword() == null){
           throw new CredentialsException("credentials tidak valid");
       }

       if (!passwordEncoder.matches((String) token.getCredentials(),
               userDetails.getPassword())){
           throw new CredentialsException("opps..credentials sepertinya tidak valid");
        }
    }

    @Override
    protected UserDetails retrieveUser(final String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        return customUserDetails.loadUserByUsername(username);
    }
}
