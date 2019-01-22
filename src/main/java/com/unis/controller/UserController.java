package com.unis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.unis.dao.mybatis.TranHistoryMapper;
import com.unis.dao.mybatis.UserMapper;
import com.unis.model.TranHistory;
import com.unis.model.User;
import com.unis.model.json_model.FreezeUser;
import com.unis.model.json_model.TranInfo;
import com.unis.model.json_model.UserSelect;
import com.unis.model.return_model.Result;
import com.unis.model.return_model.ReturnList;
import com.unis.utils.AuthCode;
import com.unis.utils.Constants;
import com.unis.utils.ERROR_CODE;

@Controller
public class UserController {
	@Autowired
	UserMapper userMapper;

	@RequestMapping(path = "getUserList.do", method = RequestMethod.POST)
	@ResponseBody
	public String getUser(@RequestBody UserSelect sel,HttpServletRequest request) {
		Result res = new Result();
		if(sel.getPageNum()==null||sel.getPageSize()==null||sel.getChannel()==null) {
			res.setCode(ERROR_CODE.PARAM_ERROR);
			return Constants.gson.toJson(res);
		}
		if(!AdminController.isAuth(request.getSession(), sel.getChannel(),AuthCode.AUTH_ACCOUNT_LIST)) {
			res.setCode(ERROR_CODE.NO_AUTH);
			return Constants.gson.toJson(res);
		}
		PageHelper.startPage(sel.getPageNum(), sel.getPageSize());
		List<User> list = userMapper.selectByUserSelect(sel);
		int num = userMapper.countByUserSelect(sel);
		res.setCode(ERROR_CODE.SUCCESS);
		ReturnList<User> data = new ReturnList<User>();
		data.setList(list);
		data.setTotal(num);
		res.setData(data);
		return Constants.gson.toJson(res);
	}
	
	@RequestMapping(path="freezeUser.do",method = RequestMethod.POST)
	@ResponseBody
	public String freeze(@RequestBody FreezeUser freeze,HttpServletRequest request) {
		Result res = new Result();
		if(freeze.getAccount()==null||"".equals(freeze.getAccount())||freeze.getFreeze()==null) {
			res.setCode(ERROR_CODE.PARAM_ERROR);
			return Constants.gson.toJson(res);
		}
		try {
			int channel = Integer.parseInt(freeze.getAccount().split("@")[0]);
			if(!AdminController.isAuth(request.getSession(), channel,AuthCode.AUTH_FREEZE_USER)) {
				res.setCode(ERROR_CODE.NO_AUTH);
				return Constants.gson.toJson(res);
			}
		}catch(NumberFormatException e) {
			res.setCode(ERROR_CODE.PARAM_ERROR);
			return Constants.gson.toJson(res);
		}
		
		User user = new User();
		user.setAccount(freeze.getAccount());
		if(freeze.getFreeze()==1) {
			user.setStatus(1);
		}else{
			user.setStatus(0);
		}
		if(1==userMapper.updateByPrimaryKeySelective(user)) {
			res.setCode(ERROR_CODE.SUCCESS);
		}else {
			res.setCode(ERROR_CODE.DB_ERROR);
		}
		return Constants.gson.toJson(res);
	}	

}
