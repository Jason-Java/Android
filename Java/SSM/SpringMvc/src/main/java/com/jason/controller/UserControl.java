package com.jason.controller;

import com.alibaba.fastjson.JSONObject;
import com.jason.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControl {

    public UserControl() {
        System.out.println("init user");
    }

    @RequestMapping(method = RequestMethod.POST)
    public User save(@RequestBody User user) {


        return user;
    }



    @GetMapping("/{id}/{name}")
    public String pojoParam(@PathVariable String id,@PathVariable String name) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("age", 12);
        jsonObject.put("sex", "男");
        jsonObject.put("height", 1.78);
        jsonObject.put("id", id);
        return jsonObject.toJSONString();
    }

    @RequestMapping(method = RequestMethod.DELETE)

    public String jsonParam() {
        return "users delete ok";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String jsonParamList() {

        return "users put ok";
    }

    @RequestMapping("/date")
    public String date(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        System.out.println(date);
        return "{\"msg\":12}";
    }


}
