package com.cmssystem.useradmin.repository;

import com.cmssystem.useradmin.entity.UserAdminToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdminTokenRepository extends CrudRepository<UserAdminToken, String> {

}
