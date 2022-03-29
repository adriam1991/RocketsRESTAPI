package com.adriamunoz.rockets.rocketsrestapi;

public class Movement {
    public static final int ACCELERATE = 1;
    public static final int DECELERATE = 2;

    private int type;
    private int movementXTimes;

    public Movement() {
    }

    public Movement(int type, int movementXTimes) {
        this.type = type;
        this.movementXTimes = movementXTimes;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMovementXTimes() {
        return movementXTimes;
    }

    public void setMovementXTimes(int movementXTimes) {
        this.movementXTimes = movementXTimes;
    }
}
