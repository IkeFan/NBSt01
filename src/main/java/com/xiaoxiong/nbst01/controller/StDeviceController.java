package com.xiaoxiong.nbst01.controller;
import com.huawei.iotplatform.client.NorthApiException;
import com.xiaoxiong.nbst01.Global;
import com.xiaoxiong.nbst01.common.ErrorCode;
import com.xiaoxiong.nbst01.config.DeviceProperties;
import com.xiaoxiong.nbst01.config.NBAppProperties;
import com.xiaoxiong.nbst01.net.RestResult;
import com.xiaoxiong.nbst01.net.RestResultGenerator;
import com.xiaoxiong.nbst01.net.requestparam.RegDeviceParam;
import com.xiaoxiong.nbst01.service.NbDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Ike.Fan
 * @date: 2018/5/22.
 */
@RestController
@RequestMapping(Global.API_URL_DEVICE)
public class StDeviceController {
    @Autowired
    DeviceProperties deviceProperties;
    @Autowired
    NbDeviceService nbDeviceService;
    @Autowired
    NBAppProperties nbAppProperties;

    @PostMapping("/register")
    public RestResult<Boolean> regDevice(@RequestBody RegDeviceParam regDeviceParam ){
        try {
            nbDeviceService.regDirectDevice(regDeviceParam.deviceId, regDeviceParam.deviceId);
            deviceProperties.setDeviceId(regDeviceParam.deviceId);
            nbDeviceService.modifyDevice(deviceProperties);
        } catch (NorthApiException e) {
            e.printStackTrace();
            RestResultGenerator.genErrorResult(ErrorCode.ACCOUNT_OR_PASSWORD_NULL);
        }

        return RestResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public RestResult<Boolean> deleteDevice(@RequestParam("deviceId") String deviceId){
        try {
            nbDeviceService.deleteDevice(deviceId);
        } catch (NorthApiException e) {
            e.printStackTrace();
        }
        return RestResultGenerator.genSuccessResult();
    }

    @PostMapping("/id/add")
    public RestResult<Boolean> addDeviceId(@RequestParam("deviceId") String qrcId){

        return RestResultGenerator.genSuccessResult();
    }
}
