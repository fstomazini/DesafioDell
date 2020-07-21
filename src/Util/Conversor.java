package Util;

public class Conversor {

    public static int conversor(String palavra) { //Converte Valor em algarismo romano em algarismos arabicos
        int valor = 0;
        switch (palavra) {
            case "I":
                valor = 1;
                break;
            case "V":
                valor = 5;
                break;
            case "X":
                valor = 10;
                break;
            case "L":
                valor = 50;
                break;
            case "C":
                valor = 100;
                break;
            case "D":
                valor = 500;
                break;
            case "M":
                valor = 1000;
                break;

        }
        return (valor);
    }
}