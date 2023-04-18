package com.hariyali.hariyali_project.service;

import com.hariyali.hariyali_project.entity.TokenLoginUser;

public interface TokenLoginUserService {

	public TokenLoginUser saveUser(TokenLoginUser tokenLoginUser);
	
	public TokenLoginUser findByUsername(String username);
	
	
	public TokenLoginUser updateToken(TokenLoginUser tokenLoginUser);
		
	public TokenLoginUser refreshToken(TokenLoginUser tokenLoginUser);

}
