package conversorGalatico;
/*
@author: Felipe Schimidt Tomazini
@version
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Mapeamento {

	public static void main(String args[]) {

		Tradutor tradutor = new Tradutor();
		String path = "C:\\Users\\felip\\IdeaProjects\\Desafio Dell\\src\\conversorGalatico/teste.txt"; //path do arquivo
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String line ="";
			do{
				String mensagem = "";
				line = br.readLine();
				if(line != null)
				{
					mensagem = tradutor.seletor(line); //Chama tradutor, classe ressponsavel por processar a entrada de texto
					if (mensagem != null)
					{
						System.out.println(mensagem);//Existem casos no programa com retorno null, esses casos são ignorados na saida em tela
					}
				}
			}while (line != null);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
