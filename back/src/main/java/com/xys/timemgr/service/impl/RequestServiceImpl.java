package com.xys.timemgr.service.impl;

import com.xys.timemgr.entity.Request;
import com.xys.timemgr.mapper.RequestMapper;
import com.xys.timemgr.service.IRequestService;
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
public class RequestServiceImpl extends ServiceImpl<RequestMapper, Request> implements IRequestService {

}
