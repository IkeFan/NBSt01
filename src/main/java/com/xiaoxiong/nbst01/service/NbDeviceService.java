package com.xiaoxiong.nbst01.service;

import com.huawei.iotplatform.client.NorthApiException;
import com.huawei.iotplatform.client.dto.RefreshVerifyCodeOutDTO;
import com.huawei.iotplatform.client.dto.RegDirectDeviceOutDTO;
import com.xiaoxiong.nbst01.config.DeviceProperties;

/**
 * @author: Ike.Fan
 * @date: 2018/5/24.
 */
public interface NbDeviceService {
    RegDirectDeviceOutDTO regDirectDevice(String deviceId, String verifyCode) throws NorthApiException;
    void deleteDevice(String deviceId) throws NorthApiException;
    void modifyDevice(DeviceProperties config) throws NorthApiException;
    RefreshVerifyCodeOutDTO refreshVerifyCode(String deviceId, String newNodeId) throws NorthApiException;
}
