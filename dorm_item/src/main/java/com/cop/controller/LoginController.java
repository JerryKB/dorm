package com.cop.controller;

import com.cop.pojo.*;
import com.cop.service.IAdminService;
import com.cop.service.IRepairManService;
import com.cop.service.IStudentService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private IStudentService iStudentService;
    @Autowired
    private IAdminService iAdminService;
    @Autowired
    private IRepairManService iRepairManService;

    @PostMapping("/login")
    public LoginBean toLogin(User user, HttpServletRequest request){

        if (user.getIdentity().equals("0")){
            List<Student> list = iStudentService.list();
            for (Student student : list) {
                if (student.getUsername().equals(user.getUsername())&&student.getPassword().equals(user.getPassword())&&student.getSituation()==1){
                    request.getServletContext().setAttribute("userId",student.getId());

                    return LoginBean.getLogin(student.getUsername(),student.getReal_name(), student.getIdentity());
                }
            }
        }
        if (user.getIdentity().equals("1")){
            List<RepairMan> list = iRepairManService.list();
            for (RepairMan repairMan : list) {
                if (repairMan.getUsername().equals(user.getUsername())&&repairMan.getPassword().equals(user.getPassword())&&repairMan.getSituation()==1){
                    request.getServletContext().setAttribute("userId",repairMan.getId());
                    return LoginBean.getLogin(repairMan.getUsername(), repairMan.getReal_name(),repairMan.getIdentity());
                }
            }
        }
        if (user.getIdentity().equals("2")){
            List<Admin> list = iAdminService.list();
            for (Admin admin : list) {
                if (admin.getUsername().equals(user.getUsername())&&admin.getPassword().equals(user.getPassword())&&admin.getSituation()==1){
                    request.getServletContext().setAttribute("userId",admin.getId());
                    return LoginBean.getLogin(admin.getUsername(), admin.getReal_name(),admin.getIdentity());
                }
            }
        }
        return null;
    }
}
