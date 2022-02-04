public class ResourceAndValue {
    Resources resource;
    int value;
    int maxVal = 100;
    int minVal = 0;


    public ResourceAndValue(Resources resource, int resourceVal) {
        this.resource = resource;
        this.value = resourceVal;
    }

    public void setResourceValue(int resourceVal) {
        this.value = resourceVal;
    }

    public void changeValue(ResourceAndValue cardResource){
        if(this.value + cardResource.value > maxVal){
            this.value = maxVal;
        } else if (this.value + cardResource.value < minVal) {
            this.value = minVal;
        } else {
            this.setResourceValue(this.value + cardResource.value);
        }
    }

    public boolean isOut(){
        return(this.value <= minVal);
    }

}
