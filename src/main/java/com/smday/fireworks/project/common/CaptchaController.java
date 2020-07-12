package com.smday.fireworks.project.common;

import com.smday.fireworks.common.api.AjaxResult;
import com.smday.fireworks.common.constants.Constants;
import com.smday.fireworks.common.utils.Base64;
import com.smday.fireworks.common.utils.IdUtils;
import com.smday.fireworks.common.utils.VerifyCodeUtils;
import com.smday.fireworks.nosql.redis.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码处理类
 *
 * @author Summerday
 */

@Api(tags = "CaptchaController", description = "验证码")
@RestController
public class CaptchaController {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaController.class);

    @Autowired
    private RedisCache redisCache;

    /**
     * 生成验证码
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/verifyCode")
    @ApiOperation("获取验证码")
    public AjaxResult getCode() throws IOException {

        //生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //生成唯一标识
        String uuid = IdUtils.fastSimpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        //captcha_codes:uuid --> 4531  //设置超时时间两分钟
        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        //生成图片
        int width = 111, height = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(width, height, stream, code);

        try {
            AjaxResult ajax = AjaxResult.success();
            ajax.put("uuid", uuid);
            ajax.put("img", Base64.encode(stream.toByteArray()));
            logger.info("uuid--> {}",uuid);
            logger.info("code--> {}",code );
            return ajax;
        } catch (Exception e) {
            logger.warn("验证码模块异常:{}", e.getMessage());
            return AjaxResult.error(e.getMessage());
        } finally {
            stream.close();
        }

    }


}
