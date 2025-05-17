package com.ali.types.design.framework.tree;
import lombok.Getter;
import lombok.Setter;


public abstract class AbstractStrategyRouter<T, D, R> implements StrategyMapper<T, D, R>, StrategyHandler<T, D, R> {

    // Lombok 注解，自动生成 getter 和 setter
    @Getter
    @Setter
    // 默认的策略处理器，初始化为上面定义的 DEFAULT 处理器
    protected StrategyHandler<T, D, R> defaultStrategyHandler = StrategyHandler.DEFAULT;

    // 这是提供给外部调用的核心路由方法
    public R router(T requestParameter, D dynamicContext) throws Exception {
        // 1. 获取策略：调用 get 方法（这个 get 方法由子类实现）
        StrategyHandler<T, D, R> strategyHandler = get(requestParameter, dynamicContext);

        // 2. 执行策略：
        if (null != strategyHandler) {
            // 如果找到了具体的策略处理器，就调用它的 apply 方法
            return strategyHandler.apply(requestParameter, dynamicContext);
        }
        // 如果没找到（get 返回 null），就执行默认的策略处理器
        return defaultStrategyHandler.apply(requestParameter, dynamicContext);
    }
}