package com.ptit.ProjectIoT.Service.Specification;

import com.ptit.ProjectIoT.Model.DataSensor;
import org.springframework.data.jpa.domain.Specification;
import org.thymeleaf.standard.expression.IStandardConversionService;

import java.time.LocalDateTime;

public class DataSensorSpecifications {
    public static Specification<DataSensor> hasTemperature(Double temperature) {
        return ((root, query, criteriaBuilder) ->
                temperature == null? null : criteriaBuilder.equal(root.get("temperature"),  temperature));
    }

    public static Specification<DataSensor> hasHumidity(Double humidity) {
        return (root, query, criteriaBuilder) ->
                humidity == null? null : criteriaBuilder.equal(root.get("humidity"), humidity);
    }

    public static Specification<DataSensor> hasBrightness(Double brightness) {
        return (root, query, criteriaBuilder) ->
                brightness == null? null : criteriaBuilder.equal(root.get("brightness"), brightness);
    }

    public static Specification<DataSensor> hasDateTimeBetween(LocalDateTime start, LocalDateTime end) {
        if (start != null && end != null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("dateTime"), start, end);
        } else if (start != null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateTime"), start);
        } else if (end != null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dateTime"), end);
        } else {
            return null;
        }
    }
}
