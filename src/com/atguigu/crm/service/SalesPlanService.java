package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.mapper.SalesPlanMapper;

@Service
public class SalesPlanService {

	@Autowired
	private SalesPlanMapper planMapper;

	@Transactional
	public void savePlan(SalesPlan plan) {
		planMapper.savePlan(plan);
	}
	@Transactional
	public List<SalesPlan> getPlanList(Integer id) {
		return planMapper.getPlanList(id);
	}
	@Transactional
	public void savePlan(Integer id, String todo) {
		planMapper.updatePlan(id,todo);
	}
	@Transactional
	public void deletePlan(Integer id) {
		planMapper.deletePlan(id);
	}
	@Transactional
	public void update(SalesPlan plan) {
		planMapper.update(plan);
	}
}
