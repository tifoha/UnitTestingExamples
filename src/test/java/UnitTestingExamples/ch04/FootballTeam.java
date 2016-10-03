package UnitTestingExamples.ch04;

/**
 * Created by Vitaly on 30.09.2016.
 */
public class FootballTeam implements Comparable<FootballTeam>{
    private int gamesWon;

    public FootballTeam(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    @Override
    public int compareTo(FootballTeam other) {
        if (this.gamesWon > other.gamesWon) {
            return 1;
        }
        if (this.gamesWon < other.gamesWon) {
            return -1;
        }
        return 0;
    }
}
