package com.smday.fireworks.controller;

import com.smday.fireworks.common.api.CommonResult;
import com.smday.fireworks.mbg.model.ImsImg;
import com.smday.fireworks.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片管理控制器
 * created by Summer-day
 */

@RestController
@RequestMapping("/img")
@Api(tags = "UserController", description = "用户管理")
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	
	@ApiOperation("文件上传功能")
	@PostMapping("/upload")
	public CommonResult<ImsImg> upload(MultipartFile file, Integer id) {
		ImsImg img = imageService.upload(file, id);
		if(img == null) {
			return CommonResult.failed("文件上传失败");
		}
		return CommonResult.success(img, "文件上传成功");
	}
	
	@ApiOperation("文件下载功能")
	@GetMapping("/download/{id}")
	public CommonResult download(@PathVariable Integer id) {
		boolean download = imageService.download(id);
		return download ? CommonResult.success("文件下载成功") : CommonResult.failed("文件下载失败");
	}
}
