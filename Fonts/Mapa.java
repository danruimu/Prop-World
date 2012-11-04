import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;



/**
 * @author Maria Josep Rodriguez Franco
 * @cluster 6
 * @grupo 1
 */

@SuppressWarnings("serial")
public class Mapa extends JComponent {
	Vector<PreMapa> arrayPreMapa = new Vector<PreMapa>(); //listado de PreMapa, vacio por defecto.
	private double dx , dy;
	private double max_x, max_y, min_x, min_y;
	private double factor_x, factor_y, factor_scale;
	private Vector<PreMapa> arrayPaises = new Vector<PreMapa>();
	private Vector<PreMapa> arrayRios = new Vector<PreMapa>();
	private Vector<PreMapa> arrayToponimos = new Vector<PreMapa>();
	//radio maximo y minimo de la tierra en Km
	final private static double R_MAJOR = 6378.1370;
    final private static double R_MINOR = 6356.7523142;
	
	int tipoProyeccion; // integer que indica el tipo de proyeccion con el que se pintara el mapa.
	
	/**
	 * Constructora generica de la clase Mapa.
	 */
	public Mapa() {
		max_x = max_y = 0;
		tipoProyeccion = 0;
		min_x = 40075.2;
		min_y = 20003.94;

		this.setPreferredSize(new Dimension(639, 470));
		this.setVisible(true);
	}

	/**
	 * Devuelve el PreMapa que coincida con la posicion del vector de PreMapa del Mapa.
	 * @param index indice para el vector de PreMapa.
	 * @return El PreMapa que corresponda al parametro de entrada.
	 */
	public PreMapa getArrayPreMapa(int index) {
		return arrayPreMapa.get(index);
	}
	
	/**
	 * Asigna un tipo de proyeccion al mapa.
	 * @param proyeccion indica el tipo de proyeccion con el que se vera el mapa, un 0 corresponde a la proyeccion sinusoidal y un 1 a la proyeccion de Mercator.
	 * @return Cierto o falso dependiendo de si se ha hecho la asignacion correctamente o no.
	 */
	public Boolean setProyeccion(int proyeccion) {
		Boolean boo = true;
		try {
			if (proyeccion > 1 || proyeccion < 0) boo = false;
			else {
				tipoProyeccion = proyeccion;
				
				max_x = max_y = 0;
				if (tipoProyeccion == 0) {
					min_x = 40075.2;
					min_y = 20003.94;
				}
				else {
					min_x = 40075.2;
					min_y = 41204;
				}
			}
		}
		catch (Exception e) {
			boo = false;
		}
		return boo;
	}

	/**
	 * Devuelve el tipo de proyeccion de un Mapa.
	 * @return tipoProyeccion el tipo de proyeccion usado en el mapa.
	 */
	public int getProyeccion() {
		return tipoProyeccion;
	}
	
	/**
	 * Se anade al Vector de PreMapa de un mapa un objeto de la clase PreMapa para poder mostrar los diferentes conjuntos de poligonos.
	 * @param conjunto es un premapa compuesto por los elementos basicos de un premapa.
	 * @return Cierto o falso dependiendo de si se ha anadido correctamente o no el premapa al mapa.
	 */
	public Boolean addPreMapa(PreMapa conjunto) {
		Boolean boo = true;
		try {
			arrayPreMapa.addElement(conjunto);
		}
		catch (Exception e) {
			boo = false;
		}
		return boo;
	}
	
	/**
	 * Muestra en una ventana los diferentes pligonos anadidos previamente al listado.
	 */
	public JPanel mostrarMapa() {
		administrarPreMapas();
        transformarPreMapas();
        dx = max_x - min_x;
        dy = max_y - min_y;
        //haremos el escalando dejando al menos 10 px por arriba, abajo y por los lados, para que no salga enganchado a los bordes en caso extermo
        factor_x = 939/dx;
        factor_y = 685/dy;
        if (factor_x < factor_y) factor_scale = factor_x;
        else factor_scale = factor_y;

        escalarPreMapas();
        
		//JFrame component = new JFrame();
		//component.setVisible(true);
		JPanel content = new JPanel();              // Create content panel.
		content.setLayout(new BorderLayout());
	        
		content.add(this, BorderLayout.CENTER);  // Put in expandable center.
        //component.setContentPane(content);
        //component.setTitle("Mapa");
        //component.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //component.pack();                                // Finalize window layout
        //component.setLocationRelativeTo(null);           // Center window on screen.
		content.setSize(959, 705);
        return content;
    }
	


    public void paint(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
        pintaPaises(g2);
        pintaRios(g2);
        pintaToponimos(g2);
    }

