package buontyhunter.model;

import buontyhunter.common.Point2d;

public class PongObject {
    private Point2d position;
    private final double height;
    private final double width;
    private double speed;

    public PongObject(Point2d position, double height, double width, double speed) {
        this.position = position;
        this.height = height;
        this.width = width;
        this.speed = speed;
    }

    public Point2d getPosition() {
        return this.position;
    }

    public void setPosition(Point2d position) {
        this.position = position;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        if (speed > 0) {
            this.speed = speed;
        }
    }

    public double getUpperBound() {
        return this.position.y - (this.height / 2);
    }

    public double getLowerBound() {
        return this.position.y + (this.height / 2);
    }

    public double getLeftBound() {
        return this.position.x - (this.width / 2);
    }

    public double getRightBound() {
        return this.position.x + (this.width / 2);
    }
}
