package com.ptit.ProjectIoT.Controller;

import com.ptit.ProjectIoT.Model.DataSensor;
import com.ptit.ProjectIoT.Service.DataSensorService;
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
public class DataSensorController {
    @Autowired
    private DataSensorService dataSensorService;

    @GetMapping("/data_sensor")
    public String getDataSensorPage() {
        return "DataSensor";
    }

    @GetMapping("/data-sensor")
    public ResponseEntity<Page<DataSensor>> getDataSensor(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @RequestParam(defaultValue = "dateTime") String sortBy,
                                                          @RequestParam(defaultValue = "desc") String direction,
                                                          @RequestParam(required = false) Double temperature,
                                                          @RequestParam(required = false) Double humidity,
                                                          @RequestParam(required = false) Double brightness,
                                                          @RequestParam(required = false) LocalDateTime start,
                                                          @RequestParam(required = false) LocalDateTime end) {
        Page<DataSensor> dataSensors = dataSensorService.getDataWithSearchAndSort(temperature, humidity, brightness, start, end, page, size, sortBy, direction);
        return ResponseEntity.ok(dataSensors);
    }

    @PostMapping("/data-sensor")
    public ResponseEntity<String> saveDataSensor (@RequestBody DataSensor dataSensor) {
        try {
            dataSensorService.saveData(dataSensor);
            return ResponseEntity.ok("Data saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save data!");
        }
    }
}
