
public class Programa {
	public static void main(String[] args) {
		try {
			ConjuntoDesordenado a = null;
			ConjuntoDesordenado b = new ConjuntoDesordenado();
			ConjuntoDesordenado c = new ConjuntoDesordenado();
			
			b.inclua(7);
            b.inclua(4);
            b.inclua(9);
            b.inclua(2);
            System.out.println(b);

            c.inclua(1);
            c.inclua(4);
            c.inclua(8);
            c.inclua(3);
            c.inclua(7);
            System.out.println(c);

            a = b.intersecaoCom(c); // agora em a deve ter: 4 e 7
            System.out.println(a);
            	
            a = b.menos(c); // agora em a deve ter: 9 e 2
                            // que é o que tem no b mas nao tem no c
        	System.out.println(a);			
			
			// Código abaixo funcionando e testado!
//			b.inclua(7);
//			b.inclua(4);
//			b.inclua(9);
//			b.inclua(2);
			
//			if(b.tem(7))
//				System.out.println("b tem 7"); //RESPS
//			else
//				System.out.println("b nao tem 7");
//			
//			b.remova(7);
//			
//			if(b.tem(7))
//				System.out.println("b tem 7");
//			else
//				System.out.println("b nao tem 7"); // RESP
//			
//			c.inclua(1);
//			c.inclua(4);
//			c.inclua(8);
//			c.inclua(3);
//			c.inclua(7);
//			
//			if(c.tem(2))
//				System.out.println("c tem 2");
//			else
//				System.out.println("c nao tem 2"); //RESP
//			
//			
//			c.inclua(2);
//			
//			if(c.tem(2))
//				System.out.println("c tem 2"); // RESP
//			else
//				System.out.println("c nao tem 2");
//			
//			a = b.unidoCom(c); // 4, 9, 2, 1, 8, 3, 7
//			System.out.println(a.getElemento());
//			
//			if(a.tem(9))
//				System.out.println("a tem 9"); // RESP
//			else
//				System.out.println("a nao tem 9");
//			
//			if(a.tem(0))
//				System.out.println("a tem 0");
//			else
//				System.out.println("a nao tem 0"); // RESP
//			
//			a = b.intersecaoCom(c); // 4, 2
//			
//			if(a.tem(4))
//				System.out.println("a tem 4"); //RESP
//			else
//				System.out.println("a nao tem 4");
//			
//			if(a.tem(9))
//				System.out.println("a tem 0");
//			else
//				System.out.println("a nao tem 0"); // RESP
//			
//			System.out.println("==================");
//			c.getElemento();
//			c.getTaxaDeAcrescimo();
			
		
		}
		catch(Exception erro) {
			System.err.println("Erro: " + erro);
		}
	}
}
