package OOPSample;

public abstract class AbstractVehicle implements Vehicle {
    protected int fuel;
    protected Color color;

    public AbstractVehicle() {
    }

//    public abstract String someMethod();

    @Override
    public void fill(int fuelAmount){
        fuel += fuelAmount;
    }

    @Override
    public void paint(Color color){
        this.color = color;
    }
}
