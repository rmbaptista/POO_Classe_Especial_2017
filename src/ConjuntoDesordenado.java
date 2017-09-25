
public class ConjuntoDesordenado {
	/*
	* Declara um vetor que vai guardar nossos elementos do conjunto
	* Em java, precisamos dizer o tamanho do nosso vetor ou array e para isso precisamos usar o new
	*/
	private int[] elemento = new int[10];
	private int ultimo = -1;
	private float taxaDeCrescimento = 10; /* em porcentagem */
	
	public String getElemento() {
		String elementosDoVetor = "";
		for(int i = 0; i <= this.ultimo; i++) {
			elementosDoVetor += this.elemento[i] + " / ";
		}
		return elementosDoVetor;
	}
	
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
	 * Inclusão de novos elementos no vetor.
	 * Não permite incluir elementos repetidos
	 * @param elem
	 */
	public void inclua(int elem) throws Exception {
		if(this.tem(elem))
			throw new Exception ("Elemento já existe no vetor");
		
		// Verifica se o vetor está cheio. Se estiver, aumenta o tamanho do vetor.
		if(this.ultimo == this.elemento.length-1)
			this.cresca();
		
		this.ultimo++;
		this.elemento[this.ultimo] = elem;
	}
	
	/**
	 * Verifica se o número existe no vetor.
	 * @param numAVerificar
	 * @return
	 */
	public boolean tem(int numAVerificar) {
		for(int i=0; i <= this.ultimo; i++) {
			if(this.elemento[i] == numAVerificar)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Faz a união de dois vetores.
	 * Como utiliiza o método 'inclua' para fazer o merge dos vetores, o novo vetor
	 * contendo os elementos dos dois vetores de origem não terá elementos repetidos
	 * já que o método 'inclua' já faz essa validação.
	 * @param conj
	 * @return
	 */
	public ConjuntoDesordenado unidoCom(ConjuntoDesordenado conj) {
		ConjuntoDesordenado ret = new ConjuntoDesordenado();
		for(int i=0; i<=this.ultimo; i++) {
			try {
				ret.inclua(this.elemento[i]);
			} catch(Exception erro) {} // Nesse caso não precisa tratar a Exceção, pois como o que queríamos era que não fosse duplicado já foi feito no método 'inclua' apenas ignoramos esse erro.
		}
		for(int i=0; i<=conj.ultimo; i++) {
			try {
				ret.inclua(conj.elemento[i]);
			} catch(Exception erro) {} // Nesse caso não precisa tratar a Exceção, pois como o que queríamos era que não fosse duplicado já foi feito no método 'inclua' apenas ignoramos esse erro.
		}
		
		return ret;
	}
	
	/**
	 * Verifica se o valor existe no vetor através do método 'tem' e se existir o remove.
	 * @param numASerRemovido
	 */
	public void remova(int numASerRemovido) {
		if(this.tem(numASerRemovido)) {
			int posElementoASerRemovido = 0;
			for(int i = 0; this.elemento[i] != numASerRemovido; i++) {
				posElementoASerRemovido = i+1;
			}
			for(int i = posElementoASerRemovido; i < this.ultimo; i++) {
				this.elemento[i] = this.elemento[i+1];
			}
			this.ultimo = this.ultimo - 1;
		}
	}
	
	/**
	 * Verifica quais são os valores que existem nos dois vetores e os insere em um novo vetor
	 * que será retornado com esses valores que representam a intersecao.
	 * @param conj
	 * @return {@link ConjuntoDesordenado}
	 */
	public ConjuntoDesordenado intersecaoCom(ConjuntoDesordenado conj) {
		ConjuntoDesordenado conjuntoIntersecao = new ConjuntoDesordenado();
		
		for(int i = 0; i < this.ultimo; i++) {
			if(conj.tem(this.elemento[i])) {
				try {
					conjuntoIntersecao.inclua(this.elemento[i]);	
				}
				catch(Exception erro) {} // Não precisamos tratar, pois, como os dois vetores de origem não possuem números repetidos, o merge deles também não terá.
			}
		}
		
		return conjuntoIntersecao;
	}
}
