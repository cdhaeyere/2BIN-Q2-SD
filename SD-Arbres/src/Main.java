public class Main {
  public static void main(String[] args){
    Tree l1 = new Tree(1);
    Tree l3 = new Tree(3);
    Tree l5 = new Tree(5);
    Tree l7 = new Tree(7);

    Tree t2 = new Tree(2, new Tree[]{l1, l3});
    Tree t6 = new Tree(6, new Tree[]{l7});

    Tree t4 = new Tree(4, new Tree[]{t2, l5, t6});

    System.out.println(Trees.nbrLeaves(t4));


    Tree[] ls = Trees.flattenLeaves(t4);
    System.out.println("Les " + ls.length + " feuilles");
    int i = 0;
    while(i != ls.length) {
      System.out.println(ls[i].getValue());
      i++;
    }

    System.out.println("Path V1");
    Trees.pathV1(l7);

    System.out.println("Path V2");
    Trees.pathV2(l7);

    System.out.println("Excercice 1");
    System.out.println("Nombre de noeuds: " + Trees.nbrNodes(t4) + " (attendu: 7)");
    System.out.println("Minimum: " + Trees.min(t4) + " (attendu: 1)");
    System.out.println("Somme: " + Trees.sum(t4) + " (attendu: 28)");
    System.out.println("Egalité: " + Trees.equals(t4, t4) + " (attendu: true)");
    System.out.println("Profondeur: " + Trees.depth(l7) + " (attendu: 3)");
    System.out.println("Même noeud: " + Trees.sameOne(l7, l7) + " (attendu: true)");

    System.out.println("DFS");
    Trees.dfsPrint(t4);

    System.out.println("BFS");
    Trees.bfsPrint(t4);

    System.out.println("Excercice 2");
    System.out.println("PrintPathV1");
    Trees.printPathV1(t4);
    System.out.println("PrintPathV2");
    Trees.printPathV2(t4);
    System.out.println("PrintPathV3");
    Trees.printPathV3(t4, 7);

    System.out.println("Excercice 3");
    int[][] m = Trees.toArray(t4);

    for (int[] row : m) {
      for (int col : row) {
        System.out.print(col + " ");
      }
      System.out.println();
    }

    Tree t = Trees.toTree(m);
    Trees.bfsPrint(t);

    System.out.println("Excercice 4");
    System.out.println("LCA: " + Trees.lca(l1, l7).getValue() + " (attendu: 4)");
  }
}
