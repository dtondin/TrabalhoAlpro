import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Util {

	// Variaveis para infraestrutura do applicativo
	private ArrayList<Integer> intArrayList = new ArrayList<Integer>();
	private ArrayList<String> stringArrayList = new ArrayList<String>();

	// Variaveis para solucao do problema proposto
	private ArrayList<String> sequenciaAtual = new ArrayList<String>();
	public static ArrayList<String> maiorSequencia = new ArrayList<String>();
	boolean deuMatch = false;
	int F = 0; // like a sentinella for First
	int N = 1; // like a sentinella for Next
	String first;
	String next;
	int elemSize;

	public Util() {
	}

	public ArrayList<Integer> getIntArrayList() {
		return intArrayList;
	}

	public ArrayList<String> getStringArrayList() {
		return stringArrayList;
	}

	/**
	 * Time counter for the program
	 * 
	 * @param startTime
	 *            - the time stated
	 * @return - total time spend
	 */
	public String timeCounter(long startTime) {
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);
		// System.out.println("\nTime in Milliseconds: " + duration);

		String timeSpend = String.format(
				"%d min, %d sec",
				TimeUnit.MILLISECONDS.toMinutes(duration),
				TimeUnit.MILLISECONDS.toSeconds(duration)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
								.toMinutes(duration)));
		return timeSpend;
	}

	/**
	 * Convert a String ArrayList to a Integer ArrayList
	 * 
	 * @param strList
	 * @return
	 */
	public void convertToInt(ArrayList<String> strList) {
		for (String s : strList)
			intArrayList.add(Integer.valueOf(s));
	}

	/**
	 * Convert a Integer ArrayList to a String ArrayList
	 * 
	 * @param strList
	 * @return
	 */
	public void convertToString(ArrayList<Integer> intList) {
		for (Integer i : intList)
			stringArrayList.add(String.valueOf(i));
	}

	/**
	 * Base converter. Receive a Integer list and convert to a String list
	 * 
	 * @param fileElements
	 * @return
	 */
	private void baseConverter(ArrayList<Integer> intList,
			ArrayList<String> stringList) {
		for (int i = 0; i < intList.size(); i++) {
			int value = intList.get(i);
			String j = Integer.toString(value, 6);
			stringList.add(j);
		}
	}

	/**
	 * 
	 * @param fileElements
	 *            - (ArrayList<String>) List where base 10 data will temporary
	 *            be allocated
	 * @param convertedElements
	 *            - (ArrayList<String>) List where converted data will be added
	 * @param TxtFileName
	 *            - (String) File with base 10 data
	 * @return - List with converted values in base 6
	 */
	public void readAndConvert(ArrayList<Integer> fileElements,
			ArrayList<String> convertedElements, String TxtFileName) {
		try {
			String linha;
			// @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(TxtFileName));
			while ((linha = br.readLine()) != null) {
				// @SuppressWarnings("resource")
				Scanner sc = new Scanner(linha).useDelimiter("\n");
				while (sc.hasNext()) {
					String dado = sc.next();
					fileElements.add(Integer.parseInt(dado));
				}
			}

			baseConverter(fileElements, convertedElements);

		} catch (IOException e) {
			System.err.format("Error reading the file.", e);
		}

		// Convert the String list to an Integer list.
		convertToInt(convertedElements);

		// Ordering the list
		Collections.sort(intArrayList);

		// Convert the Integer list to a String list.
		convertToString(intArrayList);
	}

	/**
	 * Run the character comparator. Deve comparar o PRIMEIRO com os proximos.
	 * Quando encontrar algum que 'Casa', pego a partir dele e verifico o
	 * próximo. E assim por diante. Porém preciso comparar o PRIMEIRO com todos
	 * os numeros, depois o segundo e assim por diante.
	 */
	public ArrayList<String> runTel_Dor(ArrayList<String> convertedElementsList) {
		// boolean flag = false;
		// deuMatch = false;
		first = convertedElementsList.get(F);
		next = convertedElementsList.get(N);
		//int sentinella = F;
		// TODO: Ainda necessario encontrar forma de separar tamanhos de
		// elementos para poder testar de forma correta.
		//elemSize = first.length();
		// TODO: correcao: devo contar as sequencias adicionadas. A maior (size)
		// vai substituir a menor.
		// TODO: cuidar o caso onde não haverá nenhum match e a lista deverá ser
		// vazia. Fix para esta linha.
		
		
		//sequenciaAtual.add(first);

		while(N < convertedElementsList.size()) {

			first = convertedElementsList.get(F);
			next = convertedElementsList.get(N);
			
			deuMatch = match(first, next);

			if (deuMatch) {
				sequenciaAtual.add(next);
			}else{
				atualizaMaiorSequenca(sequenciaAtual);
				sequenciaAtual.add(first);
			}
			
			atualizaVariaveis(deuMatch);
		}
		
		return maiorSequencia;
	}

	public void atualizaMaiorSequenca(ArrayList<String> sequenciaAtual) {
		int sizeSequenciaAtual = sequenciaAtual.size();
		int sizeMaiorSequenciaArmazenada = maiorSequencia.size();
		
		if (sizeSequenciaAtual > sizeMaiorSequenciaArmazenada) {
			maiorSequencia = new ArrayList<String>(sequenciaAtual);
		}		
		sequenciaAtual.clear();
	}

	public void atualizaVariaveis(boolean deuMatch) {
		if (deuMatch) {
			F++;
			N++;
		}else{
			// Supondo que nao faça sentido tendar dar match de numeros já
			// comparados.
			// Ex: 12,23,34,45,88. Começando a comparar do '12', tenho
			// sequencia ate o '45'. Nao fara
			// sentido começar do 23 e ver os proximos a partir dele pois
			// seroa os mesmos numeros a comparar
			// para fazer uma nova sequencia e que será concerteza menor.

			F = N;
			N = F + 1;
		}
	}
	
	/**
	 * Compara dois numeros e retorna True se entre eles haver apenas 1 caracter
	 * diferente na mesma posição.
	 * 
	 * @param first
	 *            (String) primeiro numero da sequencia a fazer a comparação.
	 * @param next
	 *            (String) segundo numero da sequencia a fazer a comparação.
	 * @return (boolean) resultado to match.
	 */
	public boolean match(String first, String next) {
		boolean result = false;
		int distictChar = 0;
		elemSize = first.length();
		for (int i = 0; i < elemSize && next.length() == elemSize; i++) {
			// Só pode haver UM caracter diferente em cada numero sendo
			// comparado.
			if (first.charAt(i) != next.charAt(i)) {
				distictChar++;				
				// Se qtde de caracteres diferentes for maior que 1, numero nao
				// pode ser aceito
			}
			if (distictChar > 1) {
				result = false;
				return result;
			}
			else{
				result = true;
			}
		}
		return result;
	}

	// /**
	// *
	// * @param encontrou
	// * - boolean flag que informa quando o proximo elemento faz +1
	// * pela sequencia
	// * @param elemSize
	// * - int Tamanho da string (elemento) verificado
	// * @return
	// */
	// public boolean verificaCaracter(boolean encontrou, int elemSize,
	// String first, String next) {
	// // TODO: verificar tambem para as proximas posicoes da string (1,2,3)
	// // TODO: metodo onde posicaoDesejada TROCA somente se lista (sequencia)
	// // nao aumentar (ou diminuir)
	// int posicaoDesejada = 1;
	//
	// // se sequencia 'casou' ou esta 'casando' ate o momento
	// if (encontrou == false) {
	// // vai rodar de acordo com o tamanho da string de entrada sendo
	// // verificada.
	// for (int i = 1; i < elemSize; i++) {
	// // TODO: fazer verificação em todos os caracteres da string de
	// // entrada e não apenas na primeirra que encontrar.
	// if (first.charAt(i) == next.charAt(i)) {
	// posicaoDesejada = first.charAt(i);
	// encontrou = true;
	// sequenciaAtual.add(next);
	// // TODO: verificar se realmente o break faz voltar ao array
	// // anterior.
	// return encontrou;
	// } else {
	// System.out
	// .println("\n!!!! Não era pra entrar aqui mas entrou. E agora o que será de nós !!!!!\n");
	// }
	// }
	// } else if (encontrou == true) {
	// if (first.charAt(posicaoDesejada) == next.charAt(posicaoDesejada)) {
	// sequenciaAtual.add(next);
	// // TODO: verificar a necessidade de um break aqui de acordo com
	// // o caderno
	// } else {
	// F++;
	// encontrou = false;
	// return encontrou;
	// }
	// } else {
	// encontrou = false;
	// maiorSequencia.add(sequenciaAtual);
	// }
	// return encontrou;
	// }



}