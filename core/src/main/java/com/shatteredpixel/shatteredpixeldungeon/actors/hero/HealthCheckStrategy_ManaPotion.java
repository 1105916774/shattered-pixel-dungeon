//package com.shatteredpixel.shatteredpixeldungeon.actors.hero;
//
//import com.shatteredpixel.shatteredpixeldungeon.items.Item;
//import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
//
//// 【实现具体策略类】
//public class HealthCheckStrategy_ManaPotion implements HealthCheckStrategy {
//    @Override
//    public boolean checkCondition(Hero hero) {
//        return hero.MP < 5 && hasManaPotion(hero);
//    }
//
//    @Override
//    public void executeAction(Hero hero) {
//        // 魔法值低时的特殊逻辑
//    }
//
//    @Override
//    public String getMessage() {
//        return "Warning: 魔法值低！考虑使用魔法药水。";
//    }
//
//    private boolean hasManaPotion(Hero hero) {
//        for (Item item : hero.belongings.backpack.items) {
//            if (item instanceof ManaPotion) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
