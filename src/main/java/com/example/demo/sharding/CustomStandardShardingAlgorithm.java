package com.example.demo.sharding;

import com.example.demo.utils.SpringApplicationContextHolder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

/**
 * 定义历史表
 *
 * @author lance
 * @date 2022/3/5 21:28
 */
@Slf4j
public final class CustomStandardShardingAlgorithm implements StandardShardingAlgorithm<Date> {

  String SQL = """
            
      DROP TABLE IF EXISTS `?1`;
      CREATE TABLE `?1` (
        `id` bigint NOT NULL AUTO_INCREMENT,
        `device_id` varchar(255) DEFAULT NULL,
        `time` datetime DEFAULT NULL,
        `v1` varchar(255) DEFAULT NULL,
        `v2` varchar(255) DEFAULT NULL,
        `v3` varchar(255) DEFAULT NULL,
        `v4` varchar(255) DEFAULT NULL,
        `v5` varchar(255) DEFAULT NULL,
        PRIMARY KEY (`id`)
      ) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
            
      """;
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
    return   shardingValue.getLogicTableName()  + "_"+ year + "_" + mon;

  }

  @Override
  public Collection<String> doSharding(Collection<String> collection,
      RangeShardingValue<Date> rangeShardingValue) {


//    for (String s : collection) {
//      extracted(s);
//    }
    return collection;
  }

  private static void extracted(String item) {
    ApplicationContext ctx = SpringApplicationContextHolder.getCtx();
    DataSource bean = ctx.getBean(DataSource.class);
    JdbcTemplate jdbcTemplate = new JdbcTemplate(bean);


      // 创建表
      String sql = "CREATE TABLE if not exists `" + item + "` (\n"
          + "  `id` bigint NOT NULL ,\n"
          + "  `device_id` varchar(255) DEFAULT NULL,\n"
          + "  `time` datetime DEFAULT NULL,\n"
          + "  `v1` varchar(255) DEFAULT NULL,\n"
          + "  `v2` varchar(255) DEFAULT NULL,\n"
          + "  `v3` varchar(255) DEFAULT NULL,\n"
          + "  `v4` varchar(255) DEFAULT NULL,\n"
          + "  `v5` varchar(255) DEFAULT NULL,\n"
          + "  PRIMARY KEY (`id`)\n"
          + ") ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
      jdbcTemplate.execute(sql);
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
