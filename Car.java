package Class7;

public class Car {
    private EngineMotor engine;

    public Car (String engineType ) {
        this.engine = new EngineMotor (engineType);
    }

    public void starCar(){
        System.out.println("El auto esta encendido");
        engine.startMotor();
    }
    public void showDetails (){
        System.out.println("Tipo de motor del auto: "+ engine.getTypeMotor());
    }
}
