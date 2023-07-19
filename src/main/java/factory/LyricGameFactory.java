package factory;

import data.GameContext;
import game.LyricGame;

public class LyricGameFactory {
    public LyricGame create(GameContext gameContext) {
        return new LyricGame(gameContext);
    }
}
