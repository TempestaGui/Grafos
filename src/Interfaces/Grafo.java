package Interfaces;

import java.util.ArrayList;

public interface Grafo {

    //atividade 04
    int numeroDeVertices();
    int numeroDeArestas();
    int[] sequenciaDeGraus();
    void adicionaArestas(int u, int v);
    void removerAresta(int u, int v);
    void imprimir();

    //atividade 05
    boolean isSimples();
    boolean isNulo();
    boolean isCompleto();

    //atividade 06
    ArrayList<String> getListaVertices();
    ArrayList<String> getListaArestas();
    boolean isSubgrafo(Grafo grafo);
    boolean isSubGrafoGerador(Grafo grafo);
    boolean isSubGrafoIduzido(Grafo grafo);
}
