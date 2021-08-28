package src.FindMyHome.controller;

import src.FindMyHome.model.AddressData;
import src.FindMyHome.service.AddressDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data-import")
public class DataImportController {
    @Autowired
    AddressDataService service;

    @PostMapping
    public Map<String, String> save(@RequestBody Map<String, List<AddressData>> request) {
        List<AddressData> dataList = request.get("addresses");
        for (AddressData data : dataList) {
            service.save(data);
        }
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        return response;
    }
}
