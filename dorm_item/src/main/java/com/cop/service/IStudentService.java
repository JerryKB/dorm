package com.cop.service;

import com.cop.pojo.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jiayu.Yang
 * @since 2021-12-15
 */
public interface IStudentService extends IService<Student> {

    List<Student> getDorms(Student student);
}
