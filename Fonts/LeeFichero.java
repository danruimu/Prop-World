

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class LeeFichero {
   public BufferedReader getFichero(String src) {
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (src);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
      }
      catch(Exception e){
         e.printStackTrace();
      }
      
      return br;
   }
}