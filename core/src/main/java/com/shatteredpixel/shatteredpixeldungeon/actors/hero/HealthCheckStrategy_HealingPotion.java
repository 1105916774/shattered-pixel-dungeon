package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

// 【实现具体策略类】治疗药水策略
public class HealthCheckStrategy_HealingPotion implements HealthCheckStrategy {
    @Override
    public boolean checkCondition(Hero hero) {
        return hero.HP < 10 && hasHealingPotion(hero);
    }

    @Override
    public void executeAction(Hero hero) {
        // 可以在这里添加额外逻辑，如闪烁提示等
    }

    @Override
    public String getMessage() {
        return "Warning: Low health! Using healing potion.";
    }

    // 判断治疗药水是否存在
    private boolean hasHealingPotion(Hero hero) {
        for (Item item : hero.belongings.backpack.items) {
            if (item instanceof PotionOfHealing) {
//                GLog.w("存在 治疗药水\n");
                return true;
            }
        }
        return false;
    }
}