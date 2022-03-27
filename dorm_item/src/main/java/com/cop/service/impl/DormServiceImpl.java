package com.cop.service.impl;

import com.cop.pojo.Dorm;
import com.cop.mapper.DormMapper;
import com.cop.pojo.Student;
import com.cop.service.IDormService;
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
public class DormServiceImpl extends ServiceImpl<DormMapper, Dorm> implements IDormService {

    @Autowired
    DormMapper dormMapper;
    @Override
    public List<Dorm> getClassDorms(Student student) {
        return dormMapper.getClassDorms(student);
    }
}
