package com.raghav.springCloud.currencyconversionservice;

import com.raghav.springCloud.currencyconversionservice.beans.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * we need to create this interface as a proxy to another service and thus we name it after that.
 * we can keep any method name here and return type also but it must be properly mappable.
 * the url we give here is very important as its used to work as the proxy. it makes the request to the
 * same url to the another service for us and gets the response here.
 * its awesome.
 *
 * name field in the @FiegnClient annotation is basically important when we configure it with naming server.
 */
//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
//    we removed the url prop from the @FeignClient because now we dont want to hardcode it and we will handover the task to ribbon.
@FeignClient(name = "currency-exchange-service")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean getExchangeValue(@PathVariable String from, @PathVariable String to);
}
