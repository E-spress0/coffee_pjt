package com.coffee_admin.www.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.coffee_admin.www.model.HomeModel;

@Repository
public interface HomeMapper {
	List<HomeModel> selectHome() throws Exception;
}
