package com.coffee_admin.www.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("HomeModel")
public class HomeModel {
	private Integer usercode;
	private String userid;
	private String userpw;
}
