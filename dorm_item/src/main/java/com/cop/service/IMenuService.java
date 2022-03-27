package com.cop.service;

import com.cop.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IMenuService extends IService<Menu> {

    List<Menu> listMenus(String id);


}
