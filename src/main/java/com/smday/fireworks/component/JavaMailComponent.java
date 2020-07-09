package com.smday.fireworks.component;

import com.smday.fireworks.common.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * created by Summer-day
 */

@Component
public class JavaMailComponent {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${spring.mail.from}")
	private String from;
	
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	
	public void send(String subject, String to, String content) {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setSubject(subject);
			helper.setTo(to);
			helper.setText(content, true);
			javaMailSender.send(message);
			//日志信息
			logger.info("邮件已经成功发送至邮箱");
		} catch (MessagingException e) {
			logger.error("发送邮件时发生异常！", e);
			throw new ApiException("请检查邮箱是否错误");
		}
	}
}
