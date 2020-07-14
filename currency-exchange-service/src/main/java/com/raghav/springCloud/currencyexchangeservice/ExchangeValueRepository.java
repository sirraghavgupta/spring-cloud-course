package com.raghav.springCloud.currencyexchangeservice;

import com.raghav.springCloud.currencyexchangeservice.beans.ExchangeValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeValueRepository extends CrudRepository<ExchangeValue, Long> {
    ExchangeValue findByFromAndTo(String from, String to);
}
