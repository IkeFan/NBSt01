package com.xiaoxiong.nbst01.utils;

import com.huawei.iotplatform.client.NorthApiClient;
import com.huawei.iotplatform.client.NorthApiException;
import com.huawei.iotplatform.client.dto.AuthOutDTO;
import com.huawei.iotplatform.client.dto.AuthRefreshInDTO;
import com.huawei.iotplatform.client.dto.AuthRefreshOutDTO;
import com.huawei.iotplatform.client.dto.ClientInfo;
import com.huawei.iotplatform.client.invokeapi.Authentication;
import com.huawei.iotplatform.client.invokeapi.DeviceManagement;
import com.xiaoxiong.nbst01.config.NBAppProperties;

/**
 * @author: Ike.Fan
 * @date: 2018/5/24.
 */
public class NbAuthenUtil {
    NBAppProperties nbAppProperties;
    private static NbAuthenUtil mNbAuthenUtil;


    private Authentication aaa;
    private String mToken;

    public static NbAuthenUtil getInstence(NBAppProperties nbAppProperties){
        synchronized (NbAuthenUtil.class){
            if(mNbAuthenUtil == null){
                mNbAuthenUtil = new NbAuthenUtil();
                try {
                    mNbAuthenUtil.init(nbAppProperties);
                } catch (NorthApiException e) {
                    e.printStackTrace();
                    mNbAuthenUtil = null;
                }
            }
        }
        return mNbAuthenUtil;
    }

    public void init(NBAppProperties nbAppProperties) throws NorthApiException {
        NorthApiClient nac = new NorthApiClient();
        ClientInfo ci = new ClientInfo();
        ci.setAppId(nbAppProperties.getAppId());
        ci.setSecret(nbAppProperties.getSecret());
        ci.setPlatformIp(nbAppProperties.getServerIp());
        ci.setPlatformPort(nbAppProperties.getPort());
        nac.setClientInfo(ci);
        nac.initSSLConfig();

        AuthOutDTO aod = aaa.getAuthToken();
        mToken = aod.getAccessToken();
        aaa = new Authentication(nac);
    }

    public String getToken(){
        return mToken;
    }

    public String refreshToken() throws NorthApiException {
        AuthRefreshInDTO arid = new AuthRefreshInDTO();
        arid.setAppId(nbAppProperties.getAppId());
        arid.setSecret(nbAppProperties.getSecret());

        AuthOutDTO aod = aaa.getAuthToken();
        String refreshToken = aod.getRefreshToken();
        arid.setRefreshToken(refreshToken);
        AuthRefreshOutDTO arod = aaa.refreshAuthToken(arid);
        mToken = arod.getAccessToken();
        return mToken;
    }

    public Authentication getAuthentication(){
        return aaa;
    }

    public DeviceManagement getDeviceManager(){
        return new DeviceManagement(aaa.getNorthApiClient());
    }
}
