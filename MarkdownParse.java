
// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.commonmark.node.*;
import org.commonmark.parser.*;
import org.commonmark.renderer.html.*;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        Parser parser = Parser.builder().build();
        Node node = parser.parse("Example\n=======\n\nSome more text\n[Link](www.hi.com)\n[Link2](www.hello.com)");
        TryCommonMark.LinkCountVisitor visitor = new TryCommonMark.LinkCountVisitor();
        node.accept(visitor);
        return visitor.toRet;
    }

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }

    public static String converter(String filename) throws IOException{
        Path fileName = Path.of(filename);
        String contents = Files.readString(fileName);
        return contents;
    }
}