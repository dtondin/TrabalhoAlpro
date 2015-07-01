import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Util {

	final ArrayList<String> size02 = new ArrayList<>();
	final ArrayList<String> size03 = new ArrayList<>();
	final ArrayList<String> size04 = new ArrayList<>();
	final ArrayList<String> size05 = new ArrayList<>();
	final ArrayList<String> size06 = new ArrayList<>();

	public final ArrayList<ArrayList<String>> todosArrays = new ArrayList<ArrayList<String>>();
	// Variaveis para infraestrutura do applicativo
	private final ArrayList<Integer> intArrayList = new ArrayList<Integer>();
	private final ArrayList<String> stringArrayList = new ArrayList<String>();

	// Variaveis para solucao do problema proposto
	private final ArrayList<String> sequenciaAtual = new ArrayList<String>();
	public static ArrayList<String> maiorSequencia = new ArrayList<String>();
	// boolean deuMatch = false;
	String status = null;
	int F = 0; // like a sentinella for First
	int N = 1; // like a sentinella for Next
	String first;
	String next;
	int firstSize;
	int nextSize;

	public Util() {
	}

	public ArrayList<ArrayList<String>> getTodosArray() {
		return todosArrays;
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
	public float timeCounter(final long startTime) {
		final long endTime = System.currentTimeMillis();
		final long duration = (endTime - startTime);
		float timeSpend = duration / 1000.0f;
		;
		return timeSpend;
	}

	/**
	 * Convert a String ArrayList to a Integer ArrayList
	 */
	public void convertToInt(final ArrayList<String> strList) {
		for (final String s : strList) {
			intArrayList.add(Integer.valueOf(s));
		}
	}

	/**
	 * Convert a Integer ArrayList to a String ArrayList
	 */
	public void convertToString(final ArrayList<Integer> intList) {
		for (final Integer i : intList) {
			stringArrayList.add(String.valueOf(i));
		}
	}

	/**
	 * Base converter. Receive a Integer list and convert to a String list
	 * 
	 * @param fileElements
	 * @return
	 */
	private void baseConverter(final ArrayList<Integer> intList,
			final ArrayList<String> stringList) {
		for (int i = 0; i < intList.size(); i++) {
			final int value = intList.get(i);
			final String j = Integer.toString(value, 6);
			stringList.add(j);
		}
	}

	/**
	 * @param fileElements
	 *            - (ArrayList<String>) List where base 10 data will temporary
	 *            be allocated
	 * @param convertedElements
	 *            - (ArrayList<String>) List where converted data will be added
	 * @param TxtFileName
	 *            - (String) File with base 10 data
	 * @return - List with converted values in base 6
	 */
	public void readAndConvert(final ArrayList<Integer> fileElements,
			final ArrayList<String> convertedElements, final String TxtFileName) {
		try {
			String linha;
			// @SuppressWarnings("resource")
			final BufferedReader br = new BufferedReader(new FileReader(
					TxtFileName));
			while ((linha = br.readLine()) != null) {
				// @SuppressWarnings("resource")
				final Scanner sc = new Scanner(linha).useDelimiter("\n");
				while (sc.hasNext()) {
					final String dado = sc.next();
					fileElements.add(Integer.parseInt(dado));
				}
			}

			baseConverter(fileElements, convertedElements);

		} catch (final IOException e) {
			System.err.format("Error reading the file.", e);
		}

		// Convert the String list to an Integer list.
		convertToInt(convertedElements);

		// Ordering the list
		Collections.sort(intArrayList);

		// Convert the Integer list to a String list.
		convertToString(intArrayList);

		separarTamanhoStrings(stringArrayList);

	}

	/**
	 * @param intArrayList
	 */
	private void separarTamanhoStrings(final ArrayList<String> list) {
		String valor = null;
		int valorSize = 0;

		for (int i = 0; i < list.size(); i++) {
			valor = list.get(i);
			valorSize = valor.length();
			if (valorSize == 2) {
				size02.add(list.get(i));
			}
			if (valorSize == 3) {
				size03.add(list.get(i));
			}
			if (valorSize == 4) {
				size04.add(list.get(i));
			}
			if (valorSize == 5) {
				size05.add(list.get(i));
			}
			if (valorSize == 6) {
				size06.add(list.get(i));
			}
			if (i == list.size() - 1) {
				if (size02.size() != 0) {
					todosArrays.add(size02);
				}
				if (size03.size() != 0) {
					todosArrays.add(size03);
				}
				if (size04.size() != 0) {
					todosArrays.add(size04);
				}
				if (size05.size() != 0) {
					todosArrays.add(size05);
				}
				if (size06.size() != 0) {
					todosArrays.add(size06);
				}
			}
		}
	}

	/**
	 * Run the character comparator. Deve comparar o PRIMEIRO com os proximos.
	 * Quando encontrar algum que 'Casa', pego a partir dele e verifico o
	 * próximo. E assim por diante. Porém preciso comparar o PRIMEIRO com todos
	 * os numeros, depois o segundo e assim por diante.
	 */
	public void runTel_Dor(
			final ArrayList<String> convertedElementsList) {

		int sentinella = F + 1;

		while ((N < convertedElementsList.size())
				&& (sentinella < convertedElementsList.size())) {
			first = convertedElementsList.get(F);
			next = convertedElementsList.get(N);

			status = deuMatch();
			if (status == "match") {
				// atualiza sequencia de acodo com o tamanho e sinal.
				atualizaSequencia();
			}
			// atualiza posicoes de F e N de acordo com o sinal.
			atualizaVariaveis(status);
			// 'sentinella' controla pra que todos elementos sejam verificados.
			// Saltos de
			// 'atualizaVariaveis();' faz com que F pule muitos numeros.
			if ((N == convertedElementsList.size())) {
				F = sentinella;
				N = F + 1;
				sentinella++;
				atualizaMaiorSequenca();
			}
		}
	}

	private void atualizaSequencia() {

		if (sequenciaAtual.isEmpty()) {
			sequenciaAtual.add(first);
			sequenciaAtual.add(next);
		} else {
			sequenciaAtual.add(next);
		}
	}

	public void atualizaMaiorSequenca() {

		final int sizeSequenciaAtual = sequenciaAtual.size();
		final int sizeMaiorSequenciaArmazenada = maiorSequencia.size();
		if (sizeSequenciaAtual > sizeMaiorSequenciaArmazenada) {
			maiorSequencia = new ArrayList<String>(sequenciaAtual);
		}
		sequenciaAtual.clear();
	}

	public void atualizaVariaveis(final String status) {

		if (status == "procurando") {
			N++;
		}
		if (status == "match") {
			F = N;
			N++;
		}if(status == "reset"){
			F = 0;
			N = 1;
		}
	}

	public String deuMatch() {

		int distictChar = 0;
		firstSize = first.length();
		nextSize = next.length();
		for (int i = 0; i < firstSize; i++) {
			// se caracteres diferentes, soma contador.
			if (first.charAt(i) != next.charAt(i)) {
				distictChar++;
				// se contador > 1, finaliza metodo e retorna 'procurando'.
				if (distictChar > 1) {
					status = "procurando";
					break;
				}
				continue;
			}
			// se sair do if acima, match funcionando até o momento com sucesso.
			status = "match";
		}
		return status;
	}

	public ArrayList<String> executa(ArrayList<ArrayList<String>> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			atualizaVariaveis("reset");
			runTel_Dor(arrayList.get(i));
		}
		return maiorSequencia;

	}

}
