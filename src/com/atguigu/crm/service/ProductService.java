package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.Product;
import com.atguigu.crm.mapper.ProductMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.MyParseUtils;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Transactional
	public Page<Product> getList(Map<String, Object> params, int pageNo) throws Exception {
		Page<Product> page = new Page<>();
		
		page.setPageNo(pageNo);
		
		page.setPageSize(3);
		
		Map<String, Object> map = MyParseUtils.parseRequestParams2MyBatisParams(params);
		
		int  totalElments =(int)productMapper.getTotalElements(map);
		
		page.setTotalElements(totalElments);
		
		Integer startIndex = (pageNo-1)*3 +1;
		
		Integer endIndex = startIndex+3;
		
		map.put("startIndex", startIndex);
		
		map.put("endIndex", endIndex);
		
		List<Product> content = productMapper.getList(map);
		
		page.setContent(content);
		
		return page;
	}

	public Product getProduct(Integer id) {
	
		return productMapper.getProduct(id);
	}

	public void update(Product product) {
		
	
		productMapper.update(product);
	}

	public void delete(Integer id) {
		
		productMapper.delete(id);
		
	}

	public void save(Product product) {
		productMapper.save(product);
	}
	
}
