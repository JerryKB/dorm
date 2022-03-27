package com.cop.mapper;

import com.cop.pojo.Dorm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cop.pojo.Student;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface DormMapper extends BaseMapper<Dorm> {
    List<Dorm> getClassDorms(Student student);

}
