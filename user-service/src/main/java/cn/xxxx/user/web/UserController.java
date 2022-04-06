package cn.xxxx.user.web;

import cn.xxxx.user.config.PatternProperties;
import cn.xxxx.user.pojo.User;
import cn.xxxx.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope // 配置热更新
public class UserController {

    @Autowired
    private UserService userService;

//     @Value("${pattern.dateformat}")
//     private String dateformat;

    @Autowired
    private PatternProperties properties;

    @GetMapping("prop")
    public PatternProperties properties(){
        return properties;
    }

    @GetMapping("now")
    public String now(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDateformat()));
    }

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id,
                          @RequestParam(value = "red", required = false) String color,
                          @RequestHeader(value = "X-Request-red", required = false) String red,
                          @RequestHeader(value = "Truth", required = false) String truth) {
        log.info("color={}", color);
        log.info("red={}", red);
        log.info("truth={}", truth);
        return userService.queryById(id);
    }
}
