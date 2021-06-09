package com.example.autorize.exchange;

import java.util.Map;

public class Message {
    private String Date;
    private String PreviousDate;
    private String PreviousURL;
    private String Timestamp;
    private Map<String, MessageInsrt> Valute;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPreviousDate() {
        return PreviousDate;
    }

    public void setPreviousDate(String previousDate) {
        PreviousDate = previousDate;
    }

    public String getPreviousURL() {
        return PreviousURL;
    }

    public void setPreviousURL(String previousURL) {
        PreviousURL = previousURL;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    public Map<String, MessageInsrt> getValute() {
        return Valute;
    }

    public void setValute(Map<String, MessageInsrt> valute) {
        Valute = valute;
    }
}
