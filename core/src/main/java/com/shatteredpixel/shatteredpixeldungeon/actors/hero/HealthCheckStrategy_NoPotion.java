package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;

// 【实现具体策略类】无药水策略
public class HealthCheckStrategy_NoPotion implements HealthCheckStrategy {
    @Override
    public boolean checkCondition(Hero hero) {
        return hero.HP < 10 && !hasHealingPotion(hero);
    }

    @Override
    public void executeAction(Hero hero) {
        // 可以在这里添加额外逻辑
    }

    @Override
    public String getMessage() {
        return "No healing potion";
    }

    private boolean hasHealingPotion(Hero hero) {
        for (Item item : hero.belongings.backpack.items) {
            if (item instanceof PotionOfHealing) {
                return true;
            }
        }
        return false;
    }
}