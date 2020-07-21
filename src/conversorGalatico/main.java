package conversorGalatico;
/*
@author: Felipe Schimidt Tomazini
@version
*/

import entities.Tradutor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class main {

	public static void main(String args[]) {

		Tradutor tradutor = new Tradutor();
		String path = "C:\\Users\\felip\\IdeaProjects\\DesafioDell\\src\\conversorGalatico\\teste2.txt"; //path do arquivo

		try(BufferedReader br = new BufferedReader(new FileReader(path))) {

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
		}
	}
}
