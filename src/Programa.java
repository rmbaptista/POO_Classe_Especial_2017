
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
			System.out.println("b tem 7");
		else
			System.out.println("b nao tem 7");
		
		b.remova(7);
		
		if(b.tem(7))
			System.out.println("b tem 7");
		else
			System.out.println("b nao tem 7");
		
		c.inclua(1);
		c.inclua(4);
		c.inclua(8);
		c.inclua(3);
		c.inclua(7);
		
		if(c.tem(2))
			System.out.println("c tem 2");
		else
			System.out.println("c nao tem 2");
		
		c.inclua(2);
		
		if(c.tem(2))
			System.out.println("c tem 2");
		else
			System.out.println("c nao tem 2");
		
		a = b.unidoCom(c);
		
		if(a.tem(2))
			System.out.println("a tem 2");
		else
			System.out.println("a nao tem 2");
		
		if(a.tem(0))
			System.out.println("a tem 0");
		else
			System.out.println("a nao tem 0");
		
	}
}
