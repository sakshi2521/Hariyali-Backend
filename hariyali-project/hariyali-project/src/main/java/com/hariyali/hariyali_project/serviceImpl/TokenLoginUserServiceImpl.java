package com.hariyali.hariyali_project.serviceImpl;



import java.util.Date;

import com.hariyali.hariyali_project.dao.TokenLoginUserRepository;
import com.hariyali.hariyali_project.entity.TokenLoginUser;
import com.hariyali.hariyali_project.service.TokenLoginUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class TokenLoginUserServiceImpl implements TokenLoginUserService{
	@Autowired
	private TokenLoginUserRepository tokenLoginUserRepository;

	//save User
	@Override
	public TokenLoginUser saveUser(TokenLoginUser tokenLoginUser) {
		
		//System.err.println(tokenLoginUser);
		return tokenLoginUserRepository.save(tokenLoginUser);
		
		
	}
//get user by user name
	@Override
	public TokenLoginUser findByUsername(String username) {
		return tokenLoginUserRepository.findByUsername(username);
		
	}

	//update token
	@Override
	public TokenLoginUser updateToken(TokenLoginUser tokenLoginUser) {
		TokenLoginUser user = findByUsername(tokenLoginUser.getUsername());
		user.setToken(tokenLoginUser.getToken());
		user.setId(tokenLoginUser.getId());
		user.setFlag(tokenLoginUser.isFlag());
		user.setLastUpdatedOn(tokenLoginUser.getLastUpdatedOn());
		System.out.println("user flag " +user);
		return tokenLoginUserRepository.save(tokenLoginUser);
	}
	@Override
	public TokenLoginUser refreshToken(TokenLoginUser tokenLoginUser) {
		boolean flag = tokenLoginUser.isFlag();
		if (tokenLoginUser.isFlag()) {
		tokenLoginUser.setLastUpdatedOn(new Date(System.currentTimeMillis()));
		tokenLoginUser.setToken(tokenLoginUser.getToken());
		return tokenLoginUserRepository.save(tokenLoginUser);
		}
		return tokenLoginUser ;
	}

}
