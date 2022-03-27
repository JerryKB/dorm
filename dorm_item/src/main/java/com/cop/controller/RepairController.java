package com.cop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cop.pojo.Dorm;
import com.cop.pojo.Repair;
import com.cop.pojo.RespBean;
import com.cop.pojo.Student;
import com.cop.service.IDormService;
import com.cop.service.IRepairService;
import com.cop.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
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
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    IRepairService iRepairService;
    @Autowired
    IStudentService iStudentService;
    @Autowired
    IDormService iDormService;
    @GetMapping("/")
    public List<Repair> getAllRepair(){
        return iRepairService.list();
    }

    @PostMapping("/")
    public RespBean addRepair(Repair repair){
        if (iRepairService.save(repair)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    public RespBean updateRepair(Repair repair) {
        if (iRepairService.updateById(repair)){
            return RespBean.success("修改成功");
        }
        return RespBean.error("添加失败");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteRepair(@PathVariable Integer id){
        if (iRepairService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
    @PostMapping("/submitRepair")
    public RespBean submitRepair(Repair repair, HttpServletRequest request){
        String userId = (String) request.getServletContext().getAttribute("userId");
        if (!repair.getSid().equals(userId)){
            return RespBean.error("输入学号与所登录账号不一致");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("sid", userId);
        Repair one = iRepairService.getOne(queryWrapper);
        if (one!=null){
            return RespBean.error("您已在提交，请勿重复操作");
        }
        Student student = iStudentService.getById(repair.getSid());
        if (!student.getReal_name().equals(repair.getSname())){
            return RespBean.error("输入学号与姓名不匹配");
        }

        repair.setEdit_time(LocalDate.now());
        if (iRepairService.save(repair)) {
            return RespBean.success("提交成功");
        }
        return RespBean.error("提交失败");

    }




}
