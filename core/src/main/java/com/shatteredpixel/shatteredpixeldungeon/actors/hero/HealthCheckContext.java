package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import java.util.ArrayList;
import java.util.List;

// 【策略上下文】执行策略检查
public class HealthCheckContext {
    private final List<HealthCheckStrategy> strategies;

    /**
     * 构造函数
     * @param strategies 策略列表
     */
    public HealthCheckContext(List<HealthCheckStrategy> strategies) {
        this.strategies = strategies;
    }

    /**
     * 添加新策略
     * @param strategy 要添加的策略
     */
    public void addStrategy(HealthCheckStrategy strategy) {
        strategies.add(strategy);
    }

    /**
     * 执行健康检查
     * @param hero 游戏角色
     */
    public void checkAndExecute(Hero hero) {
        for (HealthCheckStrategy strategy : strategies) {
            if (strategy.checkCondition(hero)) {
                strategy.executeAction(hero);
                GLog.w(strategy.getMessage());
                GLog.newLine();  // 添加空行
                break; // 只执行第一个匹配的策略
            }
        }
    }
}