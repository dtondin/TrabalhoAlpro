import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;


public class tests {
	
	Util util = new Util();

	@Test
	public void matchTestTrue() {
		ArrayList<String> list = new ArrayList<>(Arrays.asList("123", "124", "224", "229", "129", "179"));
		boolean response_1 = util.match(list.get(0), list.get(1));
		assertTrue("Match n�o feito!", response_1);
		boolean response_2 = util.match(list.get(1), list.get(2));
		assertTrue("Match n�o feito!", response_2);
		boolean response_3 = util.match(list.get(2), list.get(3));
		assertTrue("Match n�o feito!", response_3);
		boolean response_4 = util.match(list.get(3), list.get(4));
		assertTrue("Match n�o feito!", response_4);
		boolean response_5 = util.match(list.get(4), list.get(5));
		assertTrue("Match n�o feito!", response_5);
	}
	
	@Test
	public void matchTestFalse() {
		ArrayList<String> list = new ArrayList<>(Arrays.asList("123", "124", "226"));
		boolean response_1 = util.match(list.get(0), list.get(1));
		assertTrue("Match n�o feito!", response_1);
		boolean response_2 = util.match(list.get(1), list.get(2));
		assertFalse("Tratado corretamente", response_2);
	}
	
	@Test
	public void testMaiorSequencia() {
		ArrayList<String> sequenciaAtual = new ArrayList<>(Arrays.asList("123", "124", "226"));
		ArrayList<String> maiorSequencia = new ArrayList<>(Arrays.asList("123", "124", "226", "451"));
		boolean response_1 = util.atualizaMaiorSequenca(sequenciaAtual);
		assertTrue("Match n�o feito!", response_1);
		boolean response_2 = util.match(list.get(1), list.get(2));
		assertFalse("Tratado corretamente", response_2);
	}	
	


}
