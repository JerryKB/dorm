package com.cop.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cop.pojo.Building;
import com.cop.pojo.RespBean;
import com.cop.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jiayu.Yang
 * @since 2021-12-15
 */
@RestController
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    IBuildingService iBuildingService;

    @GetMapping("/")
    public List<Building> getAll(){
        return iBuildingService.list();
    }
    @PostMapping("/")
    public RespBean addBuilding(Building building){
        if (iBuildingService.save(building)){
            return RespBean.success("添加成功");
        }else {
            return RespBean.error("添加失败");
        }
    }
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Building building){
        System.out.println(building);
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("bid", building.getBid());
        if (iBuildingService.update(building,updateWrapper)){
            return RespBean.success("修改成功");
        }
        else {
            return RespBean.error("修改失败");
        }
    }
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id){
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("bid", id);
        if (iBuildingService.remove(updateWrapper)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");


    }

}
