package com.ali.api;

import com.ali.api.response.Response;

public interface IDCCService {
    Response<Boolean> updateConfig(String key, String value);
}
