
public class Horario implements Comparable<Horario>{
	private int hora;
	private int minuto;
	private int segundo;
	
	public void setHora(int h) throws Exception {
		if(h < 0 || h > 23)
			throw new Exception("Hora inválida");
		
		this.hora = h;
	}
	
	public void setMinuto(int m) throws Exception {
		if(m < 0 || m > 59)
			throw new Exception("Minuto inválido");
		
		this.minuto = m;
	}
	
	public void setSegundo(int s) throws Exception {
		// TODO Implementar setSegundo
	}
	
	public int getHora() {
		return this.hora;
	}
	
	public int getMinuto() {
		return this.minuto;
	}
	
	public int getSegundo() {
		return this.segundo;
	}
	
	public Horario() {
		this.hora    = 0;
		this.minuto  = 0;
		this.segundo = 0;
	}
	
	public void adianteSe(int qtdSegundos) throws Exception {
		// TODO
	}
	
	public void atraseSe(int qdeSegundos) {
		// TODO
	}
	
	public void ajusteSe(int qde) {
		// TODO
	}
	
	/**
	 * CompareTo deve:
	 * 1 - comparar this e outro
	 * 2 - retornar número negativo, caso this seja menor que outro
	 * 3 - retornar número positivo, caso this seja menor que outro
	 * 4 - retornar número zero, caso this seja igual que outro
	 */
	public int compareTo(Horario outro) {
		if(this.hora > outro.hora)
			return 666;
		if(this.hora < outro.hora)
			return -666;
		if(this.minuto > outro.minuto)
			return 666;
		if(this.minuto < outro.minuto)
			return -666;
		if(this.segundo > outro.segundo)
			return 666;
		if(this.segundo < outro.segundo)
			return -666;
		
		return 0;
	}
}
