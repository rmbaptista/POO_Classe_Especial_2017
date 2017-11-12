import java.io.*;

public class Programa {
	public static void main(String[] args) {

		int opcao=0; // Inicializando para que não reclame no switch
		
		do {
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			
			for(;;) {
				System.out.println("Programa para manipular conjuntos!!!\n");
				System.out.println("Trabalharemos com dois conjuntos: A e B");
				System.out.println("1  - Incluir em A");
				System.out.println("2  - Excluir de A");
				System.out.println("3  - Mostrar A");
				System.out.println("4  - Ver se tem em A");
				System.out.println("5  - Incluir em B");
				System.out.println("6  - Excluir de B");
				System.out.println("7  - Mostrar B");
				System.out.println("8  - Ver se tem em B");
				System.out.println("9  - Mostrar A unido com B");
				System.out.println("10 - Mostrar A intersecionado com B");
				System.out.println("11 - Ver se A contem B");
				System.out.println("12 - Ver se A está contido em B");
				System.out.println("13 - Ver se B contém A");
				System.out.println("14 - Ver se B está contido em A");
				System.out.println("15 - Sair");
				System.out.print("Escolha sua opção: ");
				
				try {
					opcao = Integer.parseInt(teclado.readLine());
					
					if(opcao < 1 || opcao > 15)
						System.err.println("Opção inválida!!! Digite direito, anta!!");
					else
						break;
				} catch (IOException e) {
					// Não faço nada, pois sei que nunca vai cair aqui, já que o tipo de entrada é o teclado
					// Só poderia dar erro quando não for entrada do teclado
				} catch (NumberFormatException  e) {
					System.err.println("Opção inválida, favor digitar um número das opções\n");
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
			}
			ConjuntoDesordenado<String> a = new ConjuntoDesordenado<String>(),
										 b = new ConjuntoDesordenado<String>();

			String elem = null;
			
			switch(opcao) {
				/* Incluir em A */
				case 1:
					for(;;) {
						System.out.println("Incluir o que?");
					
						try {
							elem = teclado.readLine();
						} catch (Exception e) {
							// Não faço nada, pois sei que nunca vai cair aqui, já que o tipo de entrada é o teclado
							// Só poderia dar erro quando não for entrada do teclado
						}
						if(elem==null || elem.trim().equals(""))
							System.err.println("Digitação vaziam, favor redigitar");
						else
							break;
					}
					try {
						a.inclua(elem);
					} catch(Exception e) {
						System.err.println(e);
					}
					break;
					
				/* Excluir de A */
				case 2:
					for(;;) {
						System.out.println("Incluir o que?");
					
						try {
							elem = teclado.readLine();
						} catch (Exception e) {
							// Não faço nada, pois sei que nunca vai cair aqui, já que o tipo de entrada é o teclado
							// Só poderia dar erro quando não for entrada do teclado
						}
						if(elem==null || elem.trim().equals(""))
							System.err.println("Digitação vaziam, favor redigitar");
						else
							break;
					}
					try {
						a.remova(elem);
					} catch(Exception e) {
						System.err.println(e);
					}
					break;
					
				/* Mostrar A */
				case 3:
					System.out.println(a);
					break;
					
				/* Ver se tem em A */
				case 4:
				
				/* Incluir em B */
				case 5:
					
				/* Excluir de B */
				case 6:
					
				/* Mostrar B */
				case 7:
					System.out.println(b);
					break;
					
				/* Ver se tem em B */
				case 8:
					
				/* Mostrar A unido com B */
				case 9:
					
				/* Mostrar A intersecionado com B */
				case 10:
				
				/* Ver se A contém B */
				case 11:
					
				/* Ver se A está contido em B */
				case 12:
					
				/* Ver se B contem A */
				case 13:
					
				/* Ver se B está contido em A */
				case 14:
					
				default: 
			}
		} while(opcao != 15);
	}
}
