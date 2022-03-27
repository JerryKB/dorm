package com.cop.service.impl;

import com.cop.pojo.Student;
import com.cop.mapper.StudentMapper;
import com.cop.service.IStudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    StudentMapper studentMapper;
    @Override
    public List<Student> getDorms(Student student) {
        return studentMapper.getDorms(student);
    }
}
