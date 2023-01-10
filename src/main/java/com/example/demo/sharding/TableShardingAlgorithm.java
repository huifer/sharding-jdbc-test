//package com.example.demo.sharding;
//
//import com.google.common.collect.Range;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import javax.sql.DataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
//import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
//import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
///**
// * 由于这段代码中有定制的数据表生成语句因此只能给一张表用
// */
//@Slf4j
//@Component
//public class TableShardingAlgorithm implements PreciseShardingAlgorithm<Timestamp>,
//    RangeShardingAlgorithm<Timestamp> {
//
//
//  static JdbcTemplate jdbcTemplate;
//  private static DataSource dataSource;
//
//  public static void setDataSource(DataSource ds) {
//    dataSource = ds;
//    jdbcTemplate = new JdbcTemplate(dataSource);
//
//  }
//
//  private static void extracted(String item) {
//    List<String> name = jdbcTemplate.query(
//        "select table_name as name from INFORMATION_SCHEMA.TABLES  where table_name like '%s';\n".formatted(
//            item),
//        new RowMapper<String>() {
//          @Override
//          public String mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return rs.getString("name");
//          }
//
//        });
//
//    if (CollectionUtils.isEmpty(name)) {
//      // 创建表
//      String sql = "CREATE TABLE `" + item + "` (\n"
//          + "  `id` bigint NOT NULL ,\n"
//          + "  `device_id` varchar(255) DEFAULT NULL,\n"
//          + "  `time` datetime DEFAULT NULL,\n"
//          + "  `v1` varchar(255) DEFAULT NULL,\n"
//          + "  `v2` varchar(255) DEFAULT NULL,\n"
//          + "  `v3` varchar(255) DEFAULT NULL,\n"
//          + "  `v4` varchar(255) DEFAULT NULL,\n"
//          + "  `v5` varchar(255) DEFAULT NULL,\n"
//          + "  PRIMARY KEY (`id`)\n"
//          + ") ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
//      jdbcTemplate.execute(sql);
//    }
//  }

//  @Override
//  public Collection<String> doSharding(Collection<String> collection,
//      RangeShardingValue<Timestamp> rangeShardingValue) {
//
//    List<String> list = new ArrayList<>();
//    log.info("availableTargetNames : " + collection);
//    log.info(rangeShardingValue.toString());
//    Range<Timestamp> valueRange = rangeShardingValue.getValueRange();
//
//    String logicTableName = rangeShardingValue.getLogicTableName();
//    List<String> name = jdbcTemplate.query(
//        "select table_name as name from INFORMATION_SCHEMA.TABLES  where table_name like '%s';\n".formatted(
//            logicTableName + "%"),
//        new RowMapper<String>() {
//          @Override
//          public String mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return rs.getString("name");
//          }
//
//        });
//    for (String s : name) {
//      String[] s1 = s.split("_");
//      if (s1.length == 4) {
//        Integer year = Integer.valueOf(s1[2]);
//        Integer month = Integer.valueOf(s1[3]);
//
//        LocalDate date = LocalDate.of(year, month, 1);
//
//        LocalDate start =
//            valueRange.hasLowerBound() ? valueRange.lowerEndpoint().toLocalDateTime().toLocalDate()
//                : LocalDate.MIN;
//        LocalDate end =
//            valueRange.hasUpperBound() ? valueRange.upperEndpoint().toLocalDateTime().toLocalDate()
//                : LocalDate.MAX;
//
//        if ((date.toEpochDay() <= end.toEpochDay()) && (date.toEpochDay() >= start.toEpochDay())) {
//          if (!list.contains(s)) {
//
//            list.add(s);
//          }
//        }
//      }
//    }
//    log.info("查询目标表 = {}", list);
//    return list;
//  }
//
//  @Override
//  public String doSharding(Collection<String> availableTargetNames,
//      PreciseShardingValue<Timestamp> shardingValue) {
//
//    availableTargetNames.stream().forEach((item) -> {
//      log.info("实际数据表:{}", item);
//    });
//
//    log.info("需要分片的表:{},分片字段:{}", shardingValue.getLogicTableName(),
//        shardingValue.getColumnName());
//
//    //精确分片
//    log.info("分片值:{}", shardingValue.getValue());
//
//    String tb_name = shardingValue.getLogicTableName() + "_";
//
//    // 根据当前日期 来 分库分表
//    Timestamp date = shardingValue.getValue();
//    LocalDateTime localDateTime = date.toLocalDateTime();
//    String year = String.valueOf(localDateTime.getYear());
//    String mon = String.valueOf(localDateTime.getMonthValue()); // 去掉前缀0
//
//    // 选择表
//    tb_name = tb_name + year + "_" + mon;
//    log.info("目标表: {}", tb_name);
//
////    for (String each : availableTargetNames) {
////      if (each.equals(tb_name)) {
////        extracted(tb_name);
////        return each;
////      }
////    }
//    extracted(tb_name);
//
//    return tb_name;
//  }
//}
