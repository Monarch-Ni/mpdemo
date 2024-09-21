package com.mm.mpdemo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mm.mpdemo.domain.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2024-09-12
 */
public interface UserMapper extends BaseMapper<User> {

    @Update("update user set balance = balance - #{money} where id = #{id}" )
    void deductMoneyById(@Param("id") Long id, @Param("money") Integer money);

    void updateBalanceByIds(@Param(Constants.WRAPPER) UpdateWrapper<User> wrapper, @Param("amount") int amount);

}
