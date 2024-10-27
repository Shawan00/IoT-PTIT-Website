package com.ptit.ProjectIoT.Controller;

import com.ptit.ProjectIoT.Model.DataSensor;
import com.ptit.ProjectIoT.Service.DataSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    private DataSensorService dataSensorService;

    @GetMapping("/")
    public String getDashboardPage() {
        return "Dashboard";
    }

    @GetMapping("/latest-data")
    public ResponseEntity<List<DataSensor>> getLatestData() {
        return ResponseEntity.ok(dataSensorService.getLatestData());
    }

    @GetMapping("/profile")
    public String getProfilePage() {
        return "Profile";
    }

}
