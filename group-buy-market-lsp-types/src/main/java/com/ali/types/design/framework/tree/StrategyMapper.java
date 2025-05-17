package com.ali.types.design.framework.tree;

public interface StrategyMapper<T, D, R> {

    /**
     * 获取待执行策略
     *
     * @param requestParameter 入参，用于决定选择哪个策略
     * @param dynamicContext   上下文，可能也参与策略的选择
     * @return StrategyHandler<T, D, R> 返回找到的策略处理器
     * @throws Exception 异常
     */
    StrategyHandler<T, D, R> get(T requestParameter, D dynamicContext) throws Exception;

}