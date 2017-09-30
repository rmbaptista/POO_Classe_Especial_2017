
public class ConjuntoDesordenado {
	/*
	* Declara um vetor que vai guardar nossos elementos do conjunto
	* Em java, precisamos dizer o tamanho do nosso vetor ou array e para isso precisamos usar o new
	*/
//	private int[] elemento = new int[10]; // Maneira antiga
	private int[] elemento; // Nova maneira de cria��o de vetor.
	private int ultimo = -1;
	private float taxaDeCrescimento; /* em porcentagem */
	
	/**
	 * Primeiro construtor, que instancia um objeto com os valores padr�o de
	 */
	public ConjuntoDesordenado() {
		this.inicie(10, 10);
	}
	
	public ConjuntoDesordenado(int tamanhoVetor, int taxaDeCrescimento) {
		// O this do construtor � SEMPRE quem o new criou.
		this.inicie(tamanhoVetor, taxaDeCrescimento);
	}
	
	private void inicie(int tamanhoVetor, int taxaDeCrescimento) {
		this.taxaDeCrescimento = taxaDeCrescimento;
		this.elemento = new int[tamanhoVetor];
	}
	
	// GETTERS
	public String getElemento() {
		String elementosDoVetor = "";
		for(int i = 0; i <= this.ultimo; i++) {
			elementosDoVetor += i < this.ultimo ? this.elemento[i] + " / " : this.elemento[i];
		}
		return elementosDoVetor;
	}
	
	public int getUltimo() {
		return this.ultimo;
	}
	
	public String getTaxaDeAcrescimo() {
		return this.taxaDeCrescimento + "%";
	}
	
	// SETTERS
	public void setTaxaDeAcrescimo(float novaTaxa) {
		this.taxaDeCrescimento = novaTaxa;
	}
	
	// OUTROS M�TODOS DA CLASSE	
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
	
	/**
	 * Verifica se o n�mero existe no vetor.
	 * @param numAVerificar
	 * @return boolean
	 */
	public boolean tem(int numAVerificar) {
		return (this.kd(numAVerificar) != 1);
	}
	
	/**
	 * Faz a uni�o de dois vetores.
	 * Como utiliiza o m�todo 'inclua' para fazer o merge dos vetores, o novo vetor
	 * contendo os elementos dos dois vetores de origem n�o ter� elementos repetidos
	 * j� que o m�todo 'inclua' j� faz essa valida��o.
	 * @param conj
	 * @return
	 */
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
	
	/**
	 * Verifica se o valor existe no vetor atrav�s do m�todo 'tem' e se existir o remove.
	 * @param numASerRemovido
	 */
	public void remova(int numASerRemovido) throws Exception {
			int posElementoASerRemovido = this.kd(numASerRemovido);
			
			if(posElementoASerRemovido == -1)
				throw new Exception("N�mero que est� tentando ser removido n�o existe no vetor");
			
			for(int i = posElementoASerRemovido; i < this.ultimo; i++) {
				this.elemento[i] = this.elemento[i+1];
			}
			this.ultimo = this.ultimo - 1;
	}
	
	/**
	 * Verifica quais s�o os valores que existem nos dois vetores e os insere em um novo vetor
	 * que ser� retornado com esses valores que representam a intersecao.
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
				catch(Exception erro) {} // N�o precisamos tratar, pois, como os dois vetores de origem n�o possuem n�meros repetidos, o merge deles tamb�m n�o ter�.
			}
		}
		
		return conjuntoIntersecao;
	}

	/**
	 * 
	 * @param conj
	 * @return Object
	 */
    public ConjuntoDesordenado menos (ConjuntoDesordenado conj)
    {
    	ConjuntoDesordenado conjunto = new ConjuntoDesordenado();
    	for(int i = 0; i < this.ultimo; i++) {
    		if(conj.tem(this.elemento[i]))
    			continue;
			try {
				conjunto.inclua(this.elemento[i]);
			} catch(Exception erro) {} // Deixamos vazio, pois s� n�o vamos
    	}
    	
    	return conjunto;
        // implementar este m�todo fazendo com que
        // ele declare, instancie e retorne um
        // ConjuntoDesordenado chamado ret, exatamente
        // como no metodo acima (unidoCom);
    	// Os elementos
        // a serem colocados em ret antes dele ser
        // retornado sao aqueles que existirem no this,
        // mas nao existitem no conj
    }
    
    /**
     * Retorna a posi��o que o elemento que est� sendo buscado est�.
     * Se n�o houver esse n�mero no vetor, retorna -1
     * @param x
     * @return int
     */
    private int kd(int x) {
    	for(int i = 0; i<this.ultimo; i++)
    		if(x == this.elemento[i])
    			return i;
    	
    	return -1;
    }
}
