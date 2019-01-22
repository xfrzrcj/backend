package com.unis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.unis.dao.mybatis.AppInfoMapper;
import com.unis.model.AppInfo;
import com.unis.model.json_model.ChangePostionInfo;

@Service
public class AppService {
	@Autowired
	AppInfoMapper appMapper;
	
	public boolean updateAppPostion(List<ChangePostionInfo> list) {
		try {
			if(list!=null) {
				for(int i=0;i<list.size();i++) {
					AppInfo app = new AppInfo();
					app.setAppId(list.get(i).getApp());
					app.setChannelId(list.get(i).getChannel());
					app.setOrderFlag(list.get(i).getPos());
					appMapper.updateByPrimaryKeySelective(app);
				}
			}
			return true;
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return false;
	}
}
