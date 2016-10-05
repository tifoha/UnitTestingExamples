package UnitTestingExamples.ch06;

import org.junit.Test;

import java.util.Collections;

import static UnitTestingExamples.ch06.BookAssert.assertThat;


/**
 * Created by Vitaly on 04.10.2016.
 */
public class CustomMarchers {
    @Test
    public void bookShouldHaveTitle() throws Exception {
        Book book = new Book();
        book.setTitle("title");
        assertThat(book)
                .hasTitle("title")
                .isNotIn(Collections.singleton(null));

    }
}
