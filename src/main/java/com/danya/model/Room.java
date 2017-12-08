package com.danya.model;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private long id;
    private Size size;
    private boolean seaViewable;
    private boolean freeMinibar;
    private String enDescription;
    private String ruDescription;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEnDescription() {
        return enDescription;
    }

    public void setEnDescription(String enDescription) {
        this.enDescription = enDescription;
    }

    public String getRuDescription() {
        return ruDescription;
    }

    public void setRuDescription(String ruDescription) {
        this.ruDescription = ruDescription;
    }
}
