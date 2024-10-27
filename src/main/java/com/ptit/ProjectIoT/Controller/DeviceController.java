package com.ptit.ProjectIoT.Controller;

import com.ptit.ProjectIoT.MQTT.MqttGateway;
import com.ptit.ProjectIoT.Model.ActionHistory;
import com.ptit.ProjectIoT.Service.ActionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeviceController {
    @Autowired
    private MqttGateway mqttGateway;

    @Autowired
    private ActionHistoryService actionHistoryService;

    @PostMapping("/control")
    public ResponseEntity<String> controlLed(@RequestParam String ledId, @RequestParam String action) {
        try {
            mqttGateway.sendLedCommand(ledId, action);
            String device = "Air conditioner";
            if (ledId.equals("led1")) device = "Light";
            else if (ledId.equals("led2")) device = "Fan";
            ActionHistory actionHistory = new ActionHistory(device, action);
            actionHistoryService.saveActionHistory(actionHistory);
            return ResponseEntity.ok("Control command sent to " + device + " to turn " + action);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save history!");
        }
    }
}
