package com.raghav.springCloud.scloudlimitsservice;

import com.raghav.springCloud.scloudlimitsservice.beans.Configuration1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Configuration1.class)
public class ScloudLimitsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScloudLimitsServiceApplication.class, args);
	}

}
/*
* in this app, we are trying to show how we can access the configuration properties on the browser.
* so we saw the ConfigurationProperties and EnableConfigurationProperties annotations to do this.*/