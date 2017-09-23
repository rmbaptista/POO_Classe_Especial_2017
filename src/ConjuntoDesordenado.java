
public class ConjuntoDesordenado {
	/*
	* Declara um vetor que vai guardar nossos elementos do conjunto
	* Em java, precisamos dizer o tamanho do nosso vetor ou array e para isso precisamos usar o new
	*/
	private int[] elemento = new int[10];
	private int ultimo = -1;
	
	public void inclua(int elem) {
		this.ultimo++;
		this.elemento[this.ultimo] = elem;
	}
	
	public ConjuntoDesordenado unidoCom(ConjuntoDesordenado conj) {
		ConjuntoDesordenado ret = new ConjuntoDesordenado();
		
		
		return ret;
	}
}
