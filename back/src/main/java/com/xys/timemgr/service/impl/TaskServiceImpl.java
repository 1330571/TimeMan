package com.xys.timemgr.service.impl;

import com.xys.timemgr.entity.Task;
import com.xys.timemgr.mapper.TaskMapper;
import com.xys.timemgr.service.ITaskService;
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
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

}
