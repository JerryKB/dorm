package com.cop.controller;


import com.cop.pojo.Menu;
import com.cop.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    IMenuService iMenuService;

    @GetMapping("/")
    public List<Menu> listMenus(HttpServletRequest request){
        String userId = (String) request.getServletContext().getAttribute("userId");
        System.out.println(userId);
        return iMenuService.listMenus(userId);
    }

}
