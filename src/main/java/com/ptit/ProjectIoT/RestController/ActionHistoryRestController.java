package com.ptit.ProjectIoT.RestController;

import com.ptit.ProjectIoT.Model.ActionHistory;
import com.ptit.ProjectIoT.Service.ActionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/iot")
public class ActionHistoryRestController {

    @Autowired
    private ActionHistoryService actionHistoryService;

    @GetMapping("/action-history")
    public Page<ActionHistory> getActionHistory (@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size,
                                                 @RequestParam(required = false) String device,
                                                 @RequestParam(required = false) LocalDateTime start,
                                                 @RequestParam(required = false) LocalDateTime end) {
        return actionHistoryService.getHistoryWithSearch(device, start, end, page, size);
    }

    @PostMapping("/action-history")
    public ResponseEntity<String> save(@RequestBody ActionHistory actionHistory) {
        try {
            this.actionHistoryService.saveActionHistory(actionHistory);
            return ResponseEntity.ok("Save action history successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save action history!");
        }
    }
}
