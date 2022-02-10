MarkdownParse.class : MarkdownParse.java
	javac -cp .:lib/* MarkdownParse.java

MarkdownParseTest.class : MarkdownParse.class MarkdownParseTest.java
	javac -cp .:lib/* MarkdownParseTest.java

test: MarkdownParseTest.class
	java -cp .:lib/* org.junit.runner.JUnitCore MarkdownParseTest