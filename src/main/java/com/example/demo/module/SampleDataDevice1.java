package com.example.demo.module;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "sample_data_device1")
@FieldNameConstants
public class SampleDataDevice1 implements Serializable {

  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String deviceId;
  private LocalDateTime time;

  private String v1;
  private String v2;
  private String v3;
  private String v4;
  private String v5;

  private Integer bol;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
