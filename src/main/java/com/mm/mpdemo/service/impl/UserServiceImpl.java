package com.mm.mpdemo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.mm.mpdemo.domain.po.Address;
import com.mm.mpdemo.domain.po.User;
import com.mm.mpdemo.domain.vo.AddressVO;
import com.mm.mpdemo.domain.vo.UserVO;
import com.mm.mpdemo.enums.UserStatus;
import com.mm.mpdemo.mapper.UserMapper;
import com.mm.mpdemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 
 * @since 2024-09-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void deductMoneyById(Long id, Integer money) {
        User user = getById(id);
        if(user == null || user.getStatus()==UserStatus.FROZEN) {
            throw new RuntimeException("用户状态异常");
        }
        if (user.getBalance()<=0){
            throw new RuntimeException("余额不足");
        }
        baseMapper.deductMoneyById(id,money);
    }

    @Override
    public UserVO queryUserByIdAndAddress(Long id) {

        User user = getById(id);
        if(user == null || user.getStatus()== UserStatus.FROZEN) {
            throw new RuntimeException("用户异常");
        }
        List<Address> addressList = Db.lambdaQuery(Address.class)
                .eq(Address::getUserId, id)
                .list();

        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);

        if(CollUtil.isNotEmpty(addressList)){
            List<AddressVO> addressVOS = BeanUtil.copyToList(addressList, AddressVO.class);
            userVO.setAddresses(addressVOS);
        }
        return userVO;
    }

    @Override
    public List<UserVO> queryUserAndAddressByIds(List<Long> ids) {

        List<User> users = listByIds(ids);
        List<UserVO> userVOS = BeanUtil.copyToList(users, UserVO.class);
        List<Address> addresses = Db.lambdaQuery(Address.class).in(Address::getUserId, ids).list();
        List<AddressVO> addressVOS = BeanUtil.copyToList(addresses, AddressVO.class);
        Map<Long, List<AddressVO>> addressList = addressVOS.stream().collect(Collectors.groupingBy(AddressVO::getUserId));
        for (UserVO userVO : userVOS) {
            userVO.setAddresses(addressList.get(userVO.getId()));
        }
        return userVOS;
    }

}
