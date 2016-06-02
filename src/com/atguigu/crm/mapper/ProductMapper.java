package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Product;

public interface ProductMapper {

	List<Product> getList(Map<String, Object> params);

	long getTotalElements(Map<String, Object> map);

	Product getProduct(@Param("id")Integer id);

	void update(Product product);

	void delete(@Param("id")Integer id);

	void save(Product product);

	List<Product> getProductList();
	
	
	
}
