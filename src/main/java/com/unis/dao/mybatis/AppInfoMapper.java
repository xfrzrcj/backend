package com.unis.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unis.model.AppInfo;
import com.unis.model.AppInfoKey;
import com.unis.model.json_model.GameInfo;

@Repository
public interface AppInfoMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_app_info
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(AppInfoKey key);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_app_info
	 * @mbg.generated
	 */
	int insert(AppInfo record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_app_info
	 * @mbg.generated
	 */
	int insertSelective(AppInfo record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_app_info
	 * @mbg.generated
	 */
	AppInfo selectByPrimaryKey(AppInfoKey key);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_app_info
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(AppInfo record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_app_info
	 * @mbg.generated
	 */
	int updateByPrimaryKey(AppInfo record);
	int countByGameInfo(GameInfo info);
    List<AppInfo> selectByGameInfo(GameInfo info);
}