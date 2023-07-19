package game;

import data.GameContext;
import data.Guess;
import data.Slot;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class LyricGame {
    private final List<Slot> slots;
    private final Map<String, List<Integer>> wordsToIndex;
    private final Instant startTime;
    private Instant endTime;
    private int totalGuesses;
    private int correctGuesses;

    public LyricGame(GameContext gameContext) {
        List<String> lyrics = gameContext.getSearchResults().getLyrics();
        this.slots = new ArrayList<>(lyrics.size());
        this.wordsToIndex = new HashMap<>(lyrics.size());

        for (int i = 0; i < lyrics.size(); i++) {
            //remove leading and trailing punctuation and whitespace and send to lowercase
            String cleanedString = clean(lyrics.get(i));
            this.slots.add(new Slot(cleanedString));
            List<Integer> indexes = this.wordsToIndex.getOrDefault(cleanedString, new ArrayList<>());
            indexes.add(i);
            this.wordsToIndex.put(cleanedString, indexes);
        }

        this.correctGuesses = 0;
        this.totalGuesses = 0;
        this.startTime = Instant.now();
    }

    public boolean isComplete() {
        if (wordsToIndex.isEmpty() && this.endTime == null) {
            this.endTime = Instant.now();
        }
        return wordsToIndex.isEmpty();
    }

    public void process(Guess guess) {
        this.totalGuesses++;

        String cleanedGuess = clean(guess.getText());
        if (wordsToIndex.containsKey(cleanedGuess)) {
            this.correctGuesses++;
            List<Integer> indexes = wordsToIndex.get(cleanedGuess);
            for (Integer index : indexes) {
                this.slots.get(index).setGuessed(true);
            }
            wordsToIndex.remove(cleanedGuess);
        }
    }

    public void display() {
        if (!isComplete()) {
            for (Slot slot : this.slots) {
                if (slot.isGuessed()) {
                    System.out.print(slot.getValue() + " ");
                } else {
                    System.out.print("? ");
                }
            }
            System.out.println();
        }
    }

    public void displayStats() {
        long seconds = Duration.between(startTime, endTime).toSeconds();
        System.out.printf("You won! Congrats. Guess stats: %d/%d, Time: %d seconds", correctGuesses, totalGuesses, seconds);

    }

    private String clean(String s) {
        return s.replaceAll("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "").trim().toLowerCase(Locale.ROOT);
    }

}
