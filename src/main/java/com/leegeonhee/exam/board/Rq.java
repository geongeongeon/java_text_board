package com.leegeonhee.exam.board;

import java.util.Map;

import java.util.Map;

public class Rq {
    public String url;
    public Map<String, String> params;
    public String urlPath;

    Rq(String url) {
        this.url = url;
        params = Util.getParamsFromUrl(this.url);
        urlPath = Util.getUrlPathFromUrl(this.url);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public int getIntParam(String paramName, int defaultValue) {
        if (params.containsKey(paramName) == false) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(params.get(paramName));
        } catch (NumberFormatException e) {
            return defaultValue;
        }

    }

    public String getParam(String paramName, String defaultValue) {
        if(params.containsKey(paramName) == false) {
            return defaultValue;
        }

        return params.get(paramName);
    }
}