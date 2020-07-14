package com.raghav.springCloud.scloudlimitsservice;

import com.raghav.springCloud.scloudlimitsservice.beans.Configuration1;
import com.raghav.springCloud.scloudlimitsservice.beans.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitConfigurationController {

    @Autowired
    Configuration1 configuration1;

    @GetMapping("/limits")
    public LimitConfiguration getLimits(){
//        return new LimitConfiguration(1, 1000);
        return new LimitConfiguration(configuration1.getMinimum(), configuration1.getMaximum());
    }
}
