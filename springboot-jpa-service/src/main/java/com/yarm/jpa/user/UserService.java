package com.yarm.jpa.user;

import com.yarm.jpa.dao.user.UserDo;
import com.yarm.jpa.web.util.user.UserPojo;

import java.util.List;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/2/25
 * Time:10:51
 * Des:用户服务接口
 */
public interface UserService {
    List<UserPojo> selectAll();

    int insert(UserPojo userPojo);

    int deleteById(Long id);

    int updateById(Long id, UserPojo userPojo);
}