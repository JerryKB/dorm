package com.cop.service.impl;

import com.cop.pojo.Admin;
import com.cop.mapper.AdminMapper;
import com.cop.pojo.Menu;
import com.cop.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiayu.Yang
 * @since 2021-12-15
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
