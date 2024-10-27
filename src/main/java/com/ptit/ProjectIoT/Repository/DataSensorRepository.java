package com.ptit.ProjectIoT.Repository;

import com.ptit.ProjectIoT.Model.DataSensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DataSensorRepository extends JpaRepository<DataSensor, Long>, JpaSpecificationExecutor<DataSensor> {

    Page<DataSensor> findAll(Pageable pageable);

    @Query("SELECT d FROM DataSensor d ORDER BY d.dateTime DESC")
    List<DataSensor> findLatestRecords(Pageable pageable);
}
