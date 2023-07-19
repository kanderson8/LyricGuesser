package app;

import dagger.AppComponent;
import dagger.DaggerAppComponent;
import game.GameOrchestrator;

public class App {

    public static void main(String[] args) {
        AppComponent appComponent = DaggerAppComponent.create();
        GameOrchestrator gameOrchestrator = appComponent.getGame();
        gameOrchestrator.playGame();
    }
}
