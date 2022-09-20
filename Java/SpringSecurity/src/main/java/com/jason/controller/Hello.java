package com.jason.controller;

import com.jason.domain.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hellos")
public class Hello {

    @GetMapping
//    @PreAuthorize("hasAuthority('system:test:list')")
    @PreAuthorize("@PermissionValidation.hasAuthority('system:test:list')")  //自定义权限认证
    public Result<String> hello() {
        Result<String> result = new Result(200, "请求成功", "啦啦啦啦");
        return result;
    }

}
