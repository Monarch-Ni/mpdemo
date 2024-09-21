package com.mm.mpdemo.service;

import com.mm.mpdemo.domain.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mm.mpdemo.domain.vo.UserVO;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 
 * @since 2024-09-12
 */
public interface IUserService extends IService<User> {

    void deductMoneyById(Long id, Integer money);

    UserVO queryUserByIdAndAddress(Long id);

    List<UserVO> queryUserAndAddressByIds(List<Long> ids);

}
