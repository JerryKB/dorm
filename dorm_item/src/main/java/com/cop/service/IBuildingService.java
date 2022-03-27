package com.cop.service;

import com.cop.pojo.Building;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jiayu.Yang
 * @since 2021-12-15
 */
public interface IBuildingService extends IService<Building> {
    Map<String, Object> page(int page,int size);

}
