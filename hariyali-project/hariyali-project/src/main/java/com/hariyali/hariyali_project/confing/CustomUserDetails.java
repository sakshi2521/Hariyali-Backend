package com.hariyali.hariyali_project.confing;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hariyali.hariyali_project.entity.UserType;
import com.hariyali.hariyali_project.entity.Users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private Users user;

	public CustomUserDetails(Users user) {
		super();
		this.user = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
			
		UserType roles = this.user.getUserRole();
		//System.err.println(roles);
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		System.err.println(roles.getUsertypeName());
		authorities.add(new SimpleGrantedAuthority(roles.getUsertypeName()));
		return authorities;	
	}

	@Override
	public String getPassword() {
		return this.user.getUserPassword();
	}


	
	
	@Override
	public String getUsername() {
		//return this.user.getUserEmail();
		return this.user.getDonorId();
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
