package com.coffee_admin.www.model.board;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Alias("BoardModel")
@EqualsAndHashCode(callSuper = false)
public class BoardModel {
	private Integer seq;
	private String writer;
	private String title;
	private String content;
}
