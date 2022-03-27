package com.cop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cop.pojo.AdjustRoom;
import com.cop.pojo.Dorm;
import com.cop.pojo.RespBean;
import com.cop.pojo.Student;
import com.cop.service.IAdjustRoomService;
import com.cop.service.IDormService;
import com.cop.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/adjust-room")
public class AdjustRoomController {
    @Autowired
    IAdjustRoomService iAdjustRoomService;
    @Autowired
    IDormService iDormService;
    @Autowired
    IStudentService iStudentService;
    @PostMapping("/")
    public RespBean submitAdjustRoom(AdjustRoom adjustRoom, HttpServletRequest request){
        System.out.println(adjustRoom);
        String userId = (String) request.getServletContext().getAttribute("userId");
        System.out.println();
        if (!adjustRoom.getSid().equals(userId)) {
            return RespBean.error("输入学号与所登录账号不一致");
        }

        QueryWrapper queryWrapper3 = new QueryWrapper();
        queryWrapper3.eq("id", adjustRoom.getSid());
        Student one = iStudentService.getOne(queryWrapper3);
        if (one.getDid().equals(adjustRoom.getT_id())) {
            return RespBean.error("您已在该宿舍，请勿重复操作");
        }


        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("Sid", userId);

        List list = iAdjustRoomService.list(queryWrapper);
        if (list.size()>0){
            return RespBean.error("您已提交申请，请勿重复提交");
        }
        boolean flag = false;
        List<Dorm> list1 = iDormService.list();
        for (Dorm dorm : list1) {
            if (dorm.getId().equals(adjustRoom.getT_id())){
                flag = true;
                break;

            }
        }
        if (flag==false){
            return RespBean.error("调换宿舍不存在请重新选择");

        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("id",adjustRoom.getT_id());
        Dorm byId = iDormService.getOne(queryWrapper1);
        if (byId.getContain_count()<=byId.getNow_count()){
            return RespBean.error("该宿舍已满请重新选择");

        }
        AdjustRoom adjustRoom1 = new AdjustRoom();

        adjustRoom1.setSid(userId);
        adjustRoom1.setT_id(adjustRoom.getT_id());
        adjustRoom1.setEdit_time(LocalDate.now());
        adjustRoom1.setReason(adjustRoom.getReason());
        if (iAdjustRoomService.save(adjustRoom1)){
            return RespBean.success("提交成功");
        }
        return RespBean.error("提交失败");

    }

    @GetMapping("/")
    public List<AdjustRoom> getOne(HttpServletRequest request) {
        String userId = (String) request.getServletContext().getAttribute("userId");
        QueryWrapper queryWrapper = new QueryWrapper();
        System.out.println(userId);

        queryWrapper.eq("Sid", userId);
        return iAdjustRoomService.list(queryWrapper);
    }
    @GetMapping("/getAll")
    public List<AdjustRoom> getAll() {



        return iAdjustRoomService.list();
    }
    @PostMapping("/update")
    public RespBean update(AdjustRoom adjustRoom){
        if (iAdjustRoomService.updateById(adjustRoom)) {
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteById(@PathVariable Integer id) {
        if (iAdjustRoomService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
