package com.cop.mapper;

import com.cop.pojo.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentMapper extends BaseMapper<Student> {

    List<Student> getDorms(Student student);
}
