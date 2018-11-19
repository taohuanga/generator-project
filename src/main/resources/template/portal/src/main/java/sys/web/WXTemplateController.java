package ${packageName}.${subPackageName}.portal.sys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.mapper.JsonMapper;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.common.web.BaseController;
import ${packageName}.${subPackageName}.common.Constant;
import ${packageName}.${subPackageName}.portal.sys.vo.input.WxTemplateIn;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * 模板信息
 * 
 * @author lijian
 *
 */
@RequestMapping(value = "wx/template", method = RequestMethod.POST)
@RestController
public class WXTemplateController extends BaseController {
	
	@Autowired
	WxMpService wxService;
	
	/**
	 * 模板消息
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "templateMsg") @Loggable
	public ResultVo sendTemplate1(@RequestBody WxTemplateIn vo) {
		ResultVo result = new ResultVo();
		try {
			WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder().toUser(vo.getOpenId())
					.templateId(vo.getTemplateId()).build();
			templateMessage.setUrl(vo.getJumpUrl());
			List<WxMpTemplateData> dataList = (List<WxMpTemplateData>) JsonMapper.fromJsonString(vo.getDataJson(), List.class);
			templateMessage.setData(dataList);
			String msgId = this.wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
			result.setData(msgId);
			result.setCode(Constant.E_000);
		} catch (WxErrorException e) {
			logger.error("发送模板消息异常",e);
			result.setCode(Constant.E_001);
		}
		return result;
	}
}
