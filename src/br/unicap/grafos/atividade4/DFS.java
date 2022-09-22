package br.unicap.grafos.atividade4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class DFS {

    static LinkedList<LinkedList<Integer>> g = new LinkedList<>();
    static String cor[];
    static String linha;
    static int i[];
    static int ante[];
    static int f[];
    static int tempo = 0;
    static int ordem;
    static int tamanho;
    static int verticeInicio;

    public static void main(String[] args) throws IOException {

        leitorTxt("C:\\Users\\luizn\\Documents\\NetBeansProjects\\Atividade4Grafos\\src\\br\\unicap\\grafos\\atividade4\\grafo.txt");

        //escolher vértice inicial
        verticeInicio = 0;

        System.out.println("Percorrendo Grafo iniciando do vértice: "+verticeInicio+"\n");
        System.out.println(verticeInicio);
        DFSstart(g, verticeInicio);
        
        //exibe situação final dos vértices: cores e (i/f)
        System.out.println("=======");
        System.out.println("STATUS FINAL DO GRAFO: \n");
        detalhesVertices();

    }

    static void DFSvisit(LinkedList<LinkedList<Integer>> g, int u) {
        cor[u] = "CINZA";
        i[u] = tempo++;
        for (Integer v : g.get(u)) {
            if ("BRANCO".equals(cor[v])) {
                ante[v] = u;
                detalhesVertices(); //exibe situação dos vértices a cada iteração
                printPath(v);

                DFSvisit(g, v);
            }
        }
        cor[u] = "PRETO";
        f[u] = tempo++;

    }

    static void DFSstart(LinkedList<LinkedList<Integer>> g, int s) {
        for (int j = 0; j < g.size(); j++) {
            cor[j] = "BRANCO";
            i[j] = f[j] = ante[j] = -1;
        }
        tempo = 1;
        DFSvisit(g, s);
    }

    static void printPath(int v) {
        if (v == verticeInicio) {
            System.out.println(v);
        } else {
            if (ante[v] == -1) {
                System.out.println("Não há caminho!");
            } else {
                printPath(ante[v]);
                System.out.println(v);
            }
        }
    }

    static void detalhesVertices() {
        for (int j = 0; j < g.size(); j++) {
            System.out.println("Vértice: "+ j + " => cor: " + cor[j]+" | ("+i[j]+"/"+f[j]+")");
        }
    }

    public static void leitorTxt(String caminho) throws FileNotFoundException, IOException {
        try (BufferedReader buffRead = new BufferedReader(new FileReader(caminho))) {
            linha = buffRead.readLine();
            ordem = Integer.parseInt(linha); //capturar ordem (nº de vértices)
            
            linha = buffRead.readLine();
            tamanho = Integer.parseInt(linha); //capturar tamanho (nº de arestas)
            
            for (int j = 0; j < ordem; ++j) {  //criando lista de adjacência
                g.add(new LinkedList<>());
            }
            
            //inicializando vetores
            cor = new String[ordem];
            i = new int[ordem];
            ante = new int[ordem];
            f = new int[ordem];
            
            //lendo txt e adicionando arestas
            linha = buffRead.readLine();
            while (linha != null) {
                String vu[] = linha.split(" ");
                int v = Integer.parseInt(vu[0]);
                int u = Integer.parseInt(vu[1]);
                g.get(v).add(u);
                g.get(u).add(v);
                linha = buffRead.readLine();
            }
        }
    }

}
