package com.masiis.shop.service;

import com.masiis.shop.dao.pbusermenu.PbUserMenu;
import com.masiis.shop.dao.pbusermenu.PbUserMenuExample;
import com.masiis.shop.dao.pbusermenu.PbUserMenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cai_tb on 16/2/29.
 */
@Service
public class PbUserMenuService {

    @Resource
    private PbUserMenuMapper pbUserMenuMapper;

    /**
     * 根据条件查询
     * @param pbUserMenuExample
     * @return
     */
    public List<PbUserMenu> findByExample(PbUserMenuExample pbUserMenuExample){
        return pbUserMenuMapper.selectByExample(pbUserMenuExample);
    }

    /**
     * 保存记录
     * @param pbUserMenu
     */
    public void add(PbUserMenu pbUserMenu){
        pbUserMenuMapper.insert(pbUserMenu);
    }

    /**
     * 根据条件更新记录
     * @param pbUserMenu
     */
    public void updateByExample(PbUserMenu pbUserMenu, PbUserMenuExample pbUserMenuExample){
        pbUserMenuMapper.updateByExample(pbUserMenu, pbUserMenuExample);
    }
}