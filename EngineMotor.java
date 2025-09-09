package Class7;

public class EngineMotor {
    private String typeMotor;

    public EngineMotor(String typeMotor) {
        this.typeMotor = typeMotor;
    }
    public String getTypeMotor() {
        return typeMotor;
    }

    public void startMotor() {
        System.out.println("Tipo de motor del auto: " + typeMotor+ " encendido.");
    }
}
