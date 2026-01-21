package com.example.demo.model.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.example.demo.model.jpa.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@RequiredArgsConstructor
public class AuthUserDetails implements UserDetails {
	@Getter
	private final Integer id;
	private final String username;
	private final String password;
	@Getter
	private final String role;
	private final Collection<? extends GrantedAuthority> authorities;

	public static AuthUserDetails build(User user) {
		List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
		return new AuthUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getRole().name(), authorities);
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
