package com.smday.fireworks.common.utils;

import com.smday.fireworks.common.exception.ApiException;
import io.swagger.annotations.Api;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FastDfs
 * created by Summer-day
 */
public class FastDfsUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(FastDfsUtils.class);
	private static StorageClient1 client1;
	
	static {
		try {
			ClientGlobal.initByProperties("fastdfs-client.properties");
			TrackerClient trackerClient = new TrackerClient();
			TrackerServer trackerServer = trackerClient.getConnection();
			client1 = new StorageClient1(trackerServer, null);
		} catch (IOException | MyException e) {
			LOGGER.warn("配置文件读取失败:{}",e.getMessage());
			throw new ApiException("配置文件读取失败");
		}
	}
	
	public static String upload(MultipartFile file) {
		String oldName = file.getOriginalFilename();
		try {
			return client1.upload_file1(file.getBytes(), oldName.substring(oldName.lastIndexOf(".") + 1), null);
		} catch (IOException | MyException e) {
			LOGGER.warn("文件上传失败:{}",e.getMessage());
			throw new ApiException("文件上传失败!");

		}
	}
	
	public static void download(String fileId){
		try{
			byte[] bytes = client1.download_file1(fileId);
			FileOutputStream fos = new FileOutputStream(new File("E://"+fileId+"png"));
			fos.write(bytes);
			fos.close();
			
		}catch (IOException | MyException e) {
			LOGGER.warn("文件下载失败:{}",e.getMessage());
			throw new ApiException("文件下载失败!");
		}
	}
	
	
}
