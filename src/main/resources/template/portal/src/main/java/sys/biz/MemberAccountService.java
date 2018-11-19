package ${packageName}.${subPackageName}.portal.sys.biz;

import com.towcent.base.common.vo.ResultVo;
import ${packageName}.${subPackageName}.portal.sys.vo.input.MemberAccountBaseInfoIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.MemberAccountFastLoginIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.MemberAccountLoginIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.MemberAccountPhoneExistIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.MemberAccountRegisterIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.MemberAccountResetIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.MemberAccountSaveBaseInfoIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.MemberAccountUpdatePasswdIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.MemberAccountUpdatePhoneIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.MemberAccountUpdateWithdrawPassIn;

/**
 * MemberAccountService
 * @author huangtao
 * @version 0.0.3
 */
public interface MemberAccountService {

	ResultVo login(MemberAccountLoginIn paramIn);
	
	ResultVo fastLogin(MemberAccountFastLoginIn paramIn);

    /**
     * 重置账户密码
     * 
     * @param paramIn
     * @return 
     * @author licheng
     * @date 2018/1/18 0018 14:55
     * @version 0.1.0 
     */
	ResultVo reset(MemberAccountResetIn paramIn);


	ResultVo register(MemberAccountRegisterIn paramIn);

	ResultVo phoneExist(MemberAccountPhoneExistIn paramIn);
	
	ResultVo saveBaseInfo(MemberAccountSaveBaseInfoIn paramIn);

	ResultVo baseInfo(MemberAccountBaseInfoIn paramIn);

	ResultVo updatePhone(MemberAccountUpdatePhoneIn paramIn);

	ResultVo updateWithdrawPass(MemberAccountUpdateWithdrawPassIn paramIn);

	ResultVo updatePasswd(MemberAccountUpdatePasswdIn paramIn);
}