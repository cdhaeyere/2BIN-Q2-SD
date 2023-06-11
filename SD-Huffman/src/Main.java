import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws IOException {
    String s = HuffmanReadFile.loadFile(new File("11-0.txt"));
    Map<Character, Integer> freq = Huffman.computeFreq(s);
    Huffman.Node root = Huffman.buildTree(freq);
    Map<Character, String> code = Huffman.buildCode(root);
    String compress = Huffman.compress(s, code);
    HuffmanWriteFile.write("fichier_compresse",compress);
    String texteOriginal =
        Huffman.expand(root,HuffmanReadFile.read("fichier_compresse"));
    System.out.println(texteOriginal);
  }
}
