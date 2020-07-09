package com.smday.fireworks;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;

@SpringBootTest
class FireworksApplicationTests {
	private final Logger LOGGER = LoggerFactory.getLogger(FireworksApplicationTests.class);
	@Test
	void upLoad() {
		try {
			ClientGlobal.initByProperties("fastdfs-client.properties");
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer, storageServer);
			NameValuePair nvp[] = null;
			//上传到文件系统
			String fileId = client.upload_file1("E:\\var\\work.png", "png", nvp);
			LOGGER.info(fileId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void downLoad(){
		try {
			ClientGlobal.initByProperties("fastdfs-client.properties");
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer, storageServer);
			byte[] bytes = client.download_file1("group1/M00/00/00/wKjVgV8CmcyAAEcPAAAWKyx7NbM594.png");
			FileOutputStream fos = new FileOutputStream(new File("E:\\6666.png"));
			fos.write(bytes);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
