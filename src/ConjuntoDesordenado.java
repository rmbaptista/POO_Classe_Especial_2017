import java.lang.reflect.*;

public class ConjuntoDesordenado <X> implements Comparable<ConjuntoDesordenado <X>>{
	/*
	* Declara um vetor que vai guardar nossos elementos do conjunto
	* Em java, precisamos dizer o tamanho do nosso vetor ou array e para isso precisamos usar o new
	*/
	private Object[] elemento; // Nova maneira de cria��o de vetor.
	private int ultimo = -1;
	private float taxaDeCrescimento; /* em porcentagem */
	
	/**
	 * Primeiro construtor, que instancia um objeto com os valores padr�o de
	 */
	public ConjuntoDesordenado() {
		this.inicie(10, 10);
	}
	
	public ConjuntoDesordenado(int tamanhoVetor) {
		this.inicie(tamanhoVetor, 10);
	}
	
	public ConjuntoDesordenado(int tamanhoVetor, int taxaDeCrescimento) {
		// O this do construtor � SEMPRE quem o new criou.
		this.inicie(tamanhoVetor, taxaDeCrescimento);
	}
	
	private void inicie(int tamanhoVetor, int taxaDeCrescimento) {
		this.taxaDeCrescimento = taxaDeCrescimento;
		this.elemento = new Object[tamanhoVetor];
	}
	
	// GETTERS
//	public String getElemento() {
//		String elementosDoVetor = "";
//		for(int i = 0; i <= this.ultimo; i++) {
//			elementosDoVetor += i < this.ultimo ? this.elemento[i] + " / " : this.elemento[i];
//		}
//		return elementosDoVetor;
//	}
	
	public int getUltimo() {
		return this.ultimo;
	}
	
	public Object[] getElemento() {
		return this.elemento;
	}

	public void setElemento(X[] elemento) {
		this.elemento = elemento;
	}

	public float getTaxaDeAcrescimo() {
		return this.taxaDeCrescimento;
	}
	
	// SETTERS
	public void setTaxaDeAcrescimo(float novaTaxa) {
		this.taxaDeCrescimento = novaTaxa;
	}
	
	// OUTROS M�TODOS DA CLASSE
	
	/**
	 * Retorna um elemento do vetor pela posi��o.
	 * @param i
	 * @return
	 * @throws Exception
	 */
	public X getElemento(int i) throws Exception {
		if(i<0 || i>this.ultimo)
			throw new Exception("Posi��o do vetor inv�lida");
		
		/*
		 * Clonar aqui seria desnecess�rio se o que est� sendo
		 * retornado, fosse removido do vetor (por exemplo uma pilha);
		 */
		if(this.elemento[i] instanceof Cloneable) {
//			return (X)this.elemento[i].clone();
			return meuCloneDeX(this.elemento[i]);
		}
		
		return (X)this.elemento[i];
	}
	
	/**
	 * Aumenta o tamanho do vetor sempre que ele chegar no seu limite atual.
	 */
	private void cresca() {
		float multiplicador = ((taxaDeCrescimento/100) + 1);
		int novoTamanhoDoVetor = (int) Math.ceil(this.elemento.length*multiplicador); 
		Object[] novo = new Object[novoTamanhoDoVetor];
		
		for(int i=0; i<this.ultimo; i++)
			novo[i] = this.elemento[i];
		
		this.elemento = novo;
	}
	
	/*
	 * String s = "PUC";
	 * 
	 * Imagine que queremos recuperar a letra 'U'.
	 * Pod�amos simplesmente fazer:
	 *      char c = s.charAt(1);
	 * Mas, masoquistas que somos, resolvemos fazer:
	 * 
	 * Class<?> classe = s.getClass();
	 * Integer parametro = 1;
	 * Class<?>[] tipoDoParametroFormal = new Class<?>[1]; // 1 pq s� tem um par�metro
	 * tipoDoParametroFormal[0] parametro = parametro.getClass();
	 * Method metodo = classe.getMethod("charAt", tipoDoParametroFormal);
	 * Object[] parametroReal = new Object[1]; // 1 pq s� tem um par�metro
	 * parametroReal[0] = parametro;
	 * char c = ((Character)metodo.invoke(s, parametroReal)).charValue();
	 */
	public X meuCloneDeX(modelo) {
		X ret = null;
		try {
//			return (X)modelo.clone();
			Class<?> classe = modelo.getClass();
			Class<?>[] tipoDoParametroFormal = null; // null pq n�o tem par�metro
			Method metodo = classe.getMethod("clone", tipoDoParametroFormal);
			Object[] parametroReal = null; // null pq n�o tem par�metro
			ret = (X)metodo.invoke(modelo, parametroReal);
		} catch(Exception e) {}
		
		return ret;
	}
	
