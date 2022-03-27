package com.cop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cop.pojo.RespBean;
import com.cop.pojo.Dorm;
import com.cop.pojo.Student;
import com.cop.service.IDormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dorm")
public class DormController {
    @Autowired
    IDormService iDormService;
    @GetMapping("/")
    public List<Dorm> getAll(){
        return iDormService.list();
    }
    @PostMapping("/")
    public RespBean addDorm(Dorm dorm){
        if (iDormService.save(dorm)){
            return RespBean.success("添加成功");
        }else {
            return RespBean.error("添加失败");
        }
    }
    @PutMapping("/")
    public RespBean updateDorm(@RequestBody Dorm dorm){
        if (iDormService.updateById(dorm)){
            return RespBean.success("修改成功");
        }
        else {
            return RespBean.error("修改失败");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDorm(@PathVariable Integer id){
        if (iDormService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");

    }
    @GetMapping("/getClassDorms")
    public List<Dorm> getClassDorm(){
        Student student = new Student();
        student.setMajor("软件工程");
        student.setSclass("1902");
        return iDormService.getClassDorms(student);

    }
    @GetMapping("/getFreeRoom")
    public List<Dorm> getFreeRooms(){
        List<Dorm> list = iDormService.list();
        List<Dorm> dormList = new ArrayList<>();
        for (Dorm dorm : list) {
            if (dorm.getNow_count()<dorm.getContain_count()&&dorm.getSituation()==1){
                dormList.add(dorm);
            }
        }

        return dormList;
    }
}
