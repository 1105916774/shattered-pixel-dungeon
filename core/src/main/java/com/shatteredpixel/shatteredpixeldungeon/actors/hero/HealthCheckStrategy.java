package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

// 【策略接口】定义所有药水检查策略必须实现的方法
public interface HealthCheckStrategy {
    /**
     * 检查条件是否满足
     * @param hero 游戏角色
     * @return 是否满足条件
     */
    boolean checkCondition(Hero hero);

    /**
     * 执行对应操作
     * @param hero 游戏角色
     */
    void executeAction(Hero hero);

    /**
     * 获取提示消息
     * @return 提示消息字符串
     */
    String getMessage();
}