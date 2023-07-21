package input;

import data.Guess;
import data.ImmutableGuess;
import data.ImmutableSearchContext;
import data.SearchContext;

import java.util.Scanner;

public class LocalInputReader implements InputReader {
    private final Scanner scanner;

    public LocalInputReader() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public SearchContext getSearchContext() {
        System.out.println("Search by artist:");
        return ImmutableSearchContext.builder().artist(scanner.nextLine()).build();
    }

    @Override
    public Guess getGuess() {
        System.out.println("Enter guess:");
        return ImmutableGuess.builder().text(scanner.next()).build();
    }
}
