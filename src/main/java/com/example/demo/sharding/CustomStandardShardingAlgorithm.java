package com.example.demo.sharding;

import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

/**
 * 定义历史表
 *
 * @author lance
 * @date 2022/3/5 21:28
 */
@Slf4j
public final class CustomStandardShardingAlgorithm implements StandardShardingAlgorithm<Date> {

  @Override
  public String doSharding(Collection<String> collection,
      PreciseShardingValue<Date> shardingValue) {
    Date date = shardingValue.getValue();
    String suffix = DateFormatUtils.format(date, "yyyyMM");

    return shardingValue.getLogicTableName() + "_" + suffix;
  }

  @Override
  public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
    return collection;
  }



  @Override
  public Properties getProps() {
    return null;
  }

  @Override
  public void init(Properties props) {

  }

  @Override
  public String getType() {
    return "ORDER_HIS";
  }
}
