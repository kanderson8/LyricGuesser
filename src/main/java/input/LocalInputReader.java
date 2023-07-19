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
        return ImmutableSearchContext.builder().artist("Lana Del Rey").album("Honeymoon").genre("Pop").build();
    }

    @Override
    public Guess getGuess() {
        System.out.println("Enter guess:");
        return ImmutableGuess.builder().text(scanner.next()).build();
    }
}
