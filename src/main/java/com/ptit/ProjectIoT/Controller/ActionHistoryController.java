package com.ptit.ProjectIoT.Controller;

import com.ptit.ProjectIoT.Model.ActionHistory;
import com.ptit.ProjectIoT.Service.ActionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class ActionHistoryController {

    @Autowired
    private ActionHistoryService actionHistoryService;

    @GetMapping("/action_history")
    public String getActionHistoryPage() {
        return "ActionHistory";
    }

    @GetMapping("/action-history")
    public ResponseEntity<Page<ActionHistory>> getActionHistory (@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size,
                                                                 @RequestParam(required = false) String device,
                                                                 @RequestParam(required = false) LocalDateTime start,
                                                                 @RequestParam(required = false) LocalDateTime end) {
        return ResponseEntity.ok(actionHistoryService.getHistoryWithSearch(device, start, end, page, size));
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
