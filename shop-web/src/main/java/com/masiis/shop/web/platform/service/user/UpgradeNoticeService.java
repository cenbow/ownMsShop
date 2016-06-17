package com.masiis.shop.web.platform.service.user;

import com.masiis.shop.common.enums.upgrade.UpGradeStatus;
import com.masiis.shop.common.enums.upgrade.UpGradeUpStatus;
import com.masiis.shop.common.exceptions.BusinessException;
import com.masiis.shop.common.util.OrderMakeUtils;
import com.masiis.shop.dao.beans.order.BOrderUpgradeDetail;
import com.masiis.shop.dao.beans.user.upgrade.UpGradeInfoPo;
import com.masiis.shop.dao.platform.user.PfUserRebateMapper;
import com.masiis.shop.dao.platform.user.PfUserUpgradeNoticeMapper;
import com.masiis.shop.dao.po.*;
import com.masiis.shop.web.platform.service.product.SkuAgentService;
import com.masiis.shop.web.platform.service.product.SkuService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by JingHao on 2016/6/15
 * 升级管理service
 */
@Service
@Transactional
public class UpgradeNoticeService {
    private static final Logger logger = Logger.getLogger(UpgradeNoticeService.class);
    @Resource
    private PfUserUpgradeNoticeMapper pfUserUpgradeNoticeMapper;

    @Resource
    private PfUserRebateMapper pfUserRebateMapper;

    @Resource
    private SkuService comSkuService;
    @Resource
    private UserService comUserService;
    @Resource
    private ComAgentLevelService comAgentLevelService;
    @Resource
    private SkuAgentService skuAgentService;
    /**
     * 根据订单id查询通知单
     * @param orderId
     * @return
     */
    public PfUserUpgradeNotice selectByPfBorderId(Long orderId){
        return pfUserUpgradeNoticeMapper.selectByPfBorderId(orderId);
    }

	/**
     * 通过id查询申请单数据
     * @param id            申请单id
     * @return
     * @throws Exception
     */
    public PfUserUpgradeNotice getUpgradeNoticeById(Long id) throws Exception{
        return pfUserUpgradeNoticeMapper.selectByPrimaryKey(id);
    }
    /**
     * jjh
     * 我的升级申请记录
     * @param userId
     * @return
     */
    public List<PfUserUpgradeNotice> getPfUserUpGradeInfoByUserId(Long userId) throws Exception{
        return pfUserUpgradeNoticeMapper.selectByUserId(userId);
    }

    /**
     * jjh
     * 我的下级申请记录
     * @param userId
     * @return
     */
    public List<PfUserUpgradeNotice> getPfUserUpGradeInfoByUserPId(Long userId) throws Exception {
        return pfUserUpgradeNoticeMapper.selectByUserPId(userId);
    }

    /**
     * jjh
     * 一次性返利<获得的返利>
     * @param userId
     * @return
     */
    public List<PfUserRebate> getPfUserRebateByUserId(Long userId) throws Exception{
        return pfUserRebateMapper.selectByUserId(userId);
    }

    /**
     * jjh
     * 一次性返利<支付的返利>
     * @param userId
     * @return
     */
    public List<PfUserRebate> getPfUserRebateByUserPId(Long userId) throws Exception{
        return pfUserRebateMapper.selectByUserPId(userId);
    }

    /**
     * jjh
     * 根据主键获取申请信息
     * @param id
     * @return
     */
    public PfUserUpgradeNotice getPfUserUpGradeInfoByPrimaryKey(Long id) throws Exception{
        return pfUserUpgradeNoticeMapper.selectByPrimaryKey(id);
    }

    /**
     * jjh
     * 组合查询
     * @param UserPId 我作为上级
     * @param skuId 商品
     * @param upStatus 申请状态
     * @return
     * @throws Exception
     */
    public List<PfUserUpgradeNotice> getPfUserUpGradeInfoByParam(Long UserPId, Integer skuId, Integer upStatus) throws Exception {
        return pfUserUpgradeNoticeMapper.selectByParam(UserPId, skuId, upStatus);
    }

