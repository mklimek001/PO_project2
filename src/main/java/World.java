import java.util.Scanner;

public class World {

    public static void main(String[] args){
        GameWindow gameWindow = new GameWindow();
        /*
        int currResp;

        System.out.println("Witaj w grze `WHICH KING ARE YOU?`");
        System.out.println("    Wybierz opcję wprowadzając liczbę: ");
        System.out.println("    1. Rozpocznij nową grę");
        System.out.println("    2. Wczytaj istniejącą grę");
        Scanner respondScanner = new Scanner(System.in);
        String firstResp = respondScanner.nextLine();
        currResp = Integer.parseInt(firstResp);

        if(currResp == 1){
            System.out.println("Wprowadź nazwę swojego królestwa: ");
            String kingdomName = respondScanner.nextLine();
            System.out.println("Witaj królu " + kingdomName + " !");

            Kingdom gamerKingdom = new Kingdom(kingdomName);

            while(gamerKingdom.isInGoodCondition){
                System.out.println("--------- dzień " + gamerKingdom.roundNum + " ---------");
                System.out.println("Posiadane zasoby: ");
                System.out.println("    Armia : " + gamerKingdom.currentResources.armyValue.value + "   Złoto: " + gamerKingdom.currentResources.goldValue.value + "    Żywność: "+ gamerKingdom.currentResources.foodValue.value + "   Technologia: " + gamerKingdom.currentResources.technologyValue.value);
                System.out.println(gamerKingdom.nextRoundCard.advice);
                System.out.println("    Wybierz opcję wprowadzając liczbę: ");
                System.out.println("    1. " + gamerKingdom.nextRoundCard.firstOption.shortProposition);
                System.out.println("    2. " + gamerKingdom.nextRoundCard.secondOption.shortProposition);
                currResp = Integer.parseInt( respondScanner.nextLine());
                if(currResp <= 1){
                    System.out.println(" -> " + gamerKingdom.nextRoundCard.firstOption.censequncesDesc);
                } else {

                    System.out.println(" -> " + gamerKingdom.nextRoundCard.secondOption.censequncesDesc);
                }

                System.out.println(" ");
                gamerKingdom.nextDay(currResp);
            }

            System.out.println("--------- Przegrano ---------");
            System.out.println("Królestwo " + gamerKingdom.kingdomName + " przetrwało " + gamerKingdom.roundNum + " rund!");
            System.out.println("    Armia : " + gamerKingdom.currentResources.armyValue.value + "   Złoto: " + gamerKingdom.currentResources.goldValue.value + "    Żywność: "+ gamerKingdom.currentResources.foodValue.value + "   Technologia: " + gamerKingdom.currentResources.technologyValue.value);

   */
    }

}
