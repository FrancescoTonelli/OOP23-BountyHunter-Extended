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

    /**
     * Returns the PongObject position
     * 
     * @return the position
     */
    public Point2d getPosition() {
        return this.position;
    }

    /**
     * Sets the position of the PongObject to the indicated one
     * 
     * @param position the position to set
     */
    public void setPosition(Point2d position) {
        this.position = position;
    }

    /**
     * Returns the PongObject height
     * 
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the PongObject width
     * 
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the PongObject speed
     * 
     * @return the speed
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Sets the speed of the PongObject to the indicated value (if it is > 0)
     * 
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        if (speed > 0) {
            this.speed = speed;
        }
    }

    /**
     * Returns the upped bound coordinate
     * 
     * @return the coordinate
     */
    public double getUpperBound() {
        return this.position.y;
    }

    /**
     * Returns the lower bound coordinate
     * 
     * @return the coordinate
     */
    public double getLowerBound() {
        return this.position.y + this.height;
    }

    /**
     * Returns the left bound coordinate
     * 
     * @return the coordinate
     */
    public double getLeftBound() {
        return this.position.x;
    }

    /**
     * Returns the right bound coordinate
     * 
     * @return the coordinate
     */
    public double getRightBound() {
        return this.position.x + this.width;
    }
}
