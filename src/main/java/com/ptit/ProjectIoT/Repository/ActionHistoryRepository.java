package com.ptit.ProjectIoT.Repository;

import com.ptit.ProjectIoT.Model.ActionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionHistoryRepository extends JpaRepository<ActionHistory, Long>, JpaSpecificationExecutor<ActionHistory> {
    @Override
    Page<ActionHistory> findAll(Pageable pageable);
}
