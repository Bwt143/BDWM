package com.josia50.TUDTardis.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {

    public enum ExteriorType implements IStringSerializable {

        POLICEBOX("Police Box",0),
        LONESHIP("Lone Ship", 1),
        LONESHIPORG("Original Lone Ship", 2),
        TIMEHYBRID("Time Hybrid", 3),
        LONDONPHONEBOOTH("Phone Booth", 4),
        BILLANDTEDS("Bill And Teds Phone Booth", 5);


        private String name;
        private int ID;

        ExteriorType(String name, int ID) {
            this.name = name;
            this.ID = ID;
        }

        @Override
        public String getName() {
            return name;
        }

        public int getID() {
            return ID;
        }
    }
}
