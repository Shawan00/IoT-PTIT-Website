package com.ptit.ProjectIoT.Service;

import com.ptit.ProjectIoT.Model.ActionHistory;
import com.ptit.ProjectIoT.Repository.ActionHistoryRepository;
import com.ptit.ProjectIoT.Service.Specification.ActionHistorySpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class ActionHistoryService {
    @Autowired
    private final ActionHistoryRepository actionHistoryRepository;

    public ActionHistoryService(ActionHistoryRepository actionHistoryRepository) {
        this.actionHistoryRepository = actionHistoryRepository;
    }

    public Page<ActionHistory> getHistoryWithSearch (String device, LocalDateTime start, LocalDateTime end, int page, int size) {
        Specification<ActionHistory> specification = Specification
                .where(ActionHistorySpecifications.hasDevice(device))
                .and(ActionHistorySpecifications.hasDateTimeBetween(start, end));

        Sort sort = Sort.by(Sort.Direction.DESC, "dateTime");
        return this.actionHistoryRepository.findAll(specification, PageRequest.of(page, size, sort));
    }

    public void saveActionHistory(ActionHistory actionHistory) {
        this.actionHistoryRepository.save(actionHistory);
    }
}
