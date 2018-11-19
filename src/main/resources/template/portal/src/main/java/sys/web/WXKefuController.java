package ${packageName}.${subPackageName}.portal.sys.web;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.common.web.BaseController;
import ${packageName}.${subPackageName}.common.Constant;
import ${packageName}.${subPackageName}.portal.sys.vo.input.WxKfMessageIn;
import ${packageName}.${subPackageName}.portal.sys.vo.output.WxKfMessageOut;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;

/**
 * 客服
 * 
 * @author lijian
 *
 */
@RequestMapping(value = "wx/kf", method = RequestMethod.POST)
@RestController
public class WXKefuController extends BaseController {
	
	@Autowired
	WxMpService wxService;
	
	/**
	 * 客服主动发送消息
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "message") @Loggable
	public ResultVo message(@RequestBody WxKfMessageIn vo) {
		ResultVo result = new ResultVo();
		boolean isRes = false;
		try {
			WxKfMessageOut out = new WxKfMessageOut();
			if(StringUtils.isBlank(vo.getOpenId())){
				result.setCode(Constant.E_003);
				return result;
			}
			WxMpKefuMessage message = new WxMpKefuMessage();
			if(vo.getMsgType().equals("text")){
				if(StringUtils.isBlank(vo.getContent())){
					result.setCode(Constant.E_003);
					return result;
				}
				message = WxMpKefuMessage.TEXT().toUser(vo.getOpenId()).content(vo.getContent()).build();
			}else if(vo.getMsgType().equals("image")){
				if(StringUtils.isBlank(vo.getMaterialUrl())){
					result.setCode(Constant.E_003);
					return result;
				}
				Resource source = new UrlResource(vo.getMaterialUrl());
				WxMediaUploadResult rs = this.wxService.getMaterialService().mediaUpload("voice", "mp3", source.getInputStream());
				out.setMediaId(rs.getMediaId());
				message = WxMpKefuMessage.VOICE().toUser(vo.getOpenId()).mediaId(rs.getMediaId()).build();
			}else if(vo.getMsgType().equals("video")){
				if(StringUtils.isBlank(vo.getMaterialUrl())){
					result.setCode(Constant.E_003);
					return result;
				}
				Resource source = new UrlResource(vo.getMaterialUrl());
				WxMediaUploadResult rs = this.wxService.getMaterialService().mediaUpload("video", "mp4", source.getInputStream());
				out.setMediaId(rs.getMediaId());
				message = WxMpKefuMessage.VIDEO().toUser(vo.getOpenId()).title(vo.getTitle()).description(vo.getDescription()).mediaId(rs.getMediaId()).build();
			}
			isRes = this.wxService.getKefuService().sendKefuMessage(message);
			out.setIsResponse(isRes);
			result.setData(out);
			result.setCode(Constant.E_000);
		} catch (WxErrorException e) {
			logger.error("发送客服消息异常",e);
			result.setCode(Constant.E_107);
		}catch(Exception e){
			logger.error("内部异常",e);
			result.setCode(Constant.E_107);
		}
		return result;
	}
}
