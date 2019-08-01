package com.cmssystem.audit.repository;

import com.cmssystem.audit.entity.Audit;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends PagingAndSortingRepository<Audit, String> {
}
