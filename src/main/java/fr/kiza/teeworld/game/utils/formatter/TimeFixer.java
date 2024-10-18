package fr.kiza.teeworld.game.utils.formatter;

public class TimeFixer {
    public static String formatTime(final int totalSeconds) {
        int
                hours = totalSeconds / 3600,
                minutes = (totalSeconds % 3600) / 60,
                seconds = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
