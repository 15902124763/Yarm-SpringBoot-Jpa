package com.yarm.jpa.controller;

import com.yarm.jpa.dao.user.UserDo;
import com.yarm.jpa.user.UserService;
import com.yarm.jpa.web.util.AjaxResult;
import com.yarm.jpa.web.util.user.UserPojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/2/25
 * Time:10:26
 * Des:公用接口
 */
@RestController
@RequestMapping("api/user")
public class ApiJpaController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "query/all",method = RequestMethod.GET)
    public AjaxResult<String> get(){

        AjaxResult ar = new AjaxResult();
        List<UserPojo> users = userService.selectAll();
        ar.setResult(users);
        return ar;
    }

    @RequestMapping(value = "commit", method = RequestMethod.POST)
    public AjaxResult<Integer> insert(@RequestBody UserPojo userPojo){
        AjaxResult ajaxResult = new AjaxResult();
        int result = this.userService.insert(userPojo);
        ajaxResult.setResult(result);
        return ajaxResult;
    }

    @RequestMapping(value = "del/{id}",method = RequestMethod.POST)
    public AjaxResult<Integer> del(@PathVariable("id") Long id){
        AjaxResult ajaxResult = new AjaxResult();
        int result = this.userService.deleteById(id);
        ajaxResult.setResult(result);
        return ajaxResult;
    }

    @RequestMapping(value = "update/{id}",method = RequestMethod.POST)
    public AjaxResult<Integer> update(@PathVariable("id") Long id, @RequestBody UserPojo userPojo){
        AjaxResult ajaxResult = new AjaxResult();
        int result = this.userService.updateById(id, userPojo);
        ajaxResult.setResult(result);
        return ajaxResult;
    }
}