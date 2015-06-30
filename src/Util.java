import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Util
{

    // Variaveis para infraestrutura do applicativo
    private final ArrayList<Integer> intArrayList = new ArrayList<Integer>();
    private final ArrayList<String> stringArrayList = new ArrayList<String>();

    // Variaveis para solucao do problema proposto
    private final ArrayList<String> sequenciaAtual = new ArrayList<String>();
    public static ArrayList<String> maiorSequencia = new ArrayList<String>();
    // boolean deuMatch = false;
    String sinal = null;
    int F = 0; // like a sentinella for First
    int N = 1; // like a sentinella for Next
    String first;
    String next;
    int firstSize;
    int nextSize;

    public Util()
    {
    }

    public ArrayList<Integer> getIntArrayList()
    {
        return intArrayList;
    }

    public ArrayList<String> getStringArrayList()
    {
        return stringArrayList;
    }

    /**
     * Time counter for the program
     * 
     * @param startTime - the time stated
     * @return - total time spend
     */
    public String timeCounter(final long startTime)
    {
        final long endTime = System.currentTimeMillis();
        final long duration = (endTime - startTime);
        // System.out.println("\nTime in Milliseconds: " + duration);

        final String timeSpend = String.format(
                "%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
        return timeSpend;
    }

    /**
     * Convert a String ArrayList to a Integer ArrayList
     * 
     * @param strList
     * @return
     */
    public void convertToInt(final ArrayList<String> strList)
    {
        for (final String s : strList)
        {
            intArrayList.add(Integer.valueOf(s));
        }
    }

    /**
     * Convert a Integer ArrayList to a String ArrayList
     * 
     * @param strList
     * @return
     */
    public void convertToString(final ArrayList<Integer> intList)
    {
        for (final Integer i : intList)
        {
            stringArrayList.add(String.valueOf(i));
        }
    }

    /**
     * Base converter. Receive a Integer list and convert to a String list
     * 
     * @param fileElements
     * @return
     */
    private void baseConverter(final ArrayList<Integer> intList, final ArrayList<String> stringList)
    {
        for (int i = 0; i < intList.size(); i++)
        {
            final int value = intList.get(i);
            final String j = Integer.toString(value, 6);
            stringList.add(j);
        }
    }

    /**
     * @param fileElements - (ArrayList<String>) List where base 10 data will temporary be allocated
     * @param convertedElements - (ArrayList<String>) List where converted data will be added
     * @param TxtFileName - (String) File with base 10 data
     * @return - List with converted values in base 6
     */
    public void readAndConvert(
            final ArrayList<Integer> fileElements,
            final ArrayList<String> convertedElements,
            final String TxtFileName)
    {
        try
        {
            String linha;
            // @SuppressWarnings("resource")
            final BufferedReader br = new BufferedReader(new FileReader(TxtFileName));
            while ((linha = br.readLine()) != null)
            {
                // @SuppressWarnings("resource")
                final Scanner sc = new Scanner(linha).useDelimiter("\n");
                while (sc.hasNext())
                {
                    final String dado = sc.next();
                    fileElements.add(Integer.parseInt(dado));
                }
            }

            baseConverter(fileElements, convertedElements);

        }
        catch (final IOException e)
        {
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
     * Run the character comparator. Deve comparar o PRIMEIRO com os proximos. Quando encontrar algum que 'Casa', pego a partir dele
     * e verifico o próximo. E assim por diante. Porém preciso comparar o PRIMEIRO com todos os numeros, depois o segundo e assim
     * por diante.
     */
    public ArrayList<String> runTel_Dor(final ArrayList<String> convertedElementsList)
    {
        // TODO: Ainda necessario encontrar forma de separar tamanhos de
        // elementos para poder testar de forma correta.
        // TODO: cuidar o caso onde não haverá nenhum match e a lista deverá ser
        // vazia. Fix para esta linha.

        int sentinella = F + 1;

        while ((N < convertedElementsList.size()) && (sentinella < convertedElementsList.size()))
        {
            first = convertedElementsList.get(F);
            next = convertedElementsList.get(N);
            if (first.equals("30332"))
            {
                System.out.print("A");
            }

            sinal = match();
            // Se vermelho, troca F e N uma posição adiante.
            // Se amarelo, troca apenas N uma posição adiante.
            // Se verde, troca variaveis: onde F recebe N. Onde N recebe N+1.

            // atualiza sequencia de acodo com o tamanho e sinal.
            if (sinal == "verde")
            {
                atualizaSequencia();
            }

            // atualiza posicoes de F e N de acordo com o sinal.
            atualizaVariaveis(sinal);

            // controla pra que todos elementos sejam verificados. Saltos de
            // 'atualizaVariaveis();' faz com que F pule muitos numeros.
            if ((N == convertedElementsList.size()))
            {
                F = sentinella;
                N = F + 1;
                sentinella++;
                atualizaMaiorSequenca();
            }
        }

        return maiorSequencia;
    }

    private void atualizaSequencia()
    {
        if (sequenciaAtual.isEmpty())
        {
            sequenciaAtual.add(first);
            sequenciaAtual.add(next);
        }
        else
        {
            sequenciaAtual.add(next);
        }
    }

    public void atualizaMaiorSequenca()
    {
        final int sizeSequenciaAtual = sequenciaAtual.size();
        final int sizeMaiorSequenciaArmazenada = maiorSequencia.size();
        // atualiza tamanho sequencia atual quando sinal "Verde!".
        if (sizeSequenciaAtual > sizeMaiorSequenciaArmazenada)
        {
            maiorSequencia = new ArrayList<String>(sequenciaAtual);
        }
        sequenciaAtual.clear();
    }

    public void atualizaVariaveis(final String result)
    {
        // Se vermelho, troca F e N uma posição adiante. Quando size diferente.
        // TEORIA: só vai acontecer no inicio.
        // Se amarelo, troca apenas N uma posição adiante.
        // Se verde, troca variaveis: onde F recebe N. Onde N recebe N+1.
        if (result == "amarelo")
        {
            N++;
        }
        if (result == "vermelho")
        {
            F++;
            N++;
        }
        if (result == "verde")
        {
            F = N;
            N = N + 1;
        }
    }

    /**
     * Compara dois numeros e retorna True se entre eles haver apenas 1 caracter diferente na mesma posição.
     * 
     * @return (boolean) resultado to match.
     */
    public String match()
    {
        // Vou retornar sinais.
        // Vermelho: Quando size diferente.
        // Amarelo: quando size igual, mas não deu match. Elemento travado
        // procurando por um proximo.
        // Verde: DeuMatch e troca elemento travado para next.
        sinal = "vermelho";

        int distictChar = 0;
        firstSize = first.length();
        nextSize = next.length();
        // se size diferente, finaliza metodo e retorna vermelho.
        for (int i = 0; (i < firstSize) && (firstSize == nextSize); i++)
        {
            // se distictChar, soma contador.
            if (first.charAt(i) != next.charAt(i))
            {
                distictChar++;
                // se contador > 1, finaliza metodo e retorna amarelo.
                if (distictChar > 1)
                {
                    sinal = "amarelo";
                    break;
                }
                continue;
            }
            // se entrar no else, match funcionando até o momento com sucesso.
            sinal = "verde";
        }
        return sinal;
    }
}
