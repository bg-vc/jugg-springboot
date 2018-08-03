package com.jugg.vince.springboot.web.login;

import com.jugg.vince.springboot.common.enums.ResultCode;
import com.jugg.vince.springboot.common.model.Result;
import com.jugg.vince.springboot.common.util.JwtUtil;
import com.jugg.vince.springboot.demo.model.Users;
import com.jugg.vince.springboot.demo.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Author:   Vince
 * Date:     2018/7/30 上午12:14
 * Description:
 */
@Api(tags = {"1、login"})
@RestController
public class LoginController {

    @Resource
    private UsersService usersService;


    @ApiOperation(value="admin", notes="admin")
    @RequestMapping(value="/api/admin", method=RequestMethod.GET)
    @PreAuthorize("hasAuthority('admin')")
    public Result helloToAdmin() {
        Result result = new Result<String>(ResultCode.SUCCESS);
        result.setData("Hello World! You are admin");
        return result;
    }

    @ApiOperation(value="hello", notes="hello")
    @RequestMapping(value="/api/hello", method=RequestMethod.GET)
    public Object hello() {
        Result result = new Result<String>(ResultCode.SUCCESS);
        result.setData("Hello World! You have valid token");
        return result;
    }

    @ApiOperation(value="login", notes="login")
    @RequestMapping(value="/login", method=RequestMethod.POST, produces="application/json")
    public Result<String> login(HttpServletResponse response, @RequestBody Account ac) {
        if(StringUtils.isBlank(ac.getUsername()) || StringUtils.isBlank(ac.getPassword())) {
            Result<String> result = new Result<String>(ResultCode.UNAUTHORIZED).setMessage("account or password null");
            return result;
        }
        if(isValidAccount(ac)) {
            String jwt = JwtUtil.generateToken(ac.getUsername());
            Result<String> result = new Result<>(ResultCode.SUCCESS);
            result.setData(jwt);
            return result;

        }else {
            Result<String> result = new Result<String>(ResultCode.UNAUTHORIZED).setMessage("account or password error");
            return result;
        }
    }

    public static class Account {
        public String username;
        public String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    private boolean isValidAccount(Account ac) {
        Condition condition = new Condition(Users.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("username", ac.getUsername().trim());
        criteria.andEqualTo("password", ac.getPassword().trim());
        int total = usersService.selectCountByCondition(condition);
        if(total > 0) {
            return true;
        } else {
            return false;
        }
    }
}
