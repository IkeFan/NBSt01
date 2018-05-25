package com.xiaoxiong.nbst01.controller;

import com.xiaoxiong.nbst01.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Ike.Fan
 * @date: 2018/5/4.
 */
@RestController
@RequestMapping(Global.API_NB_SUBSCRIBE)
public class NBNotifyController {
    Logger mLogger = LoggerFactory.getLogger(NBNotifyController.class);
    @PostMapping("/report")
    public ResponseEntity<HttpStatus> onDeviceReport(@RequestBody Object object){
        mLogger.error("NB report:" + object);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> onDeviceRegister(@RequestBody Object object){
        mLogger.error("NB register:" + object);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<HttpStatus> onDeviceDelete(@RequestBody Object object){
        mLogger.error("NB delete:" + object);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/bind")
    public ResponseEntity<HttpStatus> onDeviceBind(@RequestBody Object object){
        mLogger.error("NB delete:" + object);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus>  onDeviceAdd(@RequestBody Object object){
        mLogger.error("NB delete:" + object);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/change")
    public ResponseEntity<HttpStatus> onDeviceChange(@RequestBody Object data){
        mLogger.error("NB change:"+data);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
