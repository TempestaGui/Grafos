package Interfaces;

public interface Grafo {


    int numeroDeVertices();

    int numeroDeArestas();

    int[] sequenciaDeGraus();

    void adicionaArestas(int u, int v);

    void removerAresta(int u, int v);

    void imprimir();
}
