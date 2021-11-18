package com.devonfw.app.java.order.general.service.impl.config;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.devonfw.app.java.order.general.dataaccess.api.UserEntity;
import com.devonfw.app.java.order.general.dataaccess.api.repo.UserRepository;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
@Named
public class AuthenticationProviderImpl implements AuthenticationProvider {

  @Inject
  UserRepository userRepository;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    UserEntity user = this.userRepository.findByName(authentication.getPrincipal().toString());
    if (user == null) {
      throw new UsernameNotFoundException("User '" + authentication.getPrincipal() + "' was not found!");
    }
    if (user.getPassword() != authentication.getCredentials()) {
      throw new BadCredentialsException("Wrong credentials for user '" + authentication.getPrincipal() + "'!");
    }
    return authentication;
  }

  @Override
  public boolean supports(Class<?> authentication) {

    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

}
