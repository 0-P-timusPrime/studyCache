package com.promotion.dao;

import com.promotion.pojo.Articles;
import com.promotion.pojo.ArticlesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticlesMapper {
    int countByExample(ArticlesExample example);

    int deleteByExample(ArticlesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Articles record);

    int insertSelective(Articles record);

    List<Articles> selectByExample(ArticlesExample example);

    Articles selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Articles record, @Param("example") ArticlesExample example);

    int updateByExample(@Param("record") Articles record, @Param("example") ArticlesExample example);

    int updateByPrimaryKeySelective(Articles record);

    int updateByPrimaryKey(Articles record);
}