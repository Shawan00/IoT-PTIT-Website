package com.ptit.ProjectIoT.Service.Specification;

import com.ptit.ProjectIoT.Model.ActionHistory;
import com.ptit.ProjectIoT.Model.DataSensor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class ActionHistorySpecifications {
    public static Specification<ActionHistory> hasDevice (String device) {
        return (root, query, criteriaBuilder) ->
                device == ""? null : criteriaBuilder.equal(root.get("device"), device);
    }

    public static Specification<ActionHistory> hasDateTimeBetween(LocalDateTime start, LocalDateTime end) {
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
