package com.xiaoxiong.nbst01.controller;

import com.xiaoxiong.nbst01.Global;
import com.xiaoxiong.nbst01.common.AppException;
import com.xiaoxiong.nbst01.common.ErrorCode;
import com.xiaoxiong.nbst01.mapper.AccountInfoMapper;
import com.xiaoxiong.nbst01.model.Account;
import com.xiaoxiong.nbst01.model.AccountInfo;
import com.xiaoxiong.nbst01.model.AccountInfoExample;
import com.xiaoxiong.nbst01.net.RestResult;
import com.xiaoxiong.nbst01.net.RestResultGenerator;
import com.xiaoxiong.nbst01.net.requestparam.RequestAccount;
import com.xiaoxiong.nbst01.service.AccountService;
import com.xiaoxiong.nbst01.utils.EmptyChecker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Ike.Fan
 * @date: 2018/5/3.
 */

@Api("帐号相关接口")
@RestController
@RequestMapping(Global.API_URL_ACCOUNT)
public class AccountController {
    @Autowired
    AccountService mAccountService;
    @Autowired
    private AccountInfoMapper mAccountInfoMapper;

    @ApiOperation(value = "根据帐号注册",notes = "根据传入的account 和 password 注册新账户")
    @PostMapping("/sign_up")
    public RestResult<Boolean> accountSignUp(@RequestBody RequestAccount requestAccount){
        if(EmptyChecker.isEmpty(requestAccount.account) || EmptyChecker.isEmpty(requestAccount.password)){
            return RestResultGenerator.genErrorResult(ErrorCode.ACCOUNT_OR_PASSWORD_NULL);
        }
        try{
            return RestResultGenerator.genSuccessResult(mAccountService.accountSignUp(requestAccount.account, requestAccount.password));
        }catch (Exception e){
            return RestResultGenerator.genErrorResult(new AppException(ErrorCode.EXCEPTION_ERR.getCode(),e.getMessage()));
        }
    }

    @ApiOperation(value = "帐号登录",notes = "根据传入的account 和 password 登录")
    @PostMapping("/sign_in")
    public RestResult<Boolean> accountLogin(@RequestBody RequestAccount requestAccount){
        if(EmptyChecker.isEmpty(requestAccount.account) || EmptyChecker.isEmpty(requestAccount.password)){
            return RestResultGenerator.genErrorResult(ErrorCode.ACCOUNT_OR_PASSWORD_NULL);
        }
        if( mAccountService.accountSignIn(requestAccount.account, requestAccount.password) ){
            return RestResultGenerator.genSuccessResult(true);
        }else{
            return RestResultGenerator.genErrorResult(ErrorCode.ACCOUNT_OR_PASSWORD_ERR);
        }
    }

    @ApiOperation(value = "查询信息",notes = "返回账户信息 AccountInfo")
    @GetMapping("/{account}")
    public RestResult<AccountInfo> getInfo(@RequestParam("account") String account){
        Account user =  mAccountService.findByAccount(account);
        if(EmptyChecker.isEmpty(user)){
           return RestResultGenerator.genErrorResult(ErrorCode.ACCOUNT_NOT_EXIST);
        }
        AccountInfoExample example = new AccountInfoExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<AccountInfo> accounts = mAccountInfoMapper.selectByExample(example);
        if(EmptyChecker.isEmpty(accounts) || accounts.size()<1){
            return RestResultGenerator.genErrorResult(ErrorCode.ACCOUNT_INFO_NULL);
        }else{
            return  RestResultGenerator.genSuccessResult(accounts.get(0));
        }
    }
}