	/**
	 * Inclus�o de novos elementos no vetor.
	 * N�o permite incluir elementos repetidos
	 * @param elem
	 */
	public void inclua(X elem) throws Exception {
		if(this.tem(elem))
			throw new Exception ("Elemento j� existe no vetor");
		
		// Verifica se o vetor est� cheio. Se estiver, aumenta o tamanho do vetor.
		if(this.ultimo == this.elemento.length-1)
			this.cresca();
		
		this.ultimo++;
		if(elem instanceof Cloneable) {
			/*
			 * Gostar�amos de fazer desse modo que est� comentado abaixo,
			 * mas vai dar erro, ent�o, precisaremos fazer de ouotro jeito (n�o comentado)
			 * 
			 * this.elemento[this.ultimo] = (X)elem.clone(); // Aqui vai dar pau de compila��o pq Object n�o tem clone, ent�o vamos precisar contornar (enganar o Java)
			 */
			this.elemento[this.ultimo] = meuCloneDeX(elem);
		}
		else	
			this.elemento[this.ultimo] = elem;
	}
	
	/**
	 * Verifica se o n�mero existe no vetor.
	 * @param numAVerificar
	 * @return boolean
	 */
	public boolean tem(X numAVerificar) {
		return (this.kd(numAVerificar) != -1);
	}
	
	/**
	 * Faz a uni�o de dois vetores.
	 * Como utiliiza o m�todo 'inclua' para fazer o merge dos vetores, o novo vetor
	 * contendo os elementos dos dois vetores de origem n�o ter� elementos repetidos
	 * j� que o m�todo 'inclua' j� faz essa valida��o.
	 * @param conj
	 * @return
	 */
	public ConjuntoDesordenado<X> unidoCom(ConjuntoDesordenado<X> conj) {
		ConjuntoDesordenado ret = new ConjuntoDesordenado();
		for(int i=0; i<=this.ultimo; i++) {
			try {
				ret.inclua((X)this.elemento[i]);
			} catch(Exception erro) {} // Nesse caso n�o precisa tratar a Exce��o, pois como o que quer�amos era que n�o fosse duplicado j� foi feito no m�todo 'inclua' apenas ignoramos esse erro.
		}
		for(int i=0; i<=conj.ultimo; i++) {
			try {
				ret.inclua((X)conj.elemento[i]);
			} catch(Exception erro) {} // Nesse caso n�o precisa tratar a Exce��o, pois como o que quer�amos era que n�o fosse duplicado j� foi feito no m�todo 'inclua' apenas ignoramos esse erro.
		}
		
		return ret;
	}
	
