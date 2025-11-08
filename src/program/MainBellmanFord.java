package program;

import util.bellmanFord.GrafoBellmanFord;

public class MainBellmanFord {
    public static void main(String[] args) {

        int v = 5;
        int u = 8;

        GrafoBellmanFord gbf = new GrafoBellmanFord(v, u);

        // ciclo negativo
        System.out.println("Ciclo negativo");

        gbf.addArestaPeso(0,1, 3);
        gbf.addArestaPeso(0,2, 5);
        gbf.addArestaPeso(1,3, -5);
        gbf.addArestaPeso(1,4, 8);
        gbf.addArestaPeso(2,3, 8);
        gbf.addArestaPeso(2,4, -9);
        gbf.addArestaPeso(3,4, -3);
        gbf.addArestaPeso(4,0, 2);

        gbf.bellmanFord(0);


        // ciclo positivo
        System.out.println();
        System.out.println("Ciclo positivo");
        gbf = new  GrafoBellmanFord( v, u);

        gbf.addArestaPeso(0,1, 3);
        gbf.addArestaPeso(0,2, 5);
        gbf.addArestaPeso(1,3, 5);
        gbf.addArestaPeso(1,4, 8);
        gbf.addArestaPeso(2,3, 8);
        gbf.addArestaPeso(2,4, 4);
        gbf.addArestaPeso(3,4, -3);
        gbf.addArestaPeso(4,0, 2);

        gbf.bellmanFord(0);
    }
}
