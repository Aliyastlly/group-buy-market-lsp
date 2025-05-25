package com.ali.api;

import com.ali.api.dto.LockMarketPayOrderRequestDTO;
import com.ali.api.dto.LockMarketPayOrderResponseDTO;
import com.ali.api.response.Response;

public interface IMarketTradeService {
    Response<LockMarketPayOrderResponseDTO> lockMarketPayOrder(LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO);

}
