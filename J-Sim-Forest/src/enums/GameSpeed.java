package enums;

public enum GameSpeed {

    VERYSLOW(800, "V. Slow"),
    SLOW(400, "Slow"),
    MEDIUM(200, "Medium"),
    FAST(100, "Fast"),
    VERYFAST(50, "V. Fast");

    private int speed; // milliseconds
    private String speedLabel;

    GameSpeed(int speed, String label) {
        this.speed = speed;
        this.speedLabel = label;
    }

    public int toValue() {
        return speed;
    }

    public String getLabel() {
        return speedLabel;
    }
}
