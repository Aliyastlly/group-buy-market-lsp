package com.ali.api;

import com.ali.api.dto.GoodsMarketRequestDTO;
import com.ali.api.dto.GoodsMarketResponseDTO;
import com.ali.api.response.Response;

public interface IMarketIndexService {
    Response<GoodsMarketResponseDTO> queryGroupBuyMarketConfig(GoodsMarketRequestDTO goodsMarketRequestDTO);
}
