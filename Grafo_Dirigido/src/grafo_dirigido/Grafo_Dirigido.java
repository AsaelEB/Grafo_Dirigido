package grafo_dirigido;

import java.util.*;

public class Grafo_Dirigido
  {
   Vector vertices=new Vector();
   int[][] mat_ady;
   int[][] mat_costos;
   int[] costos_minimos;
   int[] p;
   Vector s= new Vector();
   
   
   public int tam_matriz(String[][] datos)
  	   {
  	   	Integer num;
  	   	for (int i=0; i<datos.length; i++)
       	   {
       	     for (int j=0; j<datos[i].length-1; j++)
       	        {
       	         num= new Integer(Integer.parseInt(datos[i][j]));
       	         if (vertices.indexOf(num)==-1)
       	            vertices.addElement(num);
       	        }
           }
       vertices.trimToSize();   
       return vertices.capacity();
  	   }
  	
  	
  	
  	public void crea_mat_ady(int tam,String[][] datos)
  	   {
  	   	mat_ady= new int[tam][tam];
  	  
  	   	
  	   	for(int k=0; k<tam; k++)
  	   	   	for (int l=0; l<tam; l++)
  	   	   	    mat_ady[k][l]=0;
  	   	   	
  	   	for (int i=0; i<datos.length; i++)
       	    mat_ady[Integer.parseInt(datos[i][0])][Integer.parseInt(datos[i][1])]=1;	
  	   }

  	
  	
  	public void crea_mat_costos(int tam,String[][] datos)
  	   {
  	   	mat_costos= new int[tam][tam];
  	  
  	   	for(int k=0; k<tam; k++)
  	   	   	for (int l=0; l<tam; l++)
  	   	   	   mat_costos[k][l]=10000;
  	   	
  	   	for (int i=0; i<datos.length; i++)
       	    mat_costos[Integer.parseInt(datos[i][0])][Integer.parseInt(datos[i][1])]= Integer.parseInt(datos[i][2]);	
  	   }

  	
  	
  	public void imprime_matriz(int[][] mat)
  	   {
  	   	for (int i=0; i<mat.length; i++)
       	   {
       	     for (int j=0; j<mat.length; j++)
       	   	    System.out.print(" "+mat[i][j]);
       	  
       	     System.out.println(" ");
           }
        System.out.print("\n");
  	   }




    public int primero(int v)
       {
        int v_ady=-1;
       	
       	for (int i=0;i<mat_ady.length; i++)
       	  {
       	   if (mat_ady[v][i]==1)
       	      {
       	       v_ady=i;
       	       break;
       	      }	
       	  }
       	return v_ady;  
       }

       
    public int siguiente(int v, int i)
       {
       	int v_sig=-1;
       	 for (int j=i+1; j<mat_ady.length; j++)
       	    {
       	     if (mat_ady[v][j]==1)
       	       {
       	        v_sig=j;
       	        break;
       	       } 
       	    }
       	return v_sig;
       }



    public void encuentra_vertices_adyacentes(int v)
       {
        int i=primero(v);
        System.out.print("nodos adyacentes a "+v+": ");
        
        do {
        	System.out.print(i+" ");
        	i=siguiente(v,i);
           }while(i!=-1);
        System.out.print("\n");   
       }


    public void dijkstra()
       {
        costos_minimos= new int[mat_costos.length];
        p= new int[mat_costos.length];
       	
       	int min,ind,w;
        
        s.addElement(new Integer(0));
        vertices.removeElementAt(0);
        vertices.trimToSize();
        for (int i=1; i<mat_costos.length; i++)
           costos_minimos[i]=mat_costos[0][i];
        
        for(int m=1; m<p.length;m++)
                  p[m]=0;
        
        for (int j=0; j<mat_costos.length-1; j++)
           {
           	ind=0;
           	min=costos_minimos[((Integer)vertices.elementAt(0)).intValue()];
           	w=((Integer)vertices.elementAt(0)).intValue();
           	for (int k=1; k<vertices.size(); k++)
           	   {
           	   	if (min>costos_minimos[((Integer)vertices.elementAt(k)).intValue()])
           	   	  {
           	   	   min=costos_minimos[((Integer)vertices.elementAt(k)).intValue()];
           	   	   ind=k;
           	   	   w=((Integer)vertices.elementAt(k)).intValue();
           	   	  }
           	   }
             vertices.removeElementAt(ind);
             vertices.trimToSize();
             s.addElement(new Integer(w));
             s.trimToSize();
             
             for (int l=0; l<vertices.size(); l++)
                {
                 if (costos_minimos[((Integer)vertices.elementAt(l)).intValue()]> costos_minimos[w]+mat_costos[w][((Integer)vertices.elementAt(l)).intValue()])
                   {
                    costos_minimos[((Integer)vertices.elementAt(l)).intValue()]=costos_minimos[w]+mat_costos[w][((Integer)vertices.elementAt(l)).intValue()];
                    p[((Integer)vertices.elementAt(l)).intValue()]=w; 
                   }
                } 
           }
       }

    public void recorre(int destino)
       {
       	Vector recorrido=new Vector();
             
        System.out.print("\nCamino mas corto entre el nodo 0 y el nodo "+ destino+": ");
        if ((destino!=0) && (costos_minimos[destino]!=10000))
          {
           do
             {
              recorrido.insertElementAt(new Integer(destino),0);
              destino=p[destino];    
              }while(destino!=0);
              
              recorrido.insertElementAt(new Integer(0),0);
              
              for (int t=0; t<recorrido.size(); t++)
                 System.out.print(recorrido.elementAt(t)+"->");
              System.out.print("\n");   
          }else System.out.println("No existe");
       }
    

    public static void main(String[] args ) 
      {
       String[][] datos;
       int i;
       Grafo_Dirigido digrafo= new Grafo_Dirigido();
       
       try {
             Lee_Grafo archgrafo= new Lee_Grafo("grafo.txt");//args[0]
         
             datos=archgrafo.lee_arch();
      
             if (archgrafo.formato_valido())
                {
                 int n=digrafo.tam_matriz(datos);
                 digrafo.crea_mat_ady(n,datos);
                 digrafo.crea_mat_costos(n,datos);
                 
                 System.out.println("Matriz de Adyacencia: \n");
                 digrafo.imprime_matriz(digrafo.mat_ady);
                 
                 System.out.println("Matriz de Costos: \n");
                 digrafo.imprime_matriz(digrafo.mat_costos);
                
                 for (i=0; i<n; i++)
                    digrafo.encuentra_vertices_adyacentes(i);
                 
                 digrafo.dijkstra();
                 
                 System.out.print("\nDistancia mas corta... \n");
                 for (int v=1; v<digrafo.costos_minimos.length; v++)
                    System.out.print("De 0 a "+v+": "+digrafo.costos_minimos[v]+" ");
                 
                 for (i=0; i<n; i++)
                     digrafo.recorre(i);
                }
               
             else System.out.println("ERROR: El archivo no contiene un formato valido");
           } catch (Exception e) {System.err.println("Falta nombre de archivo correcto");}
      }
  }
