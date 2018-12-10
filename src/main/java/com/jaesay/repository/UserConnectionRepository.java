package com.jaesay.repository;

import org.springframework.data.repository.CrudRepository;

import com.jaesay.domain.UserConnection;
import com.jaesay.domain.UserConnectionPK;

public interface UserConnectionRepository extends CrudRepository<UserConnection, UserConnectionPK> {

	public UserConnection findFirstByProviderUserId(String providerUserId);
}
