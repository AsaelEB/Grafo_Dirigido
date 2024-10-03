# Grafo_Dirigido
El objetivo de este proyecto es crear un grafo usando un archivo con una estructura específica:
- Solamente 3 números entros en cada línea, separados por un espacio, iniciando desde el nodo 0:
  - 1: El número del nodo.
  - 2: El número de un nodo adyacente.
  - 3: El costo entre los nodos.

El nombre del archivo está dentro del programa con el nombre 'grafo.txt', no se puede introducir por medio de interacción con el usuario, para cambiar el grafo se debe editar o reemplazar el archivo en la carpeta del proyecto.

El programa muestra la matriz de adyacencia, la matriz de costos, los nodos adyacentes, la distancia más corta desde el nodo 0 al resto de los nodos (usando el aloritmo de Dijkstra) y el camino más corto haciendo un recorido del grafo. La ejecución del programa con el grafo de muestra se encuentra en la siguiente imagen:

![image](/img1.png)

El proyecto se desarrollo en Java usando NetBeans IDE, razón de que existan muchas carpetas. La carpeta que contiene el resto es la principal, en ella se encuentra el archivo del grafo, dentro de la carpeta 'src' se encuentran los códigos del programa realizado con las clases creadas para este problema.
