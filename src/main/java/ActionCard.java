public class ActionCard {
    String advice;
    Decision firstOption;
    Decision secondOption;

    public ActionCard(String advice, Decision first, Decision second){
        this.advice = advice;
        this.firstOption = first;
        this.secondOption = second;
    }

    public Decision selectedOption(int number){
        if(number <= 1) return this.firstOption;
        else return this.secondOption;
    }
}
