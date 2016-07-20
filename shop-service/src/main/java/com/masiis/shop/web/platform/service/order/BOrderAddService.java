package com.masiis.shop.web.platform.service.order;

import com.masiis.shop.common.enums.platform.BOrderStatus;
import com.masiis.shop.common.enums.platform.BOrderType;
import com.masiis.shop.common.exceptions.BusinessException;
import com.masiis.shop.common.util.OrderMakeUtils;
import com.masiis.shop.dao.beans.order.BOrderAdd;
import com.masiis.shop.dao.platform.order.PfBorderConsigneeMapper;
import com.masiis.shop.dao.platform.order.PfBorderItemMapper;
import com.masiis.shop.dao.platform.order.PfBorderMapper;
import com.masiis.shop.dao.platform.product.PfSkuAgentMapper;
import com.masiis.shop.dao.platform.user.ComUserMapper;
import com.masiis.shop.dao.platform.user.PfUserCertificateMapper;
import com.masiis.shop.dao.po.*;
import com.masiis.shop.web.common.service.UserAddressService;
import com.masiis.shop.web.platform.service.product.PfUserSkuStockService;
import com.masiis.shop.web.platform.service.product.SkuAgentService;
import com.masiis.shop.web.common.service.SkuService;
import com.masiis.shop.web.platform.service.user.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * BOrderAddService
 *
 * @author ZhaoLiang
 * @date 2016/4/14
 */
@Service
public class BOrderAddService {
    private final static Logger logger = Logger.getLogger(BOrderService.class);
    @Resource
    private PfBorderMapper pfBorderMapper;
    @Resource
    private PfBorderItemMapper pfBorderItemMapper;
    @Resource
    private PfBorderConsigneeMapper pfBorderConsigneeMapper;
    @Resource
    private ComUserMapper comUserMapper;
    @Resource
    private BOrderOperationLogService bOrderOperationLogService;
    @Resource
    private PfUserSkuStockService pfUserSkuStockService;
    @Resource
    private PfSkuAgentMapper pfSkuAgentMapper;
    @Resource
    private SkuService skuService;
    @Resource
    private UserAddressService userAddressService;
    @Resource
    private SkuAgentService skuAgentService;
    @Resource
    private PfUserCertificateMapper pfUserCertificateMapper;
    @Resource
    private PfUserStatisticsService pfUserStatisticsService;
    @Resource
    private PfUserSkuService pfUserSkuService;
    @Resource
    private PfUserRecommendRelationService pfUserRecommendRelationService;
    @Resource
    private PfBorderRecommenRewardService pfBorderRecommenRewardService;
    @Resource
    private PfUserUpgradeNoticeService userUpgradeNoticeService;

