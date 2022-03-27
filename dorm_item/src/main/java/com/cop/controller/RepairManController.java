package com.cop.controller;


import com.cop.pojo.Repair;
import com.cop.pojo.RepairMan;
import com.cop.pojo.RespBean;
import com.cop.pojo.UserRole;
import com.cop.service.IRepairManService;
import com.cop.service.IRepairService;
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
@RequestMapping("/repairMan")
public class RepairManController {
    @Autowired
    IRepairManService iRepairManService;
    @Autowired
    IRepairService iRepairService;
    @Autowired
    IUserRoleService iUserRoleService;

    @GetMapping("/")
    public List<RepairMan> getAll(){
        return iRepairManService.list();
    }
    @PostMapping("/")
    public RespBean addRepairMan(RepairMan repairMan){
        UserRole userRole = new UserRole();
        userRole.setSid(repairMan.getId());
        userRole.setRid(6);
        iUserRoleService.save(userRole);

        if (iRepairManService.save(repairMan)){
            return RespBean.success("添加成功");
        }else {
            return RespBean.error("添加失败");
        }
    }
    @PutMapping("/")
    public RespBean updateRepairMan(@RequestBody RepairMan repairMan){
        System.out.println(repairMan);
        if (iRepairManService.updateById(repairMan)){
            return RespBean.success("修改成功");
        }
        else {
            return RespBean.error("修改失败");
        }
    }
    @DeleteMapping("/{id}")
    public RespBean deleteRepairMan(@PathVariable Integer id){
        if (iRepairManService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");


    }

    @PostMapping("/permitRepair")
    public RespBean permitRepair(Integer repairId, Integer state, HttpServletRequest request) {
        String userId = (String) request.getServletContext().getAttribute("userId");
        Repair repair = iRepairService.getById(repairId);
        repair.setSituation(state);
        repair.setRepairman_id(userId);
        if (iRepairService.updateById(repair)) {
            return RespBean.success("提交成功");
        }
        return RespBean.error("提交失败");
    }

}
