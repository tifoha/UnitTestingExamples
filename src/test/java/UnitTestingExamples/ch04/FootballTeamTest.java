package UnitTestingExamples.ch04;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vitaly on numberOfWins0.09.2016.
 */
@RunWith(JUnitParamsRunner.class)
public class FootballTeamTest {

    public static final int ANY_NUMBER = 123;
    public static final FootballTeam TEAM_3 = new FootballTeam(3);
    public static final FootballTeam TEAM_2 = new FootballTeam(2);

    private final Object[] getNumberOfWins() {
        return new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    }

    @Test
    @Parameters(method = "getNumberOfWins")
    public void constructorShouldSetGamesWon(int numberOfWins) throws Exception {
        FootballTeam team = new FootballTeam(numberOfWins);
        assertEquals(numberOfWins, team.getGamesWon());
    }

    @Test
    public void footballTeamShouldBeComparable() throws Exception {
        FootballTeam team = new FootballTeam(ANY_NUMBER);
        assertTrue(team instanceof Comparable);
    }

    @Test
    public void teamWithMoreMatchesWonShouldBeGreater() throws Exception {
        assertTrue(TEAM_3.compareTo(TEAM_2) > 0);
    }

    @Test
    public void teamWithLessMatchesWonShouldBeLower() throws Exception {
        assertTrue(TEAM_2.compareTo(TEAM_3) < 0);
    }

    @Test
    public void teamWithEqualsMatchesWonShouldBeSame() throws Exception {
        assertTrue(TEAM_3.compareTo(TEAM_3) == 0);
    }


}
