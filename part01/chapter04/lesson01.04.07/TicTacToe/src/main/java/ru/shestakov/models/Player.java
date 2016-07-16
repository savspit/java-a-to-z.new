package ru.shestakov.models;

public class Player {
    private Chip chip;
    private boolean isBot;

    public boolean getIsBot() {
        return this.isBot;
    }

    public void setIsBot(boolean isBot) {
        this.isBot = isBot;
    }

    public Chip getChip() {
        return this.chip;
    }

    public void setChip(Chip chip) {
        this.chip = chip;
    }

}
