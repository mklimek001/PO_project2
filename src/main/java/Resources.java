public enum Resources {
    ARMY,
    GOLD,
    FOOD,
    TECHNOLOGY;


    public String toString() {
        switch (this) {
            case ARMY:
                return "Wojsko";
            case GOLD:
                return "Złoto";
            case FOOD:
                return "Pożywienie";
            case TECHNOLOGY:
                return "Technologia";
        }
        return " ";
    }


}