    /**
     * jjh
     * 组合查询
     * @param skuId 商品
     * @param userPid 支付返利
     * @param userId 获取返利
     * @return
     */
    public List<PfUserUpgradeNotice> getPfUserUpGradeInfoByRebateAndSkuId(Integer skuId, Long userPid, Long userId) {
        return pfUserUpgradeNoticeMapper.selectBySkuIdAndRebateType(skuId, userPid, userId);
    }

    public String coverCodeByMyUpgrade(Integer upStatus) {
        return UpGradeStatus.statusPickList.get(upStatus);
    }

    public String coverCodeByLowerUpgrade(Integer upStatus) {
        return UpGradeUpStatus.upStatusPickList.get(upStatus);
    }
	/**
     * 处理代理用户升级
     * @param userId        代理用户id
     * @param userPid       代理用户上级id
     * @param curAgentLevel 代理用户当前代理等级
     * @param upgradeLevel  代理用户申请代理等级
     * @param pAgentLevel   代理用户上级代理等级
     * @param skuId         合伙skuId
     * @return              主键
     * @auth:wbj
     * @throws Exception
     */
    public Long dealAgentUpGrade(Long userId, Long userPid, Integer curAgentLevel, Integer upgradeLevel, Integer pAgentLevel, Integer skuId) throws Exception{
        logger.info("查询是否已经有申请单");
        List<PfUserUpgradeNotice> upgradeNotices = pfUserUpgradeNoticeMapper.selectBySkuIdAndUserIdAndUserPid(skuId, userPid, userId);
        if (upgradeNotices != null & upgradeNotices.size()>0){
            for (PfUserUpgradeNotice upGrade : upgradeNotices){
                if (upGrade.getStatus().intValue() == UpGradeStatus.STATUS_Untreated.getCode().intValue() ||
                    upGrade.getStatus().intValue() == UpGradeStatus.STATUS_NoPayment.getCode().intValue() ||
                    upGrade.getStatus().intValue() == UpGradeStatus.STATUS_Processing.getCode().intValue())
                {
                    throw new BusinessException("当前还有未处理完成的升级申请单");
                }
            }
        }
        PfUserUpgradeNotice upgradeNotice = new PfUserUpgradeNotice();
        logger.info("生成申请单号begin");
        String code = OrderMakeUtils.makeOrder("U");
        upgradeNotice.setCode(code);
        upgradeNotice.setCreateTime(new Date());
        upgradeNotice.setUserId(userId); //申请人id
        upgradeNotice.setUserPid(userPid);  //申请人上级合伙人id
        upgradeNotice.setSkuId(skuId);      //申请升级skuid
        upgradeNotice.setOrgAgentLevelId(curAgentLevel);    //原合伙代理等级
        upgradeNotice.setWishAgentLevelId(upgradeLevel);    //申请合伙代理等级
        logger.info("生成申请单号end");
        upgradeNotice.setStatus(UpGradeStatus.STATUS_Untreated.getCode());
        if (upgradeLevel.intValue() == pAgentLevel.intValue()){
            logger.info("代理用户申请代理等级等于上级代理等级");
            upgradeNotice.setUpStatus(UpGradeUpStatus.UP_STATUS_Untreated.getCode());
        }else {
            upgradeNotice.setUpStatus(UpGradeUpStatus.UP_STATUS_Complete.getCode());
            logger.info("代理用户申请代理等级小于上级代理等级");
        }
        upgradeNotice.setRemark("升级提交申请");
        if (pfUserUpgradeNoticeMapper.insert(upgradeNotice) == 0){
            throw new BusinessException("创建升级申请数据失败");
        }
        logger.info("创建升级申请单后处理下级申请单数据");
        List<PfUserUpgradeNotice> pfUserUpgradeNotices = pfUserUpgradeNoticeMapper.selectBySkuIdAndUserIdAndUserPid(skuId, userPid, userId);
        for (PfUserUpgradeNotice upgrade : pfUserUpgradeNotices){
            //当申请用户处理状态为未处理时进行数据更新
            if (upgrade.getStatus().intValue() == UpGradeStatus.STATUS_Untreated.getCode().intValue()){
                upgrade.setUpStatus(UpGradeUpStatus.UP_STATUS_Upgrade.getCode());
                upgrade.setStatus(UpGradeStatus.STATUS_Processing.getCode());
                //更新代理升级申请信息
                updateUpgradeNotice(upgrade);
            }
        }
        return upgradeNotice.getId();
    }

