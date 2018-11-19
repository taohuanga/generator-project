package ${packageName}.${subPackageName}.app.server.sys.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.towcent.base.common.exception.ServiceException;
import ${packageName}.${subPackageName}.app.client.sys.dto.SysUser;
import ${packageName}.${subPackageName}.app.server.BaseServiceTest;

public class SysUserServiceTest extends BaseServiceTest {
	
	@Resource SysUserService userService;
	
	@Test public void findByKeyVal() throws ServiceException {
		List<SysUser> list = userService.findByKeyVal("id", 1);
		
		Assert.assertEquals(1, list.size());
	}
	
	@Test public void findByKeyValSingle() throws ServiceException {
		SysUser user = userService.findByKeyValSingle("id", 1);
		
		Assert.assertEquals("admin", user.getLoginName());
		System.out.println("===>");
	}
}
