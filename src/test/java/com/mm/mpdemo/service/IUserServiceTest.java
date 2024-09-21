//package com.mm.mpdemo.service;
//
//import com.mm.mpdemo.domain.po.User;
//import com.mm.mpdemo.domain.po.UserInfo;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class IUserServiceTest {
//    @Autowired
//    private IUserService userService;
//
//    @Test
//    void testSaveUser(){
//        User user = new User();
//        // user.setId(5L);
//        user.setUsername("LeiLi");
//        user.setPassword("123");
//        user.setPhone("18688990013");
//        user.setBalance(200);
//        user.setInfo(UserInfo.of(24,"英文老师","female"));
//        user.setCreateTime(LocalDateTime.now());
//        user.setUpdateTime(LocalDateTime.now());
//        userService.save(user);
//    }
//
//    @Test
//    void testUpQuery(){
//        List<Long> ids = List.of(1L, 2L, 3L);
//        List<User> users = userService.listByIds(ids);
//        System.out.println(users);
//
//    }
//}