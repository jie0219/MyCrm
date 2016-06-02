package com.atguigu.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.SalesPlan;


public interface SalesPlanMapper {

	void savePlan(SalesPlan plan);

	List<SalesPlan> getPlanList(@Param("id")Integer id);

	void updatePlan(@Param("id")Integer id, @Param("todo")String todo);

	void deletePlan(@Param("id") Integer id);

	void update(SalesPlan plan);
}
