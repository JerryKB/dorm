package com.cop.service;

import com.cop.pojo.Dorm;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cop.pojo.Student;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jiayu.Yang
 * @since 2021-12-15
 */
public interface IDormService extends IService<Dorm> {

    List<Dorm> getClassDorms(Student student);

}
