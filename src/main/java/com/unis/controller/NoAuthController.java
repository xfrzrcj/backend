package com.unis.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unis.dao.mybatis.AppConfigMapper;
import com.unis.dao.mybatis.ChannelConfigMapper;
import com.unis.model.AppConfig;
import com.unis.model.ChannelConfig;
import com.unis.model.return_model.Result;
import com.unis.model.return_model.ReturnList;
import com.unis.model.return_model.ReturnPage;
import com.unis.utils.Constants;
import com.unis.utils.ERROR_CODE;
import com.unis.utils.PropertyUtils;

@Controller
public class NoAuthController {
	@Autowired
	AppConfigMapper appConfigMapper;
	@Autowired
	ChannelConfigMapper channelMapper;
	
	@RequestMapping(path = "getApps.do", method = RequestMethod.POST)
	@ResponseBody
	public String getGames(HttpServletRequest request) {
		Result res = new Result();
		List<AppConfig> list = appConfigMapper.selectAll();
		res.setCode(ERROR_CODE.SUCCESS);
		ReturnList<AppConfig> data = new ReturnList<AppConfig>();
		data.setList(list);
		data.setTotal(list.size());
		res.setData(data);
		return Constants.gson.toJson(res);
	}
	
	@RequestMapping(path="getChannel.do",method=RequestMethod.POST)
	@ResponseBody
	public String getChannel(HttpServletRequest request) {
		Result res = new Result();
		Iterator<ChannelConfig> it = channelMapper.selectAll().iterator();
		List<ChannelConfig> reslist = new ArrayList<>();
		String user = (String)request.getSession().getAttribute("user");
		Set<Integer> set = Constants.channelsMap.get(Constants.sessionMap.get(user));
		while(it.hasNext()) {
			ChannelConfig cc = it.next();
			if(set.contains(cc.getChannelId())) {
				reslist.add(cc);
			}
		}
		res.setCode(ERROR_CODE.SUCCESS);
		ReturnList<ChannelConfig> ll = new ReturnList<>();
		ll.setTotal(reslist.size());
		ll.setList(reslist);
		res.setData(ll);
		return Constants.gson.toJson(res);
	}
	
	@RequestMapping(path = "getPages.do",method = RequestMethod.GET)
	@ResponseBody
	public String getPages(HttpServletRequest request) {
		Result res = new Result();
		String session = (String)request.getSession().getAttribute("user");
		Set<Integer> set = Constants.pagesMap.get(Constants.sessionMap.get(session));
		Properties property = PropertyUtils.getProperty(Constants.XML_MAPPING);
		if(set!=null) {
			List<ReturnPage> data = new ArrayList<>();
			res.setData(data);
			Iterator<Object> keysIt = property.keySet().iterator();
			Set<Integer> sortSet = new TreeSet<>();
			while(keysIt.hasNext()) {
				String key = (String)keysIt.next();
				if(key.startsWith("group_")) {
					try {
						sortSet.add(Integer.parseInt(key.substring(6)));
					}catch(NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
			Iterator<Integer> itt = sortSet.iterator();
			while(itt.hasNext()) {
				String key = "group_"+itt.next();
				String value = (String)property.get(key);
				if(null!=value) {
					String[] vv = value.split("@");
					if(vv.length==2) {
						String[] pages = vv[0].split(",");
						ReturnPage returnPage = new ReturnPage();
						List<String> dataList = new ArrayList<>();
						returnPage.setPages(dataList);
						returnPage.setGroup(vv[1]);
						for(int j = 0;j<pages.length;j++) {
							try {
								int page = Integer.parseInt(pages[j]);
								if(set.contains(page)) {
									dataList.add(property.getProperty(Constants.STR_PAGE+page));
								}
							}catch(NumberFormatException e) {
								e.printStackTrace();
							}
						}
						data.add(returnPage);
					}
				}
			}
			
			res.setCode(ERROR_CODE.SUCCESS);
		}else {
			res.setCode(ERROR_CODE.NO_AUTH);
		}
		return Constants.gson.toJson(res);
	}
}
