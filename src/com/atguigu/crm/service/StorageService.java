package com.atguigu.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.mapper.ProductMapper;
import com.atguigu.crm.mapper.StorageMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.MyParseUtils;

@Service
public class StorageService {
	@Autowired
	 private StorageMapper storageMapper;
	@Autowired
	private ProductMapper productMapper;

	public Page<Storage> list(int pageNo, Map<String, Object> params) throws Exception {
		Page<Storage> page = new Page<>();
		
		page.setPageNo(pageNo);
		page.setPageSize(3);
		
		Map<String, Object> map = MyParseUtils.parseRequestParams2MyBatisParams(params);
		
		int totalElements = (int)storageMapper.getTotalElements(map);
		page.setTotalElements(totalElements);
		
		Integer startIndex = (pageNo-1)*3 +1;
		
		Integer endIndex = startIndex+3;
		
		map.put("startIndex", startIndex);
		
		map.put("endIndex", endIndex);	
		
		List<Storage> content =   storageMapper.getList(map);
		
		page.setContent(content);
		
		return page;
	}

	public List<Product> getProductList() {
	
		List<Product> list = productMapper.getProductList();
		
		return list;
	}

	public Storage getStorage(Integer id) {
		
		return storageMapper.getStorage(id);
	}

	public void save(Storage storage) {
		storageMapper.save(storage);
	}

	public void update(Storage storage, int count) {
	  int stockCount=	storage.getStockCount()+count;
	  storage.setStockCount(stockCount);
	  storageMapper.update(storage);
	}

	public void delete(Integer id) {
		storageMapper.delete(id);
	}
	
	
}
