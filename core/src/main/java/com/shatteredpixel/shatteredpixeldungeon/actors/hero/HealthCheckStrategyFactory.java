package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import java.util.ArrayList;
import java.util.List;

// 【策略工厂】创建和管理所有健康检查策略
public class HealthCheckStrategyFactory {

    /**
     * 创建默认策略集合
     * @return 包含所有默认策略的列表
     */
    public static List<HealthCheckStrategy> createDefaultStrategies() {
        List<HealthCheckStrategy> strategies = new ArrayList<>();

        // 添加治疗药水策略
        strategies.add(createHealingPotionStrategy());

        // 添加无药水策略
        strategies.add(createNoPotionStrategy());

        // 可以继续添加其他策略...
        // strategies.add(createManaPotionStrategy());

        return strategies;
    }

    /**
     * 创建治疗药水策略
     * @return 治疗药水策略实例
     */
    private static HealthCheckStrategy createHealingPotionStrategy() {
        return new HealthCheckStrategy() {
            @Override
            public boolean checkCondition(Hero hero) {
                return hero.HP < 10 && hasHealingPotion(hero);
            }

            @Override
            public void executeAction(Hero hero) {
                // 可以添加额外操作，如闪烁UI提示
            }

            @Override
            public String getMessage() {
                return "Warning: 生命值低！考虑使用治疗药水。";
            }

            private boolean hasHealingPotion(Hero hero) {
                for (Item item : hero.belongings.backpack.items) {
                    if (item instanceof PotionOfHealing) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**
     * 创建无药水策略
     * @return 无药水策略实例
     */
    private static HealthCheckStrategy createNoPotionStrategy() {
        return new HealthCheckStrategy() {
            @Override
            public boolean checkCondition(Hero hero) {
                return hero.HP < 10 && !hasHealingPotion(hero);
            }

            @Override
            public void executeAction(Hero hero) {
                // 可以添加额外操作
            }

            @Override
            public String getMessage() {
                return "不存在 治疗药水";
            }

            private boolean hasHealingPotion(Hero hero) {
                for (Item item : hero.belongings.backpack.items) {
                    if (item instanceof PotionOfHealing) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    // 可以继续添加其他策略的创建方法...
    /*
    private static HealthCheckStrategy createManaPotionStrategy() {
        return new HealthCheckStrategy() {
            // 实现魔法药水检查逻辑
        };
    }
    */
}