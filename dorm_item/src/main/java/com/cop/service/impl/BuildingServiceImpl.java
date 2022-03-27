package com.cop.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cop.pojo.Building;
import com.cop.mapper.BuildingMapper;
import com.cop.service.IBuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jiayu.Yang
 * @since 2021-12-15
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements IBuildingService {
    @Autowired
    private BuildingMapper buildingMapper;
    @Override
    public Map<String, Object> page(int page, int size) {
        Page pageInfo = new Page(page, size);
        Page<Building> iPage = buildingMapper.selectPage(pageInfo, null);
        Map<String, Object> pageMap = new HashMap<>(3);
        pageMap.put("total_record", iPage.getTotal());
        pageMap.put("total_page", iPage.getPages());
        pageMap.put("current_data", iPage.getRecords());
        return pageMap;
    }

}
