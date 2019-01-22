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
import com.unis.dao.mybatis.AppInfoMapper;
import com.unis.model.AppInfo;
import com.unis.model.AppInfoKey;
import com.unis.model.json_model.ChangePostionInfo;
import com.unis.model.json_model.GameInfo;
import com.unis.model.json_model.GameUpdate;
import com.unis.model.return_model.Result;
import com.unis.model.return_model.ReturnList;
import com.unis.service.AppService;
import com.unis.utils.AuthCode;
import com.unis.utils.Constants;
import com.unis.utils.ERROR_CODE;

@Controller
public class GameController {
	@Autowired
	AppInfoMapper appMapper;
	@Autowired
	AppService appService;
	
	@RequestMapping(path = "getGameByKey.do", method = RequestMethod.POST)
	@ResponseBody
	public String getGameByKey(@RequestBody AppInfoKey key,HttpServletRequest request) {
		Result res = new Result();
		if(!AdminController.isAuth(request.getSession(), key.getChannelId(),AuthCode.AUTH_EDIT_APP)) {
			res.setCode(ERROR_CODE.NO_AUTH);
			return Constants.gson.toJson(res);
		}
		AppInfo info = appMapper.selectByPrimaryKey(key);
		res.setData(info);
		res.setCode(ERROR_CODE.SUCCESS);
		return Constants.gson.toJson(res);
	}
	
	
	@RequestMapping(path = "getGames.do", method = RequestMethod.POST)
	@ResponseBody
	public String getGames(@RequestBody GameInfo info,HttpServletRequest request) {
		Result res = new Result();
		if (info.getPageNum() == null || info.getPageSize() == null || info.getChannel() == null) {
			res.setCode(ERROR_CODE.PARAM_ERROR);
			return Constants.gson.toJson(res);
		}
		if(!AdminController.isAuth(request.getSession(), info.getChannel(),AuthCode.AUTH_GAME_LIST)) {
			res.setCode(ERROR_CODE.NO_AUTH);
			return Constants.gson.toJson(res);
		}
		PageHelper.startPage(info.getPageNum(), info.getPageSize());
		List<AppInfo> list = appMapper.selectByGameInfo(info);
		int num = appMapper.countByGameInfo(info);
		res.setCode(ERROR_CODE.SUCCESS);
		ReturnList<AppInfo> data = new ReturnList<AppInfo>();
		data.setList(list);
		data.setTotal(num);
		res.setData(data);
		return Constants.gson.toJson(res);
	}

	

	@RequestMapping(path = "updateApps.do", method = RequestMethod.POST)
	@ResponseBody
	public String updateGames(@RequestBody GameUpdate update,HttpServletRequest request) {
		Result res = new Result();
		if (update.getChannel() == null || update.getAppId() == null) {
			res.setCode(ERROR_CODE.PARAM_ERROR);
			return Constants.gson.toJson(res);
		}
		if(!AdminController.isAuth(request.getSession(), update.getChannel(),AuthCode.AUTH_EDIT_APP)) {
			res.setCode(ERROR_CODE.NO_AUTH);
			return Constants.gson.toJson(res);
		}
		AppInfo info = new AppInfo();
		info.setChannelId(update.getChannel());
		info.setAppId(update.getAppId());
		if(update.getStatus()!=null) {
			info.setStatus(update.getStatus());
			if(appMapper.updateByPrimaryKeySelective(info)==1) {
				res.setCode(ERROR_CODE.SUCCESS);
			}else{
				res.setCode(ERROR_CODE.DB_ERROR);
			}
			return Constants.gson.toJson(res);
		}
		info.setStatus(update.getStatus());
		info.setDescription(update.getComment());
		info.setPlayerCount(update.getHot());
		if(update.getTags()!=null) {
			int len = update.getTags().size();
			StringBuffer sb = new StringBuffer();
			for(int i = 0;i<len;i++) {
				sb.append(update.getTags().get(i)+",");
			}
			String s = sb.toString();
			if(!s.equals("")) {
				s = s.substring(0, s.length()-1);
			}
			info.setTarget(s);
		}
		if(1==appMapper.updateByPrimaryKeySelective(info)) {
			res.setCode(ERROR_CODE.SUCCESS);
		}else{
			res.setCode(ERROR_CODE.DB_ERROR);
		}
		return Constants.gson.toJson(res);
	}
	
	@RequestMapping(path = "changePostion.do", method = RequestMethod.POST)
	@ResponseBody
	public String changePostion(@RequestBody List<ChangePostionInfo> list,HttpServletRequest request) {
		Result res = new Result();
		Integer channel = 0;
		if(list!=null&&!list.isEmpty()) {
			channel = list.get(0).getChannel();
			if(channel!=null) {
				for(int i = 0;i<list.size();i++) {
					if(channel!=list.get(i).getChannel()) {
						res.setCode(ERROR_CODE.PARAM_ERROR);
						return Constants.gson.toJson(res);
					}
				}
				if(AdminController.isAuth(request.getSession(), channel,AuthCode.AUTH_EDIT_APP)) {
					if(appService.updateAppPostion(list)) {
						res.setCode(ERROR_CODE.SUCCESS);
					}else {
						res.setCode(ERROR_CODE.DB_ERROR);
					}
				}else {
					res.setCode(ERROR_CODE.NO_AUTH);
				}
			}else {
				res.setCode(ERROR_CODE.PARAM_ERROR);
			}
		}else {
			res.setCode(ERROR_CODE.PARAM_ERROR);
		}
		return Constants.gson.toJson(res);
	}
	
}
