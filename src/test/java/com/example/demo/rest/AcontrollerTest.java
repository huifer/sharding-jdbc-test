package com.example.demo.rest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("shar")
class AcontrollerTest {
  @Autowired
  private Acontroller acontroller;

  @Test
  void gen(){
    acontroller.generator(2024);
  }

  @Test
  void data() {
    Object data = acontroller.data(2077);
    System.out.println();
  }
}