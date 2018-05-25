package com.xiaoxiong.nbst01.service.impl;

import com.huawei.iotplatform.client.NorthApiException;
import com.huawei.iotplatform.client.dto.*;
import com.xiaoxiong.nbst01.config.DeviceProperties;
import com.xiaoxiong.nbst01.config.NBAppProperties;
import com.xiaoxiong.nbst01.service.NbDeviceService;
import com.xiaoxiong.nbst01.utils.NbAuthenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: Ike.Fan
 * @date: 2018/5/24.
 */
@Component
@Service
public class NbDeviceServiceImpl implements NbDeviceService{
    @Autowired
    NBAppProperties nbAppProperties;

    @Override
    public RegDirectDeviceOutDTO regDirectDevice(String deviceId, String verifyCode) throws NorthApiException {
        RegDirectDeviceInDTO rddid = new RegDirectDeviceInDTO();
        rddid.setNodeId(deviceId);
        rddid.setVerifyCode(verifyCode);
        rddid.setTimeout(0);
        return NbAuthenUtil.getInstence(nbAppProperties).getDeviceManager().regDirectDevice(rddid, nbAppProperties.getAppId(), NbAuthenUtil.getInstence(nbAppProperties).getToken());
    }

    @Override
    public void deleteDevice(String deviceId) throws NorthApiException {
        NbAuthenUtil.getInstence(nbAppProperties).getDeviceManager().deleteDirectDevice(deviceId, nbAppProperties.getAppId(), NbAuthenUtil.getInstence(nbAppProperties).getToken());
    }

    @Override
    public void modifyDevice(DeviceProperties config) throws NorthApiException {
        ModifyDeviceInfoInDTO mdiid = new ModifyDeviceInfoInDTO();
        mdiid.setDeviceId(config.getDeviceId());
        mdiid.setName(config.getName());
        mdiid.setDeviceType(config.getDeviceType());
        mdiid.setManufacturerId(config.getManufacturerId());
        mdiid.setManufacturerName(config.getManufactureName());
        mdiid.setModel(config.getModel());
        NbAuthenUtil.getInstence(nbAppProperties).getDeviceManager().modifyDeviceInfo(mdiid, nbAppProperties.getAppId(),
                NbAuthenUtil.getInstence(nbAppProperties).getToken());
    }

    @Override
    public RefreshVerifyCodeOutDTO refreshVerifyCode(String deviceId, String newNodeId) throws NorthApiException {
        RefreshVerifyCodeInDTO rdsid = new RefreshVerifyCodeInDTO();
        RefreshVerifyCodeDTO request = new RefreshVerifyCodeDTO();
        rdsid.setDeviceId(deviceId);

        request.setNodeId(newNodeId);
        request.setVerifyCode(newNodeId);
        rdsid.setRequest(request);
        return NbAuthenUtil.getInstence(nbAppProperties).getDeviceManager().refreshDeviceSecret(rdsid, nbAppProperties.getAppId(), NbAuthenUtil.getInstence(nbAppProperties).getToken());
    }
}
