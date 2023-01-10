package com.example.demo.module;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

public interface AbsSampleMapper<T extends AbsSampleData> extends BaseMapper<T> {




}
