package models;

public class Neutralizer {
    public static String neutralize(String param){
        return param == null ? "" : param.replaceAll("<", "&lt")
                .replaceAll(">", "&gt");
    }
}
