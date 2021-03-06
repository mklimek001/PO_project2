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

        Decision dec1 = new Decision("Zbierz wojsko i strze?? granicy", "Zebranie wojska i wyruszenie na granic?? pozwoli??o unikn???? najazdu wroga, ale okaza??o si?? bardzo kosztowne",20,-5,-10,0);
        Decision dec2 = new Decision("Poczekaj a?? sytuacja si?? uspokoi", "Wr??g zaatakowa??, zanim Twoja armia odpowiedzia??a ograbi?? trzy du??e miasta w pobli??u granicy",0,-15,-5,0);
        ActionCard war1Action = new ActionCard("Du??e kr??lestwo na wschodzie zbiera swoje si??y, czy zebra?? wojsko i wyruszy?? do walki", dec1, dec2);
        cardsSet.add(war1Action);

        Decision dec4 = new Decision("Poczekaj a?? sytuacja si?? uspokoi", "Wr??g nie zaatakowa??, kr??lestwo rozwija??o si?? w spokoju",0,0,15,10);
        ActionCard war2Action = new ActionCard("Ma??e kr??lestwo na po??udniu zbiera swoje si??y, czy zebra?? wojsko i wyruszy?? do walki", dec1, dec4);
        cardsSet.add(war2Action);

        Decision dec5 = new Decision("Przyjmij propozycj??", "Przyj??cie o??wiadczyn doprowadzi??o do nowego sojuszu, jednak sam posag okaza?? si?? bardzo kosztowny",10,-15,0,15);
        Decision dec6 = new Decision("Odrzu?? propozycj??", "Odrzucenie o??wiadczyn doprowadzi??o do kosztownej wojny pomi??dzy kr??lestwami",-10,-10,-10,0);
        ActionCard marriage1Action = new ActionCard("Kr??l du??ego kr??lestwa na zachodzie prosi o r??k?? kr??lewskiej c??rki, czy przyj???? o??wiadczyny?", dec5, dec6);
        cardsSet.add(marriage1Action);

        Decision dec7 = new Decision("Przyjmij propozycj??", "Przyj??cie o??wiadczyn doprowadzi??o do wojny z pot????nymi sojusznikami ma??ego ksi??stwa",-15,5,-5,0);
        Decision dec8 = new Decision("Odrzu?? propozycj??", "Odrzucenie o??wiadczyn pozwoli??o kr??lestwu rozwija?? si?? w spokoju",0,5,10,5);
        ActionCard marriage2Action = new ActionCard("Ksi?????? ma??ego ksi??stwa na p????nocy prosi o r??k?? kr??lewskiej c??rki, czy przyj???? o??wiadczyny?", dec7, dec8);
        cardsSet.add(marriage2Action);

        Decision dec9 = new Decision("Udziel dofinansowania", "Co prawda, mimo udzielonego dofinansowania, alchamikowi nie uda??o si?? spe??ni?? obietnic, ale przygotowane przez niego nawozy pozwoli??y zwi??kszy?? produkcj?? ??ywno??ci.",0,-5,10,5);
        Decision dec10 = new Decision("Ode??lij alchemika z niczym", "Oburzony brakiem funduszy alchemik zatru?? wod?? w rzece, przez co wielu zo??nierzy si?? zatru??o. Ucierpia??a te?? produkcja ??ywno??ci",-10,0,-10,0);
        ActionCard researches1Action = new ActionCard("Lokalny alchemik twierdzi, ??e przy po zakupie odpowiednich substanji b??dzie w stanie zamieni?? dowolny metal w z??oto, czy udzieli?? dofinansowania", dec9, dec10);
        cardsSet.add(researches1Action);

        Decision dec11 = new Decision("Udziel dofinansowania", "Udzielona dofinansowanie spowodowa??o bunt w??r??d ??o??nierzy, z kt??rych cz?????? odesz??a z armii.",-15,-10,0,10);
        Decision dec12 = new Decision("Odm??w dofinansowania", "Obra??ony brakiem dofinansowania m??drzec odszed?? z dworu i wspar?? s??siednie kr??lestwo",0,0,0,-10);
        ActionCard researches2Action = new ActionCard("Nadworny m??drzec twierdzi, ??e wynalaz?? mikstur??, po kt??rej wojownicy staj?? si?? silniejsi, czy udzieli?? dofinansowania na dalsze badania?", dec11, dec12);
        cardsSet.add(researches2Action);

        Decision dec13 = new Decision("Pozw??l dowodzi?? oddzia??em", "By??y wi??zie?? sprawnie wprowadzi?? now?? taktyk??, kt??ra spodoba??a si?? ??o??nierzon i zwi??kszy??a zadowolenie w armii",20,0,0,10);
        Decision dec14 = new Decision("Pozostaw wi????nia w celi", "Pozostawiony w lochu wi??zie?? rozpocz???? strajk g??odowy, w wyniku kt??rego zako??czy?? sw??j ??ywot",0,0,1,0);
        ActionCard researches3Action = new ActionCard("Jeniec wojenny trzymany w lochach twierdzi, ??e ma pomys?? na now?? taktyk?? wojenn??, ale chce po??wiczy?? z oddzia??em wojskowym", dec13, dec14);
        cardsSet.add(researches3Action);

        Decision dec15 = new Decision("Zarz??d?? dop??aty", "Zarz??dzenie dop??at pozwoli??o zwi??kszy?? produkcj?? roln?? i sprawi??o, ??e na wsie trafi??y nowe technologie",0,-15,20,15);
        Decision dec16 = new Decision("Zignoruj protesty ch??op??w", "Zdenerwowani brakiem reakcji ch??opi wzniecili powstanie, kt??re trzeba by??o krwawo st??umi??",-5,0,-15,0);
        ActionCard protest1Action = new ActionCard("Ch??opi zbuntowali si?? przeciwko niskim cenom ??ywno??ci, przez kt??re ich praca staje si?? coraz mniej op??acalna, czy zarz??dzi?? dop??aty?", dec15, dec16);
        cardsSet.add(protest1Action);

        Decision dec17 = new Decision("Przyznaj podwy??ki", "Przyznanie wysoko postawionym wojskowym podwy??ek uszczupli??o skarbiec i sprawi??o, ??e wojskowi bardzo si?? rozleniwili",-10,-15,0,0);
        Decision dec18 = new Decision("Odm??w przyznania podwyzki", "Zdenerwowani brakiem podwy??ki wojskowi odeszli z armii i zaj??li si?? upraw?? roli, na ich miejsce przyszli inni bardziej ambitni, kt??rzy doprowadzili do rozwoju armii ",5,0,0,10);
        ActionCard protest2Action = new ActionCard("Wojskowi na wysokich szczeblach domagaj?? si?? podwy??ek, czy przyzna?? im je?", dec17, dec18);
        cardsSet.add(protest2Action);

        Decision dec19 = new Decision("Przyznaj podwy??ki", "Przyznanie szeregowym podwy??ek nieznacznie uszczupli??o skarbiec, ale sprawi??o, ??e zwi??kszy??o si?? zainteresowanie s??u??b?? w armii",15,-5,0,10);
        Decision dec20 = new Decision("Odm??w przyznania podwyzki", "Zdenerwowani brakiem podwy??ki szeregowi wywo??ali woj?? domow??, kt??ra przynios??a za sob?? wiele ofiar i spawi??a, ??e kraj cofn???? si?? w rozwoju ",-15,0,-10,-20);
        ActionCard protest3Action = new ActionCard("Szeregowi ??o??nierze domagaj?? si?? podwy??ek, czy przyzna?? im je?", dec19, dec20);
        cardsSet.add(protest3Action);

        Decision dec21 = new Decision("Przyznaj dofinansowanie", "Dzi??ki dofinansowaniu rybacy mogli zbudowa?? lepsze ??odzie, kt??re pozwoli??y zwi??kszy?? ilo???? ??owionych ryb.",0,-10,15,10);
        Decision dec22 = new Decision("Odm??w przyznania dofinansowania", "Rybacy uznali, ??e bez dofinansowania dalsza praca nie ma sensu i wyemigrowali do miast lub wst??pili do armii",5,0,-15,-5);
        ActionCard protest4Action = new ActionCard("Rybacy domagaj?? si?? dofinansowania do zakupu nowych sieci, czy przyzna?? im je?", dec21, dec22);
        cardsSet.add(protest4Action);

        Decision dec23 = new Decision("Rozka?? ??o??nierzom chroni?? karczmy", "??o??nierze wys??ani do ochrony karcz sami przyczynili si?? do wielu awantur, a znaczna ich cz?????? ju?? nigdy nie wr??ci??a do s??u??by",-10,-15,0,0);
        Decision dec24 = new Decision("Zignoruj ????dania", "??o??nierze pozostali w koszarach, w karczmach wci???? dochodzi??o do sporadycznych awantur",0,-5,-5,0);
        ActionCard protest5Action = new ActionCard("Karczmarze skar???? si?? na cz??ste b??jki w karczmach, podczas kt??rych cierpi ich dobytek i klienci. Chc??, aby karczmy by??y chronione przez wojskowych. Czy zgodzi?? si?? na to?", dec23, dec24);
        cardsSet.add(protest5Action);

        Decision dec25 = new Decision("Wy??lij pomoc", "Wojskowi szybko odbudowali gospodarstwa, spalone wsie zyska??y dost??p do nowych technologii i zwi??kszy??y produkcj?? ??ywno??ci",-5,-5,5,10);
        Decision dec26 = new Decision("Odm??w pomocy", "G??odni i zm??czeni ch??opi zbuntowali si?? i konieczne by??o wys??anie wojsk, kt??re st??umi??y rebeli??",-10,0,-5,0);
        ActionCard disaster1Action = new ActionCard("Po??ar starwi?? du???? powierzchni?? p??l uprawnych, czy wys??a?? mieszka??com pomoc w postaci jedzenia i ??o??nierzy, kt??rzy pomog?? odbudowa?? gospodarstwa?", dec25, dec26);
        cardsSet.add(disaster1Action);

        Decision dec27 = new Decision("Przyznaj pomoc", "Kupiec po otrzymaniu dofinansowania zam??wi?? wszystkie statki w lokalnych stoczniach, a zyski z hadlu lokalnymi towarami wkr??tce znacznie przewy??szy??y koszty udzielonej mu pomocy",0,25,0,15);
        Decision dec28 = new Decision("Odm??w pomocy", "Zdenerwowany kupiec wkr??tce przni??s?? swoj?? flot?? do portu w innym kr??lestwie i przesta?? handlowa?? towarami z Twojego kr??lestwa",0,-15,5,0);
        ActionCard disaster2Action = new ActionCard("Sztorm zatopi?? po??ow?? statk??w najwi??kszego kupca w kr??lestwie, czy udzieli?? mu dofinansowania na budow?? nowych statk??w?", dec27, dec28);
        cardsSet.add(disaster2Action);

        Decision dec29 = new Decision("Kup ??ywno????", "S??siednie pa??stwa wykorzysta??y sytuacj?? i zarz??da??y wysokich stawek za swoje produkty.",0,-20,10,0);
        Decision dec30 = new Decision("Zrezygnuj z zakupu ??ywno????i", "??ywno???? wyprodukowana na po??udniu kraju wystarczy??a do wy??ywienia ca??ego kraju,a do ich dostarczenia ulepszono istniej??ce metody transportu",0,0,0,10);
        ActionCard disaster3Action = new ActionCard("D??ugotrwa??a susza sprawi??a, ??e tegoroczne zbiory na p????nocy kraju by??y znacznie gorsze ni?? we wcze??niejszych latach, czy zakupi?? po??ywienie od s??siednich kraj??w?", dec29, dec30);
        cardsSet.add(disaster3Action);

        Decision dec31 = new Decision("Kup ??ywno????", "S??siednie pa??stwa wykorzysta??y sytuacj?? i zarz??da??y wysokich stawek za swoje produkty.",0,-20,10,0);
        Decision dec32 = new Decision("Zrezygnuj z zakupu ??ywno????i", "G????d w kr??lestwie pezyczyni?? si?? do ??mierci wielu mieszka??c??w, w tym r??wnie?? wojskowych, nie uda??o si?? unikn???? powa??nych bunt??w.",-10,0,-10,-5);
        ActionCard disaster4Action = new ActionCard("D??ugotrwa??a susza sprawi??a, ??e tegoroczne zbiory na po??udniu kraju by??y znacznie gorsze ni?? we wcze??niejszych latach, czy zakupi?? po??ywienie od s??siednich kraj??w?", dec31, dec32);
        cardsSet.add(disaster4Action);

        Decision dec33 = new Decision("Zorganizuj ekspedycj??", "Ekspedycja znalaz??a poka??ne pok??ady z??ota, a do ich wydobycia potrzebny by?? rozw??j sprz??tu g??rniczego.",0,25,0,15);
        Decision dec34 = new Decision("Zignoruj t?? informacj??", "S??siednie kr??lestwo przeprowadzi??o swoj?? ekspedycj??, kt??ra znalaz??a z??oto, a nast??pnie rozpocz???? wojn?? o ten fragment kr??lestwa.",-10,-10,-10,0);
        ActionCard gold1Action = new ActionCard("Pojawi??y si?? plotki o zasobach z??ota na wschodzie kraju, czy zorganizowa?? ekspedycj??, kt??ra przeprowadzi badania?", dec33, dec34);
        cardsSet.add(gold1Action);

        Decision dec35 = new Decision("Zorganizuj ekspedycj??", "Ekspedycja znalaz??a poka??ne pok??ady z??ota, ale niczego nie znalaz??a.",0,-5,0,5);
        Decision dec36 = new Decision("Zignoruj t?? informacj??", "Kraj w dalszym ci??gu si?? rozwija??, a badacze, kt??rzy mieli by?? wys??ani na ekspedycj?? udoskonalili spos??b wytopu ??elaza.",0,0,0,10);
        ActionCard gold2Action = new ActionCard("Pojawi??y si?? plotki o zasobach z??ota na zachodzie kraju, czy zorganizowa?? ekspedycj??, kt??ra przeprowadzi badania?", dec35, dec36);
        cardsSet.add(gold2Action);

        Decision dec37 = new Decision("Zarz??d?? zabicie byd??a", "Choroba nie roznios??a si??, a w??a??ciciele chorych zwierz??t zadowolili si?? rekompensatami.",0,-5,-5,0);
        Decision dec38 = new Decision("Zostaw zwierz??ta przy ??yciu","Choroba szybko si?? rozwin????a, zgin????o na ni?? wiele zwierz??t, przez co w kr??lestwie zapanowa?? g????d.",0,0,-15,0);
        ActionCard illness1Action = new ActionCard("W kr??lestwie pojawi??a si?? dziwna choroba byd??a, kt??ra mo??e by?? ??miertelna dla chorych zwierz??t. Czy zabi?? wszystkie osobniki w gospodarstwach, gdzie si?? pojawi??a?", dec37, dec38);
        cardsSet.add(illness1Action);

        Decision dec39 = new Decision("Zarz??d?? zabicie drobiu", "Choroba nie roznios??a si??, a w??a??ciciele chorych zwierz??t zadowolili si?? rekompensatami.",0,-5,-5,0);
        Decision dec40 = new Decision("Zostaw zwierz??ta przy ??yciu","Choroba okaza??a si?? mniej gro??na ni?? s??dzono, kr??lestwo rozwija??o si?? w spokoju.",0,0,0,5);
        ActionCard illness2Action = new ActionCard("W kr??lestwie pojawi??a si?? dziwna choroba drobiu, kt??ra mo??e by?? ??miertelna dla chorych zwierz??t. Czy zabi?? wszystkie osobniki w gospodarstwach, gdzie si?? pojawi??a?", dec39, dec40);
        cardsSet.add(illness2Action);

        Decision dec41 = new Decision("Zarz??d?? zabicie ??wi??", "Niezadowoleni w??a??ciciele trzody domagali si?? olbrzymich rekompensat za zabite zwierz??ta.",0,-15,-5,0);
        Decision dec42 = new Decision("Zostaw zwierz??ta przy ??yciu","Choroba okaza??a si?? mniej gro??na ni?? s??dzono, kr??lestwo rozwija??o si?? w spokoju.",0,0,0,5);
        ActionCard illness3Action = new ActionCard("W kr??lestwie pojawi??a si?? dziwna choroba trzody chlewnej, kt??ra mo??e by?? ??miertelna dla chorych zwierz??t. Czy zabi?? wszystkie osobniki w gospodarstwach, gdzie si?? pojawi??a?", dec41, dec42);
        cardsSet.add(illness3Action);

        Decision dec43 = new Decision("Zarz??d?? zabicie zwierz??t", "Niezadowoleni w??a??ciciele k??z i owiec domagali si?? olbrzymich rekompensat za zabite zwierz??ta.",0,-15,-5,0);
        Decision dec44 = new Decision("Zostaw zwierz??ta przy ??yciu","Choroba szybko si?? rozwin????a, zwierz??ta masowo gin????y i wkr??tce w kr??lestwie zacz????o brakowa?? we??ny, kt??ra by??a z nich pozyskiwana, dlatego konieczny by?? rozw??j w????kiennictwa.",-5,-5,0,15);
        ActionCard illness4Action = new ActionCard("W kr??lestwie pojawi??a si?? dziwna choroba trzody k??z i owiec, kt??ra mo??e by?? ??miertelna dla chorych zwierz??t. Czy zabi?? wszystkie osobniki w gospodarstwach, gdzie si?? pojawi??a?", dec43, dec44);
        cardsSet.add(illness4Action);

        return cardsSet;
    }


}
