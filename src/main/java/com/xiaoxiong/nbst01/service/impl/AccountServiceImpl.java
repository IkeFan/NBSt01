package com.xiaoxiong.nbst01.service.impl;

import com.xiaoxiong.nbst01.mapper.AccountMapper;
import com.xiaoxiong.nbst01.model.Account;
import com.xiaoxiong.nbst01.model.AccountExample;
import com.xiaoxiong.nbst01.service.AccountService;
import com.xiaoxiong.nbst01.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Ike.Fan
 * @date: 2018/5/3.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper mAccountMapper;

    @Override
    public boolean accountSignIn(String account, String password) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountEqualTo(account).andPasswordEqualTo(password);
        return mAccountMapper.selectByExample(accountExample).size()>0;
    }

    @Override
    public boolean phoneSignIn(String phone, String password, String code) {
        Account newAccount = new Account();
        newAccount.setPhone(phone);
        newAccount.setPassword(password);
        newAccount.setLoginType(ACCOUNT_TYPE.PHONE.value());
        newAccount.setCreateDate(CalendarUtil.getCalendar().getTime());
        return mAccountMapper.insert(newAccount) > 0;
    }

    @Override
    public boolean emailSignIn(String email, String password) {
        return false;
    }

    @Override
    public boolean thirdPartSignIn(String openId, int type) {
        return false;
    }

    @Override
    public boolean accountSignUp(String account, String password) {
        Account newAccount = new Account();
        newAccount.setAccount(account);
        newAccount.setPassword(password);
        newAccount.setLoginType(ACCOUNT_TYPE.ACCOUNT.value());
        newAccount.setCreateDate(CalendarUtil.getCalendar().getTime());
        return addItem(newAccount);
    }

    @Override
    public boolean thirdPartSignUp(String account, String password) {
        return false;
    }

    @Override
    public Account findByAccount(String account) {
        AccountExample example = new AccountExample();
        example.createCriteria().andAccountEqualTo(account);
        List<Account> accounts = mAccountMapper.selectByExample(example);
        return accounts.size()>0?accounts.get(0):null;
    }

    @Override
    public List<Account> getList() {
        return null;
    }

    @Override
    public Account getItem(int id) {
        return mAccountMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(Account account) {
        return false;
    }

    @Override
    public boolean deleteItem(int id) {
        return mAccountMapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean addItem(Account account) {
        return mAccountMapper.insert(account)>0;
    }
}
