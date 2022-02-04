public class Decision {
    String shortProposition;
    String censequncesDesc;
    AllResources censequncesResources;

    public Decision(String prop, String desc, int armyChng, int goldChng, int foodChng, int techChange){
        this.shortProposition = prop;
        this.censequncesDesc = desc;
        this.censequncesResources = new AllResources(armyChng, goldChng, foodChng, techChange);
    }
}
