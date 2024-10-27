package com.ptit.ProjectIoT.RestController;

import com.ptit.ProjectIoT.Model.DataSensor;
import com.ptit.ProjectIoT.Service.DataSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/iot")
public class DataSensorRestController {
    @Autowired
    private DataSensorService dataSensorService;
    @GetMapping("/data-sensor")
    public Page<DataSensor> getDataSensor(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "dateTime") String sortBy,
                                          @RequestParam(defaultValue = "desc") String direction,
                                          @RequestParam(required = false) Double temperature,
                                          @RequestParam(required = false) Double humidity,
                                          @RequestParam(required = false) Double brightness,
                                          @RequestParam(required = false) LocalDateTime start,
                                          @RequestParam(required = false) LocalDateTime end) {
        return dataSensorService.getDataWithSearchAndSort(temperature, humidity, brightness, start, end, page, size, sortBy, direction);
    }

    @GetMapping("/latest-data")
    public List<DataSensor> getLatestData() {
        return dataSensorService.getLatestData();
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
