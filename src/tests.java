/* import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;


public class tests {
	
	Util util = new Util();
	ArrayList<String> maiorSequencia = new ArrayList<>();

	@Test
//	public void matchTestTrue() {
//		ArrayList<String> list = new ArrayList<>(Arrays.asList("123", "124", "224", "229", "129", "179"));
//		boolean response_1 = util.match(list.get(0), list.get(1));
//		assertTrue("Match não feito!", response_1);
//		boolean response_2 = util.match(list.get(1), list.get(2));
//		assertTrue("Match não feito!", response_2);
//		boolean response_3 = util.match(list.get(2), list.get(3));
//		assertTrue("Match não feito!", response_3);
//		boolean response_4 = util.match(list.get(3), list.get(4));
//		assertTrue("Match não feito!", response_4);
//		boolean response_5 = util.match(list.get(4), list.get(5));
//		assertTrue("Match não feito!", response_5);
//	}
//	
//	@Test
//	public void matchTestFalse() {
//		ArrayList<String> list = new ArrayList<>(Arrays.asList("123", "124", "226"));
//		boolean response_1 = util.match(list.get(0), list.get(1));
//		assertTrue("Match não feito!", response_1);
//		boolean response_2 = util.match(list.get(1), list.get(2));
//		assertFalse("Tratado corretamente", response_2);
//	}
	
	public void atualizaMaiorSequenca(final ArrayList<String> sequenciaAtual) {
		final int sizeSequenciaAtual = sequenciaAtual.size();
		final int sizeMaiorSequenciaArmazenada = maiorSequencia.size();
		
		if (sizeSequenciaAtual > sizeMaiorSequenciaArmazenada) {
			maiorSequencia = new ArrayList<String>(sequenciaAtual);
		}		
		sequenciaAtual.clear();
	}
	
	@Test
	public void testMaiorSequenciaTroca() {
		final ArrayList<String> sequenciaAtual = new ArrayList<>(Arrays.asList("123", "124", "226"));
		maiorSequencia.add("123");
		maiorSequencia.add("124");
		atualizaMaiorSequenca(sequenciaAtual);
		final int actuals = maiorSequencia.size();
		assertEquals(3, actuals);
	}
	
	@Test
	public void testMaiorSequenciaNaoTroca() {
		final ArrayList<String> sequenciaAtual = new ArrayList<>(Arrays.asList("123"));
		maiorSequencia.add("123");
		maiorSequencia.add("124");
		atualizaMaiorSequenca(sequenciaAtual);
		final int actuals = maiorSequencia.size();
		assertEquals(2, actuals);
	}
	
	@Test
	public void testMaiorSequenciaNovaVazia() {
		final ArrayList<String> sequenciaAtual = new ArrayList<>();
		maiorSequencia.add("123");
		maiorSequencia.add("124");
		atualizaMaiorSequenca(sequenciaAtual);
		final int actuals = maiorSequencia.size();
		assertEquals(2, actuals);
	}
	
//	@Test
//	public void testAtualizaVariaveis() {
//		boolean deuMatch = true;
//		//boolean naoDeuMatch = false;
//		util.atualizaVariaveis(deuMatch);
//		ArrayList<String> sequenciaAtual = new ArrayList<>();
//		maiorSequencia.add("123");
//		maiorSequencia.add("124");
//		atualizaMaiorSequenca(sequenciaAtual);
//		int actuals = maiorSequencia.size();
//		assertEquals(2, actuals);
//	}
	


}
 */