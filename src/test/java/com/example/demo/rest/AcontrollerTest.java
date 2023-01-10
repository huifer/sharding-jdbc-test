package com.example.demo.rest;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.module.SampleData;
import com.example.demo.module.SampleDataDevice1;
import com.example.demo.module.SampleDevice1Mapper;
import com.example.demo.module.SampleMapper;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("shar")
class AcontrollerTest {

  @Autowired
  private Acontroller acontroller;
  @Autowired
  private SampleMapper sampleMapper;
  @Autowired
  private SampleDevice1Mapper sampleDevice1Mapper;
  @Test
  void device1(){
    for (int i = 0; i < 3; i++) {
      SampleDataDevice1 entity = new SampleDataDevice1();
      entity.setTime(LocalDateTime.of(2023, 1, 01, 00, 00, 00));
      entity.setDeviceId("device" + (i + 1));
      entity.setV1(RandomUtil.randomNumbers(1));
      entity.setV2(RandomUtil.randomNumbers(2));
      entity.setV3(RandomUtil.randomNumbers(3));
      entity.setV4(RandomUtil.randomNumbers(4));
      entity.setV5(RandomUtil.randomNumbers(5));
      entity.setBol(i % 2);
      sampleDevice1Mapper.insert(entity);
    }
  }

  @Test
  void device1Query() {
    QueryWrapper<SampleDataDevice1> queryWrapper = new QueryWrapper<>();
    queryWrapper.lambda()
        .lt(SampleDataDevice1::getTime, LocalDateTime.of(2025, 3, 1, 00, 00, 00));

    List<SampleDataDevice1> sampleDataDevice1s = sampleDevice1Mapper.selectList(queryWrapper);
    System.out.println();
  }

  @Test
  void save() {
    for (int i = 0; i < 3; i++) {
      SampleData entity = new SampleData();
      entity.setTime(LocalDateTime.of(2023, 2, 01, 00, 00, 00));
      entity.setDeviceId("device" + (i + 1));
      entity.setV1(RandomUtil.randomNumbers(1));
      entity.setV2(RandomUtil.randomNumbers(2));
      entity.setV3(RandomUtil.randomNumbers(3));
      entity.setV4(RandomUtil.randomNumbers(4));
      entity.setV5(RandomUtil.randomNumbers(5));
      entity.setBol(i % 2);
      sampleMapper.insert(entity);
    }
  }

  @Test
  void find() {
    QueryWrapper<SampleData> queryWrapper = new QueryWrapper<>();
    queryWrapper.lambda()
        .in(SampleData::getDeviceId, "device1", "device2")
        .lt(SampleData::getTime, LocalDateTime.of(2025, 3, 1, 00, 00, 00));
    List<SampleData> sampleData = sampleMapper.selectList(queryWrapper);
    System.out.println();
  }


  @Test
  void gen() {
    acontroller.generator(2023);
  }

  @Test
  void data() {
    Object data = acontroller.data(2077);
    System.out.println();
  }
}