    private void pintaPaises(Graphics2D g2) {
        g2.setStroke(new BasicStroke(1.75f));
        int k = arrayPaises.size();
        for (int i = 0; i < k; ++i) {
            ArrayList<Coordenada> v = new ArrayList<Coordenada>();
            Path2D.Double p = new Path2D.Double();
            PreMapa pm = arrayPaises.get(i);
            g2.setColor(Color.black);
            v = pm.getConjuntoCoordenadas();
            p.moveTo(v.get(0).getLongitud(), v.get(0).getLatitud());
            for (int j = 1; j < v.size(); ++j) {
                    p.lineTo(v.get(j).getLongitud(), v.get(j).getLatitud());	
            }
            g2.setTransform(AffineTransform.getScaleInstance(1, 1));
            
            g2.draw(p);
            String col = pm.getColor();
            determine_color(g2, col);
            g2.fill(p);
            //si esta indicado que se pinte el nombre lo pintamos en el centro de la figura
            if (pm.getEscribirNombre()) {
                if (col == "black" || col == "darkGray") g2.setColor(Color.white);
                else g2.setColor(Color.black);
                Rectangle2D rc = p.getBounds();
                g2.setFont((new Font("Arial", Font.BOLD, 13)));
                g2.drawString(pm.getNombre(), (int)rc.getCenterX(), (int)rc.getCenterY());
            }
        }		
    }
	
    private void pintaRios(Graphics2D g2) {
        g2.setStroke(new BasicStroke(2.0f));
        int k = arrayRios.size();
        for (int i = 0; i < k; ++i) {
        	ArrayList<Coordenada> v = new ArrayList<Coordenada>();
            Path2D.Double p = new Path2D.Double();
            PreMapa pm = arrayRios.get(i);
            String col = pm.getColor();
            determine_color(g2, col);
            v = pm.getConjuntoCoordenadas();
            p.moveTo(v.get(0).getLongitud(), v.get(0).getLatitud());
            for (int j = 1; j < v.size(); ++j) p.lineTo(v.get(j).getLongitud(), v.get(j).getLatitud());		
            g2.setTransform(AffineTransform.getScaleInstance(1, 1));
            g2.draw(p);	
        //si esta indicado que se pinte el nombre lo pintamos en el centro de la figura
            if (pm.getEscribirNombre()) {
                if (col == "black" || col == "darkGray") g2.setColor(Color.white);
                else g2.setColor(Color.black);
                Rectangle2D rc = p.getBounds();
                g2.setFont((new Font("Arial", Font.PLAIN, 8)));
                g2.drawString(pm.getNombre(), (int)rc.getCenterX(), (int)rc.getCenterY());
            }
        }
    }

	private void pintaToponimos(Graphics2D g2) {
		g2.setStroke(new BasicStroke(4.5f));
		int k = arrayToponimos.size();
		for (int i = 0; i < k; ++i) {
			ArrayList<Coordenada> v = new ArrayList<Coordenada>();
			PreMapa pm = arrayToponimos.get(i);
			String col = pm.getColor();
			determine_color(g2, col);
			v = pm.getConjuntoCoordenadas();
			g2.setTransform(AffineTransform.getScaleInstance(1, 1));
			g2.draw(new Line2D.Double(v.get(0).getLongitud(), v.get(0).getLatitud(), v.get(0).getLongitud(), v.get(0).getLatitud()));
			//si esta indicado que se pinte el nombre lo pintamos en el centro de la figura
		    if (pm.getEscribirNombre()) {
		    	if (col == "black" || col == "darkGray") g2.setColor(Color.white);  	
		    	else g2.setColor(Color.black);
		    	g2.setFont((new Font("Arial", Font.ITALIC, 10)));
		    	g2.drawString(pm.getNombre(), (int)v.get(0).getLongitud(), (int)v.get(0).getLatitud());
		    }
		}
	}	

	private void administrarPreMapas() {
		int x = this.arrayPreMapa.size();
		for (int i = 0; i < x; ++i) {
			PreMapa pm = getArrayPreMapa(i);
			int k = pm.getTipoPoligono();
			//PAIS
			if (k == 0) arrayPaises.addElement(pm);
			//RIO
			else if(k == 1) arrayRios.addElement(pm);			
			//k == 2, toponimo
			else arrayToponimos.addElement(pm);
		}
	}
	
	private void transformarPreMapas() {
		int x = arrayPaises.size();
		for (int i = 0; i < x; ++i) {
			ArrayList<Coordenada> v = new ArrayList<Coordenada>();
			PreMapa pm = new PreMapa();
			pm = arrayPaises.get(i);
			v = pm.getConjuntoCoordenadas();
			transformVector(v);
			pm.setConjuntoCoordenadas(v);
			arrayPaises.set(i, pm);
		}
		
		x = arrayRios.size();
		for (int i = 0; i < x; ++i) {
			ArrayList<Coordenada> v = new ArrayList<Coordenada>();
			PreMapa pm = new PreMapa();
			pm = arrayRios.get(i);
			v = pm.getConjuntoCoordenadas();
			transformVector(v);
			pm.setConjuntoCoordenadas(v);
			arrayRios.set(i, pm);
		}
		
		x = arrayToponimos.size();
		for (int i = 0; i < x; ++i) {
			ArrayList<Coordenada> v = new ArrayList<Coordenada>();
			PreMapa pm = new PreMapa();
			pm = arrayToponimos.get(i);
			v = pm.getConjuntoCoordenadas();
			transformVector(v);
			pm.setConjuntoCoordenadas(v);
			arrayToponimos.set(i, pm);
		}
		
	}
	
