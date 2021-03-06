package com.unis.model;

import java.util.Date;

public class AppConfig {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_app_config.app_id
     *
     * @mbg.generated
     */
    private Integer appId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_app_config.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_app_config.app_key
     *
     * @mbg.generated
     */
    private String appKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_app_config.app_name
     *
     * @mbg.generated
     */
    private String appName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_app_config.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_app_config.app_id
     *
     * @return the value of t_app_config.app_id
     *
     * @mbg.generated
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_app_config.app_id
     *
     * @param appId the value for t_app_config.app_id
     *
     * @mbg.generated
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_app_config.id
     *
     * @return the value of t_app_config.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_app_config.id
     *
     * @param id the value for t_app_config.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_app_config.app_key
     *
     * @return the value of t_app_config.app_key
     *
     * @mbg.generated
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_app_config.app_key
     *
     * @param appKey the value for t_app_config.app_key
     *
     * @mbg.generated
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_app_config.app_name
     *
     * @return the value of t_app_config.app_name
     *
     * @mbg.generated
     */
    public String getAppName() {
        return appName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_app_config.app_name
     *
     * @param appName the value for t_app_config.app_name
     *
     * @mbg.generated
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_app_config.create_time
     *
     * @return the value of t_app_config.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_app_config.create_time
     *
     * @param createTime the value for t_app_config.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}