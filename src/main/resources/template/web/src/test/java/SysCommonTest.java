package ${packageName}.${subPackageName}.web.common;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.base.common.exception.RpcException;
import ${packageName}.${subPackageName}.app.client.sys.service.SysCommonApi;
import ${packageName}.${subPackageName}.web.BaseServiceTest;

public class SysCommonTest extends BaseServiceTest {
	
	@Resource SysCommonApi commonApi;
	
	@Test public void getAreaList() throws RpcException {
		// List<SysArea> list = commonApi.
		
		// System.out.println(list.size());
	}
	
}