    /**
     * 添加订单
     *
     * @param bOrderAdd
     * @return
     * @throws Exception
     */
    @Transactional
    public Long addBOrder(BOrderAdd bOrderAdd) throws Exception {
        if (bOrderAdd == null) {
            throw new BusinessException("bOrderAdd为空");
        }
        BigDecimal retailPrice;//微信零售价
        BigDecimal bailPrice = BigDecimal.ZERO;//代理保证金
        Integer quantity = 0;//数量
        Integer agentLevelId;//代理等级
        String weiXinId;
        ComSku comSku = skuService.getSkuById(bOrderAdd.getSkuId());
        retailPrice = comSku.getPriceRetail();
        PfUserSku pfUserSku = pfUserSkuService.getPfUserSkuByUserIdAndSkuId(bOrderAdd.getUserId(), comSku.getId());
        if (bOrderAdd.getOrderType().equals(BOrderType.UPGRADE.getCode())) {
            agentLevelId = bOrderAdd.getApplyAgentLevel();
            weiXinId = pfUserCertificateMapper.selectByUserSkuId(pfUserSku.getId()).getWxId();
            logger.info("------升级流程-----获得期望升级的等级--------" + agentLevelId);
        } else {
            if (pfUserSku == null) {
                logger.info("pfUser为空------userId----" + bOrderAdd.getUserId() + "-----商品id------" + comSku.getId());
                agentLevelId = bOrderAdd.getAgentLevelId();
                weiXinId = bOrderAdd.getWeiXinId();
            } else {
                logger.info("pfUser不为空------userId----" + bOrderAdd.getUserId() + "-----商品id------" + comSku.getId());
                agentLevelId = pfUserSku.getAgentLevelId();
                weiXinId = pfUserCertificateMapper.selectByUserSkuId(pfUserSku.getId()).getWxId();
            }
        }
        logger.info("agentLevelId------" + agentLevelId);
        logger.info("weiXinId------" + weiXinId);
        //v1.2 Begin如果合伙人和上级的合伙等级相同，那么合伙人的上级将是推荐人的上级
        Long recommendUserId = 0L;
        PfUserSku _parentPfUserSku = pfUserSkuService.getPfUserSkuByUserIdAndSkuId(bOrderAdd.getpUserId(), bOrderAdd.getSkuId());
        if (bOrderAdd.getpUserId() != 0 && bOrderAdd.getOrderType().equals(BOrderType.agent.getCode())) {
            if (_parentPfUserSku.getAgentLevelId().equals(bOrderAdd.getAgentLevelId())) {
                recommendUserId = bOrderAdd.getpUserId();
                bOrderAdd.setpUserId(_parentPfUserSku.getUserPid());
            }
        }
        //v1.2 End如果合伙人和上级的合伙等级相同，那么合伙人的上级将是上级的上级
        PfSkuAgent pfSkuAgent = skuAgentService.getBySkuIdAndLevelId(comSku.getId(), agentLevelId);
        if (pfSkuAgent == null) {
            throw new BusinessException("找不到要代理的商品信息");
        }
        //合伙订单需要缴纳保证金 订单类型(0代理1补货2拿货3升级)
        BigDecimal bailChange = BigDecimal.ZERO;
        if (bOrderAdd.getOrderType() == 0) {
            bailPrice = pfSkuAgent.getBail();
            quantity = pfSkuAgent.getQuantity();
        } else if (bOrderAdd.getOrderType() == 1) {
            bailPrice = BigDecimal.ZERO;
            quantity = bOrderAdd.getQuantity();
        } else if (bOrderAdd.getOrderType() == 3) {
            logger.info("商品-----skuId----" + comSku.getId() + "-----等级------" + bOrderAdd.getAgentLevelId());
            PfSkuAgent oldPfSkuAgent = skuAgentService.getBySkuIdAndLevelId(comSku.getId(), bOrderAdd.getCurrentAgentLevel());
            logger.info("新代理保证金-------" + pfSkuAgent.getBail());
            logger.info("旧代理保证金-------" + oldPfSkuAgent.getBail());
            bailChange = pfSkuAgent.getBail().subtract(oldPfSkuAgent.getBail());
            if (bailChange.compareTo(BigDecimal.ZERO) < 0) {
                logger.info("升级保证金不能为负数");
                throw new BusinessException("升级保证金不能为负数");
            }
            logger.info("保证金差额-------" + bailChange);
            quantity = pfSkuAgent.getQuantity();
        }
        //处理订单数据
        PfBorder pfBorder = new PfBorder();
        pfBorder.setCreateTime(new Date());
        pfBorder.setCreateMan(bOrderAdd.getUserId());
        String orderCode = OrderMakeUtils.makeOrder("B");
        pfBorder.setOrderCode(orderCode);
        pfBorder.setUserMessage(bOrderAdd.getUserMessage());
        pfBorder.setUserId(bOrderAdd.getUserId());
        pfBorder.setUserPid(bOrderAdd.getpUserId());
        pfBorder.setSupplierId(0);
        //商品折扣后价格
        BigDecimal unitPrice = pfSkuAgent.getUnitPrice();
        //商品总金额=商品微信销售价*折扣*数量
        BigDecimal productAmount = unitPrice.multiply(BigDecimal.valueOf(quantity));
        //订单总金额=商品总金额+保证金+运费
        BigDecimal orderAmount;
        if (bOrderAdd.getOrderType() == 3) {
            orderAmount = productAmount.add(bailChange).add(bOrderAdd.getShipAmount());
            pfBorder.setBailAmount(bailChange);
        } else {
            orderAmount = productAmount.add(bailPrice).add(bOrderAdd.getShipAmount());
            pfBorder.setBailAmount(bailPrice);
        }
        pfBorder.setReceivableAmount(orderAmount);
        pfBorder.setOrderAmount(orderAmount);
        pfBorder.setProductAmount(productAmount);
        pfBorder.setShipAmount(bOrderAdd.getShipAmount());
        pfBorder.setPayAmount(BigDecimal.ZERO);
        pfBorder.setShipManId(0);
        pfBorder.setShipManName("");
        pfBorder.setShipType(0);
        //确定订单的拿货方式
        pfBorder.setSendType(bOrderAdd.getSendType());
        pfBorder.setOrderType(bOrderAdd.getOrderType());
        pfBorder.setOrderStatus(0);
        pfBorder.setShipStatus(0);
        pfBorder.setPayStatus(0);
        pfBorder.setIsCounting(0);
        pfBorder.setIsShip(0);
        pfBorder.setIsReceipt(0);
        pfBorder.setRecommenAmount(BigDecimal.ZERO);
        pfBorder.setRemark("");
        //添加订单
        pfBorderMapper.insert(pfBorder);
        //处理订单商品数据
        PfBorderItem pfBorderItem = new PfBorderItem();
        pfBorderItem.setPfBorderId(pfBorder.getId());
        pfBorderItem.setCreateTime(new Date());
        pfBorderItem.setSpuId(comSku.getSpuId());
        pfBorderItem.setSkuId(comSku.getId());
        pfBorderItem.setSkuName(comSku.getName());
        logger.info("pfborderItem-------中的agentLevelId------" + agentLevelId);
        pfBorderItem.setAgentLevelId(agentLevelId);
        pfBorderItem.setWxId(weiXinId);
        pfBorderItem.setQuantity(quantity);
        pfBorderItem.setOriginalPrice(retailPrice);
        pfBorderItem.setUnitPrice(unitPrice);
        pfBorderItem.setTotalPrice(productAmount);
        pfBorderItem.setBailAmount(bailPrice);
        pfBorderItem.setIsComment(0);
        pfBorderItem.setIsReturn(0);
        pfBorderItemMapper.insert(pfBorderItem);
        //v1.2 Begin处理订单推荐奖励表
        if (bOrderAdd.getpUserId() != 0) {
            PfBorderRecommenReward pfBorderRecommenReward = null;
            PfUserRecommenRelation pfUserRecommenRelation = pfUserRecommendRelationService.selectRecommenRelationByUserIdAndSkuId(bOrderAdd.getUserId(), bOrderAdd.getSkuId());
            if (pfUserRecommenRelation != null && pfUserRecommenRelation.getPid() > 0) {
                pfBorderRecommenReward = new PfBorderRecommenReward();
                pfBorderRecommenReward.setCreateTime(new Date());
                pfBorderRecommenReward.setPfBorderId(pfBorder.getId());
                pfBorderRecommenReward.setPfBorderItemId(pfBorderItem.getId());
                pfBorderRecommenReward.setSkuId(pfBorderItem.getSkuId());
                pfBorderRecommenReward.setRecommenUserId(pfUserRecommenRelation.getUserPid());
                pfBorderRecommenReward.setQuantity(pfBorderItem.getQuantity());
                pfBorderRecommenReward.setRewardUnitPrice(pfSkuAgent.getRewardUnitPrice());
                pfBorderRecommenReward.setRewardTotalPrice(pfBorderRecommenReward.getRewardUnitPrice().multiply(BigDecimal.valueOf(pfBorderRecommenReward.getQuantity())));
                pfBorderRecommenReward.setRemark("已经有了推荐关系的奖励");
            } else {
                if (recommendUserId > 0 && !recommendUserId.equals(pfBorder.getUserPid())) {
                    pfBorderRecommenReward = new PfBorderRecommenReward();
                    pfBorderRecommenReward.setCreateTime(new Date());
                    pfBorderRecommenReward.setPfBorderId(pfBorder.getId());
                    pfBorderRecommenReward.setPfBorderItemId(pfBorderItem.getId());
                    pfBorderRecommenReward.setSkuId(pfBorderItem.getSkuId());
                    pfBorderRecommenReward.setRecommenUserId(recommendUserId);
                    pfBorderRecommenReward.setQuantity(pfBorderItem.getQuantity());
                    pfBorderRecommenReward.setRewardUnitPrice(pfSkuAgent.getRewardUnitPrice());
                    pfBorderRecommenReward.setRewardTotalPrice(pfBorderRecommenReward.getRewardUnitPrice().multiply(BigDecimal.valueOf(pfBorderRecommenReward.getQuantity())));
                    pfBorderRecommenReward.setRemark("新建推荐关系的奖励");
                }
            }
            if (pfBorderRecommenReward != null) {
                pfBorderRecommenRewardService.insert(pfBorderRecommenReward);
                pfBorder.setRecommenAmount(pfBorderRecommenReward.getRewardTotalPrice());
                pfBorderMapper.updateById(pfBorder);
            }
        }
        //v1.2 End处理订单推荐奖励表
        //添加订单日志
        bOrderOperationLogService.insertBOrderOperationLog(pfBorder, "新增订单");
        //拿货方式(0未选择1平台代发2自己发货)
        if (pfBorder.getOrderType() == 2 || pfBorder.getSendType() == 2) {
            //获得地址
            ComUserAddress comUserAddress = userAddressService.getOrderAddress(bOrderAdd.getUserAddressId(), bOrderAdd.getUserId());
            if (comUserAddress == null) {
                throw new BusinessException("请填写收货地址");
            }
            PfBorderConsignee pfBorderConsignee = new PfBorderConsignee();
            pfBorderConsignee.setPfBorderId(pfBorder.getId());
            pfBorderConsignee.setCreateTime(new Date());
            pfBorderConsignee.setUserId(comUserAddress.getUserId());
            pfBorderConsignee.setConsignee(comUserAddress.getName());
            pfBorderConsignee.setMobile(comUserAddress.getMobile());
            pfBorderConsignee.setProvinceId(comUserAddress.getProvinceId());
            pfBorderConsignee.setProvinceName(comUserAddress.getProvinceName());
            pfBorderConsignee.setCityId(comUserAddress.getCityId());
            pfBorderConsignee.setCityName(comUserAddress.getCityName());
            pfBorderConsignee.setRegionId(comUserAddress.getRegionId());
            pfBorderConsignee.setRegionName(comUserAddress.getRegionName());
            pfBorderConsignee.setAddress(comUserAddress.getAddress());
            pfBorderConsignee.setZip(comUserAddress.getZip());
            pfBorderConsigneeMapper.insert(pfBorderConsignee);
        }
        if (bOrderAdd.getOrderType().equals(BOrderType.UPGRADE.getCode())) {
            PfUserUpgradeNotice pfUserUpgradeNotice = userUpgradeNoticeService.selectByPrimaryKey(bOrderAdd.getUpgradeNoticeId());
            pfUserUpgradeNotice.setStatus(2);//待支付
            pfUserUpgradeNotice.setPfBorderId(pfBorder.getId());
            userUpgradeNoticeService.update(pfUserUpgradeNotice);
        }
        return pfBorder.getId();
    }

