package dagger;

import game.GameOrchestrator;

@Component(modules = GameModule.class)
public interface AppComponent {
    GameOrchestrator getGame();
}
