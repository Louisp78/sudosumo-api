package com.sudosumo.user.types;

public enum DifficultyEnum {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private String attribute;

    DifficultyEnum(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return this.attribute;
    }

    // Static method to convert a string to the corresponding enum
    public static DifficultyEnum fromAttribute(String attribute) {
        for (DifficultyEnum difficulty : DifficultyEnum.values()) {
            if (difficulty.getAttribute().equalsIgnoreCase(attribute)) {
                return difficulty;
            }
        }
        // If no matching abbreviation is found, you can either return null or throw an
        // exception
        throw new IllegalArgumentException("No enum constant with abbreviation " + attribute);
    }
}