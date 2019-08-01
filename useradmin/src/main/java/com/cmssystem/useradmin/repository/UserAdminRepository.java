package com.cmssystem.useradmin.repository;

import com.cmssystem.useradmin.entity.UserAdmin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdminRepository extends PagingAndSortingRepository<UserAdmin, String> {

    UserAdmin findByName(String name);
}
