package fr.kiza.teeworld.game.client.status;

public enum  GameState {

    MENU, PLAY, SETTINGS, PAUSE, LOSE, QUIT;

    private static GameState currentState;

    public static GameState getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(final GameState newState) {
        currentState = newState;
    }
}
