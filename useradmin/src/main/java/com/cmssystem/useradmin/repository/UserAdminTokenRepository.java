package com.cmssystem.useradmin.repository;

import com.cmssystem.useradmin.entity.UserAdmin;
import com.cmssystem.useradmin.entity.UserAdminToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAdminTokenRepository extends CrudRepository<UserAdminToken, String> {

}
