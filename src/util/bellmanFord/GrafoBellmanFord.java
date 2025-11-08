package util.bellmanFord;

import Interfaces.GrafoBF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//atividade 5 segundo bi
//Algoritmo de Bellman-Ford
public class GrafoBellmanFord implements GrafoBF {
    private int numVertice;
    private int numAresta;
    private List<Aresta> arestas;

    public GrafoBellmanFord(int numVertice, int numAresta) {
        this.numVertice = numVertice;
        this.numAresta = numAresta;
        arestas = new ArrayList<>();
    }

    @Override
    public void addArestaPeso(int origem, int destino, int peso) {
        arestas.add(new Aresta(origem, destino, peso));
    }

    @Override
    public void bellmanFord(int origem) {
        int[] distancia = new int[numVertice];
        Arrays.fill(distancia, Integer.MAX_VALUE);
        distancia[origem] = 0;

        //Passo 1 -> relaxar todas as arestas V-1 vezes
        for (int i = 0; i < numVertice; i++) {
            for (Aresta aresta : arestas) {
                int u = aresta.getOrigem();
                int v = aresta.getDestino();
                int peso = aresta.getPeso();

                if (distancia[u] != Integer.MAX_VALUE && distancia[u] + peso < distancia[v]) {
                    distancia[v] = distancia[u] + peso;
                }
            }
        }

        //passo 2 -> verificar ciclos negativos
        for (Aresta aresta : arestas) {
            int u = aresta.getOrigem();
            int v = aresta.getDestino();
            int peso = aresta.getPeso();

            if (distancia[u] != Integer.MAX_VALUE && distancia[u] + peso < distancia[v]) {
                System.out.println("Grafo possui um ciclo de peso negativo");
                return;
            }
        }
        imprimirSolucaoBellmanFord(distancia);
    }

    @Override
    public void imprimirSolucaoBellmanFord(int[] distancia) {
        System.out.println("Vértice\t\tDistância");
        for (int i = 0; i < numVertice; i++) {
            if (distancia[i] == Integer.MAX_VALUE)
                System.out.println(i + "\t∞");
            else
                System.out.println(i + "\t\t\t" + distancia[i]);
        }
    }
}
