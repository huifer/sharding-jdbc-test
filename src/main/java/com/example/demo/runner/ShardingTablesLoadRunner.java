//package com.example.demo.runner;
//
////import com.example.demo.sharding.TableShardingAlgorithm;
//import javax.sql.DataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * 项目启动后 读取已有分表 进行缓存
// */
//@Slf4j
//@Order(value = 1) // 数字越小 越先执行
//@Component
//public class ShardingTablesLoadRunner implements CommandLineRunner {
//
//    @Autowired
//    private DataSource dataSource;
//    @Override
//    public void run(String... args) throws Exception {
//
//        // 给 分表工具类注入属性
//        TableShardingAlgorithm.setDataSource(dataSource);
//        log.info("ShardingTablesLoadRunner start OK");
//    }
//}
//
