package util;

import Interfaces.Grafo;

import java.util.*;

public class GrafoDenso implements Grafo {
    private int[][] matrizAdj;
    private int numVertice;
    private int numAresta;
    private String[] rotulo;
    private List<List<Integer>> adjacencia;

    //atividade 04
    public GrafoDenso(int n) {
        this.numVertice = n;
        this.numAresta = 0;
        this.matrizAdj = new int[this.numVertice][this.numVertice];
        this.rotulo = new String[this.numVertice];

        for (int i = 0; i < n; i++) {
            rotulo[i] = String.valueOf(i);
        }
    }

    public GrafoDenso(String[] rotulo) {
        this.numVertice = rotulo.length;
        this.numAresta = 0;
        this.matrizAdj = new int[this.numVertice][this.numVertice];
        this.rotulo = rotulo;
        adjacencia = new ArrayList<>();
        for (int i = 0; i < this.numVertice; i++) {
            adjacencia.add(new ArrayList<>());
        }
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
        if (u >= 0 && v >= 0 && u < this.numVertice && v < this.numVertice && u != v) {
            this.matrizAdj[u][v] = 1;
            this.matrizAdj[v][u] = 1;
            numAresta++;
            System.out.println("Aresta adicionada entre " + rotulo[u] + " e " + rotulo[v]);
        }
    }

    @Override
    public void removerAresta(int u, int v) {
        if (this.matrizAdj[u][v] == 1) {
            this.matrizAdj[u][v] = 0;
            this.matrizAdj[v][u] = 0;
            numAresta--;
            System.out.println("Aresta removida entre " + rotulo[u] + " e " + rotulo[v]);
        }
    }

    @Override
    public void imprimir() {
        System.out.println();
        System.out.println("Matriz de adjacência");
        System.out.print("   ");
        for (String r : rotulo) {
            System.out.print(r + " ");
        }
        System.out.println();
        for (int i = 0; i < numVertice * 2.5; i++) {
            System.out.print("_");
        }
        System.out.println();
        for (int i = 0; i < this.numVertice; i++) {
            System.out.print(rotulo[i] + " |");
            for (int j = 0; j < this.numVertice; j++) {
                System.out.print(this.matrizAdj[i][j] + " ");
            }
            System.out.println();
        }
    }

    //atividade 05
    @Override
    public boolean isCompleto() {
        int formulaCompleto = (numVertice * (numVertice - 1)) / 2;
        return isSimples() && numAresta == formulaCompleto;
    }


