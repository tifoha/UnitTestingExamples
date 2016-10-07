package UnitTestingExamples.ch06;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

/**
 * Created by Vitaly on 04.10.2016.
 */
public class BookAssert extends GenericAssert<BookAssert, Book> {
    protected BookAssert(Book actual) {
        super(BookAssert.class, actual);
    }

    public static BookAssert assertThat(Book book) {
        return new BookAssert(book);
    }

    public BookAssert hasTitle(String title) {
        isNotNull();
        String actualTitle = actual.getTitle();
        String errorMessage = String.format("Expected book's title <%s>, but was <%s>" , title, actualTitle);
        Assertions.assertThat(actual.getTitle())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(title);

        return this;
    }

}
