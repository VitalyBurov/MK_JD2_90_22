package web.taskAPI.core.dto;

import java.time.LocalDateTime;

public class Message {
    private LocalDateTime dateAndTime;
    private String fromWhom;
    private String toWhom;
    private String content;


    public Message(String fromWhom, String toWhom, String content) {
        this.dateAndTime = LocalDateTime.now();
        this.fromWhom = fromWhom;
        this.toWhom = toWhom;
        this.content = content;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getFromWhom() {
        return fromWhom;
    }

    public void setFromWhom(String fromWhom) {
        this.fromWhom = fromWhom;
    }

    public String getToWhom() {
        return toWhom;
    }

    public void setToWhom(String toWhom) {
        this.toWhom = toWhom;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
