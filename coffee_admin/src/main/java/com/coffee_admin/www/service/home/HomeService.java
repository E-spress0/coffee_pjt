package com.coffee_admin.www.service.home;

import java.util.List;

public interface HomeService<HomeModel> {
	List<HomeModel> selectHome() throws Exception;
}
