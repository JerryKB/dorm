package com.cop.service.impl;

import com.cop.pojo.Role;
import com.cop.mapper.RoleMapper;
import com.cop.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiayu.Yang
 * @since 2021-12-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
