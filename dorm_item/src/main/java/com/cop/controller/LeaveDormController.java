package com.cop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cop.pojo.LeaveDorm;
import com.cop.pojo.RespBean;
import com.cop.pojo.Student;
import com.cop.service.ILeaveDormService;
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
@RequestMapping("/leave-dorm")
public class LeaveDormController {
    @Autowired
    ILeaveDormService iLeaveDormService;
    @Autowired
    IStudentService iStudentService;
    @PostMapping("/")
    public RespBean submitLeaveRoom(LeaveDorm leaveDorm, HttpServletRequest request) {
        String id = (String) request.getServletContext().getAttribute("userId");
        if (!leaveDorm.getSid().equals(id)){
            return RespBean.error("输入学号与所登录账号不一致");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("Sid", id);
        LeaveDorm dorm = iLeaveDormService.getOne(queryWrapper);

        if (dorm!=null){
                return RespBean.error("您已在提交申请，请勿重复操作");
            }


        Student student = iStudentService.getById(id);

        leaveDorm.setDid(student.getDid());
        leaveDorm.setEdit_time(LocalDate.now());
        if (iLeaveDormService.save(leaveDorm)) {
            return RespBean.success("提交成功");
        }
        return RespBean.error("提交失败");

    }
    @GetMapping("/")
    public List<LeaveDorm> getAll(){
        return iLeaveDormService.list();
    }
    @DeleteMapping("/{id}")
    public RespBean deleteById(@PathVariable Integer id){
        if (iLeaveDormService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }


}
