package com.example.demo.rest;

import cn.hutool.core.util.RandomUtil;
import com.example.demo.module.SampleData;
import com.example.demo.module.SampleDataRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class Acontroller {

  static List<String> deviceId = new ArrayList<>();

  static {
    deviceId.add("device_1");
    deviceId.add("device_2");
    deviceId.add("device_3");
  }

  @Autowired
  private SampleDataRepository sampleDataRepository;
  @Autowired
  private DataSource dataSource;

  @GetMapping("/ac")
  public void generator(int year) {
    List<SampleData> datas = new ArrayList<>();
    long i = 1;
    for (String s : deviceId) {

      SampleData entity = new SampleData();
      entity.setTime(LocalDateTime.of(year, 2, 01, 00, 00, 00));
      entity.setDeviceId(s);
      entity.setV1(RandomUtil.randomNumbers(1));
      entity.setV2(RandomUtil.randomNumbers(2));
      entity.setV3(RandomUtil.randomNumbers(3));
      entity.setV4(RandomUtil.randomNumbers(4));
      entity.setV5(RandomUtil.randomNumbers(5));
      datas.add(entity);
    }

    sampleDataRepository.saveAll(datas);
    System.out.println();
  }

  @GetMapping("/query")
  public List<SampleData> data(int year) {
    List<SampleData> byTimeLessThan = sampleDataRepository.findByTimeLessThanEqual(LocalDateTime.of(year, 1, 01, 00, 00, 00));
    return byTimeLessThan;
  }
}
