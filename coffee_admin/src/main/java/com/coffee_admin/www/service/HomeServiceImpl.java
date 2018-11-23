package com.coffee_admin.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffee_admin.www.mapper.HomeMapper;
import com.coffee_admin.www.model.HomeModel;

@Service
public class HomeServiceImpl implements HomeService<HomeModel> {

	@Autowired
	HomeMapper homeMapper;

	@Override
	public List<HomeModel> selectHome() throws Exception {
		return homeMapper.selectHome();
	}

}
