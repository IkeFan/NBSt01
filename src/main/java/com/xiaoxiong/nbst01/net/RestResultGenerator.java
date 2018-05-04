package com.xiaoxiong.nbst01.net;
import com.xiaoxiong.nbst01.common.AppException;
import com.xiaoxiong.nbst01.common.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 生成restResult工具类
 * Created by Administrator on 2017/2/23 0023.
 */
public class RestResultGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestResultGenerator.class);

    /**
     * normal
     *
     * @param success
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genResult(boolean success, T data, int code, String message) {
        RestResult<T> result = RestResult.newInstance();
        result.setResult(success);
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("generate rest result:{}", result);
        }
        return result;
    }

    /**
     * success
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genSuccessResult(T data) {

        return genResult(true, data, ErrorCode.RESULT_SUCCESS.getCode(), null);
    }

    /**
     * error message
     *
     * @param message error message
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genErrorResult(int code, String message) {

        return genResult(false, null, code, message);
    }

    /**
     * errorCode message
     *
     * @param errorCode error message
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genErrorResult(ErrorCode errorCode) {

        return genResult(false, null, errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * error
     *
     * @param error error enum
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genErrorResult(AppException error) {

        return genErrorResult(error.getCode(), error.getMessage());
    }

    /**
     * success no message
     *
     * @return
     */
    public static RestResult genSuccessResult() {
        return genSuccessResult(null);
    }


}
