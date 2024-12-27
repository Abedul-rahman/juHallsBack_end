package com.swproj.SWProject.projenums;

public enum Periodically {
    None(0),Sunday(1),Monday(2),Tuesday(3),Wednesday(4),Thursday(5);
    private int value;
    Periodically(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
