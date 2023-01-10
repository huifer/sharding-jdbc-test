package com.example.demo.sharding;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
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


  public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
  }

  @Override
  public String doSharding(Collection<String> collection,
      PreciseShardingValue<Date> shardingValue) {

    Date date = shardingValue.getValue();
    LocalDate localDate = convertToLocalDateViaMilisecond(date);
    String year = String.valueOf(localDate.getYear());
    String mon = String.valueOf(localDate.getMonthValue()); // 去掉前缀0

    // 选择表
    return shardingValue.getLogicTableName() + "_" + year + "_" + mon;

  }

  @Override
  public Collection<String> doSharding(Collection<String> collection,
      RangeShardingValue<Date> rangeShardingValue) {

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
