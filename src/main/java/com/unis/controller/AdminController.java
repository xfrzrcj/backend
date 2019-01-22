package com.unis.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unis.dao.mybatis.AdminMapper;
import com.unis.model.Admin;
import com.unis.utils.AuthCode;
import com.unis.utils.Constants;
import com.unis.utils.StringUtils;

@Controller
public class AdminController {
	@Autowired
	AdminMapper adminMapper;
	
	@RequestMapping(path = "login.do",method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam String name,@RequestParam String password, HttpServletRequest request) {
		Admin admin = adminMapper.selectByPrimaryKey(name);
		if(admin!=null&&admin.getPassword()!=null&&admin.getPassword().equals(StringUtils.getSha256(password))) {
			String hash = StringUtils.getSha256(name+password);
			request.getSession().setAttribute("user", StringUtils.getSha256(name+password));
			Constants.sessionMap.put(hash,name);
			if(admin.getPages()!=null&&!"".equals(admin.getPages())) {
				String[] pages = admin.getPages().split(",");
				Set<Integer> pagesList = new HashSet<Integer>();
				for(int i=0;i<pages.length;i++) {
					try {
						int page = Integer.parseInt(pages[i]);
						pagesList.add(page);
					}catch(NumberFormatException e) {
						e.printStackTrace();
					}
				}
				Constants.pagesMap.put(name, pagesList);
			}
			if(admin.getChannels()!=null&&!"".equals(admin.getChannels())) {
				String[] channels = admin.getChannels().split(",");
				Set<Integer> channelList = new HashSet<Integer>();
				for(int i=0;i<channels.length;i++) {
					try {
						int channel = Integer.parseInt(channels[i]);
						channelList.add(channel);
					}catch(NumberFormatException e) {
						e.printStackTrace();
					}
				}
				Constants.channelsMap.put(name, channelList);
			}
			return "true";
		}
		return "false";
	}
	
	public static boolean haveAuth(HttpSession session,AuthCode code) {
		String user = (String)session.getAttribute("user");
		Set<Integer> set = Constants.pagesMap.get(Constants.sessionMap.get(user));
		if(set!=null&&set.contains(code.getCode())) {
			return true;
		}
		return false;
	}
	
	public static boolean haveChannelAuth(HttpSession session,int channel) {
		String user = (String)session.getAttribute("user");
		Set<Integer> set = Constants.channelsMap.get(Constants.sessionMap.get(user));
		if(set!=null&&set.contains(channel)) {
			return true;
		}
		return false;
	}
	
	public static boolean isAuth(HttpSession session,int channel,AuthCode authcode) {
		String user = (String)session.getAttribute("user");
		Set<Integer> chanSet = Constants.channelsMap.get(Constants.sessionMap.get(user));
		Set<Integer> pageSet = Constants.pagesMap.get(Constants.sessionMap.get(user));
		if(chanSet!=null&&chanSet.contains(channel)&&pageSet!=null&&pageSet.contains(authcode.getCode())) {
			return true;
		}
		return false;
	}
}
