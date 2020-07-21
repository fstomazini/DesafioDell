package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Calculadora {

    public Double calculaCreditos(String[] frase, Map<String, Integer> valores ,Map<String, Double> produtos) { // calcula quantos creditos custam uma quantidade x de um metal especifico

        String metal = frase[frase.length - 1];
        List<Integer> pesos = new ArrayList<>();
        for (String palavra : frase) {
            if (valores.containsKey(palavra)) {
                pesos.add(valores.get(palavra));

            }
        }
        if (pesos.isEmpty()) {
            throw new RuntimeException("Valor de Numerico fora do Sistema de convers�o");
        }
        Double valor = somador(pesos);

        Double preco = valor * produtos.get(metal);

        return preco;

    }

    public Double somador(List<Integer> valores) { //  funcção responsavel por Somar dois numeros em Algarismos romanos
        Double soma = 0.0;
        int contadorRepeticao = 1;
        for (int i = 0; i < valores.size(); i++) {
            if (i+1<valores.size()&& valores.get(i) < valores.get(i + 1)) {
                if ((valores.get(i + 1)/valores.get(i) == 5) || valores.get(i + 1)/valores.get(i) == 10) {
                    soma += valores.get(i + 1) - valores.get(i);
                    i++;

                } else {
                    throw new RuntimeException("Valor de Numerico fora do Sistema de convers�o");
                }
            }

            else {
                if (i+1<valores.size() && valores.get(i) == valores.get(i + 1)) {
                    contadorRepeticao++;
                } else {
                    contadorRepeticao = 1;

                }
                soma += valores.get(i);
                if (contadorRepeticao > 3) {
                    throw new RuntimeException("Valor de Numerico fora do Sistema de convers�o");
                }
            }
        }
        return (soma);
    }
    public Double calculaValor(String[] frase, Map<String, Integer> valores) { // responsavel pelo calculo do valor em creditos de saida de um metal

        List<Integer> pesos = new ArrayList<>();
        for (String palavra : frase) {
            if (valores.containsKey(palavra)) {
                pesos.add(valores.get(palavra));

            }
        }
        if (pesos.isEmpty()) {
            throw new RuntimeException("Valor de Numerico fora do Sistema de convers�o");
        }
        return somador(pesos);

    }
}
