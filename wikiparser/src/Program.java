import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.PrintWriter;

public class Program {
    public static void main(String[] args) throws Exception {
        String pageUrl =
            //"Го";
            //"Хаббл_(телескоп)";
            //"Международная_космическая_станция";
            //"Октоберфест";
            //"Индуизм";
            //"Белая_акула";
            //"Синий_кит";
            //"Дезоксирибонуклеиновая_кислота";
            //"Эволюция";
            //"Руны";
            //"Банан";
            //"Юпитер";
            //"Солнце";
            //"Тигр";
            //"Ёж_Соник";
            //"Покемон";
            //"Тираннозавр";
            //"Кошка";
            //"Удод";
            "Берёза";

        WikiParser wikiParser = new WikiParser();

        String result = wikiParser.parseWikiPage(pageUrl);

        try(  PrintWriter out = new PrintWriter("test.txt")  ){
            out.print( result.trim() );
        }
    }
}

class WikiParser {
    private static int MIN_CHARS_IN_SENTENCE = 20;
    private String baseWikiLink = "https://ru.wikipedia.org/wiki/";

    public String parseWikiPage(String pageUrl) {
        String result = "";

        try {
            Document doc = Jsoup.connect(baseWikiLink + pageUrl).get();
            Elements body = doc.select("#mw-content-text > p");

            for (Element element: body) {
                String text = element.text();
                if (text.length() > 0) {
                    String preparedText = cutSquareLinks(text);
                    String[] sentences = preparedText.split("\\.");

                    String newSentence = "";

                    for (String sentence: sentences) {
                        if (isNewSentence(sentence, newSentence)) {
                            result += parseSentence(newSentence);
                            newSentence = "";
                        }

                        newSentence += sentence + ".";
                    }

                    result += parseSentence(newSentence);
                }

                result += System.lineSeparator();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private String parseSentence(String sentence) {
        if (sentence.length() == 0) {
            return "";
        }

        System.out.println(sentence.length());

        return sentence.trim() + System.lineSeparator();
    }

    private boolean isNewSentence(String sentence, String prevSentence) {
        return  sentence.trim().length() > MIN_CHARS_IN_SENTENCE &&
                Character.isUpperCase(sentence.trim().codePointAt(0)) &&
                isNotEndWithInitial(prevSentence);
    }

    private boolean isNotEndWithInitial(String sentence) {
        String sentenceWithoutDots = sentence.replace(".", "").trim();
        if (sentenceWithoutDots.length() == 0) {
            return true;
        }

        int lastIndex = sentenceWithoutDots.length() - 1;
        if (Character.isUpperCase(sentenceWithoutDots.codePointAt(lastIndex))) {
            return false;
        }

        if (lastIndex != 0 && Character.isUpperCase(sentenceWithoutDots.codePointAt(lastIndex - 1))) {
            return false;
        }

        return true;
    }

    private String cutSquareLinks(String text) {
        return cutContentBetween(text, "[", "]");
    }

    private String cutContentBetween(String text, String begin, String end) {
        String result = text;

        while (result.indexOf(begin) != -1 && result.indexOf(end) != -1) {
            int startIndex = result.indexOf(begin);
            int endIndex = result.indexOf(end);

            String toBeReplaced = result.substring(startIndex, endIndex + 1);
            result = result.replace(toBeReplaced, "");
        }

        return result;
    }
}