package com.masiis.shop.web.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masiis.shop.dao.pbmenu.PbMenu;
import com.masiis.shop.dao.pbuser.PbUser;
import com.masiis.shop.service.PbMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 首页
 * Created by cai_tb on 16/2/22.
 */
@Controller
@RequestMapping("/main")
public class MainController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Resource
    private PbMenuService pbMenuService;

    @RequestMapping("/index.shtml")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mav = new ModelAndView("index");

        HttpSession session = request.getSession();
        PbUser pbUser = (PbUser)session.getAttribute("pbUser");
        List<PbMenu> pbMenus = pbMenuService.findByPbUserId(pbUser.getId());

        String pbMenusJson = objectMapper.writeValueAsString(pbMenus);
        mav.addObject("pbMenus", pbMenusJson);

        return mav;
    }


}
