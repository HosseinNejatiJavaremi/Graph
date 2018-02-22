package graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.graphstream.algorithm.Toolkit;
import org.graphstream.algorithm.coloring.WelshPowell;
import org.graphstream.graph.Graph;
import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.HypercubeGenerator;
import org.graphstream.algorithm.generator.LobsterGenerator;
import org.graphstream.algorithm.generator.PetersenGraphGenerator;
import org.graphstream.algorithm.generator.lcf.HeawoodGraphGenerator;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import static org.graphstream.algorithm.Toolkit.*;

public class DrawingGraph {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello my dear classmate");
        while (true) {

            System.out.println("Specify the type of graph");
            System.out.println("1)path graph 2)cycle graph 3)complete graph");
            System.out.println("4)complete bipartite graph 5)complete multi partite graph");
            System.out.println("6)hypercube graph 7)Heawood graph 8)Petersen graph 9)tree");
            System.out.println("10)desired graph 11)exit");

            int graphTest = in.nextInt();

            if (graphTest == 1) {
                System.out.println("Enter number of vertices");
                Graph graph = new SingleGraph("path graph");
                int verticesNum = in.nextInt();
                for (int i = 1; i <= verticesNum; i++) {
                    graph.addNode(String.valueOf(i));
                }
                for (int i = 1; i < verticesNum; i++) {
                    graph.addEdge(String.valueOf(i), String.valueOf(i), String.valueOf(i + 1));
                }
                graph.display();
                propertyOfGraph(in, graph);
                choromaticNumerOfGraph(in, graph);
                int[][] adjacencyMatrix = adjancyMatrix(graph);
                printMatrix(adjacencyMatrix , in);
                complementOfGraph(in, graph);
            }

            if (graphTest == 2) {
                System.out.println("Enter number of vertices (n>2)");
                Graph graph = new SingleGraph("cycle graph");
                int verticesNum = in.nextInt();
                if (verticesNum >= 3) {
                    for (int i = 1; i <= verticesNum; i++) {
                        graph.addNode(String.valueOf(i));
                    }
                    for (int i = 1; i < verticesNum; i++) {
                        graph.addEdge(String.valueOf(i), String.valueOf(i), String.valueOf(i + 1));
                    }
                    graph.addEdge(String.valueOf(verticesNum), String.valueOf(verticesNum), String.valueOf(1));
                    graph.display();
                    propertyOfGraph(in, graph);
                    choromaticNumerOfGraph(in, graph);
                    int[][] adjacencyMatrix = adjancyMatrix(graph);
                    printMatrix(adjacencyMatrix , in);
                    complementOfGraph(in, graph);
                } else {
                    System.out.println("you enter wrong number");
                }
            }

            if (graphTest == 3) {
                System.out.println("Enter number of vertices (n>2)");
                Graph graph = new SingleGraph("complete graph");
                int verticesNum = in.nextInt();
                if (verticesNum >= 3) {
                    for (int i = 1; i <= verticesNum; i++) {
                        graph.addNode(String.valueOf(i));
                    }
                    for (int i = 1; i < verticesNum; i++) {
                        for (int j = i + 1; j <= verticesNum; j++) {
                            graph.addEdge(String.valueOf(i) + String.valueOf(j), String.valueOf(i), String.valueOf(j));
                        }
                    }
                    graph.display();
                    propertyOfGraph(in, graph);
                    choromaticNumerOfGraph(in, graph);
                    int[][] adjacencyMatrix = adjancyMatrix(graph);
                    printMatrix(adjacencyMatrix , in);
                    complementOfGraph(in, graph);
                } else {
                    System.out.println("you enter wrong number");
                }
            }

            if (graphTest == 4) {
                System.out.println("Enter the number of vertices of the first part");
                int firstPartNum = in.nextInt();
                System.out.println("Enter the number of vertices of the second part");
                int secondPartNum = in.nextInt();
                Graph graph = new SingleGraph("complete bipartite graph");
                for (int i = 1; i <= firstPartNum; i++) {
                    graph.addNode(String.valueOf(i));
                }

                int k = 1;
                for (int i = firstPartNum + 1; i <= firstPartNum + secondPartNum; i++) {
                    graph.addNode(String.valueOf(i));
                }
                for (int i = 1; i <= firstPartNum; i++) {
                    for (int j = firstPartNum + 1; j <= firstPartNum + secondPartNum; j++) {
                        graph.addEdge(String.valueOf(k), String.valueOf(i), String.valueOf(j));
                        k++;
                    }
                }
                graph.display();
                propertyOfGraph(in, graph);
                choromaticNumerOfGraph(in, graph);
                int[][] adjacencyMatrix = adjancyMatrix(graph);
                printMatrix(adjacencyMatrix , in);
                complementOfGraph(in, graph);
            }

            if (graphTest == 5) {
                Graph graph = new SingleGraph("complete multi partite graph");
                System.out.println("Enter the number part");
                int partNum = in.nextInt();
                int[] numberOfvertices = new int[partNum];
                for (int i = 1; i <= partNum; i++) {
                    System.out.println("Enter the number of vertices of part " + i);
                    numberOfvertices[i - 1] = in.nextInt();
                }
                int k = 1;
                for (int i = 0; i < partNum; i++) {
                    for (int j = 1; j <= numberOfvertices[i]; j++) {
                        graph.addNode(String.valueOf(k));
                        k++;
                    }
                }

                k = 1;
                for (int l = 0; l < partNum - 1; l++) {
                    for (int i = 1; i <= verticesCounter(numberOfvertices, l); i++) {
                        for (int j = verticesCounter(numberOfvertices, l) + 1; j <= verticesCounter(numberOfvertices, l) + numberOfvertices[l + 1]; j++) {
                            graph.addEdge(String.valueOf(k), String.valueOf(i), String.valueOf(j));
                            k++;
                        }
                    }
                }
                graph.display();
                propertyOfGraph(in, graph);
                choromaticNumerOfGraph(in, graph);
                int[][] adjacencyMatrix = adjancyMatrix(graph);
                printMatrix(adjacencyMatrix , in);
                complementOfGraph(in, graph);
            }

            if (graphTest == 6) {
                System.out.println("Enter the size set of the vertices");
                int n = in.nextInt();
                Graph graph = new SingleGraph("hypercube");
                Generator gen = new HypercubeGenerator();
                gen.addSink(graph);
                gen.begin();
                for (int dim = 1; dim <= n; dim++) {
                    gen.nextEvents();
                }
                gen.end();
                graph.display();
                propertyOfGraph(in, graph);
                choromaticNumerOfGraph(in, graph);
                int[][] adjacencyMatrix = adjancyMatrix(graph);
                printMatrix(adjacencyMatrix , in);
                complementOfGraph(in, graph);
            }
            if (graphTest == 7) {
                Graph graph = new SingleGraph("HeawoodGraph");
                Generator gen = new HeawoodGraphGenerator();
                gen.addSink(graph);
                gen.begin();
                gen.nextEvents();
                gen.end();
                graph.display();
                propertyOfGraph(in, graph);
                choromaticNumerOfGraph(in, graph);
                int[][] adjacencyMatrix = adjancyMatrix(graph);
                printMatrix(adjacencyMatrix , in);
                complementOfGraph(in, graph);
            }

            if (graphTest == 8) {
                Graph graph = new SingleGraph("PetersenGraph");
                Generator gen = new PetersenGraphGenerator();
                gen.addSink(graph);
                gen.begin();
                gen.nextEvents();
                gen.end();
                graph.display();
                propertyOfGraph(in, graph);
                choromaticNumerOfGraph(in, graph);
                int[][] adjacencyMatrix = adjancyMatrix(graph);
                printMatrix(adjacencyMatrix , in);
                complementOfGraph(in, graph);
            }

            if (graphTest == 9) {
                System.out.println("Enter the size set of the vertices");
                int n = in.nextInt();
                Graph graph = new SingleGraph("tree");
                Generator gen = new LobsterGenerator();
                gen.addSink(graph);
                gen.begin();
                for (int dim = 1; dim < n; dim++) {
                    gen.nextEvents();
                }
                gen.end();
                graph.display();
                propertyOfGraph(in, graph);
                choromaticNumerOfGraph(in, graph);
                int[][] adjacencyMatrix = adjancyMatrix(graph);
                printMatrix(adjacencyMatrix , in);
                complementOfGraph(in, graph);
            }

            if (graphTest == 10) {
                System.out.println("Enter number of vertices (n>2)");
                Graph graph = new SingleGraph("desired graph");
                int verticesNum = in.nextInt();
                for (int i = 0; i < verticesNum; i++) {
                    graph.addNode(String.valueOf(i));
                }
                for (int i = 0; i < graph.getNodeCount(); i++) {
                    for (int j = i+1; j < graph.getNodeCount() ; j++) {
                        System.out.println("Is there an edge " + String.valueOf(i) +String.valueOf(j) +"?(y/n)");
                        String temp = in.next();
                        if( temp.split("")[0].equalsIgnoreCase("y")){
                            graph.addEdge(String.valueOf(i) + String.valueOf(j), String.valueOf(i), String.valueOf(j));
                        }
                    }
                }
                graph.display();
                propertyOfGraph(in, graph);
                choromaticNumerOfGraph(in, graph);
                int[][] adjacencyMatrix = adjancyMatrix(graph);
                printMatrix(adjacencyMatrix , in);
                complementOfGraph(in, graph);
            }

            if (graphTest == 11) {
                break;
            }
        }
    }

    private static void complementOfGraph(Scanner in, Graph graph) {
        System.out.println("Would you like to see complement of graph?(y,n)");
        String test = in.next();
        if (test.split("")[0].equalsIgnoreCase("y")) {
            Graph complementGraph = new SingleGraph("complement of graph");
            for (int i = 0; i < graph.getNodeCount(); i++) {
                complementGraph.addNode(String.valueOf(i));
            }
            for (int i = 0; i < graph.getNodeCount() ; i++) {
                Node node = graph.getNode(i);
                for (int j = i + 1; j < graph.getNodeCount(); j++) {
                    if (!node.hasEdgeBetween(j)) {
                        complementGraph.addEdge(String.valueOf(i) + String.valueOf(j), String.valueOf(i), String.valueOf(j));
                    }
                }
            }
            complementGraph.display();
            propertyOfGraph(in, complementGraph);
            choromaticNumerOfGraph(in, complementGraph);
            int[][] adjacencyMatrix = adjancyMatrix(complementGraph);
            printMatrix(adjacencyMatrix , in);
        }
    }

    private static int[][] adjancyMatrix(Graph graph) {
        int n = graph.getNodeCount();
        int adjacencyMatrix[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjacencyMatrix[i][j] = graph.getNode(i).hasEdgeBetween(j) ? 1 : 0;
            }
        }
        return adjacencyMatrix;
    }

    private static void choromaticNumerOfGraph(Scanner in, Graph graph) {
        System.out.println("Would you like to see chromatic number of a graph?(y,n)");
        String chromaticNumberTest = in.next();
        if (chromaticNumberTest.split("")[0].equalsIgnoreCase("y")) {
            WelshPowell wp = new WelshPowell("color");
            wp.init(graph);
            wp.compute();
            System.out.println("The chromatic number of this graph is : " + wp.getChromaticNumber());
            for (Node n : graph) {
                System.out.println("Node " + n.getId() + " : color " + n.getAttribute("color"));
            }
            Color[] cols = new Color[wp.getChromaticNumber()];
            for (int i = 0; i < wp.getChromaticNumber(); i++) {
                cols[i] = Color.getHSBColor((float) (Math.random()), 0.8f, 0.9f);
            }
            for (Node n : graph) {
                int col = (int) n.getNumber("color");
                n.addAttribute("ui.style", "fill-color:rgba(" + cols[col].getRed() + "," + cols[col].getGreen() + "," + cols[col].getBlue() + ",200);");
            }
        }
    }

    private static void propertyOfGraph(Scanner in, Graph graph) {
        System.out.println();
        System.out.println("Would you like to see the properties of Graph?(y/n)");
        String propertiesTest = in.next();
        if (propertiesTest.split("")[0].equalsIgnoreCase("y")) {
            System.out.println("THe number of edges = " + graph.getEdgeCount());
            System.out.println("diameter = " + diameter(graph));
            int min = getMin(graph);
            int max = getMax(graph);
            System.out.println("min degree = " + min);
            System.out.println("max degree = " + max);
            isRegular(min, max);
            System.out.println("average degree = " + averageDegree(graph));
            ConnectedComponents cc = new ConnectedComponents();
            cc.init(graph);
            System.out.println("number of Connected Components = " + cc.getConnectedComponentsCount());
            System.out.println("The number of vertices of The biggest Components = " + cc.getGiantComponent());
            maxClique(graph);
            System.out.println();
        }
    }

    private static void maxClique(Graph graph) {
        List<Node> maximumClique = new ArrayList<>();
        for (List<Node> clique : Toolkit.getMaximalCliques(graph))
            if (clique.size() > maximumClique.size())
                maximumClique = clique;
        System.out.println("maximum Clique = " + maximumClique);
        System.out.println("maximum Clique size = " + maximumClique.size());
    }

    private static void isRegular(int min, int max) {
        if (min == max) {
            System.out.println("graph is " + min + "-regular");
        }
    }

    private static int getMax(Graph graph) {
        int max = 0;
        Node e;
        for (int i = 0; i < graph.getNodeCount(); i++) {
            e = graph.getNode(i);
            if (e.getDegree() >= max) {
                max = e.getDegree();
            }
        }
        return max;
    }

    private static int getMin(Graph graph) {
        int min = Integer.MAX_VALUE;
        Node e;
        for (int i = 0; i < graph.getNodeCount(); i++) {
            e = graph.getNode(i);
            if (e.getDegree() <= min) {
                min = e.getDegree();
            }
        }
        return min;
    }

    private static int verticesCounter(int[] numberOfvertices, int i) {
        int count = 0;
        for (int j = 0; j <= i; j++) {
            count += numberOfvertices[j];
        }
        return count;
    }

    private static void printMatrix(int[][] m , Scanner in) {
        System.out.println();
        System.out.println("Would you like to see the adjacency matrix of Graph?(y/n)");
        String propertiesTest = in.next();
        if (propertiesTest.split("")[0].equalsIgnoreCase("y")) {
            try {
                int columns = m[0].length;
                StringBuilder str = new StringBuilder("|\t");

                for (int[] aM : m) {
                    for (int j = 0; j < columns; j++) {
                        str.append(aM[j]).append("\t");
                    }

                    System.out.println(str + "|");
                    str = new StringBuilder("|\t");
                }
                System.out.println("");
            } catch (Exception e) {
                System.out.println("Matrix is empty!!");
            }
        }
    }
}