package com.saboo;

public class TopicModal {
    boolean solved;
    String text;

    public TopicModal() {
    }

    public TopicModal(boolean solved, String text) {
        this.solved = solved;
        this.text = text;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
