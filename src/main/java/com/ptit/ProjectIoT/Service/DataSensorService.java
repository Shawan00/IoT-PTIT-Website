package com.ptit.ProjectIoT.Service;

import com.ptit.ProjectIoT.Model.DataSensor;
import com.ptit.ProjectIoT.Repository.DataSensorRepository;
import com.ptit.ProjectIoT.Service.Specification.DataSensorSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DataSensorService {

    @Autowired
    private final DataSensorRepository dataSensorRepository;

    public DataSensorService(DataSensorRepository dataSensorRepository) {
        this.dataSensorRepository = dataSensorRepository;
    }

    public Page<DataSensor> getDataWithSearchAndSort (Double temperature,Double humidity, Double brightness,
                                                      LocalDateTime start, LocalDateTime end,
                                                      int page, int size, String sortBy, String direction) {
        Specification<DataSensor> specification = Specification
                .where(DataSensorSpecifications.hasTemperature(temperature))
                .and(DataSensorSpecifications.hasBrightness(brightness))
                .and(DataSensorSpecifications.hasHumidity(humidity))
                .and(DataSensorSpecifications.hasDateTimeBetween(start, end));

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        return this.dataSensorRepository.findAll(specification, PageRequest.of(page, size, sort));
    }

    public List<DataSensor> getLatestData() {
        Pageable pageable = PageRequest.of(0, 20);
        return dataSensorRepository.findLatestRecords(pageable);
    }

    public void saveData (DataSensor dataSensor) {
        this.dataSensorRepository.save(dataSensor);

    }
}
