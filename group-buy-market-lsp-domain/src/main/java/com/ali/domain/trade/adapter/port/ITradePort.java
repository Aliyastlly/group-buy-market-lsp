package com.ali.domain.trade.adapter.port;

import com.ali.domain.trade.model.entity.NotifyTaskEntity;

public interface ITradePort {
    String groupBuyNotify(NotifyTaskEntity notifyTask) throws Exception;
}
