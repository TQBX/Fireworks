package com.smday.fireworks.service.impl;

import com.smday.fireworks.common.utils.FastDfsUtils;
import com.smday.fireworks.common.utils.StringUtils;
import com.smday.fireworks.mbg.mapper.ImsImgMapper;
import com.smday.fireworks.mbg.model.ImsImg;
import com.smday.fireworks.mbg.model.ImsImgExample;
import com.smday.fireworks.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * ImageService实现类
 * created by Summer-day
 */
@Service
public class ImageServiceImpl implements ImageService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);
	
	@Value("${nginx.host}")
	private String nginxHost;
	
	@Autowired
	private ImsImgMapper imsImgMapper;
	
	@Override
	public ImsImg upload(MultipartFile file, Integer id) {
		
		ImsImg imsImg = new ImsImg();
		imsImg.setUserId(id);
		String imgUrl = null;
		try {
			//返回imgUrl   group/M00/00/00/xxx.png
			imgUrl = FastDfsUtils.upload(file);
		} catch (Exception e) {
			LOGGER.warn("{}:{}", e.getMessage(), file.getName());
			return null;
		}
		imsImg.setImgUrl(nginxHost + imgUrl);
		imsImg.setUploadTime(new Date());
		return imsImg;
	}
	
	@Override
	public boolean download(Integer id) {
		
		ImsImg img = imsImgMapper.selectByPrimaryKey(id);
		
		String imgUrl = img.getImgUrl();
		String fileId = StringUtils.substring(imgUrl, nginxHost.length());
		try {
			FastDfsUtils.download(fileId);
		}catch (Exception e){
			LOGGER.warn("下载异常:{}",e.getMessage());
			return false;
			
		}
		return true;
	
	
		
	}
	
	
}