    @Override
    public boolean isNulo() {
        if (numAresta == 0) {
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

    //atividade 06
    @Override
    public ArrayList<String> getListaVertices() {
        ArrayList<String> listVertices = new ArrayList<>();
        for (int i = 0; i < numVertice; i++) {
            listVertices.add(rotulo[i]);
        }
        return listVertices;
    }

    @Override
    public ArrayList<String> getListaArestas() {
        ArrayList<String> listArestas = new ArrayList<>();
        for (int i = 0; i < numVertice; i++) {
            for (int j = 0; j < numVertice; j++) {
                if (matrizAdj[i][j] == 1) {
                    listArestas.add(rotulo[i] + "-" + rotulo[j]);
                }
            }
        }
        return listArestas;
    }

    @Override
    public boolean isSubgrafo(Grafo grafo) {
        if (!grafo.getListaVertices().containsAll(this.getListaVertices())) return false;
        if (!grafo.getListaArestas().containsAll(this.getListaArestas())) return false;
        return true;
    }

    @Override
    public boolean isSubGrafoGerador(Grafo grafo) {
        if (grafo.getListaVertices().containsAll(this.getListaVertices())) return true;
        return grafo.getListaArestas().containsAll(this.getListaArestas());
    }

    @Override
    public boolean isSubGrafoIduzido(Grafo grafo) {
        if (!isSubgrafo(grafo)) return false;
        ArrayList<String> vertices = this.getListaArestas();

        for (String v : vertices) {
            for (String u : vertices) {
                if (!v.equals(u)) {
                    String arestas = u + "-" + v;
                    if (grafo.getListaArestas().contains(arestas) && !this.getListaArestas().contains(arestas)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //atividade 07

    @Override
    public boolean isGraficoIsomorfo(Grafo grafo) {
        // Etapa 1: checagem rápida
        if (numeroDeArestas() != grafo.numeroDeArestas()) return false;
        if (numeroDeVertices() != grafo.numeroDeVertices()) return false;

        int[] grau1 = sequenciaDeGraus();
        int[] grau2 = grafo.sequenciaDeGraus();
        Arrays.sort(grau1);
        Arrays.sort(grau2);
        if (!Arrays.equals(grau1, grau2)) return false;

        // Etapa 2: Permutar vértices do outro grafo
        List<String> vertices1 = this.getListaVertices();
        List<String> vertices2 = grafo.getListaVertices();

        List<List<String>> todasPermutacoes = gerarPermutacao(vertices2);

        for (List<String> permutacao : todasPermutacoes) {
            Map<String, String> mapping = new HashMap<>();
            for (int i = 0; i < vertices1.size(); i++) {
                mapping.put(vertices1.get(i), permutacao.get(i));
            }
            if (checarSeMapeamentoPreservaAdjacencia(this, grafo, mapping)) {
                return true;
            }
        }
        return false;
    }

    private boolean checarSeMapeamentoPreservaAdjacencia(Grafo g1, Grafo g2, Map<String, String> mapping) {
        List<String> arestas1 = g1.getListaArestas();
        List<String> arestas2 = g2.getListaArestas();

        for (String a : arestas1) {
            String[] partes = a.split("-");
            String u1 = partes[0], v1 = partes[1];
            String u2 = mapping.get(u1), v2 = mapping.get(v1);

            // Verifica se existe (u2,v2) ou (v2,u2)
            if (!(arestas2.contains(u2 + "-" + v2) || arestas2.contains(v2 + "-" + u2))) {
                return false;
            }
        }
        return true;
    }

    private List<List<String>> gerarPermutacao(List<String> vertices) {
        List<List<String>> resultado = new ArrayList<>();
        permutar(vertices, 0, resultado);
        return resultado;
    }

    private void permutar(List<String> list, int inicio, List<List<String>> resultado) {
        if (inicio == list.size() - 1) {
            resultado.add(new ArrayList<>(list));
            return;
        }
        for (int i = inicio; i < list.size(); i++) {
            Collections.swap(list, inicio, i);
            permutar(list, inicio + 1, resultado);
            Collections.swap(list, inicio, i);
        }
    }

//atividade 09

    @Override
    public boolean isPodeColorir(int v, int[] cores, int c) {
        for(int i = 0; i<numVertice; i++){
            if(matrizAdj[v][i] == 1 && cores[i] == c){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean colorirGrafo(int v, int[] cores, int max) {
        if(v == numVertice) return true;
        for(int c = 1; c <= max; c++){
            if(isPodeColorir(v, cores, c)){
                cores[v] = c;
                if(colorirGrafo(v+1, cores, max)){
                    return true;
                }
                cores[v] = 0;
            }
        }
        return false;
    }

    @Override
    public void colorir(String[] labels) {
        int[] cores = new int[numVertice];
        int max = numVertice;

        if(colorirGrafo(0, cores, max)){
            int maxCoresUsadas = Arrays.stream(cores).max().orElse(0);
            System.out.println("O minimo de horarios necessarios: "+maxCoresUsadas);
            for(int i = 0; i<numVertice; i++){
                System.out.println("Aulas: "+labels[i]+" -> horario: "+cores[i]);
            }
        }else{
            System.out.println("Nao foi possivel colorir os grafos");
        }
    }
}