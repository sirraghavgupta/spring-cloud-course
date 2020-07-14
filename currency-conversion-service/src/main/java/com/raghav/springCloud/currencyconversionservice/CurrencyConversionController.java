package com.raghav.springCloud.currencyconversionservice;

import com.raghav.springCloud.currencyconversionservice.beans.CurrencyConversionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    Environment environment;

    @Autowired
    CurrencyExchangeServiceProxy proxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean getConvertedValue(@PathVariable("from") String from, @PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity){
        Map<String, String> uriVariables = new HashMap<String, String>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        // RestTemplates are going to be deprecated in future versions. Rather we should use WebClient which supports asynchronous calling also.
        ResponseEntity<CurrencyConversionBean> response = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);
        CurrencyConversionBean currencyConversionBean = response.getBody();
        currencyConversionBean.setQuantity(quantity);
        currencyConversionBean.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        currencyConversionBean.setConvertedValue(currencyConversionBean.getConversionMultiple().multiply(quantity));
        return currencyConversionBean;
    }

    /**
     * use of netflix FEIGN --------------------
     * 1. make it easy to call other microservices.
     * 2. integrate with ribbon which is a client side load balancing framework.
     */
    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean getConvertedValueByFiegn(@PathVariable("from") String from, @PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity){
        // previous rest template based code reduced to one line.
        CurrencyConversionBean currencyConversionBean = proxy.getExchangeValue(from, to);

        currencyConversionBean.setQuantity(quantity);
        currencyConversionBean.setConvertedValue(currencyConversionBean.getConversionMultiple().multiply(quantity));
        return currencyConversionBean;
    }
}
