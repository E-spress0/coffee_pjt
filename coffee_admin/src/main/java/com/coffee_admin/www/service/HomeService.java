package com.coffee_admin.www.service;

import java.util.List;

public interface HomeService<HomeModel> {
	List<HomeModel> selectHome() throws Exception;
}
