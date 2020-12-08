package javabean;

public enum Difficulty {
    EASY,
    NORMAL,
    HARD;

    public static Difficulty fromInt(int num) {
        switch (num) {
            case 0:
                return EASY;
            case 1:
                return NORMAL;
            case 2:
                return HARD;

            default:
                return null;
        }
    }
}
