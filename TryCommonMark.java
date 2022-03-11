import java.util.ArrayList;

import org.commonmark.node.*;
import org.commonmark.parser.*;
import org.commonmark.renderer.html.*;
class TryCommonMark {
public static void main(String[] args) {

Parser parser = Parser.builder().build();
Node document = parser.parse("This is *Sparta*");
HtmlRenderer renderer = HtmlRenderer.builder().build();
System.out.println(renderer.render(document));  // "<p>This is <em>Sparta</em></p>\n"

// this part actually does the computation
Node node = parser.parse("Example\n=======\n\nSome more text\n[Link](www.hi.com)\n[Link2](www.hello.com)");
LinkCountVisitor visitor = new LinkCountVisitor();
node.accept(visitor);
System.out.println(visitor.toRet);  // 4
}
// this class can be defined anywhere in the file
static class WordCountVisitor extends AbstractVisitor {
    int wordCount = 0;

    @Override
    public void visit(Text text) {
        // This is called for all Text nodes. Override other visit methods for other node types.

        // Count words (this is just an example, don't actually do it this way for various reasons).
        wordCount += text.getLiteral().split("\\W+").length;
        System.out.println(text.getLiteral());
        // Descend into children (could be omitted in this case because Text nodes don't have children).
        visitChildren(text);
    }
}

static class LinkCountVisitor extends AbstractVisitor {
    int linkCount = 0;
    ArrayList<String> toRet = new ArrayList<String>();
    @Override
    public void visit(Link link) {
        // This is called for all Text nodes. Override other visit methods for other node types.

        // Count words (this is just an example, don't actually do it this way for various reasons).
        linkCount += link.getDestination().split(" ").length;
        toRet.add(link.getDestination());
        //System.out.println(link.getDestination());
        // Descend into children (could be omitted in this case because Text
        // nodes don't have children).
        visitChildren(link);
    }
}
}