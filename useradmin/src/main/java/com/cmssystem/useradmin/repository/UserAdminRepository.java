package com.cmssystem.useradmin.repository;

import com.cmssystem.useradmin.entity.UserAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdminRepository extends PagingAndSortingRepository<UserAdmin, String> {

    UserAdmin findByName(String name);

    UserAdmin findByEmail(String email);

    boolean existsByEmail(String email);

    long countByRoleId(Integer roleId);

    Page<UserAdmin> findByNameIgnoreCaseStartingWithOrEmailStartingWith(String name, String email, Pageable pageable);
    //Page<UserAdmin> findByNameOrEmail(String input, Pageable pageable);
}