	/**
	 * Verifica se o valor existe no vetor atrav�s do m�todo 'tem' e se existir o remove.
	 * @param numASerRemovido
	 */
	public void remova(X numASerRemovido) throws Exception {
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
		
		for(int i = 0; i <= this.ultimo; i++) {
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
	 * Remove do vetor do objeto que chama o m�todo (this) os elementos do vetor
	 * do objeto passado no par�metro
	 * @param conj
	 * @return Object
	 */
    public ConjuntoDesordenado menos (ConjuntoDesordenado conj)
    {
    	ConjuntoDesordenado conjunto = new ConjuntoDesordenado();
    	for(int i = 0; i <= this.ultimo; i++) {
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
    private int kd(X x) {
    	for(int i = 0; i<=this.ultimo; i++)
    		if(x.equals(this.elemento[i]))
    			return i;
    	
    	return -1;
    }
    
	/**
	 * Deve retornar true, caso TODOS os elementos de
     * this forem elementos do parametro do metodo (conj)
	 * @param conj
	 * @return boolean
	 */
    public boolean estaContidoEm (ConjuntoDesordenado<X> conj) {
    	if(conj == null)
    		return false;
    	
    	if(this.ultimo > conj.ultimo)
    		return false;
    	
    	for(int i=0; i<=this.ultimo; i++)
    		if(!(conj.tem((X)this.elemento[i])))
    			return false;
    	
    	return true;
    }
    
    /**
     * Compara se 'this' possui TODOS elementos dos elementos do conjunto do par�metro (conj)
     * @param conj
     * @return boolean
     */
    public boolean contem (ConjuntoDesordenado<X> conj) {
        if(conj.ultimo > this.ultimo)
        	return false;
        
        for(int i=0; i<=conj.ultimo; i++ )
        	if(!(this.tem((X)conj.elemento[i])))
        		return false;
    	
    	return true;
    }
    
    /////////////////////////////////////////////
    // M�TODOS APOCAL�PTICOS - MU HA HA HA
    /////////////////////////////////////////////
    
    /**
     * Comparar em termis de conte�do o 'this' que � do tipo ConjuntoDesordenado
     * com obj que � do tipo Object e portanto pode ser qualquer coisa.
     * Isso porque tudo herda de Object e logo, tudo pode ser considerado Object
     * retornando true ou false
     */
    public boolean equals(Object obj) {
    	/*
    	* Estou testando endere�os de mem�ria.
    	* Se estiverem no mesmo endere�o, possuem o mesmo conte�do.
    	*/
    	if(this == obj)
    		return true;
    	
    	/*
    	 * Como temos certeza que this n�o � nulo (pois se ele chamou
    	 * o m�todo j� quer dizer que ele n�o � nulo, porque se fosse,
    	 * d�ria NullPointerException e n�o entraria aqui no m�todo),
    	 * podemos afirmar que se obj for nulo (ou seja, ele n�o foi instanciado,
    	 * pois um objeto s� � nulo quando ele n�o foi instanciado, n�o guardando
    	 * nenhum endere�o de mem�ria) ele n�o � igual ao this.
    	 */
    	if(obj == null)
    		return false;
    	
    	/*
    	 * Esse jeito de fazer comentado abaixo s� funciona para classes
    	 * que n�o s�o gen�ricas. Para classes gen�rica, podemos utilizar
    	 * o jeito feito abaixo do coment�rio pode ser utilizado em classes
    	 * gen�ricas e as que n�o s�o tamb�m.
    	 * if(!(obj instanceof ConjuntoDesordenado))
    	 *  	return false;
		*/
    	if(this.getClass() != obj.getClass())
    		return false;
    	
    	
    	/*
    	 * Chegando aqui, EUUUUU sei que estou comparando 2 (n�o 1 com dois objetos
    	 * que referenciam o mesmo endere�o de mem�ria) ConjuntoDesordenado. Mas o
    	 * Java obj ainda continua sendo Object.
    	 */
    	
    	ConjuntoDesordenado<X> conj = (ConjuntoDesordenado<X>) obj;
    	
    	if(this.taxaDeCrescimento != conj.taxaDeCrescimento)
    		return false;
    	
    	if(this.ultimo != conj.ultimo)
    		return false;
    	
    	if(!this.contem(conj) || !conj.contem(this))
    		return false;
    	
    	/*
    	 * Estava desse jeito, mas foi modificado para o if acima, para
    	 * que n�o entre em conflito com o "compareTo"
    	 */
//    	for(int i=0; i<=this.ultimo; i++)
//	    	if(!this.elemento[i].equals(conj.elemento[i]))
//	    		return false;
    	
    	return true;
    }
    
    /**
     * Calcular e retornar um n�mero inteiro a partir dos dadosd que est�o
     * dentro do 'this'. O n�mero que retornaremos servir� para
     * estruturas de dados baseadas em hash
     * determinarem o local de armazenamento de algo, 
     * assim como localizarem alfo armazenado
     */
    public int hashCode() {
    	int ret = 666;
    	 
    	/*
    	 * if(this.ultimo != null) {
    	 *     ret = 7 * ret + this.ultimo.hashCode();
    	 * }
    	 * Fazemos desse jeito abaixo, pois int n�o � um Objeto, se fosse far�amos da maneira comentada acima:
    	 */
    	ret = 7 * ret + new Integer(this.ultimo).hashCode();
    	ret = 7 * ret + new Float(this.taxaDeCrescimento).hashCode();
    	
    	for(int i=0; i<=this.ultimo; i++)
    		ret = 7 * ret + this.elemento[i].hashCode();
    	/*
    	 * O loop acima poderia ser com o for tradicional, como abaixo:
    	 * for(int i=0; i<=this.ultimo;i++
    	 *     ret = 7 * ret + new Integer(this.elemento[i]).hashCode();
    	 */
    	
    	return ret;
    }
    
    /**
     * Formata no formato de uma String o que vai ser printado.
     */
    public String toString() {
    	String ret = null;
    	
    	if(this.ultimo == -1) {
    		ret =  "===============\n";
    		ret += "Conjunto vazio!";
    		ret += "===============";
    		return "";
    	}
    	
    	ret =  "===============\n";
    	ret += "�ltimo: " + this.ultimo + "\n";
    	ret += "Elementos do vetor: {";
    	for(int i = 0; i < this.ultimo; i++)
			ret += this.ultimo != i ? this.elemento[i] + ", " : this.elemento[i];
    	ret += this.elemento[this.ultimo];
    	ret += "}\n";
    	ret += "Taxa de crescimento: " + this.taxaDeCrescimento + "%\n";
    	ret += "===============";
    	
    	return ret;
    }
    
    /**
     * � raro fazer compareTo.
     * Fazemos quando � poss�vel comparar dois objetos da classe,
     * decidindo se um � menor que o outro, se o outro � menor que o um,
     * n�o sendo nem maior nem maior que s�o iguais.
     * 
     * Quando o compareTo diz que duas coisas s�o iguais
     * o equals tem que dizer o omesmo, e vice-versa
     * (o equals e o compareTo tem que concordar 1 com o outro)
     * 
     * O compareTo deve retornar um n�mero negativo quando this for menor que outro
     * O compareTo deve retornar um n�mero positivo quando this for maiorque outro
     * O compareTo deve retornar zero quando this for igual ao outro
     * 
     * Quando fazemos o m�todo compareTo, colocamos:
     *     implements Comparable<ConjuntoDesordenado<X>>
     * na defini��o da classe 
     * @param outro
     * @return int
     */
    public int compareTo(ConjuntoDesordenado<X> outro) {
    	if(this.equals(outro))
    		return 0;
    	if(this.estaContidoEm(outro))
    		return -666;
    	
    	return 666;
    }
    
    
//    
//    /*
//     * Faz de conta que existe a classe Data, com dia,
//     * mes, ano, getters, setters, obrigat�rios al�m de outros
//     */
//    
//    Data niverCaio = Data(5,3,1991);
//    Data niverAugusto = Data(23,12,1991);
//    Data niverFernando = Data(20,3,1991);
//    Data niverRodrigo = Data(23,3,1987);
//    Data niverAndre = Data(19,1,1966);
//    
//    ConjuntoDesordenado<Data> conj;
//    conj.inclua(niverCaio);
//    conj.inclua(niverAugusto);
//	conj.inclua(niverFernando);
//	conj.inclua(niverRodrigo);
//	conj.inclua(niverAndre);
//	
//	/*
//	 * Caso, mesmo devendo ter, a classe Data n�o tenha
//	 * clone e contrutor de c�pia, � claro que a classe
//	 * ConjuntoDesordenado n�o vai se capaz de clonar
//	 * o par�mentro do inclua ao armazena-lo no vetor e,
//	 * assim sendo, estar� armazendando no vetor um ponteiro
//	 * para a mesma data criada aqui. naturalmente, mudando
//	 * aqui o mes como estamos como estamos fazendo, mudara no vetor
//	 * privatr da classe COnjungo.
//	 * ABSURDO!!! N�O ACREDITO NISSO!!! HERESIA!!!
//	 * Um m�todo de uma classe est� conseguindo mudar
//	 * atrivutos privare de outra classe!!!!
//	 */
//	niverCaio.setMes(3);
    
    public ConjuntoDesordenado(ConjuntoDesordenado<X> modelo) {
    	if(modelo == null)
    		throw new Exception("Modelo ausente");
    	
    	this.ultimo= modelo.ultimo;
    	this.taxaDeCrescimento = modelo.taxaDeCrescimento;
    	this.elemento = new Object[modelo.elemento.length];
    	for(int i=0; i<=modelo.ultimo; i++) {
    		this.elemento[i] = modelo.elemento[i];
    		/*
    		 * Fa�o conforme acima e n�o abaixo, porque n�o h� m�todo em
    		 * conjuntoDesordenado que altere valores de atributos dos Xs
    		 * nele guardados; Ali�s, como n�o se sabe o que s�o esses Xs
    		 * (classe gen�rica) ser� bem dif�cil produzir um m�todo que fizesse tais altera��es;
    		 * A forma abaixo fica portanto, com probabilidade quase 0 de ser
    		 * a adotada em classes gen�ricas; Ficando a forma de cima, inclusive,
    		 * gera economia de mem�ria;
    		 * 
    		 * if(modelo.elemento[i] instanceof Cloneable)
    		 *     this.elemento[i] = meuCloneDeX((X)modelo.elemento[i]);
    		 * else
    		 *     this.elemento[i] = modelo.elemento[i];
    		 */
    	}
    }
    
    public Object clone() {
    	ConjuntoDesordenado<X> ret = null;
    	try {
    	/*
    	 * A princ�pio seria feito apenas o comando feito abaixo,
    	 * no entando, como o construtor de c�pia tem o potencial de lan�ar exce��o
    	 * vou precisar lan�ar try/catch
    	 */
    		ret = new ConjuntoDesordenado<>(this);
    	} catch (Exception e) {}
    	/*
    	 * Ignoro o catch pq sei que o construtor de c�pia s� da exce��o quando seu par�metro
    	 * for null, e como estou passando como mar�metor o this, e this nunca � null, � desnecess�rio
    	 * dar um tratamento para o erro j� que ele jamais poder� acontecer
    	 */
    	
    	return ret;
	
}
