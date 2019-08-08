package com.cmssystem.audit.repository;

import com.cmssystem.audit.entity.Audit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditRepository extends PagingAndSortingRepository<Audit, String> {

    Page<Audit> findByActionTimeBetween(Long start, Long end, Pageable pageable);

    List<Audit> findByActionTimeBetween(Long start, Long end);

    Page<Audit> findByModifierContainingAndActionTimeBetween(String modifier, Long start, Long end, Pageable pageable);

    Page<Audit> findByModifierContaining(String modifier, Pageable pageable);

    @Query(value = "SELECT modifier FROM audit WHERE asset_id = ?1 ORDER BY action_time LIMIT 1 OFFSET 0", nativeQuery = true)
    String findRecentModifierByAssetId(String contentId);
}
