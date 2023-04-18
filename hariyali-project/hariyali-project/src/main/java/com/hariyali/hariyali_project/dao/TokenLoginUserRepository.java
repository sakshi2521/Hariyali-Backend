package com.hariyali.hariyali_project.dao;

import com.hariyali.hariyali_project.entity.TokenLoginUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenLoginUserRepository extends JpaRepository<TokenLoginUser,Long> {

	public TokenLoginUser findByUsername(String username);
}
