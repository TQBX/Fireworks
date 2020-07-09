package com.smday.fireworks.service;

import com.smday.fireworks.mbg.model.ImsImg;
import com.smday.fireworks.mbg.model.UmsUser;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片service
 *
 * created by Summer-day
 */
public interface ImageService {
	
	ImsImg upload(MultipartFile file, Integer id);
	
	boolean download(Integer id);
}
