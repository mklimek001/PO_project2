import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Kingdom {
    String kingdomName;
    ArrayList<ActionCard> cards;
    AllResources currentResources;
    ActionCard nextRoundCard;
    boolean isInGoodCondition;
    int roundNum;

    public Kingdom(String kingdomName){
        this.kingdomName = kingdomName;
        this.currentResources = new AllResources(50,50,50,50);
        this.cards = this.startingCards();
        this.roundNum = 0;
        this.nextRoundCard = this.generateRandomCard();
        this.isInGoodCondition = true;
    }

    public ActionCard generateRandomCard(){
        int length = this.cards.size();
        int randomIndex = (int)Math.floor(Math.random()*length);
        return this.cards.get(randomIndex);
    }

    public void nextDay(int decision){
        Decision takenOption = this.nextRoundCard.selectedOption(decision);
        this.currentResources.resourceChange(takenOption.censequncesResources);

        this.isInGoodCondition = !(this.currentResources.isAnyOut());

        if(!this.currentResources.isAnyOut()){
            this.nextRoundCard = this.generateRandomCard();
            this.roundNum+=1;
        }

    }

    public void saveToFile() throws FileNotFoundException {
        PrintWriter save = new PrintWriter("saves.txt");
        save.println(this.currentResources.armyValue.value);
        save.println(this.currentResources.goldValue.value);
        save.println(this.currentResources.foodValue.value);
        save.println(this.currentResources.technologyValue.value);
        save.println(this.roundNum);
        save.println(this.kingdomName);
        save.close();
    }

    public void downloadFromFile() throws FileNotFoundException {
        File saves = new File("saves.txt");
        Scanner read = new Scanner(saves);

        this.currentResources.armyValue.value = Integer.parseInt(read.nextLine());
        this.currentResources.goldValue.value = Integer.parseInt(read.nextLine());
        this.currentResources.foodValue.value = Integer.parseInt(read.nextLine());
        this.currentResources.technologyValue.value = Integer.parseInt(read.nextLine());
        this.roundNum = Integer.parseInt(read.nextLine());
        this.kingdomName = read.nextLine();
    }

    public ArrayList<ActionCard> startingCards(){
        ArrayList<ActionCard> cardsSet = new ArrayList<>();

        Decision dec1 = new Decision("Zbierz wojsko i strzeż granicy", "Zebranie wojska i wyruszenie na granicę pozwoliło uniknąć najazdu wroga, ale okazało się bardzo kosztowne",20,-5,-10,0);
        Decision dec2 = new Decision("Poczekaj aż sytuacja się uspokoi", "Wróg zaatakował, zanim Twoja armia odpowiedziała ograbił trzy duże miasta w pobliżu granicy",0,-15,-5,0);
        ActionCard war1Action = new ActionCard("Duże królestwo na wschodzie zbiera swoje siły, czy zebrać wojsko i wyruszyć do walki", dec1, dec2);
        cardsSet.add(war1Action);

        Decision dec4 = new Decision("Poczekaj aż sytuacja się uspokoi", "Wróg nie zaatakował, królestwo rozwijało się w spokoju",0,0,15,10);
        ActionCard war2Action = new ActionCard("Małe królestwo na południu zbiera swoje siły, czy zebrać wojsko i wyruszyć do walki", dec1, dec4);
        cardsSet.add(war2Action);

        Decision dec5 = new Decision("Przyjmij propozycję", "Przyjęcie oświadczyn doprowadziło do nowego sojuszu, jednak sam posag okazał się bardzo kosztowny",10,-15,0,15);
        Decision dec6 = new Decision("Odrzuć propozycję", "Odrzucenie oświadczyn doprowadziło do kosztownej wojny pomiędzy królestwami",-10,-10,-10,0);
        ActionCard marriage1Action = new ActionCard("Król dużego królestwa na zachodzie prosi o rękę królewskiej córki, czy przyjąć oświadczyny?", dec5, dec6);
        cardsSet.add(marriage1Action);

        Decision dec7 = new Decision("Przyjmij propozycję", "Przyjęcie oświadczyn doprowadziło do wojny z potężnymi sojusznikami małego księstwa",-15,5,-5,0);
        Decision dec8 = new Decision("Odrzuć propozycję", "Odrzucenie oświadczyn pozwoliło królestwu rozwijać się w spokoju",0,5,10,5);
        ActionCard marriage2Action = new ActionCard("Książę małego księstwa na północy prosi o rękę królewskiej córki, czy przyjąć oświadczyny?", dec7, dec8);
        cardsSet.add(marriage2Action);

        Decision dec9 = new Decision("Udziel dofinansowania", "Co prawda, mimo udzielonego dofinansowania, alchamikowi nie udało się spełnić obietnic, ale przygotowane przez niego nawozy pozwoliły zwiększyć produkcję żywności.",0,-5,10,5);
        Decision dec10 = new Decision("Odeślij alchemika z niczym", "Oburzony brakiem funduszy alchemik zatruł wodę w rzece, przez co wielu zołnierzy się zatruło. Ucierpiała też produkcja żywności",-10,0,-10,0);
        ActionCard researches1Action = new ActionCard("Lokalny alchemik twierdzi, że przy po zakupie odpowiednich substanji będzie w stanie zamienić dowolny metal w złoto, czy udzielić dofinansowania", dec9, dec10);
        cardsSet.add(researches1Action);

        Decision dec11 = new Decision("Udziel dofinansowania", "Udzielona dofinansowanie spowodowało bunt wśród żołnierzy, z których część odeszła z armii.",-15,-10,0,10);
        Decision dec12 = new Decision("Odmów dofinansowania", "Obrażony brakiem dofinansowania mędrzec odszedł z dworu i wsparł sąsiednie królestwo",0,0,0,-10);
        ActionCard researches2Action = new ActionCard("Nadworny mędrzec twierdzi, że wynalazł miksturę, po której wojownicy stają się silniejsi, czy udzielić dofinansowania na dalsze badania?", dec11, dec12);
        cardsSet.add(researches2Action);

        Decision dec13 = new Decision("Pozwól dowodzić oddziałem", "Były więzień sprawnie wprowadził nową taktykę, która spodobała się żołnierzon i zwiększyła zadowolenie w armii",20,0,0,10);
        Decision dec14 = new Decision("Pozostaw więźnia w celi", "Pozostawiony w lochu więzień rozpoczął strajk głodowy, w wyniku którego zakończył swój żywot",0,0,1,0);
        ActionCard researches3Action = new ActionCard("Jeniec wojenny trzymany w lochach twierdzi, że ma pomysł na nową taktykę wojenną, ale chce poćwiczyć z oddziałem wojskowym", dec13, dec14);
        cardsSet.add(researches3Action);

        Decision dec15 = new Decision("Zarządź dopłaty", "Zarządzenie dopłat pozwoliło zwiększyć produkcję rolną i sprawiło, że na wsie trafiły nowe technologie",0,-15,20,15);
        Decision dec16 = new Decision("Zignoruj protesty chłopów", "Zdenerwowani brakiem reakcji chłopi wzniecili powstanie, które trzeba było krwawo stłumić",-5,0,-15,0);
        ActionCard protest1Action = new ActionCard("Chłopi zbuntowali się przeciwko niskim cenom żywności, przez które ich praca staje się coraz mniej opłacalna, czy zarządzić dopłaty?", dec15, dec16);
        cardsSet.add(protest1Action);

        Decision dec17 = new Decision("Przyznaj podwyżki", "Przyznanie wysoko postawionym wojskowym podwyżek uszczupliło skarbiec i sprawiło, że wojskowi bardzo się rozleniwili",-10,-15,0,0);
        Decision dec18 = new Decision("Odmów przyznania podwyzki", "Zdenerwowani brakiem podwyżki wojskowi odeszli z armii i zajęli się uprawą roli, na ich miejsce przyszli inni bardziej ambitni, którzy doprowadzili do rozwoju armii ",5,0,0,10);
        ActionCard protest2Action = new ActionCard("Wojskowi na wysokich szczeblach domagają się podwyżek, czy przyznać im je?", dec17, dec18);
        cardsSet.add(protest2Action);

        Decision dec19 = new Decision("Przyznaj podwyżki", "Przyznanie szeregowym podwyżek nieznacznie uszczupliło skarbiec, ale sprawiło, że zwiększyło się zainteresowanie służbą w armii",15,-5,0,10);
        Decision dec20 = new Decision("Odmów przyznania podwyzki", "Zdenerwowani brakiem podwyżki szeregowi wywołali woję domową, która przyniosła za sobą wiele ofiar i spawiła, że kraj cofnął się w rozwoju ",-15,0,-10,-20);
        ActionCard protest3Action = new ActionCard("Szeregowi żołnierze domagają się podwyżek, czy przyznać im je?", dec19, dec20);
        cardsSet.add(protest3Action);

        Decision dec21 = new Decision("Przyznaj dofinansowanie", "Dzięki dofinansowaniu rybacy mogli zbudować lepsze łodzie, które pozwoliły zwiększyć ilość łowionych ryb.",0,-10,15,10);
        Decision dec22 = new Decision("Odmów przyznania dofinansowania", "Rybacy uznali, że bez dofinansowania dalsza praca nie ma sensu i wyemigrowali do miast lub wstąpili do armii",5,0,-15,-5);
        ActionCard protest4Action = new ActionCard("Rybacy domagają się dofinansowania do zakupu nowych sieci, czy przyznać im je?", dec21, dec22);
        cardsSet.add(protest4Action);

        Decision dec23 = new Decision("Rozkaż żołnierzom chronić karczmy", "Żołnierze wysłani do ochrony karcz sami przyczynili się do wielu awantur, a znaczna ich część już nigdy nie wróciła do służby",-10,-15,0,0);
        Decision dec24 = new Decision("Zignoruj żądania", "Żołnierze pozostali w koszarach, w karczmach wciąż dochodziło do sporadycznych awantur",0,-5,-5,0);
        ActionCard protest5Action = new ActionCard("Karczmarze skarżą się na częste bójki w karczmach, podczas których cierpi ich dobytek i klienci. Chcą, aby karczmy były chronione przez wojskowych. Czy zgodzić się na to?", dec23, dec24);
        cardsSet.add(protest5Action);

        Decision dec25 = new Decision("Wyślij pomoc", "Wojskowi szybko odbudowali gospodarstwa, spalone wsie zyskały dostęp do nowych technologii i zwiększyły produkcję żywności",-5,-5,5,10);
        Decision dec26 = new Decision("Odmów pomocy", "Głodni i zmęczeni chłopi zbuntowali się i konieczne było wysłanie wojsk, które stłumiły rebelię",-10,0,-5,0);
        ActionCard disaster1Action = new ActionCard("Pożar starwił dużą powierzchnię pól uprawnych, czy wysłać mieszkańcom pomoc w postaci jedzenia i żołnierzy, którzy pomogą odbudować gospodarstwa?", dec25, dec26);
        cardsSet.add(disaster1Action);

        Decision dec27 = new Decision("Przyznaj pomoc", "Kupiec po otrzymaniu dofinansowania zamówił wszystkie statki w lokalnych stoczniach, a zyski z hadlu lokalnymi towarami wkrótce znacznie przewyższyły koszty udzielonej mu pomocy",0,25,0,15);
        Decision dec28 = new Decision("Odmów pomocy", "Zdenerwowany kupiec wkrótce przniósł swoją flotę do portu w innym królestwie i przestał handlować towarami z Twojego królestwa",0,-15,5,0);
        ActionCard disaster2Action = new ActionCard("Sztorm zatopił połowę statków największego kupca w królestwie, czy udzielić mu dofinansowania na budowę nowych statków?", dec27, dec28);
        cardsSet.add(disaster2Action);

        Decision dec29 = new Decision("Kup żywność", "Sąsiednie państwa wykorzystały sytuację i zarządały wysokich stawek za swoje produkty.",0,-20,10,0);
        Decision dec30 = new Decision("Zrezygnuj z zakupu żywnośći", "Żywność wyprodukowana na południu kraju wystarczyła do wyżywienia całego kraju,a do ich dostarczenia ulepszono istniejące metody transportu",0,0,0,10);
        ActionCard disaster3Action = new ActionCard("Długotrwała susza sprawiła, że tegoroczne zbiory na północy kraju były znacznie gorsze niż we wcześniejszych latach, czy zakupić pożywienie od sąsiednich krajów?", dec29, dec30);
        cardsSet.add(disaster3Action);

        Decision dec31 = new Decision("Kup żywność", "Sąsiednie państwa wykorzystały sytuację i zarządały wysokich stawek za swoje produkty.",0,-20,10,0);
        Decision dec32 = new Decision("Zrezygnuj z zakupu żywnośći", "Głód w królestwie pezyczynił się do śmierci wielu mieszkańców, w tym również wojskowych, nie udało się uniknąć poważnych buntów.",-10,0,-10,-5);
        ActionCard disaster4Action = new ActionCard("Długotrwała susza sprawiła, że tegoroczne zbiory na południu kraju były znacznie gorsze niż we wcześniejszych latach, czy zakupić pożywienie od sąsiednich krajów?", dec31, dec32);
        cardsSet.add(disaster4Action);

        Decision dec33 = new Decision("Zorganizuj ekspedycję", "Ekspedycja znalazła pokaźne pokłady złota, a do ich wydobycia potrzebny był rozwój sprzętu górniczego.",0,25,0,15);
        Decision dec34 = new Decision("Zignoruj tą informację", "Sąsiednie królestwo przeprowadziło swoją ekspedycję, która znalazła złoto, a następnie rozpoczął wojnę o ten fragment królestwa.",-10,-10,-10,0);
        ActionCard gold1Action = new ActionCard("Pojawiły się plotki o zasobach złota na wschodzie kraju, czy zorganizować ekspedycję, która przeprowadzi badania?", dec33, dec34);
        cardsSet.add(gold1Action);

        Decision dec35 = new Decision("Zorganizuj ekspedycję", "Ekspedycja znalazła pokaźne pokłady złota, ale niczego nie znalazła.",0,-5,0,5);
        Decision dec36 = new Decision("Zignoruj tą informację", "Kraj w dalszym ciągu się rozwijał, a badacze, którzy mieli być wysłani na ekspedycję udoskonalili sposób wytopu żelaza.",0,0,0,10);
        ActionCard gold2Action = new ActionCard("Pojawiły się plotki o zasobach złota na zachodzie kraju, czy zorganizować ekspedycję, która przeprowadzi badania?", dec35, dec36);
        cardsSet.add(gold2Action);

        Decision dec37 = new Decision("Zarządź zabicie bydła", "Choroba nie rozniosła się, a właściciele chorych zwierząt zadowolili się rekompensatami.",0,-5,-5,0);
        Decision dec38 = new Decision("Zostaw zwierzęta przy życiu","Choroba szybko się rozwinęła, zginęło na nią wiele zwierząt, przez co w królestwie zapanował głód.",0,0,-15,0);
        ActionCard illness1Action = new ActionCard("W królestwie pojawiła się dziwna choroba bydła, która może być śmiertelna dla chorych zwierząt. Czy zabić wszystkie osobniki w gospodarstwach, gdzie się pojawiła?", dec37, dec38);
        cardsSet.add(illness1Action);

        Decision dec39 = new Decision("Zarządź zabicie drobiu", "Choroba nie rozniosła się, a właściciele chorych zwierząt zadowolili się rekompensatami.",0,-5,-5,0);
        Decision dec40 = new Decision("Zostaw zwierzęta przy życiu","Choroba okazała się mniej groźna niż sądzono, królestwo rozwijało się w spokoju.",0,0,0,5);
        ActionCard illness2Action = new ActionCard("W królestwie pojawiła się dziwna choroba drobiu, która może być śmiertelna dla chorych zwierząt. Czy zabić wszystkie osobniki w gospodarstwach, gdzie się pojawiła?", dec39, dec40);
        cardsSet.add(illness2Action);

        Decision dec41 = new Decision("Zarządź zabicie świń", "Niezadowoleni właściciele trzody domagali się olbrzymich rekompensat za zabite zwierzęta.",0,-15,-5,0);
        Decision dec42 = new Decision("Zostaw zwierzęta przy życiu","Choroba okazała się mniej groźna niż sądzono, królestwo rozwijało się w spokoju.",0,0,0,5);
        ActionCard illness3Action = new ActionCard("W królestwie pojawiła się dziwna choroba trzody chlewnej, która może być śmiertelna dla chorych zwierząt. Czy zabić wszystkie osobniki w gospodarstwach, gdzie się pojawiła?", dec41, dec42);
        cardsSet.add(illness3Action);

        Decision dec43 = new Decision("Zarządź zabicie zwierząt", "Niezadowoleni właściciele kóz i owiec domagali się olbrzymich rekompensat za zabite zwierzęta.",0,-15,-5,0);
        Decision dec44 = new Decision("Zostaw zwierzęta przy życiu","Choroba szybko się rozwinęła, zwierzęta masowo ginęły i wkrótce w królestwie zaczęło brakować wełny, która była z nich pozyskiwana, dlatego konieczny był rozwój włókiennictwa.",-5,-5,0,15);
        ActionCard illness4Action = new ActionCard("W królestwie pojawiła się dziwna choroba trzody kóz i owiec, która może być śmiertelna dla chorych zwierząt. Czy zabić wszystkie osobniki w gospodarstwach, gdzie się pojawiła?", dec43, dec44);
        cardsSet.add(illness4Action);

        return cardsSet;
    }


}
