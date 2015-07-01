import java.util.ArrayList;

public class App
{

    static Util u = new Util();
    private static ArrayList<Integer> listFileElements = new ArrayList<>();
    private static ArrayList<String> listConvertedElements = new ArrayList<String>();
    private static ArrayList<ArrayList<String>> listTodosArray;
    private static ArrayList<ArrayList> list = new ArrayList<ArrayList>();
    static String file = "arquivos_JB/teste5000b";

    public static void main(final String[] args)
    {
    	long startTime = System.currentTimeMillis();
    	
        u.readAndConvert(listFileElements, listConvertedElements, file);
        
        listTodosArray = new ArrayList<ArrayList<String>>(u.getTodosArray());
        
        list.add(u.run(listTodosArray));

        System.out.println("CASO DE TESTE: " + file);
        System.out.print("\nResultado do caso de teste: ");
        System.out.println("\nTAMANHO ARQUIVO: " + list.get(0).size());
        System.out.print("SEQUENCIA: ");
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
        System.out.println("\nTEMPO: " + u.timeCounter(startTime) + "s.");
    }
}
