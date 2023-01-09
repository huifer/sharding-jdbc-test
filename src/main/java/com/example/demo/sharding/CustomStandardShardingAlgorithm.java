package com.example.demo.sharding;

import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Slf4j
public final class CustomStandardShardingAlgorithm implements StandardShardingAlgorithm<Date> {
  @Override
  public String doSharding(Collection<String> collection, PreciseShardingValue<Date> shardingValue) {

    return null;
  }

  @Override
  public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
    return collection;
  }

  public void init() {

  }

  @Override
  public void init(Properties props) {

  }

  @Override
  public Properties getProps() {
    return null;
  }

  @Override
  public String getType() {
    return "ORDER_HIS";
  }
}
