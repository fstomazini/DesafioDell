package conversorGalatico;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Mapeamento {
    Calculadora calc = new Calculadora();

    public void mapeamentoValor(String frase, Map<String, Integer> valores) { // Armazena o Simbolo do algarismo Galatico e seu valor em algaritimos arabicos

        String[] array = frase.split(" representa ");
        valores.put(array[0], Conversor.conversor(array[1]));

    }
    public void mapeamentoProduto(String frase, Map<String, Integer> valores,Map<String, Double> produtos) { // Responsavel por armazenar os nomes dos metais vendidos e seus respectivos valores

        String[] array = frase.split(" valem ");
        int credito = Integer.parseInt(array[1].replace(" créditos", ""));
        String[] nomes = array[0].split(" ");
        String metal = nomes[nomes.length - 1];
        List<Integer> pesos = new ArrayList<>();
        for (String palavra : nomes) {
            if (valores.containsKey(palavra)) {
                pesos.add(valores.get(palavra));

            }
        }

        Double valor = calc.somador(pesos);

        produtos.put(metal, (credito / valor));

    }
}
