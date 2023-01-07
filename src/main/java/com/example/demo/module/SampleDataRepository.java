package com.example.demo.module;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleDataRepository extends JpaRepository<SampleData, Long> {

  @Query("from SampleData where time < ?1")
  List<SampleData> findByTimeLessThanEqual(LocalDateTime time);

  List<SampleData> findByTimeBefore(LocalDateTime time);

  List<SampleData> findByTimeLessThan(LocalDateTime time);

}