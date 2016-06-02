package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Dict;
/*
 * 阿谱在此祝福
 */
public interface DictMapper {

	long getTotalElements();

	List<Dict> getContent(Map<String, Object> params);

	void save(Dict dict);

	void delete(Integer id);

	Dict getById(Integer id);

	void update(Dict dict);

	long getTotalElements2(Map<String, Object> params);

	List<Dict> getContent2(Map<String, Object> params);

}
