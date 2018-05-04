package com.xiaoxiong.nbst01.service;

import com.xiaoxiong.nbst01.model.Account;

/**
 * @author: Ike.Fan
 * @date: 2018/5/3.
 */
public interface AccountService extends IBaseService<Account> {
    public enum ACCOUNT_TYPE {
        UNKOWN(0), ACCOUNT(1), EMAILE(2), PHONE(3),QQ(4),WECHAT(5);
        private int type = 0;
        private ACCOUNT_TYPE(int type){
            this.type = type;
        }

        public int value(){
            return type;
        }
    }

    boolean accountSignIn(String account, String password);
    boolean phoneSignIn(String phone, String password, String code);
    boolean emailSignIn(String email, String password);
    boolean thirdPartSignIn(String openId, int type);

    boolean accountSignUp(String account, String password);
    boolean thirdPartSignUp(String account, String password);
    Account findByAccount(String account);
}

