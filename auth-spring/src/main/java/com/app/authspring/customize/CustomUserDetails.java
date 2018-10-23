package com.app.authspring.customize;

import com.app.authspring.entity.Privileges;
import com.app.authspring.entity.Roles;
import com.app.authspring.entity.Users;
import com.app.authspring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomUserDetails implements UserDetailsService{

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        final Optional<Users> loginUser = usersRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (!loginUser.isPresent()){
            throw new UsernameNotFoundException("user : "+usernameOrEmail+" tidak terdapat di database.");
        }

        final Users users = loginUser.get();
        if (users.getRoles() == null && users.getRoles().isEmpty()){
            throw new UsernameNotFoundException("user : "+usernameOrEmail+" gagal login");
        }

        return new User(
                users.getUsername(),
                users.getPassword(),
                users.isEnable(),
                true,
                true,
                true,
                getAuthorities(users.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Roles> roless){
        return getGrantedAuthorities(getPrivilege(roless));
    }

    private List<String> getPrivilege(final Collection<Roles> roles){
        final List<String> privileges = new ArrayList<>();
        final List<Privileges> collections = new ArrayList<Privileges>();
        for (final Roles role : roles){
            collections.addAll(role.getPrivileges());
        }

        for (final Privileges priviles : collections){
            privileges.add(priviles.getName());
        }

        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges){
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final String privilege : privileges){
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
