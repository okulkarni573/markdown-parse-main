
// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while (currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if(nextOpenBracket<0){break;}
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if(nextCloseBracket<0){break;}
            int openParen = markdown.indexOf("(", nextCloseBracket);
            if(openParen<0){break;}
            int closeParen = markdown.indexOf(")", openParen);
            if (currentIndex < 0 || nextCloseBracket < 0 || nextOpenBracket < 0 || openParen < 0 || closeParen < 0) {
                break;
            }
            if (openParen == nextCloseBracket + 1) {
                if (nextOpenBracket != 0 && markdown.charAt(nextOpenBracket - 1) != '!') {
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                } else if (markdown.charAt(currentIndex) == '[')
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            currentIndex = closeParen + 1;

        }

        return toReturn;
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