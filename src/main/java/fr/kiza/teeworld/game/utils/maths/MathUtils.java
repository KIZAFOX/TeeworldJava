package fr.kiza.teeworld.game.utils.maths;

public class MathUtils {

    /**
     * Adjust the speed of entity in diagonal by using Pythagoras theorem.
     * This function reduce the speed on X and Y axes to keep the total speed.
     *
     * @param speedX Speed on X axe (horizontal)
     * @param speedY Speed on Y axe (vertical)
     *
     * @return Table of [newSpeedX, new SpeedY] with adjusted speeds for X and Y.
     *
     */
    public static float[] adjustSpeed(final float speedX, final float speedY){
        if(speedX != 0 && speedY != 0){
            final double factor = 1 / Math.sqrt(2);
            return new float[] {(float) (speedX * factor), (float) (speedY * factor)};
        }

        return new float[] {speedX, speedY};
    }
}
