package com.mm.mpdemo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mm.mpdemo.domain.po.User;
import com.mm.mpdemo.domain.po.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        // user.setId(5L);
        user.setUsername("ErGouZi1");
        user.setPassword("123");
        user.setPhone("18688990013");
        user.setBalance(200);
        user.setInfo(UserInfo.of(24,"英文老师","female"));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    void testCustomSqlUpdate(){
        List<Long> ids = List.of(1L, 2L, 4L);
        int amount = 200;
        /*QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .in("id", ids);*/
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>().in("id", ids);
        userMapper.updateBalanceByIds(wrapper,amount);
    }

    @Test
    void testLabmdaQueryWrapper2() {
        //1.构建查询条件
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .select(User::getId, User::getUsername, User::getInfo, User::getBalance)
                .ge(User::getBalance,1000);


        //2.查询
        List<User> users = userMapper.selectList(wrapper);
        System.out.println("users = " + users);
    }


}
