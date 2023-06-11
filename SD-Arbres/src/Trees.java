import java.util.ArrayDeque;
import java.util.Deque;

public class Trees {
	// *******************************************************
	// Un premier exemple: le nombre de feuilles d'un arbre
	// *******************************************************
	public static int nbrLeaves(Tree t) {
		int r;
		if (t.getChildren().length == 0) {
			r = 1;
		} else {
			r = 0;
			for (Tree tChild : t.getChildren()) {
				r += nbrLeaves(tChild);
			}
		}
		return r;
	}

	// *******************************************************
	// Un deuxième exemple: aplanir un arbre
	// *******************************************************
	public static Tree[] flattenLeaves(Tree t) {
		int nl = nbrLeaves(t);
		Tree[] r = new Tree[nl];
		flattenLeaves(t, r, 0);
		return r;
	}

	private static int flattenLeaves(Tree t, Tree[] a, int idx) {
		int r;
		if (t.getChildren().length == 0) {
			a[idx] = t;
			r = 1;
		} else {
			r = 0;
			for (Tree tChild : t.getChildren()) {
				r += flattenLeaves(tChild, a, idx + r);

			}
		}
		return r;
	}

	// *******************************************************
	// Un troisième exemple:
	// tous les algorithmes ne sont pas récursifs
	// *******************************************************
	public static void pathV1(Tree t) {
		System.out.println(t.getValue());
		if (t.getParent() != null) {
			pathV1(t.getParent());
		}
	}

	public static void pathV2(Tree t) {
		while (t != null) {
			System.out.println(t.getValue());
			t = t.getParent();
		}
	}

	// *******************************************************
	// Exercices 1
	// *******************************************************

	// 1.1)
	public static int nbrNodes(Tree t) {
		int r = 1;
		for (Tree tChild : t.getChildren()) {
			r += nbrNodes(tChild);
		}
		return r;
	}

	// 1.2)
	public static int min(Tree t) {
		int min = t.getValue();
		for (Tree tChild : t.getChildren()) {
			int minChild = min(tChild);
			if (minChild < min) {
				min = minChild;
			}
		}
		return min;
	}

	// 1.3)
	public static int sum(Tree t) {
		int sum = t.getValue();
		for (Tree tChild : t.getChildren()) {
			sum += sum(tChild);
		}
		return sum;
	}

	// 1.4)
	public static boolean equals(Tree t1, Tree t2) {
		if (t1.getValue() != t2.getValue() || t1.getChildren().length != t2.getChildren().length) {
			return false;
		}
		for (int i = 0; i < t1.getChildren().length; i++) {
			if (!equals(t1.getChildren()[i], t2.getChildren()[i])) {
				return false;
			}
		}
		return true;
	}

	// 1.5)
	public static int depth(Tree n) {
		if (n.getParent() != null) {
			return 1 + depth(n.getParent());
		}
		return 0;
	}

	// 1.6)
	public static boolean sameOne(Tree n1, Tree n2) {
		if (n2.getParent() != null) {
			return sameOne(n1, n2.getParent());
		}
		if (n1.getParent() != null) {
			return sameOne(n1.getParent(), n2);
		}
		return n1 == n2;
	}

	// 1.7)
	public static void dfsPrint(Tree t) {
		System.out.println(t.getValue());
		for (Tree tChild : t.getChildren()) {
			dfsPrint(tChild);
		}
	}

	// 1.8)
	public static void bfsPrint(Tree t) {
		Deque<Tree> file = new ArrayDeque<>();
		file.push(t);
		bfsPrint(file);
	}

	private static void bfsPrint(Deque<Tree> file) {
		if (!file.isEmpty()) {
			Tree t = file.pop();
			System.out.println(t.getValue());
			for (Tree tChild : t.getChildren()) {
				file.addLast(tChild);
			}
			bfsPrint(file);
		}
	}

	// *******************************************************
	// Exercices 2
	// *******************************************************

	// 2.1)
	public static void printPathV1(Tree n) {
		if (n.getParent() != null) {
			printPathV1(n.getParent());
		}
		System.out.println(n.getValue());
	}

	// 2.2)
	public static void printPathV2(Tree n) {
		int depth = depth(n);
		Tree[] parents = new Tree[depth + 1];
		while (n != null) {
			parents[depth] = n;
			n = n.getParent();
			depth--;
		}
		for (Tree tParent : parents) {
			System.out.println(tParent.getValue());
		}
	}

	// 2.3
	public static void printPathV3(Tree t, int v) {
		printPathV3(t, v, false);
	}

	private static boolean printPathV3(Tree t, int v, boolean r) {
		if (t.getValue() == v) {
			printPathV1(t);
			r = true;
		} else {
			for (int i = 0; i != t.getChildren().length && !r; i++) {
				r = printPathV3(t.getChildren()[i], v, r);
			}
		}
		return r;
	}

	// *******************************************************
	// Exercices 3
	// *******************************************************

	// 3.1
	public static int[][] toArray(Tree t) {
		int[][] tab = new int[nbrNodes(t)][3];
		toArray(t, tab, 0);
		return tab;
	}

	private static int toArray(Tree t, int[][] tab, int idx) {
		int r = 1;
		tab[idx][0] = t.getValue();
		int tLength = t.getChildren().length;
		if (tLength == 0) {
			tab[idx][1] = idx;
			tab[idx][2] = idx;
		} else if (tLength != 0) {
			tab[idx][1] = idx + 1;
			r += toArray(t.getChildren()[0], tab, tab[idx][1]);
			if (tLength == 1) {
				tab[idx][2] = idx;
			} else {
				tab[idx][2] = idx + r;
				r += toArray(t.getChildren()[1], tab, tab[idx][2]);
			}
		}
		return r;
	}

	// 3.2
	public static Tree toTree(int[][] t) {
		return toTree(t, 0);
	}

	private static Tree toTree(int[][] t, int idx) {
		Tree[] children = new Tree[] {};
		if (t[idx][1] != idx && t[idx][2] != idx) {
			children = new Tree[] { toTree(t, t[idx][1]), toTree(t, t[idx][2]) };
		} else if (t[idx][2] == idx && t[idx][1] != idx) {
			children = new Tree[] { toTree(t, t[idx][1]) };
		} else if (t[idx][1] == idx && t[idx][2] != idx) {
			children = new Tree[] { toTree(t, t[idx][2]) };
		}
		return new Tree(t[idx][0], children);
	}

	// *******************************************************
	// Exercices 4
	// *******************************************************

	public static Tree lca(Tree n1, Tree n2) {
		return lca(n1, n2, depth(n1), depth(n2));
	}

	private static Tree lca(Tree n1, Tree n2, int depthN1, int depthN2) {
		if (n1 == n2) {
			return n1;
		}
		if (depthN1 < depthN2) {
			return lca(n1, n2.getParent(), depthN1, depthN2 - 1);
		} else {
			return lca(n1.getParent(), n2, depthN1 - 1, depthN2);
		}
	}
}
