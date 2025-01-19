package org.epf.hadoop.colfil3;

public class Recommendation {
    private final String user;
    private final int score;

    public Recommendation(String user, int score) {
        this.user = user;
        this.score = score;
    }

    public String getUser() {
        return user;
    }

    public int getScore() {
        return score;
    }
}
