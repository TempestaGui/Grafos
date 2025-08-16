package util;

import Interfaces.Grafo;

import java.util.ArrayList;

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
    public boolean isCompleto(){
        int formulaCompleto = (numVertice * (numVertice - 1)) / 2;
        return isSimples() && numAresta == formulaCompleto;
        }


    @Override
    public boolean isNulo(){
        if(numAresta == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isSimples() {
        for (int i = 0; i < numVertice; i++) {
            if (matrizAdj[i][i] != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
     public ArrayList<String> getListaVertices (){
        ArrayList<String> listVertices = new ArrayList<>();
        for (int i = 0; i<numVertice; i++){
            listVertices.add(rotulo[i]);
        }
        return listVertices;
    }

    @Override
    public ArrayList<String> getListaArestas() {
        ArrayList<String> listArestas = new ArrayList<>();
        for (int i = 0; i<numVertice; i++){
            for (int j = 0; j < numVertice; j++){
                if (matrizAdj[i][j] == 1){
                    listArestas.add(rotulo[i]+"-"+rotulo[j]);
                }
            }
        }
        return listArestas;
    }

    @Override
    public boolean isSubgrafo(Grafo grafo) {
        if(!grafo.getListaVertices().containsAll(this.getListaVertices())) return false;
        if (!grafo.getListaArestas().containsAll(this.getListaArestas())) return false;
        return true;
    }

    @Override
    public boolean isSubGrafoGerador(Grafo grafo) {
        if(grafo.getListaVertices().containsAll(this.getListaVertices())) return true;
        return grafo.getListaArestas().containsAll(this.getListaArestas());
    }

    @Override
    public boolean isSubGrafoIduzido(Grafo grafo) {
        if(!isSubgrafo(grafo)) return false;
        ArrayList<String> vertices = this.getListaArestas();

        for(String v : vertices){
            for(String u: vertices){
                if(!v.equals(u)){
                    String arestas = u +"-"+ v;
                    if (grafo.getListaArestas().contains(arestas) && !this.getListaArestas().contains(arestas)){
                        return false;
                    }
                }
            }
        }
        return true;
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
