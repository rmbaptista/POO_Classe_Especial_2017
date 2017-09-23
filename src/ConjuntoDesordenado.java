
public class ConjuntoDesordenado {
	/*
	* Declara um vetor que vai guardar nossos elementos do conjunto
	* Em java, precisamos dizer o tamanho do nosso vetor ou array e para isso precisamos usar o new
	*/
	private int[] elemento = new int[10];
	private int ultimo = -1;
	private float taxaDeCrescimento = 10; /* em porcentagem */
	
	/**
	 * Aumenta o tamanho do vetor sempre que ele chegar no seu limite atual.
	 */
	private void cresca() {
		float multiplicador = ((taxaDeCrescimento/100) + 1);
		int novoTamanhoDoVetor = (int) Math.ceil(this.elemento.length*multiplicador); 
		int[] novo = new int[novoTamanhoDoVetor];
		
		for(int i=0; i<this.ultimo; i++)
			novo[i] = this.elemento[i];
		
		
		this.elemento = novo;
	}
	
	/**
	 * Inclus�o de novos elementos no vetor.
	 * N�o permite incluir elementos repetidos
	 * @param elem
	 */
	public void inclua(int elem) throws Exception {
		if(this.tem(elem))
			throw new Exception ("Elemento j� existe no vetor");
		
		// Verifica se o vetor est� cheio. Se estiver, aumenta o tamanho do vetor.
		if(this.ultimo == this.elemento.length-1)
			this.cresca();
		
		this.ultimo++;
		this.elemento[this.ultimo] = elem;
	}
	
//	private boolean tem(int vetor) {
//		
//		
//		return possuiElementoRepetido;
//	}
	
	public ConjuntoDesordenado unidoCom(ConjuntoDesordenado conj) {
		ConjuntoDesordenado ret = new ConjuntoDesordenado();
		for(int i=0; i<=this.ultimo; i++) {
			try {
				ret.inclua(this.elemento[i]);
			} catch(Exception erro) {} // Nesse caso n�o precisa tratar a Exce��o, pois como o que quer�amos era que n�o fosse duplicado j� foi feito no m�todo 'inclua' apenas ignoramos esse erro.
		}
		for(int i=0; i<=conj.ultimo; i++) {
			try {
				ret.inclua(conj.elemento[i]);
			} catch(Exception erro) {} // Nesse caso n�o precisa tratar a Exce��o, pois como o que quer�amos era que n�o fosse duplicado j� foi feito no m�todo 'inclua' apenas ignoramos esse erro.
		}
			
		
		return ret;
	}
}
