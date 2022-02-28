package ru.novlk.lengthunitconverter.models;

public class Language {
    private String lang;
    private String abbLang;

    public Language(String lang, String abbLang) {
        this.lang = lang;
        this.abbLang = abbLang;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getAbbLang() {
        return abbLang;
    }

    public void setAbbLang(String abbLang) {
        this.abbLang = abbLang;
    }


}