    /**
     * 根据id查询申请信息
     * @param id 申请信息表id
     * @return
     */
    public UpGradeInfoPo getUpGradeInfo(Long id){
        logger.info("根据id查询申请信息 id=" + id);
        return pfUserUpgradeNoticeMapper.selectUpGradeInfoPoById(id);
    }

    /**
     * 更新升级申请信息
     * @param pfUserUpgradeNotice
     * @return
     */
    public int updateUpgradeNotice(PfUserUpgradeNotice pfUserUpgradeNotice) throws Exception{
        int a = pfUserUpgradeNoticeMapper.updateByPrimaryKey(pfUserUpgradeNotice);
        if (a == 0){
            throw new BusinessException("处理失败");
        }
        return a;
    }

    /**
     * 代理暂不升级处理下级申请单数据
     * @param pfUserUpgradeNotice
     * @throws Exception
     */
    public void dealLowerUpgradeNotice(PfUserUpgradeNotice pfUserUpgradeNotice) throws Exception{
        //当申请用户处理状态不是取消状态则将处理状态设置为待支付状态
        if (pfUserUpgradeNotice.getStatus().intValue() != UpGradeStatus.STATUS_Revocation.getCode().intValue()){
            PfUserUpgradeNotice upgrade = new PfUserUpgradeNotice();
            upgrade.setSkuId(pfUserUpgradeNotice.getSkuId());
            upgrade.setUserPid(pfUserUpgradeNotice.getUserPid());
            List<PfUserUpgradeNotice> upgradeNotices = pfUserUpgradeNoticeMapper.selectByCondition(upgrade);
            if (upgradeNotices == null || upgradeNotices.size() == 0){
                throw new BusinessException("无下级代理申请信息");
            }
            for (PfUserUpgradeNotice upgradeNotice : upgradeNotices){
                upgradeNotice.setStatus(UpGradeStatus.STATUS_NoPayment.getCode());
                upgradeNotice.setUpStatus(UpGradeUpStatus.UP_STATUS_NotUpgrade.getCode());
                //更新代理升级申请信息
                updateUpgradeNotice(upgradeNotice);
            }
        }
    }

