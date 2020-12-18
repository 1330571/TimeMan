package com.xys.timemgr.service.impl;

import com.xys.timemgr.entity.User;
import com.xys.timemgr.mapper.UserMapper;
import com.xys.timemgr.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Xu Yusheng
 * @since 2020-12-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
