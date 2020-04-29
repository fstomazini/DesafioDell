package conversorGalatico;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tradutor {
	private Map<String, Integer> valores = new HashMap<>(); //Armazena os Simbolos e valores usados nas transações galaticas
	private Map<String, Double> produtos = new HashMap<>(); //Armazena os metais em posse e seus respectivos valores Ex: (ouro, 870)

	public String seletor(String frase) { //Existem 4 tipos de entradas validas nesse programa, "seletor" seleciona como um dos tipos validos ou trata como entrada invalida.

		try {

			if (frase.contains("Credits")) {
				mapeamentoProduto(frase);
				return null;
			} else if (frase.contains("quanto vale ")) {
				String[] array = frase.replace("quanto vale ", "").replace(" ?", "").split(" ");
				Double valor = calculaValor(array);
				String mensagem = "";
				for (String s : array) {
					mensagem = mensagem.concat(s + " ");
				}
				return mensagem + "is " + valor.toString();

			} else if (frase.contains("quantos créditos"))

			{
				String[] array = frase.replace("quantos créditos são ", "").replace(" ?", "").split(" ");
				Double valor = calculaCreditos(array);
				String mensagem = "";
				for (String s : array) {
					mensagem = mensagem.concat(s + " ");
				}
				return mensagem + "is " + valor.toString() + " Credits";
			} else if ((frase.split(" ").length == 3) && frase.contains(" is ")) {
				mapeamentoValor(frase);
				return null;
			} else {
				return "I have no idea what you are talking about";
			}
		} catch (RuntimeException e) { // numero invalidos por qualquer motivo (XXXX , XIC, etc) são tratados como entrada invalida
			return "I have no idea what you are talking about";
		}
	}

	private void mapeamentoValor(String frase) { // Armazena o Simbolo do algarismo Galatico e seu valor em algaritimos arabicos

		String[] array = frase.split(" is ");
		valores.put(array[0], conversor(array[1]));

	}

	private int conversor(String palavra) { //Converte Valor em algarismo romano em algarismos arabicos
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

	private Double somador(List<Integer> valores) { //  funcção responsavel por Somar dois numeros em Algarismos romanos
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

	private void mapeamentoProduto(String frase) { // Responsavel por armazenar os nomes dos metais vendidos e seus respectivos valores

		String[] array = frase.split(" is ");
		int credito = Integer.parseInt(array[1].replace(" Credits", ""));
		String[] nomes = array[0].split(" ");
		String metal = nomes[nomes.length - 1];
		List<Integer> pesos = new ArrayList<>();
		for (String palavra : nomes) {
			if (valores.containsKey(palavra)) {
				pesos.add(valores.get(palavra));

			}
		}

		Double valor = somador(pesos);

		produtos.put(metal, (credito / valor));

	}

	private Double calculaValor(String[] frase) { // responsavel pelo calculo do valor em creditos de saida de um metal

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

	private Double calculaCreditos(String[] frase) { // calcula quantos creditos custam uma quantidade x de um metal especifico

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
}
