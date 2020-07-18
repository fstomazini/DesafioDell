package conversorGalatico;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tradutor {
	private Map<String, Integer> valores = new HashMap<>(); //Armazena os Simbolos e valores usados nas transações galaticas
	private Map<String, Double> produtos = new HashMap<>(); //Armazena os metais em posse e seus respectivos valores Ex: (ouro, 870)
	Calculadora calc = new Calculadora();

	public String seletor(String frase) { //Existem 4 tipos de entradas validas nesse programa, "seletor" seleciona como um dos tipos validos ou trata como entrada invalida.

		try {

			if (frase.contains("valem")) {
				mapeamentoProduto(frase);
				return null;
			} else if (frase.contains("quanto vale ")) {
				String[] array = frase.replace("quanto vale ", "").replace(" ?", "").split(" ");
				Double valor = calc.calculaValor(array, valores);
				String mensagem = "";
				for (String s : array) {
					mensagem = mensagem.concat(s + " ");
				}
				return mensagem + "vale " + valor.toString();

			} else if (frase.contains("quantos créditos"))

			{
				String[] array = frase.replace("quantos créditos são ", "").replace(" ?", "").split(" ");
				Double valor = calc.calculaCreditos(array,  valores , produtos);
				String mensagem = "";
				for (String s : array) {
					mensagem = mensagem.concat(s + " ");
				}
				return mensagem + "custa " + valor.toString() + " créditos";
			} else if ((frase.split(" ").length == 3) && frase.contains(" representa ")) {
				mapeamentoValor(frase);
				return null;
			} else {
				return "Nem ideia do que isto significa!";
			}
		} catch (RuntimeException e) { // numero invalidos por qualquer motivo (XXXX , XIC, etc) são tratados como entrada invalida
			return "Nem ideia do que isto significa!";
		}
	}

	private void mapeamentoValor(String frase) { // Armazena o Simbolo do algarismo Galatico e seu valor em algaritimos arabicos

		String[] array = frase.split(" representa ");
		valores.put(array[0], Conversor.conversor(array[1]));

	}
	private void mapeamentoProduto(String frase) { // Responsavel por armazenar os nomes dos metais vendidos e seus respectivos valores

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
