MarkdownParse.class : MarkdownParse.java
	javac -cp .:lib/* MarkdownParse.java

MarkdownParseTest.class : MarkdownParse.class MarkdownParseTest.java
	javac -cp .:lib/* MarkdownParseTest.java

test: MarkdownParseTest.class
	java -cp .:lib/* org.junit.runner.JUnitCore MarkdownParseTest

TryCommonMark.class : TryCommonMark.java
	javac -g -cp ".;lib\junit-4.12.jar;lib\hamcrest-core-1.3.jar;lib\commonmark-0.18.2.jar" TryCommonMark.java

actual: TryCommonMark.class
	java -cp ".;lib\junit-4.12.jar;lib\hamcrest-core-1.3.jar;lib\commonmark-0.18.2.jar" TryCommonMark