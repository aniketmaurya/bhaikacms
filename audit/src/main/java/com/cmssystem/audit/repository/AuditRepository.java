package com.cmssystem.audit.repository;

import com.cmssystem.audit.entity.Audit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends PagingAndSortingRepository<Audit, String> {

    Page<Audit> findByActionTimeBetween(Long start, Long end, Pageable pageable);

    Page<Audit> findByModifierAndActionTimeBetween(String modifier, Long start, Long end, Pageable pageable);

    Page<Audit> findByModifier(String modifier, Pageable pageable);

    @Query(value = "SELECT modifier FROM audit WHERE assetId = ?1 ORDER BY action_time LIMIT 1 OFFSET 0", nativeQuery = true)
    String findRecentModifierByAssetId(String contentId);
}
