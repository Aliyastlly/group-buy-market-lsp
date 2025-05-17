package com.ali.types.design.framework.tree;

public interface StrategyHandler<T, D, R> {

    // 提供一个默认的、什么都不做的处理器实例
    // (T, D) -> null 是一个 lambda 表达式，实现了 apply 方法，直接返回 null
    StrategyHandler DEFAULT = (T, D) -> null;

    /**
     * 策略执行方法
     * @param requestParameter 入参，用于策略执行的特定参数
     * @param dynamicContext   动态上下文，策略执行可能需要的额外数据
     * @return R 策略执行后的返回结果
     * @throws Exception 异常
     */
    R apply(T requestParameter, D dynamicContext) throws Exception;

}
