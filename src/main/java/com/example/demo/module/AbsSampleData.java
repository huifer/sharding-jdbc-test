package com.example.demo.module;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbsSampleData {
  protected Long id;

  private String deviceId;
  private LocalDateTime time;

  private String v1;
  private String v2;
  private String v3;
  private String v4;
  private String v5;

  private Integer bol;

}
