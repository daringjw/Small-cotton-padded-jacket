package com.jinkun.care.model.entity;

/**
 * @Created by coderwjq on 2017/8/18 14:03.
 * @Desc
 */

public class DamageEntity {
    private String damageName;
    private String damageTime;

    public DamageEntity(String damageName, String damageTime) {

        this.damageName = damageName;
        this.damageTime = damageTime;
    }

    public String getDamageName() {
        return damageName;
    }

    public void setDamageName(String damageName) {
        this.damageName = damageName;
    }

    public String getDamageTime() {
        return damageTime;
    }

    public void setDamageTime(String damageTime) {
        this.damageTime = damageTime;
    }
}
