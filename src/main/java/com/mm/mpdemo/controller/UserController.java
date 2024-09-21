package com.mm.mpdemo.controller;


import cn.hutool.core.bean.BeanUtil;
import com.mm.mpdemo.domain.dto.UserFormDTO;
import com.mm.mpdemo.domain.po.User;
import com.mm.mpdemo.domain.vo.UserVO;
import com.mm.mpdemo.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author
 * @since 2024-09-12
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation("新增用户接口")
    @PostMapping
    public void saveUser(@RequestBody UserFormDTO userDTO) {
        User user = BeanUtil.copyProperties(userDTO, User.class);
        userService.save(user);
    }

/*

    @ApiOperation("删除用户接口")
    @DeleteMapping("{id}")
    public void deleteUserById(@ApiParam("用户id") @PathVariable("id") Long id){
        userService.removeById(id);
    }
*/

  /*  @ApiOperation("根据id查询用户接口")
    @GetMapping("{id}")
    public UserVO queryUserById(@ApiParam("用户id") @PathVariable("id") Long id){
        User user = userService.getById(id);
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        return userVO;
    }*/

    @ApiOperation("根据id批量查询用户及其地址接口")
    @GetMapping
    public List<UserVO> queryUserByIds(@ApiParam("用户id集合") @RequestParam("ids") List<Long> ids){
        return userService.queryUserAndAddressByIds(ids);
    }


    @ApiOperation("根据id查询用户及其地址接口")
    @GetMapping("{id}")
    public UserVO queryUserByIdAndAddress(@ApiParam("用户id") @PathVariable("id") Long id){
        UserVO userVO = userService.queryUserByIdAndAddress(id);
        System.out.println("a");
        System.out.println("b");
        System.out.println("c");
        System.out.println("hot-fix");
        return userVO;
    }

    @ApiOperation("扣减用户余额接口")
    @DeleteMapping("{id}/deduction/{money}")
    public void deductMoneyById(
            @ApiParam("用户id") @PathVariable("id") Long id,
            @ApiParam("扣减金额") @PathVariable("money") Integer money){

        userService.deductMoneyById(id,money);
    }



}
