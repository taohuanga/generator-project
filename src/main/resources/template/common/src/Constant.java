package ${packageName}.${subPackageName}.common;

import com.towcent.base.common.constants.BaseConstant;

/**
 * 常量类
 * 
 * @author huangtao
 * @date 2015年6月24日 下午4:23:06
 * @version 0.1.0
 */
public final class Constant extends BaseConstant {

	/** 图片上传url */
	public static final String UPLOAD_URL = "/api/sys/image/upload";
	
	public static final String UPLOAD_URL_WX = "/api/sys/image/wxupload";
	
    /**
     * APP类型(0:客户)
     */
    public static final byte APP_TYPE_0 = 0;
    
	/** 用户注册 */
	public static final byte SMS_TYPE_1 = 1;
	/** 用户登录 */
    public static final byte SMS_TYPE_2 = 2;
    /** 用户修改密码 */
    public static final byte SMS_TYPE_3 = 3;

	/**
     * 常量：id
     */
    public static final String ID = "id";
    /**
     * 常量：userId
     */
    public static final String USER_ID = "userId";
}
