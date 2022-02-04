public class AllResources {
    ResourceAndValue goldValue;
    ResourceAndValue armyValue;
    ResourceAndValue technologyValue;
    ResourceAndValue foodValue;

    public AllResources(int armyVal, int goldVal, int foodVal, int technologyVal){
        armyValue = new ResourceAndValue(Resources.ARMY, armyVal);
        goldValue = new ResourceAndValue(Resources.GOLD, goldVal);
        foodValue = new ResourceAndValue(Resources.FOOD, foodVal);
        technologyValue = new ResourceAndValue(Resources.TECHNOLOGY, technologyVal);
    }

    public boolean isAnyOut() {
        return (armyValue.isOut() || goldValue.isOut() || foodValue.isOut() || technologyValue.isOut());
    }

    public void resourceChange( AllResources consequences) {
        this.armyValue.changeValue(consequences.armyValue);
        this.goldValue.changeValue(consequences.goldValue);
        this.foodValue.changeValue(consequences.foodValue);
        this.technologyValue.changeValue(consequences.technologyValue);
    }

}
