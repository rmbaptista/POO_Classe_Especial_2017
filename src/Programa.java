
public class Programa {
	public static void main(String[] args) {
		Conjunto a = null;
		Conjunto b = new Conjunto();
		Conjunto c = new Conjunto();
		
		b.inclua(7);
		b.inclua(4);
		b.inclua(9);
		b.inclua(2);
		
		if(b.tem(7))
			System.out.println("b tem 7"); //RESPS
		else
			System.out.println("b nao tem 7");
		
		// Fazer
		b.remova(7);
		
		// Fazer
		if(b.tem(7))
			System.out.println("b tem 7");
		else
			System.out.println("b nao tem 7"); // RESP
		
		c.inclua(1);
		c.inclua(4);
		c.inclua(8);
		c.inclua(3);
		c.inclua(7);
		
		if(c.tem(2))
			System.out.println("c tem 2");
		else
			System.out.println("c nao tem 2"); //RESP
		
		
		c.inclua(2);
		
		if(c.tem(2))
			System.out.println("c tem 2"); // RESP
		else
			System.out.println("c nao tem 2");
		
		
		a = b.unidoCom(c); // 4, 9, 2, 1, 8, 3, 7
		
		if(a.tem(9))
			System.out.println("a tem 9"); // RESP
		else
			System.out.println("a nao tem 9");
		
		if(a.tem(0))
			System.out.println("a tem 0");
		else
			System.out.println("a nao tem 0"); // RESP
		
		// Fazer
		a = b.intersecaoCom(c); // 4, 2
		
		if(a.tem(4))
			System.out.println("a tem 4"); //RESP
		else
			System.out.println("a nao tem 4");
		
		if(a.tem(9))
			System.out.println("a tem 0");
		else
			System.out.println("a nao tem 0"); // RESP
		
	}
}