    /**
     * 订单界面获得升级通知信息
     * @param id   通知单id
     */
    public BOrderUpgradeDetail getUpgradeNoticeInfo(Long id){
        //升级订单通知信息
        if (id==null){
            throw new BusinessException("升级通知信息id为null");
        }
        logger.info("获取升级通知信息------start");
        logger.info("获取升级通知信息------id----"+id);
        PfUserUpgradeNotice upgradeNotice = pfUserUpgradeNoticeMapper.selectByPrimaryKey(id);
        BOrderUpgradeDetail upgradeDetail = null;
        if (upgradeNotice!=null){
            //验证条件是否可以进入
            if (true){
                upgradeDetail = new BOrderUpgradeDetail();
                ComUser oldComUser = comUserService.getUserById(upgradeNotice.getUserPid());
                if (oldComUser!=null){
                    upgradeDetail.setOldPUserId(upgradeNotice.getUserPid());
                    upgradeDetail.setOldPUserName(oldComUser.getRealName());
                }
                //商品信息
                ComSku comSku = getComSku(upgradeNotice.getSkuId());
                if (comSku!=null){
                    upgradeDetail.setSkuId(comSku.getId());
                    upgradeDetail.setSpuId(comSku.getSpuId());
                    upgradeDetail.setSkuName(comSku.getName());
                    upgradeDetail.setPriceRetail(comSku.getPriceRetail());
                }else{
                    logger.info("商品信息为null-----id--"+upgradeNotice.getSkuId());
                    throw new BusinessException("商品信息为null-----id--"+upgradeNotice.getSkuId());
                }
                //级别
                PfSkuAgent oldSkuAgent = getPfSkuAgent(comSku.getId(),upgradeNotice.getOrgAgentLevelId());
                PfSkuAgent newSkuAgent = getPfSkuAgent(comSku.getId(),upgradeNotice.getWishAgentLevelId());
                if (oldSkuAgent!=null){
                    ComAgentLevel oldAgentLevel = getComAgentLeveal(oldSkuAgent.getAgentLevelId());
                    upgradeDetail.setCurrentAgentLevel(newSkuAgent.getAgentLevelId());
                    upgradeDetail.setCurrentAgentLevelName(oldAgentLevel.getName());
                }
                if (newSkuAgent!=null){
                    ComAgentLevel newAgentLevel = getComAgentLeveal(newSkuAgent.getAgentLevelId());
                    upgradeDetail.setApplyAgentLevel(newSkuAgent.getAgentLevelId());
                    upgradeDetail.setApplyAgentLevelName(newAgentLevel.getName());
                }
                //拿货数量
                upgradeDetail.setQuantity(newSkuAgent.getQuantity());
                //保证金差额
                upgradeDetail.setBailChange(getBailChange(oldSkuAgent,newSkuAgent));
                //总价
                BigDecimal totalPrice = getTotalPrice(oldSkuAgent,newSkuAgent);
                upgradeDetail.setTotalPrice(totalPrice);
            }else{
                logger.info("升级通知信息状态不正确-----");
                throw new BusinessException("升级通知信息状态不正确-----");
            }
        }else{
            logger.info("升级通知信息为null------id----"+id);
        }
        logger.info("获取升级通知信息------end");
        return upgradeDetail;
    }

    /**
     * 商品信息
     * @param skuId
     * @return
     */
    private ComSku getComSku (Integer skuId){
        return comSkuService.getSkuById(skuId);
    }

    /**
     * 获取保证金的差额
     * @param oldSkuAgent  旧代理商品
     * @param newSkuAgent  新代理商品
     * @return
     */
    private BigDecimal getBailChange(PfSkuAgent oldSkuAgent,PfSkuAgent newSkuAgent){
        if (oldSkuAgent!=null&&newSkuAgent!=null){
            logger.info("保证金差额-------"+newSkuAgent.getBail().subtract(oldSkuAgent.getBail()).intValue());
            return newSkuAgent.getBail().subtract(oldSkuAgent.getBail());
        }else{
            logger.info("获取保证金差额失败");
            throw new BusinessException("获取保证金差额失败");
        }
    }

    /**
     * 获取订单的总价  = 新代理产品总价（数量*单价） + 保证金差额
     * @param oldSkuAgent
     * @param newSkuAgent
     * @return
     */
    private BigDecimal getTotalPrice(PfSkuAgent oldSkuAgent,PfSkuAgent newSkuAgent){
        BigDecimal totalPice = null;
        if (oldSkuAgent!=null&&newSkuAgent!=null){
            BigDecimal bailChange = getBailChange(oldSkuAgent,newSkuAgent);
            totalPice = newSkuAgent.getTotalPrice().add(bailChange);
        }else{
            totalPice = BigDecimal.ZERO ;
        }
        logger.info("总价------"+totalPice.intValue());
        return totalPice;
    }

    /**
     * 获得商品代理等级信息
     * @param skuId
     * @param levelId
     * @return
     */
    private PfSkuAgent getPfSkuAgent(Integer skuId,Integer levelId){
        logger.info("获得商品代理等级信息----商品skuId----"+skuId+"-----等级id----"+levelId);
        return skuAgentService.getBySkuIdAndLevelId(skuId,levelId);
    }
    /**
     * 获得等级信息
     * @param levelId
     * @return
     */
    private ComAgentLevel getComAgentLeveal(Integer levelId){
        logger.info("获得等级信息----等级id-----"+levelId);
        return comAgentLevelService.selectByPrimaryKey(levelId);
    }
}
