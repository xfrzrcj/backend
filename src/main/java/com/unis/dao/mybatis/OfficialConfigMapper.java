package com.unis.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.unis.model.OfficialConfig;

@Repository
public interface OfficialConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_official_config
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String account);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_official_config
     *
     * @mbg.generated
     */
    int insert(OfficialConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_official_config
     *
     * @mbg.generated
     */
    int insertSelective(OfficialConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_official_config
     *
     * @mbg.generated
     */
    OfficialConfig selectByPrimaryKey(String account);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_official_config
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OfficialConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_official_config
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OfficialConfig record);
}