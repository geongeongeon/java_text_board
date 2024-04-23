package com.leegeonhee.exam.board;

import java.util.Map;

class Rq {
    String url;
    Map<String, String> params;
    String urlPath;

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
}
