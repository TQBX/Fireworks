package com.smday.fireworks.controller;

import com.smday.fireworks.common.utils.VerificationCode;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.swing.plaf.synth.DefaultSynthStyle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 普通请求控制
 * created by Summer-day
 */
@RestController
@Api(tags = "CommonController", description = "普通请求")
public class CommonController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);
	@GetMapping("/verifyCode")
	public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
		VerificationCode code = new VerificationCode();
		BufferedImage image = code.getImage();
		String text = code.getText();
		HttpSession session = request.getSession();
		session.setAttribute("verifyCode",text);
		LOGGER.info("验证码是:{}",text);
		VerificationCode.output(image,resp.getOutputStream());
	}
	
	
}
