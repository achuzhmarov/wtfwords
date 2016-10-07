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
            //"Берёза";
            //"Фотон";
            //"Галактика";
            //"Насекомые";
            //"Арифметика";
            //"Ладожское_озеро";
            //"Карибское_море";
            //"Ледоём";
            //"Эконометрика";
            //"Кикимора";
            //"Леший";
            //"Грош";
            //"Планеризм";

            //"DNA";
            //"Evolution";
            //"Metabolism";
            //"Virus";
            //"Genetics";
            //"Dodo";
            //"Emu";
            //"Tyrannosaurus";
            //"Frog";
            //"Oxygen";
            //"Titanium";
            //"Helium";
            //"Diamond";
            //"Cabbage";
            //"Sea";
            //"Lion";
            //"Koala";
            //"Raccoon";
            //"Blue_whale";
            //"Elephant";
            //"Atheism";
            //"Kitsune";
            //"Vampire";
            //"Gliding";
            //"Space_Invaders";
            //"Japan";
            //"Canada";
            //"Irish_phonology";
            //"Batman";
            //"Archimedes";
            //"Firefly_(TV_series)";
            //"Jack_Sparrow";
            //"Tropical_cyclone";
            //"Tornado";
            //"Wind";
            //"Confirmation_bias";
            //"Asteroid_belt";
            //"Atom";
            //"Big_Bang";
            //"Dwarf_planet";
            //"Earth";
            //"Electron";
            //"Galaxy";
            //"Hubble_Space_Telescope";
            //"Jupiter";
            //"Moon";
            //"Saturn";
            //"Sun";
            //"Star";
            "Mercury_(planet)";

        WikiParser wikiParser = new WikiParser();

        String result = wikiParser.parseWikiPage(pageUrl);

        try(  PrintWriter out = new PrintWriter(pageUrl.toLowerCase().replace("_", "") + ".txt")  ){
            out.print( result.trim() );
        }
    }
}

class WikiParser {
    private static int MIN_CHARS_IN_SENTENCE = 20;
    //private String baseWikiLink = "https://ru.wikipedia.org/wiki/";
    private String baseWikiLink = "https://en.wikipedia.org/wiki/";

    public String parseWikiPage(String pageUrl) {
        String result = pageUrl;
        result += System.lineSeparator();
        result += pageUrl.replace("_", " ");
        result += System.lineSeparator();

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

            try {
                String toBeReplaced = result.substring(startIndex, endIndex + 1);
                result = result.replace(toBeReplaced, "");
            } catch (Exception e) {
                e.printStackTrace();
                //return same result for manual analysis
                break;
            }
        }

        return result;
    }
}