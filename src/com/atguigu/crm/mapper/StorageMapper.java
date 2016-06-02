package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Storage;

public interface StorageMapper {

	long getTotalElements(Map<String, Object> map);

	List<Storage> getList(Map<String, Object> map);

	Storage getStorage(@Param("id")Integer id);

	void save(Storage storage);

	void update(Storage storage);

	void delete(@Param("id")Integer id);

}
