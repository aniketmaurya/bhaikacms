package com.cmssystem.audit.repository;

import com.cmssystem.audit.entity.Audit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends PagingAndSortingRepository<Audit, String> {

    Page<Audit> findByActionTimeBetween(Long start, Long end, Pageable pageable);

    Page<Audit> findByActionByAndActionTimeBetween(String actionBy, Long start, Long end, Pageable pageable);

    Page<Audit> findByActionBy(String actionBy, Pageable pageable);
}
