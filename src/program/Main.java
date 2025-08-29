package program;

import util.GrafoDenso;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //atividade 04
        String[] rotulo = {"A","B","C","D","E"};
        GrafoDenso gd = new GrafoDenso(rotulo);
        gd.adicionaArestas(0,1);
        gd.adicionaArestas(0,2);
        gd.adicionaArestas(2,3);
        gd.adicionaArestas(2,4);
        gd.adicionaArestas(1,3);
        gd.imprimir();

        System.out.println("Numero de vertices: "+gd.numeroDeVertices());
        System.out.println("Numero de arestas: "+gd.numeroDeArestas());
        System.out.println("Sequencia de graus: "+ Arrays.toString(gd.sequenciaDeGraus()));
        gd.removerAresta(0,2);
        gd.imprimir();
        System.out.println();
        //atividade 05
        System.out.println("esse grafo é um grafo completo?: "+gd.isCompleto());
        System.out.println("esse grafo é um grafo simples?: "+gd.isSimples());
        System.out.println("esse grafo é um grafo nulo?: "+gd.isNulo());
        System.out.println();


        gd.getListaVertices();
        gd.getListaArestas();
        System.out.println();
        String[] rotulo2 = {"A","B","C","D","E"};
        GrafoDenso gd2 = new GrafoDenso(rotulo2);
        gd2.adicionaArestas(0,1);
        gd2.adicionaArestas(0,2);
        gd2.adicionaArestas(2,3);
        gd2.adicionaArestas(2,4);

        //atividade 06
        gd2.imprimir();
        System.out.println();
        System.out.println("subgrafo: "+ gd.isSubgrafo(gd2));
        System.out.println("subgrafo Gerador: "+ gd.isSubGrafoGerador(gd2));
        System.out.println("subGrafo Induzido: "+gd.isSubGrafoIduzido(gd2));
    }
}