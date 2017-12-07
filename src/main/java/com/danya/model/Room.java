package com.danya.model;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private Size size;
    private boolean seaViewable;
    private boolean freeMinibar;
    private String description;

    public enum Size {
        SMALL, MEDIUM, LARGE;

        private static Map<Integer, Size> map = new HashMap<>();
        static {
            for (Size size : Size.values()) {
                map.put(size.ordinal(), size);
            }
        }
        public static Size valueOf(int n) {
           return map.get(n);
        }
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public boolean isSeaViewable() {
        return seaViewable;
    }

    public void setSeaViewable(boolean seaViewable) {
        this.seaViewable = seaViewable;
    }

    public boolean isFreeMinibar() {
        return freeMinibar;
    }

    public void setFreeMinibar(boolean freeMinibar) {
        this.freeMinibar = freeMinibar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
