package com.smday.fireworks.testBeanUtils;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.smday.fireworks.common.utils.bean.BeanUtils;
import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * @author Summerday
 */
public class Test1 {

    @Test
    public void mai() {

     User user = new User();
     user.setAge(10);
     user.setPassword("xxx");
     user.setUsername("yyy");
     user.setEmail("zzzz");
     UserDto ut = new UserDto();
        BeanUtils.copyBeanProp(ut,user);
        System.out.println(ut);



    }


}

@Data
class User{
    String username;
    String password;
    Integer age;
    String email;
}

@Data
class UserDto{
    String username;
    String password;
    Integer age;

}