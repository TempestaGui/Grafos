package Interfaces;

public interface GrafoBF {

    void addArestaPeso(int origem, int destino, int peso);

    void bellmanFord(int origem);

    void imprimirSolucaoBellmanFord(int[] distancia);
}
