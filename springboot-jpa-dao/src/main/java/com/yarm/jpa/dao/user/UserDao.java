package com.yarm.jpa.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/2/25
 * Time:10:46
 * Des:用户表
 */
@Repository
public interface UserDao extends JpaRepository<UserDo, Long> {

}