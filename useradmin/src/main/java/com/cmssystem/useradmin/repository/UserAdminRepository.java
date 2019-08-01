package com.cmssystem.useradmin.repository;

import com.cmssystem.useradmin.entity.UserAdmin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdminRepository extends CrudRepository<UserAdmin, String> {

}