    /**
     * 添加拿货订单
     *
     * @param userId   用户id
     * @param skuId    商品id
     * @param quantity 拿货数量
     * @param message  用户留言
     *                 <1>处理订单数据
     *                 <2>添加订单日志
     *                 <3>冻结sku库存 如果用户id是0 则为平台直接代理商扣减平台商品库存
     *                 <4>添加订单地址信息
     *                 <5>增加统计数据
     * @return
     * @throws Exception
     * @auth:wbj
     */
    @Transactional
    public Long addProductTake(Long userId, Integer skuId, int quantity, String message, long userAddressId) throws Exception {
        logger.info("进入拿货订单处理Service");
        logger.info("<1>处理订单数据");
        PfUserSku pfUserSku = pfUserSkuService.getPfUserSkuByUserIdAndSkuId(userId, skuId);
        if (pfUserSku == null) {
            throw new BusinessException("您还没有代理过此商品，不能拿货。");
        }
        ComUser comUser = comUserMapper.selectByPrimaryKey(userId);
        if (comUser.getSendType() != 1) {
            throw new BusinessException("发货方式不是平台代发，不能拿货");
        }
        Integer levelId = pfUserSku.getAgentLevelId();//代理等级
        Long pUserId = 0L;//上级代理用户id
        BigDecimal amount;//订单总金额
        Long rBOrderId;//返回生成的订单id
        //获取上级代理
        PfUserSku paremtUserSku = pfUserSkuService.getPfUserSkuByUserIdAndSkuId(pfUserSku.getUserPid(), pfUserSku.getSkuId());
        if (paremtUserSku != null) {
            pUserId = paremtUserSku.getUserId();
        }
        PfSkuAgent pfSkuAgent = pfSkuAgentMapper.selectBySkuIdAndLevelId(skuId, levelId);
        ComSku comSku = skuService.getSkuById(skuId);
        amount = pfSkuAgent.getUnitPrice().multiply(BigDecimal.valueOf(quantity));
        //处理订单数据
        PfBorder order = new PfBorder();
        order.setCreateTime(new Date());
        order.setCreateMan(userId);
        String orderCode = OrderMakeUtils.makeOrder("B");
        order.setOrderCode(orderCode);
        order.setUserMessage(message);
        order.setUserId(userId);
        order.setUserPid(pUserId);
        order.setBailAmount(new BigDecimal(0));
        order.setSupplierId(0);
        order.setReceivableAmount(amount);
        order.setOrderAmount(amount);//运费到付，商品总价即订单总金额
        order.setProductAmount(amount);
        order.setShipAmount(BigDecimal.ZERO);
        order.setPayAmount(BigDecimal.ZERO);
        order.setShipType(0);
        order.setSendType(comUser.getSendType());
        order.setOrderStatus(BOrderStatus.WaitShip.getCode());
        order.setShipStatus(0);
        order.setPayStatus(1);//支付
        order.setIsShip(0);
        order.setIsReceipt(0);
        order.setIsCounting(0);
        order.setRemark("拿货订单");
        order.setOrderType(2);
        pfBorderMapper.insert(order);
        rBOrderId = order.getId();
        PfBorderItem pfBorderItem = new PfBorderItem();
        pfBorderItem.setCreateTime(new Date());
        pfBorderItem.setPfBorderId(rBOrderId);
        pfBorderItem.setSpuId(comSku.getSpuId());
        pfBorderItem.setSkuId(comSku.getId());
        pfBorderItem.setSkuName(comSku.getName());
        pfBorderItem.setBailAmount(new BigDecimal(0));
        pfBorderItem.setQuantity(quantity);
        pfBorderItem.setAgentLevelId(levelId);
        pfBorderItem.setOriginalPrice(comSku.getPriceRetail());
        pfBorderItem.setUnitPrice(pfSkuAgent.getUnitPrice());//合伙人价
        pfBorderItem.setTotalPrice(pfSkuAgent.getUnitPrice().multiply(BigDecimal.valueOf(quantity)));
        pfBorderItem.setIsComment(0);
        pfBorderItem.setIsReturn(0);
        pfBorderItemMapper.insert(pfBorderItem);
        logger.info("<2>添加订单日志");
        bOrderOperationLogService.insertBOrderOperationLog(order, "订单已支付,拿货订单");
        logger.info("<3>冻结usersku库存 用户加冻结库存存");
        PfUserSkuStock pfUserSkuStock;
        //冻结usersku库存 用户加冻结库存
        pfUserSkuStock = pfUserSkuStockService.selectByUserIdAndSkuId(userId, pfBorderItem.getSkuId());
        if (pfUserSkuStock == null) {
            throw new BusinessException("拿货失败：没有库存信息");
        }
        if (pfUserSkuStock.getStock() - pfUserSkuStock.getFrozenStock() < quantity) {
            throw new BusinessException("拿货失败：拿货数量超过库存数量");
        }
        pfUserSkuStock.setFrozenStock(pfUserSkuStock.getFrozenStock() + quantity);
        if (pfUserSkuStockService.updateByIdAndVersions(pfUserSkuStock) == 0) {
            throw new BusinessException("并发修改库存失败");
        }
        logger.info("<4>添加订单地址信息");
        ComUserAddress comUserAddress = userAddressService.getUserAddressById(userAddressId);
        PfBorderConsignee pfBorderConsignee = new PfBorderConsignee();
        pfBorderConsignee.setCreateTime(new Date());
        pfBorderConsignee.setPfBorderId(order.getId());
        pfBorderConsignee.setUserId(comUserAddress.getUserId());
        pfBorderConsignee.setConsignee(comUserAddress.getName());
        pfBorderConsignee.setMobile(comUserAddress.getMobile());
        pfBorderConsignee.setProvinceId(comUserAddress.getProvinceId());
        pfBorderConsignee.setProvinceName(comUserAddress.getProvinceName());
        pfBorderConsignee.setCityId(comUserAddress.getCityId());
        pfBorderConsignee.setCityName(comUserAddress.getCityName());
        pfBorderConsignee.setRegionId(comUserAddress.getRegionId());
        pfBorderConsignee.setRegionName(comUserAddress.getRegionName());
        pfBorderConsignee.setAddress(comUserAddress.getAddress());
        pfBorderConsignee.setZip(comUserAddress.getZip());
        pfBorderConsigneeMapper.insert(pfBorderConsignee);
        logger.info("<5>增加统计数据");
        PfUserStatistics pfUserStatistics = pfUserStatisticsService.selectByUserIdAndSkuId(userId, pfBorderItem.getSkuId());
        pfUserStatistics.setTakeOrderCount(pfUserStatistics.getTakeOrderCount() + 1);
        pfUserStatistics.setTakeProductCount(pfUserStatistics.getTakeProductCount() + pfBorderItem.getQuantity());
//        pfUserStatistics.setTakeFee(pfBorderItem.getTotalPrice());
        pfUserStatistics.setVersion(pfUserStatistics.getVersion());
        pfUserStatisticsService.updateByIdAndVersion(pfUserStatistics);
        return rBOrderId;
    }

}