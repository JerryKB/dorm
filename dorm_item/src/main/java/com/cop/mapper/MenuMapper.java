package com.cop.mapper;

import com.cop.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> listMenus(@Param("id") String id);

}
