package com.cop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cop.pojo.*;
import com.cop.service.ICheckinRoomService;
import com.cop.service.IDormService;
import com.cop.service.IStudentService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/checkinRoom")
public class CheckinRoomController {
    @Autowired
    ICheckinRoomService iCheckinRoomService;
    @Autowired
    IStudentService iStudentService;
    @Autowired
    IDormService iDormService;

    @PostMapping("/")
    public RespBean addCheckinRoom( Dorm dorm,HttpServletRequest request) {

        System.out.println(dorm.getId());
        String sid = (String) request.getServletContext().getAttribute("userId");
        CheckinRoom checkinRoom = new CheckinRoom();
        checkinRoom.setSid(sid);
        checkinRoom.setTime(LocalDate.now());
        checkinRoom.setDid(dorm.getId());


        Dorm dorm1 = iDormService.getById(checkinRoom.getDid());
        Student student = iStudentService.getById(sid);

        if ((dorm1.getContain_count()-dorm1.getNow_count())<=0){
            return RespBean.error("该宿舍已满，请重新选择");
        }

        if (student.getDid()!=null&&!student.getDid().equals("")){
            return RespBean.error("您已分配宿舍，请勿再次操作");
        }
        if (iCheckinRoomService.save(checkinRoom)) {

            dorm1.setNow_count(dorm1.getNow_count()+1);
            iDormService.updateById(dorm1);
            student.setDid(checkinRoom.getDid());
            student.setCheckin_time(LocalDate.now());
            student.setSituation(1);


            iStudentService.updateById(student);

            return RespBean.success("入住成功");
        }
        return RespBean.error("入住失败");
    }

}
