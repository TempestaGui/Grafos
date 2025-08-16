package Interfaces;

import java.util.ArrayList;

public interface Grafo {


    int numeroDeVertices();

    int numeroDeArestas();

    int[] sequenciaDeGraus();

    void adicionaArestas(int u, int v);

    void removerAresta(int u, int v);

    void imprimir();

    boolean isSimples();

    boolean isNulo();

    boolean isCompleto();

    ArrayList<String> getListaVertices();

    ArrayList<String> getListaArestas();

    boolean isSubgrafo(Grafo grafo);

    boolean isSubGrafoGerador(Grafo grafo);

    boolean isSubGrafoIduzido(Grafo grafo);

}
