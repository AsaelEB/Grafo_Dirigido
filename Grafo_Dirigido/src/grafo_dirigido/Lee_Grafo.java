package grafo_dirigido;

import java.io.*;
import java.util.*;

public class Lee_Grafo
  {
   public String[][] datos;
   String nomarch;
   boolean correcto;
   
   
   public Lee_Grafo(String nomarch)
      {
       this.nomarch=nomarch;
       this.correcto=true;	
       this.datos=null;
      }
   
   public int cuenta_lineas()
      {
       FileReader fich;
       int x=0;
       try{
       	   fich= new FileReader(nomarch);
       	  }	catch(Exception e) { System.err.println(e); return -1; }
       
       BufferedReader fichGrafo= new BufferedReader(fich);
      
       	   try{ 
       	    while(fichGrafo.readLine()!=null)
       	       x++;
       	    }catch(Exception e){ System.err.println("ERROR :"+e);}   
       return x;
    }
       
   public boolean formato_valido()
      {
       if (correcto)
          return true;
       else return false;
      }
        
  
   public String[][] lee_arch()
      {
    
       FileReader fich;
       
       try{
       	   fich= new FileReader(nomarch);
       	  }	catch(Exception e) { System.err.println(e); return datos; }
       
       BufferedReader fichGrafo= new BufferedReader(fich);
       
       try {
       	    String linea;
       	    int x=-1;
       	   // System.out.println(cuenta_lineas());   	
       	   datos= new String[cuenta_lineas()][3];
       	    
       	    while((linea=fichGrafo.readLine())!=null)
       	       {
       	       	 x++;
       	       	 StringTokenizer tokens=new StringTokenizer(linea);
       	       	 try{
       	       	 	 if (tokens.countTokens() != 3)
       	       	 	    {correcto=false;
       	       	 	     throw new Exception();
       	       	 	    }
       	       	 	 datos[x][0]= tokens.nextToken();
       	       	 	 datos[x][1]= tokens.nextToken();
       	       	 	 datos[x][2]= tokens.nextToken();
       	       	 	 
       	       	    }catch(Exception e) { System.err.println("ERROR (linea "+x+"): "+linea); }
       	       }
       	    
       	    
       	   }catch(Exception e) {System.err.println("ERROR: "+e);}	  
       return datos;
      }	
  }