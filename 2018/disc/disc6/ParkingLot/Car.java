package ParkingLot;

public class Car {
    private String type;

    public Car(String type){
        this.type = type;
    }

    public boolean isRegular(){
        return this.type.equals("regular");
    }

    public boolean isCompact(){
        return this.type.equals("compact");
    }

    public boolean isHandicap(){
        return this.type.equals("handicap");
    }

    public boolean findSpotAndPark(){
        return false;
    }

}
