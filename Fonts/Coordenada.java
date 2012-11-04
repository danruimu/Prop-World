/**
 * @author Maria Josep Rodriguez Franco
 * @cluster 6
 * @grupo 1
 */
public class Coordenada {
	private double longitud;
	private double latitud;
	
	/**
	 * Creadora de la clase. 
	 * Crea una coordenada geografica con la longitud y latitud que se indique por parametros.
	 * @param lat latitud de un punto en concreto.
	 * @param lon longitud de un punto en concreto.
	 */
	public Coordenada(double lon, double lat) { 
		longitud = lon;
		latitud = lat;
	}
	
	/**
	 * Creadora generica de la clase.
	 */
	public Coordenada() {}
	
	/**
	 * Le da valor 'a' a la longitud de la coordenada y valor 'b' a la latitud de la misma.
	 * @param a valor de la longitud.
	 * @param b valor de la latitud.
	 * @return Cierto o falso dependiendo de si se ha hecho la asignacion correctamente o no.
	 */
	public Boolean setLongitudLatitud(double a, double b) {
		Boolean boo = true;
		try {
			if (a >= -180 && a <= 180 && b >= -90 && b <= 90) {
				longitud = a;
				latitud = b;
			}
			else boo = false;
		}
		catch (Exception e) {
			boo = false;
		}
		return boo;
	}
	
	/**
	 * Devuelve la longitud de la coordenada.
	 * @return Valor de la longitud.
	 */
	public double getLongitud() {
		return longitud;
	}
	
	/**
	 * Le da valor 'a' a la longitud de la coordenada.
	 * @param a valor de la longitud.
	 * @return Cierto o falso dependiendo de si se ha hecho la asignacion correctamente o no.
	 */
	public Boolean setLongitud(double a) {
		Boolean boo = true;
		try {
			if (a >= -180 && a <= 180) longitud = a;
			else boo = false;
		}
		catch (Exception e) {
			boo = false;
		}
		return boo;
	}
  
	/**
	 * Devuelve la latitud de la coordenada.
	 * @return Valor de la latitud.
	 */
	public double getLatitud() {
		return latitud;
	}
	
	/**
	 * Le da valor 'b' a la latitud de la coordenada
	 * @param b valor de la latitud.
	 * @return Cierto o falso dependiendo de si se ha hecho la asignacion correctamente o no.
	 */
	public Boolean setLatitud(double b) {
		Boolean boo = true;
		try {
			if (b >= -90 && b <= 90) latitud = b;
			else boo = false;
		}
		catch (Exception e) {
			boo = false;
		}
		return boo;
	}
	
	/**
	 * Transforma la coordenada a un string.
	 * @return Devuelve un string con los valores de la coordenada entre parentesis y separados por una coma.
	 */
	public String toString() { 
		return "(" + longitud + ", " + latitud + ")"; 
  	}	  
	
    public Coordenada clone() {
        Coordenada aux = new Coordenada();
        aux.setLongitudLatitud(this.getLongitud(), this.getLatitud());
        return aux;
    }
    
     /**
	 * Calcula la distancia que hay entre dos coordenadas
	 * @param c1 Es la coordenada Origen
	 * @param c2 Es la coordenada Destino
	 * @return Retorna la distancia que hay entre c1 y c2
	 */
	public double calcularDistancia(Coordenada c2) {
		double DEG_TO_RAD = 0.017453292519943295769236907684886;
	    double latitudeArc  = (latitud - c2.getLatitud())*DEG_TO_RAD;
	    double longitudeArc = (longitud - c2.getLongitud()) * DEG_TO_RAD;
	    double latitudeH = Math.sin(latitudeArc * 0.5);
	    latitudeH *= latitudeH;
	    double lontitudeH = Math.sin(longitudeArc * 0.5);
	    lontitudeH *= lontitudeH;
	    double tmp = Math.cos(longitud*DEG_TO_RAD) * Math.cos(c2.getLatitud()*DEG_TO_RAD);
	    return 2.0 * Math.asin(Math.sqrt(latitudeH + tmp*lontitudeH))*6372.797560856; //6372
	}
	
}