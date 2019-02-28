package com.demo.shiro;

import com.demo.shiro.bean.ResultBean;
import com.demo.shiro.bean.UserBean;
import com.demo.shiro.common.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: ZHANG.HAO
 * @Date: 2019-02-21 15:26
 * @Description:
 */
@RestController
public class WebController {


    private UserService userService;

    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResultBean login(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        UserBean userBean = userService.findByName(username);
        if (userBean.getPassword().equals(password)) {
            return new ResultBean(200, "Login success", JWTUtil.sign(username, password));
        } else {
            throw new UnauthorizedException();
        }
    }

    @GetMapping("/article")
    public ResultBean article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResultBean(200, "You are already logged in", null);
        } else {
            return new ResultBean(200, "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResultBean requireAuth() {
        return new ResultBean(200, "You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResultBean requireRole() {
        return new ResultBean(200, "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
//    @RequiresPermissions(logical = Logical.OR, value = {"view", "edit"})
    @RequiresPermissions("view")
    public ResultBean requirePermission() {
        return new ResultBean(200, "You are visiting permission require edit,view", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultBean unauthorized() {
        return new ResultBean(401, "Unauthorized", null);
    }
}
