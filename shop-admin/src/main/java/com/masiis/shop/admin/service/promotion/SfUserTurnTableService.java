package com.masiis.shop.admin.service.promotion;

import com.masiis.shop.admin.service.turn.SfTurnTableRuleService;
import com.masiis.shop.admin.service.user.ComUserService;
import com.masiis.shop.common.enums.promotion.SfTurnTableRuleStatusEnum;
import com.masiis.shop.common.enums.promotion.SfUserTurnTableTimesTypeEnum;
import com.masiis.shop.common.exceptions.BusinessException;
import com.masiis.shop.dao.mall.promotion.SfUserTurnTableMapper;
import com.masiis.shop.dao.po.ComUser;
import com.masiis.shop.dao.po.SfTurnTableRule;
import com.masiis.shop.dao.po.SfUserTurnTable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户转盘信息service
 */
@Service
public class SfUserTurnTableService {

    @Resource
    private SfUserTurnTableMapper userTurnTableMapper;
    @Resource
    private SfTurnTableRuleService turnTableRuleService;
    @Resource
    private SfUserTurnTableService userTurnTableService;
    @Resource
    private ComUserService comUserService;

    public SfUserTurnTable getSfUserTurnTable(Long userId,Integer turnTableId){
        return userTurnTableMapper.getSfUserTurnTable(userId,turnTableId);
    }
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int updateSfUserTurnTable(SfUserTurnTable sfUserTurnTable){
        return userTurnTableMapper.updateByPrimaryKey(sfUserTurnTable);
    }

    public int insert(SfUserTurnTable sfUserTurnTable){
        return userTurnTableMapper.insert(sfUserTurnTable);
    }

    /**
     * 下完单后增加抽奖次数
     * @param comUser
     * @param comUserId
     * @param turnTableRuleType    B端或者C端
     * @param changeTimes          增加的次数
     * @return
     */
    public SfUserTurnTable addTimes(ComUser comUser,Long comUserId,Integer turnTableRuleType,Integer changeTimes){
        if (comUser==null){
            comUser = comUserService.getUserById(comUserId);
        }
        //先判断是有转盘活动
        List<SfTurnTableRule> turnTableRules =  turnTableRuleService.getRuleByTypeAndStatus(turnTableRuleType, SfTurnTableRuleStatusEnum.EFFECT.getCode());
        if (turnTableRules!=null&&turnTableRules.size()>0){
            SfTurnTableRule rule = turnTableRules.get(0);
            return userTurnTableService.reduceTimesOrAddTimes(SfUserTurnTableTimesTypeEnum.ADD_TIMES.getCode(), changeTimes,comUser.getId(),rule.getTurnTableId());
        }
        return null;
    }

    /**
     * 减少或者增加 用户转盘的次数
     * @param type
     * @param changeTimes
     * @param userId
     * @param turnTableId
     */
    public SfUserTurnTable reduceTimesOrAddTimes(Integer type,Integer changeTimes,Long userId,Integer turnTableId){
        //用户转盘增加已抽奖次数，减少未抽奖次数
        int i = 0;
        SfUserTurnTable sfUserTurnTable = null;
        if (type.equals(SfUserTurnTableTimesTypeEnum.REDUCE_TIMES.getCode())){
            sfUserTurnTable   = getSfUserTurnTable(userId,turnTableId);
            if (sfUserTurnTable!=null){
                //未使用的次数
                sfUserTurnTable.setNotUsedTimes(sfUserTurnTable.getNotUsedTimes()-changeTimes);
                //已使用的次数
                sfUserTurnTable.setUsedTimes(sfUserTurnTable.getUsedTimes()+changeTimes);
                sfUserTurnTable.setUpdateTime(new Date());
                sfUserTurnTable.setRemark("抽奖减少次数");
                i = updateSfUserTurnTable(sfUserTurnTable);
            }
        }else if (type.equals(SfUserTurnTableTimesTypeEnum.ADD_TIMES.getCode())){
                SfUserTurnTable userTurnTable = getSfUserTurnTable(userId,turnTableId);
                if (userTurnTable!=null){
                    sfUserTurnTable.setNotUsedTimes(userTurnTable.getNotUsedTimes()+changeTimes);
                    sfUserTurnTable.setUpdateTime(new Date());
                    sfUserTurnTable.setRemark("下单更新抽奖次数");
                    i = updateSfUserTurnTable(sfUserTurnTable);
                }else {
                    sfUserTurnTable = new SfUserTurnTable();
                    sfUserTurnTable.setTurnTableId(turnTableId);
                    sfUserTurnTable.setCreateTime(new Date());
                    sfUserTurnTable.setCreateMan(userId);
                    sfUserTurnTable.setNotUsedTimes(0);
                    //未使用的次数
                    sfUserTurnTable.setNotUsedTimes(sfUserTurnTable.getNotUsedTimes()-changeTimes);
                    sfUserTurnTable.setRemark("下单新增抽奖次数");
                    i = insert(sfUserTurnTable);
                }
            }
        if (i!=1){
            throw new BusinessException("更新或增加用户转盘次数失败");
        }else{
            return sfUserTurnTable;
        }
    }
}
