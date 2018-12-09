package com.jaesay.repository;

import org.springframework.data.repository.CrudRepository;

import com.jaesay.domain.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, String> {
	
}
