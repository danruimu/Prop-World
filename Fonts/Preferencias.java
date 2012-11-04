
/**
 * Esta clase guarda las preferencias de cada usuario
 * @author Victor Cayuela Narbona
 * @cluster 6
 * @grupo 2
 * @version 1.0
 */
public class Preferencias {
	
//Atributos...
	
	/**
	 *Representa el numero minimo de habitantes que ha de tener un toponimo poblado para ser considerado como toponimo principal  
	 */
	private int numhabitantes; 
	
	/**
	 *Representa el codigo de toponimo que se considera como toponimo principal
	 */
	private String codigotoponimo;
	
//Operaciones
	/**
	 * Creadora de preferencia vacia
	 */
	public Preferencias() {
		numhabitantes = 0;
		codigotoponimo = "";
	}

	/**
	 * Introduce el parametro pasado por referencia(numhabitantes) 
	 */
	public void setNumHabitantes(int num) {
		numhabitantes = num;
	}
	
	/**
	 * Introduce el parametro pasado por referencia(numhabitantes)
	 */
	public void setCodigo(String codigotop) {
		codigotoponimo = codigotop;
	}
	
	/**
	 * Retorna el numero de habitantes
	 */
	public int getNumHabitantes() {
		return numhabitantes;
	}
	
	/**
	 * Retorna el codigo de toponimos
	 */
	public String getCodigo() {
		return codigotoponimo;
	}
	
}
