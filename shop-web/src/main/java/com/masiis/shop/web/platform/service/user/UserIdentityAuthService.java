package com.masiis.shop.web.platform.service.user;

import com.masiis.shop.common.exceptions.BusinessException;
import com.masiis.shop.common.util.OSSObjectUtils;
import com.masiis.shop.dao.po.ComUser;
import com.masiis.shop.web.platform.constants.SysConstants;
import com.masiis.shop.web.platform.utils.UploadImage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by hzz on 2016/3/30.
 */
@Service
public class UserIdentityAuthService {

    @Resource
    private UserService userService;

    private final Integer saveType = 0;//保存操作
    private final Integer updateType = 1;//更新操作

    /**
     * 获得身份证信息
     *
     * @author hanzengzhi
     * @date 2016/3/31 15:27
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public ComUser getIdentityAuthInfo(HttpServletRequest request,ComUser comUser ){
        switch (comUser.getAuditStatus()){
            case 3://审核不通过，从云服务器身份证下载到本地服务器供展示
                loadIdCardFromOSSToLocal(request,comUser);
                break;
            default:
                break;
        }
        return null;
    }
    /**
     * 阿里云身份证图片下载到本地
     * @author hanzengzhi
     * @date 2016/3/31 15:47
     */
    private void loadIdCardFromOSSToLocal(HttpServletRequest request,ComUser comUser){
        String rootPath = request.getServletContext().getRealPath("/");
        String webappPath = rootPath.substring(0, rootPath.lastIndexOf(File.separator));
        String savepath = SysConstants.ID_CARD_PATH;
        String realpath = webappPath + savepath;
        //OSS下载到本地服务器
        OSSObjectUtils.downloadFile(OSSObjectUtils.OSS_DOWN_LOAD_IMG_KEY + comUser.getIdCardFrontUrl(), realpath+"\\"+comUser.getIdCardFrontUrl());
        OSSObjectUtils.downloadFile(OSSObjectUtils.OSS_DOWN_LOAD_IMG_KEY + comUser.getIdCardBackUrl(), realpath+"\\"+comUser.getIdCardBackUrl());
        //OSS删除
        //OSSObjectUtils.deleteBucketFile(comUser.getIdCardFrontUrl());
        //OSSObjectUtils.deleteBucketFile(comUser.getIdCardBackUrl());

    }

    /**
     * 提交实名认证审核
     * @author hanzengzhi
     * @date 2016/3/30 15:39
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int sumbitAudit(HttpServletRequest request, ComUser comUser,String idCardFrontUrl,String idCardBackUrl,Integer type){
        try{
            String rootPath = request.getServletContext().getRealPath("/");
            String webappPath = rootPath.substring(0, rootPath.lastIndexOf(File.separator));
            String frontFillFullName = uploadFile(webappPath + SysConstants.ID_CARD_PATH + idCardFrontUrl);
            String backFillFullName = uploadFile(webappPath + SysConstants.ID_CARD_PATH + idCardBackUrl);
            if (type.equals(updateType)){
                //第一次审核不通过重新提交身份证审核,删除服务器之前的身份证
                UploadImage.deleteFile(webappPath + SysConstants.ID_CARD_PATH + comUser.getIdCardFrontUrl());
                UploadImage.deleteFile(webappPath + SysConstants.ID_CARD_PATH + comUser.getIdCardBackUrl());
            }
            //修改用户数据
            comUser.setIdCardFrontUrl(frontFillFullName);
            comUser.setIdCardBackUrl(backFillFullName);
            comUser.setAuditStatus(1);
            int i = userService.updateComUser(comUser);
            if (i == 1){
                //更新缓存
                request.getSession().removeAttribute("comUser");
                request.getSession().setAttribute("comUser", comUser);
                //删除最新上传的本地服务器照片
                UploadImage.deleteFile(webappPath + SysConstants.ID_CARD_PATH + idCardFrontUrl);
                UploadImage.deleteFile(webappPath + SysConstants.ID_CARD_PATH + idCardBackUrl);
            }
            return i;
        }catch (Exception e){
            throw new BusinessException("");
        }
    }

    /**
     * 上传文件
     *
     * @author ZhaoLiang
     * @date 2016/3/11 15:12
     */
    private String uploadFile(String filePath) throws FileNotFoundException {
        File frontFile = new File(filePath);
        OSSObjectUtils.uploadFile(frontFile, "static/user/idCard/");
        return frontFile.getName();
    }
}