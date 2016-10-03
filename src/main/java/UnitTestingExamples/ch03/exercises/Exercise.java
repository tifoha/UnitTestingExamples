package UnitTestingExamples.ch03.exercises;

import javax.print.event.PrintEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitaly on 29.09.2016.
 */
public class Exercise {
    public static String reverse(String s) {

//        List<String> tempArray = new ArrayList<String>(s.length());
//
//        for (int i = 0; i < s.length(); i++) {
//            tempArray.add(s.substring(i, i + 1));
//        }
//
//        StringBuilder reversedString = new StringBuilder(s.length());
//
//        for (int i = tempArray.size() - 1; i >= 0; i--) {
//            reversedString.append(tempArray.get(i));
//        }

        StringBuilder reversedString = new StringBuilder(s)
                .reverse();
        return reversedString.toString();
    }
}
