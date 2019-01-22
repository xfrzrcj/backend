package com.unis.model.json_model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserSelect {
	private Integer status;
	private String mobile;
	private String account;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTimeStart;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTimeEnd;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTimeStart;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTimeEnd;
	private Double balanceMax;
	private Double balanceMin;
	private Double rechargeMax;
	private Double rechargeMin;
	private Integer channel;
	private String displayName;
	private Integer pageNum;
	private Integer pageSize;
	public Double getRechargeMax() {
		return rechargeMax;
	}
	public void setRechargeMax(Double rechargeMax) {
		this.rechargeMax = rechargeMax;
	}
	public Double getRechargeMin() {
		return rechargeMin;
	}
	public void setRechargeMin(Double rechargeMin) {
		this.rechargeMin = rechargeMin;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Date getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public Date getUpdateTimeStart() {
		return updateTimeStart;
	}
	public void setUpdateTimeStart(Date updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}
	public Date getUpdateTimeEnd() {
		return updateTimeEnd;
	}
	public void setUpdateTimeEnd(Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}
	public Double getBalanceMax() {
		return balanceMax;
	}
	public void setBalanceMax(Double balanceMax) {
		this.balanceMax = balanceMax;
	}
	public Double getBalanceMin() {
		return balanceMin;
	}
	public void setBalanceMin(Double balanceMin) {
		this.balanceMin = balanceMin;
	}
	public UserSelect(Integer channel,Integer status, String mobile, String account, Date createTimeStart, Date createTimeEnd,
			Date updateTimeStart, Date updateTimeEnd, Double balanceMax, Double balanceMin) {
		super();
		this.channel = channel;
		this.status = status;
		this.mobile = mobile;
		this.account = account;
		this.createTimeStart = createTimeStart;
		this.createTimeEnd = createTimeEnd;
		this.updateTimeStart = updateTimeStart;
		this.updateTimeEnd = updateTimeEnd;
		this.balanceMax = balanceMax;
		this.balanceMin = balanceMin;
	}
	public UserSelect() {
		super();
		// TODO Auto-generated constructor stub
	}

}
