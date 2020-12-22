package com.yznu.qinggua.utils;

public class ResponseUtil {
    public static Result success(Object object) {
        Result jsonResult = new Result();
        jsonResult.setData(object);
        jsonResult.setCode(200);
        jsonResult.setMessage("success");
        return jsonResult;
    }

    public static Result success(Object object, Integer code, String message) {
        Result jsonResult = new Result();
        jsonResult.setData(object);
        jsonResult.setCode(code);
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String message) {
        Result jsonResult = new Result();
        jsonResult.setCode(code);
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public static Result success(Integer code, String message) {
        Result jsonResult = new Result();
        jsonResult.setCode(code);
        jsonResult.setMessage(message);
        return jsonResult;
    }
}
