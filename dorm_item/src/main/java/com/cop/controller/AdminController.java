package com.cop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cop.pojo.*;
import com.cop.service.*;

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
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService iAdminService;
    @Autowired
    IAdjustRoomService iAdjustRoomService;
    @Autowired
    IStudentService iStudentService;
    @Autowired
    IDormService iDormService;
    @Autowired
    ILeaveDormService iLeaveDormService;
    @Autowired
    IUserRoleService iUserRoleService;



    @GetMapping("/")
    public List<Admin> getAll()
    {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_name", "普通管理员");
        return iAdminService.list(queryWrapper);
    }
    @PostMapping("/")
    public RespBean addAdmin(Admin admin){
        if (admin.getRole_name().equals("")){
            System.out.println(1);
            admin.setRole_name("普通管理员");
        }
        UserRole userRole = new UserRole();

        userRole.setSid(admin.getId());
        userRole.setRid(4);
        iUserRoleService.save(userRole);
        System.out.println(admin);
        if (iAdminService.save(admin)){
            return RespBean.success("添加成功");
        }else {
            return RespBean.error("添加失败");
        }
    }
    @PutMapping("/")
    public RespBean updateAdmin(Admin admin){
        if (iAdminService.updateById(admin)){

            return RespBean.success("修改成功");
        }
        else {
            return RespBean.error("修改失败");
        }
    }
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id){
        if (iAdminService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
    @PostMapping("/permitAdjust")
    public RespBean permitAdjust(Integer adjustId, Integer state,HttpServletRequest request){
        AdjustRoom adjustRoom = iAdjustRoomService.getById(adjustId);
        String userId = (String) request.getServletContext().getAttribute("userId");
        adjustRoom.setPermit_id(userId);
        adjustRoom.setPermit_time(LocalDate.now());
        adjustRoom.setPermit_state(state);
        if (state==1&&iAdjustRoomService.updateById(adjustRoom)){
            Student student = iStudentService.getById(adjustRoom.getSid());
            Dorm dorm1 = iDormService.getById(student.getDid());
            dorm1.setNow_count(dorm1.getNow_count() - 1);
            iDormService.updateById(dorm1);
            student.setDid(adjustRoom.getT_id());
            student.setCheckin_time(LocalDate.now());
            iStudentService.updateById(student);
            Dorm dorm = iDormService.getById(adjustRoom.getT_id());
            dorm.setNow_count(dorm.getNow_count() + 1);
            iDormService.updateById(dorm);
            return RespBean.success("提交成功");


        }else if(state==2&&iAdjustRoomService.updateById(adjustRoom)){

           return RespBean.error("提交成功");
        }



        return RespBean.error("提交失败");
    }

    @PostMapping("/permitLeave")
    public RespBean permitLeave(Integer leaveId, Integer state, HttpServletRequest request) {
        String id = (String) request.getServletContext().getAttribute("userId");
        LeaveDorm leaveDorm = iLeaveDormService.getById(leaveId);
        leaveDorm.setPermit_id(id);
        leaveDorm.setPermit_time(LocalDate.now());
        leaveDorm.setPermit_state(state);
        if (state==1&&iLeaveDormService.updateById(leaveDorm)){
            Student student = iStudentService.getById(leaveDorm.getSid());
            Dorm dorm = iDormService.getById(student.getDid());
            dorm.setNow_count(dorm.getNow_count() - 1);
            iDormService.updateById(dorm);
            student.setDid("");
            iStudentService.updateById(student);
            return RespBean.success("提交成功");
        }
        else if(state==2&&iLeaveDormService.updateById(leaveDorm)){

            return RespBean.error("提交成功");
        }
        return RespBean.error("提交失败");
    }



}
