import java.io.IOException;
import java.util.ArrayList;

public class App
{

    static Util u = new Util();
    private static ArrayList<Integer> listFileElements = new ArrayList<>();
    private static ArrayList<String> listConvertedElements = new ArrayList<String>();
    private static ArrayList<ArrayList<String>> listTodosArray;

    static ArrayList<ArrayList> list = new ArrayList<ArrayList>();

    public static void main(final String[] args)
            throws IOException
    {

        System.currentTimeMillis();

        // Read the elements list
        u.readAndConvert(listFileElements, listConvertedElements, "teste1000b");

        //listConvertedElements = new ArrayList<String>(u.getStringArrayList());

        //list.add(u.runTel_Dor(listConvertedElements));
        
        listTodosArray = new ArrayList<ArrayList<String>>(u.getTodosArray());
        
        list.add(u.run(listTodosArray));

        // Verifies list elements
        //for (int i = 0; i < u.getStringArrayList().size(); i++)
        //{
        //System.out.println(u.getStringArrayList().get(i));
        //}

        for (int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }

        // Total time spend on the program in minutes and seconds
        //System.out.println("\nTime Spend: " + u.timeCounter(startTime));

        //String a = "123";
        //System.out.print(a.length());

    }
}
