package entities;

import conversorGalatico.Util.Calculadora;
import conversorGalatico.Util.Mapeamento;

import java.util.Map;
import java.util.HashMap;

public class Tradutor {
	private Map<String, Integer> valores = new HashMap<>(); //Armazena os Simbolos e valores usados nas transações galaticas
	private Map<String, Double> produtos = new HashMap<>(); //Armazena os metais em posse e seus respectivos valores Ex: (ouro, 870)
	Calculadora calc = new Calculadora();
	Mapeamento map = new Mapeamento();

	public String seletor(String frase) { //Existem 4 tipos de entradas validas nesse programa, "seletor" seleciona como um dos tipos validos ou trata como entrada invalida.

		try {

			if (frase.contains("valem")) {
				map.mapeamentoProduto(frase, valores, produtos);
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
				map.mapeamentoValor(frase,valores);
				return null;
			} else {
				return "Nem ideia do que isto significa!";
			}
		} catch (RuntimeException e) { // numero invalidos por qualquer motivo (XXXX , XIC, etc) são tratados como entrada invalida
			return "Nem ideia do que isto significa!";
		}
	}



}