	private void transformVector(ArrayList<Coordenada> v) {
		for (int j = 0; j < v.size(); ++j) {
			double xx = v.get(j).getLongitud();
			double yy = v.get(j).getLatitud();
			if (tipoProyeccion == 0) { //SINUSOIDAL
				double aux = yy;
				yy = (-1*yy*111.133) + 10001.97;
				if (aux < 0) aux *=-1;
				xx = (Math.cos(aux*Math.PI/180))*xx*111.32 + 20037.6; 
			}
			else { //tipoProyeccion == 1, MERCATOR
				xx = R_MAJOR*Math.toRadians(xx) + 20037.6;
				if (yy > 85.5) {
		            yy = 85.5;
		        }
		        if (yy < -85.5) {
		            yy = -85.5;
		        }
		        double temp = R_MINOR/R_MAJOR;
		        double es = 1.0 - (temp*temp);
		        double eccent = Math.sqrt(es);
		        double phi = Math.toRadians(yy);
		        double sinphi = Math.sin(phi);
		        double con = eccent*sinphi;
		        double com = 0.5*eccent;
		        con = Math.pow(((1.0 - con)/(1.0 + con)), com);
		        double ts = Math.tan(0.5*((Math.PI*0.5) - phi))/con;
		        double y = R_MAJOR*Math.log(ts);
		        yy = y + 20602;
			}
			if (xx > max_x) max_x = xx;
			if (yy > max_y) max_y = yy;
			if (xx < min_x) min_x = xx;
			if (yy < min_y) min_y = yy;
			Coordenada c = new Coordenada(xx, yy);
			v.set(j, c);					
		}						
	}

	private void escalarPreMapas() {
		int x;
		x = arrayPaises.size();
		if (x > 0) {
			for (int i = 0; i < x; ++i) {
				ArrayList<Coordenada> v = new ArrayList<Coordenada>(x);
				PreMapa pm = new PreMapa();
				pm = arrayPaises.get(i);
				v = pm.getConjuntoCoordenadas();
				for (int j = 0; j < v.size(); ++j) {
					double xx = v.get(j).getLongitud();
					double yy = v.get(j).getLatitud();
					xx = (xx - min_x)*factor_scale + 10;
					yy = (yy - min_y)*factor_scale + 10;
					Coordenada c = new Coordenada(xx, yy);
					v.set(j, c);					
				}				
				pm.setConjuntoCoordenadas(v);
				arrayPaises.set(i, pm);
			}
		}
		x = arrayRios.size();
		if (x > 0) {
			for (int i = 0; i < x; ++i) {
				ArrayList<Coordenada> v = new ArrayList<Coordenada>(x);
				PreMapa pm = new PreMapa();
				pm = arrayRios.get(i);
				v = pm.getConjuntoCoordenadas();
				for (int j = 0; j < v.size(); ++j) {
					double xx = v.get(j).getLongitud();
					double yy = v.get(j).getLatitud();
					xx = (xx - min_x)*factor_scale + 10;
					yy = (yy - min_y)*factor_scale + 10;
					Coordenada c = new Coordenada(xx, yy);
					v.set(j, c);					
				}				
				pm.setConjuntoCoordenadas(v);
				arrayRios.set(i, pm);
			}
		}
		x = arrayToponimos.size();
		if (x > 0) {
			for (int i = 0; i < x; ++i) {
				ArrayList<Coordenada> v = new ArrayList<Coordenada>(x);
				PreMapa pm = new PreMapa();
				pm = arrayToponimos.get(i);
				v = pm.getConjuntoCoordenadas();
				for (int j = 0; j < v.size(); ++j) {
					double xx = v.get(j).getLongitud();
					double yy = v.get(j).getLatitud();
					xx = (xx - min_x)*factor_scale + 10;
					yy = (yy - min_y)*factor_scale + 10;
					Coordenada c = new Coordenada(xx, yy);
					v.set(j, c);					
				}				
				pm.setConjuntoCoordenadas(v);
				arrayToponimos.set(i, pm);
			}
		}
	}

	private void determine_color(Graphics2D g2, String col) {
		if (col == "black") g2.setColor(Color.black);
		else if (col == "blue") g2.setColor(Color.blue);
		else if (col == "darkGray,") g2.setColor(Color.darkGray);
		else if (col == "cyan") g2.setColor(Color.cyan);
		else if (col == "gray") g2.setColor(Color.gray);
		else if (col == "green") g2.setColor(Color.green);
		else if (col == "lightGray") g2.setColor(Color.lightGray);
		else if (col == "magenta") g2.setColor(Color.magenta);
		else if (col == "orange") g2.setColor(Color.orange);
		else if (col == "pink") g2.setColor(Color.pink);
		else if (col == "red") g2.setColor(Color.red);
		else if (col == "white") g2.setColor(Color.white);
		else if (col == "yellow") g2.setColor(Color.yellow);		
	}
}