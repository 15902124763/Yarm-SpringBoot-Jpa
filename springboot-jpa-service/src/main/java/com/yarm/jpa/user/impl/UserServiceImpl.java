package com.yarm.jpa.user.impl;

import com.yarm.jpa.dao.user.UserDao;
import com.yarm.jpa.dao.user.UserDo;
import com.yarm.jpa.user.UserService;
import com.yarm.jpa.web.util.user.UserPojo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/2/25
 * Time:10:52
 * Des:用户接口实现
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public int insert(UserPojo userPojo) {
        if(userPojo == null){
            return 0;
        }

        UserDo userDo = this.converUserPojoToDo(userPojo);
        if(userDo == null){
            return 0;
        }
        UserDo save = this.userDao.save(userDo);
        if(save == null){
            return 0;
        }

        return 1;
    }

    private UserDo converUserPojoToDo(UserPojo u) {
        if(u == null){
            return null;
        }
        UserDo userDo = new UserDo();
        userDo.setId(u.getId());
        userDo.setAccount(u.getAccount());
        userDo.setMobile(u.getMobile());
        userDo.setName(u.getName());
        userDo.setPwd(u.getPwd());
        return userDo;
    }

    @Override
    public int deleteById(Long id) {
        this.userDao.deleteById(id);
        return 1;
    }

    @Override
    public int updateById(Long id, UserPojo userPojo) {
        if(id == null || userPojo == null){
            return 0;
        }
        int result = this.updateBySave(id, userPojo);

        return result;
    }

    private int updateBySave(Long id, UserPojo userPojo) {
        UserDo userDo = new UserDo();
        Optional<UserDo> byId = this.userDao.findById(id);
        userDo = byId.get();
        this.converUserUpdateData(userPojo, userDo);
        if(userDo == null){
            return 0;
        }
        UserDo save = this.userDao.save(userDo);
        if(save == null){
            return 0;
        }
        return 1;
    }

    private UserDo converUserUpdateData(UserPojo userPojo, UserDo userDo) {
        if(userDo == null){
            return null;
        }
        if (StringUtils.isNotBlank(userPojo.getAccount())){
            userDo.setAccount(userPojo.getAccount());
        }
        if(StringUtils.isNotBlank(userPojo.getMobile())){
            userDo.setMobile(userPojo.getMobile());
        }
        if(StringUtils.isNotBlank(userPojo.getName())){
            userDo.setName(userPojo.getName());
        }
        if(StringUtils.isNotBlank(userPojo.getPwd())){
            userDo.setPwd(userPojo.getPwd());
        }
        userDo.setUpdateDate(new Date());
        return userDo;
    }

    @Override
    public List<UserPojo> selectAll() {
        List<UserDo> all = userDao.findAll();
        if(all == null || all.isEmpty()){
            return null;
        }
        List<UserPojo> list = new ArrayList<>();
        for (UserDo userDo : all){
            UserPojo pojo = this.converUserDoToPojo(userDo);
            if(pojo == null){
                continue;
            }
            list.add(pojo);
        }

        return list;
    }

    private UserPojo converUserDoToPojo(UserDo u) {
        if(u == null){
            return null;
        }
        UserPojo userPojo = new UserPojo();
        userPojo.setAccount(u.getAccount());
        userPojo.setCreateDate(u.getCreateDate());
        userPojo.setCreateUser(u.getCreateUser());
        userPojo.setId(u.getId());
        userPojo.setMobile(u.getMobile());
        userPojo.setName(u.getName());
        userPojo.setPwd(u.getPwd());
        userPojo.setUpdateDate(u.getUpdateDate());
        userPojo.setUpdateUser(u.getUpdateUser());

        return userPojo;
    }
}