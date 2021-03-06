package com.vbobot.common.rpc.utils;

import com.vbobot.common.rpc.RpcResult;
import com.vbobot.common.utils.enums.ResultCode;
import com.vbobot.common.utils.web.vo.CommonResult;

import java.io.Serializable;

/**
 * Utils for RpcResult
 * Created by Liang.Zhuge on 02/05/2017.
 */
public class RpcResultUtils {

    public static <T extends Serializable> CommonResult<T> toCommonResult(RpcResult rpcResult, T t) {
        if (rpcResult == null) {
            return new CommonResult<>(ResultCode.ERROR, "Unknown error");
        }

        final CommonResult<T> tCommonResult = new CommonResult<>(rpcResult.getResult(), rpcResult.getMsg());
        if (!rpcResult.isSuccess()) {
            return tCommonResult;
        }

        tCommonResult.setData(t);
        return tCommonResult;
    }
}
