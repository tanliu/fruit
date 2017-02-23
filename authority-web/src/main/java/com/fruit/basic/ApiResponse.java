/**
 * @(#)ApiResponse.java, Dec 15, 2015. 
 * 
 * Created by Hoswey
 * 
 * Copyright 2015 Feigong, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.fruit.basic;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;


@SuppressWarnings("serial")
public class ApiResponse extends HashMap<String, Object> {

    public static final ApiResponse SUCC = ApiResponse.newBuilder().succ().build();

    public static final ApiResponse UNKNOW_ERROR = ApiResponse.newBuilder().unknowError().build();

    private int code;

    private String message;

    private Map<String, Object> data = Maps.newHashMap();

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public static class Builder {

        private int code = 1;

        private String message = "";

        private Map<String, Object> data = Maps.newHashMap();

        public Builder succ() {
            this.code = 1;

            return this;
        }

//        public Builder invalidParam() {
//            this.code = ExpCode.INVALID_PARAM.getCode();
//
//            return this;
//        }
//
//        public Builder notLogin() {
//            this.code = ExpCode.NOT_LOGIN.getCode();
//
//            return this;
//        }

        public Builder unknowError() {

            this.code = 0;

            return this;
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {

            this.message = message;
            return this;
        }

        public Builder node(String key, Object value) {
            this.data.put(key, value);
            return this;
        }

        public ApiResponse build() {
            this.data.put("code", code);
            this.data.put("message", message);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.putAll(data);

            return apiResponse;
        }
    }

}
