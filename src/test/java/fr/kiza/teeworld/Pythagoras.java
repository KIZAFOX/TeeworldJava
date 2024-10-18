package fr.kiza.teeworld;

public class Pythagoras {

    /**
     * D'après le théorème de Pythagore :
     * C² = A² + B²
     *
     */
    public static void main(String[] args) {
        findA(7.615, 3);
        findB(7.615, 7);
        findC(2, 2);
    }

    public static void findA(double c, double b){
        int square = 2;
        double
                cSquare = Math.pow(c, square), // C²= value²
                bSquare = Math.pow(b, square), // B² = value²
                aSquare = cSquare - bSquare; // A² = C²-B²

        double squareRoot = Math.sqrt(aSquare); //sqrt = √()

        System.out.println("A=" + squareRoot);
        System.out.println("A=" + Math.round(squareRoot));
    }

    public static void findB(double c, double a){
        int square = 2;
        double
                cSquare = Math.pow(c, square), // C²= value²
                aSquare = Math.pow(a, square), // A²= value²
                bSquare = cSquare - aSquare; // B² = C²-A²

        double squareRoot = Math.sqrt(bSquare); //sqrt = √()

        System.out.println("B=" + squareRoot);
        System.out.println("B=" + Math.round(squareRoot));
    }

    public static void findC(double a, double b){
        int square = 2;
        double
                aSquare = Math.pow(a, square), // A²= value²
                bSquare = Math.pow(b, square), // B²= value²
                cSquare = aSquare + bSquare; // C²= A²+B²

        double squareRoot = Math.sqrt(cSquare); //sqrt = √()

        System.out.println("C=" + squareRoot);
        System.out.println("C=" + Math.round(squareRoot));
    }
}
