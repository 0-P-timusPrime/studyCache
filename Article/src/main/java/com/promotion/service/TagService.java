package com.promotion.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.promotion.pojo.Tag;
import com.promotion.pojo.TagExample;

public interface TagService {
	
	 int countByExample(TagExample example);

	    int deleteByExample(TagExample example);

	    int deleteByPrimaryKey(Integer id);

	    int insert(Tag record);

	    int insertSelective(Tag record);

	    List<Tag> selectByExample(TagExample example);

	    Tag selectByPrimaryKey(Integer id);

	    int updateByExampleSelective(@Param("record") Tag record, @Param("example") TagExample example);

	    int updateByExample(@Param("record") Tag record, @Param("example") TagExample example);

	    int updateByPrimaryKeySelective(Tag record);

	    int updateByPrimaryKey(Tag record);
}