package com.cop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cop.pojo.Student;
import com.cop.pojo.RespBean;
import com.cop.pojo.User;
import com.cop.pojo.UserRole;
import com.cop.service.IStudentService;
import com.cop.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/student")
public class StudentController {
    @Autowired
    IStudentService iStudentService;
    @Autowired
    IUserRoleService iUserRoleService;
    @GetMapping("/")
    public List<Student> getAll(){
        return iStudentService.list();
    }
    @PostMapping("/")
    public RespBean addStudent(Student student){
        UserRole userRole = new UserRole();
        userRole.setSid(student.getId());
        if (student.getRole_name().equals("学生")) {
            userRole.setRid(1);

            iUserRoleService.save(userRole);
        }
        if (iStudentService.save(student)){
            return RespBean.success("添加成功");
        }else {
            return RespBean.error("添加失败");
        }
    }
    @PutMapping("/")
    public RespBean updateStudent(@RequestBody Student student){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("sid", student.getId());
        UserRole one = iUserRoleService.getOne(queryWrapper);
        UserRole userRole = new UserRole();
        userRole.setSid(student.getId());
        System.out.println(student);
        if (one==null){
            if (student.getRole_name().equals("学生")){
                userRole.setRid(1);
            }else {
                userRole.setRid(3);
            }
            iUserRoleService.save(userRole);

        }
        if (one!=null){
            if (student.getRole_name().equals("学生")){
                userRole.setRid(1);
            }else {
                userRole.setRid(3);
            }
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("sid", student.getId());
            iUserRoleService.update(userRole, queryWrapper);

        }

        if (iStudentService.updateById(student)){
            return RespBean.success("修改成功");
        }
        else {
            return RespBean.error("修改失败");
        }
    }
    @DeleteMapping("/{id}")
    public RespBean deleteStudent(@PathVariable Integer id){
        if (iStudentService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");


    }

    @GetMapping("/getDorms")
    public List<Student> getDorm(HttpServletRequest request) {
        String userId = (String) request.getServletContext().getAttribute("userId");
        Student student = iStudentService.getById(userId);
        System.out.println(student);
        return iStudentService.getDorms(student);
    }



}
