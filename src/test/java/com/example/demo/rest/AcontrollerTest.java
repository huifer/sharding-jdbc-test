package com.example.demo.rest;

import static org.junit.jupiter.api.Assertions.*;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.module.SampleData;
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

  @Test
  void save(){
    for (int i = 0; i < 3; i++) {
      SampleData entity = new SampleData();
      entity.setTime(LocalDateTime.of(2023, 2, 01, 00, 00, 00));
      entity.setDeviceId("dev_" + i);
      entity.setV1(RandomUtil.randomNumbers(1));
      entity.setV2(RandomUtil.randomNumbers(2));
      entity.setV3(RandomUtil.randomNumbers(3));
      entity.setV4(RandomUtil.randomNumbers(4));
      entity.setV5(RandomUtil.randomNumbers(5));
      sampleMapper.insert(entity);
    }
  }

  @Test
  void find(){
    QueryWrapper<SampleData> queryWrapper = new QueryWrapper<>();
    queryWrapper.lambda().lt(SampleData::getTime, LocalDateTime.of(2025, 3, 1, 00, 00, 00));
    List<SampleData> sampleData = sampleMapper.selectList(queryWrapper);
    System.out.println();
  }


  @Test
  void gen(){
    acontroller.generator(2023);
  }

  @Test
  void data() {
    Object data = acontroller.data(2077);
    System.out.println();
  }
}