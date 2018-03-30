package com.example.springbootshiro.domain;

import java.util.HashMap;

/**
 * Created by Douglee on 2018/3/30.
 */
public class ResponseResult extends HashMap<String, Object> {

    private static final long serialVersionUID = -556622754874885339L;

    private static final Integer SUCCESS = 0;
    private static final Integer WARN = 0;
    private static final Integer FAIL = 500;

    public ResponseResult() {
        put("code", SUCCESS);
        put("msg", "操作成功");
    }

    public static ResponseResult error(Object msg) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("msg", msg);
        responseResult.put("code", FAIL);

        return responseResult;
    }

    public static ResponseResult warn(Object msg) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("msg", msg);
        responseResult.put("code", WARN);

        return responseResult;
    }

    public static ResponseResult ok(Object msg) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("msg", msg);
        responseResult.put("code", SUCCESS);

        return responseResult;
    }

    public static ResponseResult ok() {
        return new ResponseResult();
    }

    @Override
    public ResponseResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
