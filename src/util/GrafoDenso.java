package util;

import Interfaces.Grafo;

public class GrafoDenso implements Grafo {
    private int[][] matrizAdj;
    private int numVertice;
    private int numAresta;
    private String[] rotulo;

    public GrafoDenso(int n){
        this.numVertice = n;
        this.numAresta = 0;
        this.matrizAdj = new int[this.numVertice][this.numVertice];
        this.rotulo = new String[this.numVertice];

        for(int i = 0; i < n; i++){
            rotulo[i] = String.valueOf(i);
        }
    }

    public GrafoDenso(String[] rotulo){
        this.numVertice = rotulo.length;
        this.numAresta = 0;
        this.matrizAdj = new int[this.numVertice][this.numVertice];
        this.rotulo = rotulo;
    }

    @Override
    public int numeroDeVertices() {
        return numVertice;
    }

    @Override
    public int numeroDeArestas() {
        return numAresta;
    }

    @Override
    public int[] sequenciaDeGraus() {
        int[] graus = new int[numVertice];

        for (int i = 0; i < numVertice; i++) {
            int grau = 0;
            for (int j = 0; j < numVertice; j++) {
                grau += matrizAdj[i][j];
            }
            graus[i] = grau;
        }

        return graus;
    }



    @Override
    public void adicionaArestas(int u, int v) {
        if(u >= 0 && v >= 0 && u<this.numVertice && v < this.numVertice && u != v){
            this.matrizAdj[u][v] = 1;
            this.matrizAdj[v][u] = 1;
            numAresta++;
            System.out.println("Aresta adicionada entre "+ rotulo[u] + " e "+ rotulo[v]);
        }
    }

    @Override
    public void removerAresta(int u, int v) {
        if(this.matrizAdj[u][v] == 1){
            this.matrizAdj[u][v] = 0;
            this.matrizAdj[v][u] = 0;
            numAresta--;
            System.out.println("Aresta removida entre "+ rotulo[u] + " e "+ rotulo[v]);
        }
    }


                @Override
                public void imprimir() {
                    System.out.println();
                    System.out.println("Matriz de adjacência");
                    System.out.print("   ");
                    for(String r:rotulo){
                        System.out.print(r+" ");
                    }
                    System.out.println();
                    for(int i = 0; i < numVertice * 2.5; i++){
                        System.out.print("_");
                    }
                    System.out.println();
                    for(int i = 0; i < this.numVertice; i++){
                        System.out.print(rotulo[i]+" |");
                        for(int j = 0; j < this.numVertice; j++){
                            System.out.print(this.matrizAdj[i][j] + " ");
                        }
                        System.out.println();
                    }
                }
}
