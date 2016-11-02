public class DistanceConverter {
    public double toMeters(DistanceUnit unit, double amount) {
        switch(unit) {
            case MILES:
                return amount * 1609.34;
            case KILOMETERS:
                return amount * 1000;
            case METERS:
                return amount;
            case YARDS:
                return amount * 0.9144;
        }
        throw new IllegalArgumentException();
    }
    
    public double fromMeters(DistanceUnit unit, double meters) {
        switch(unit) {
            case MILES:
                return meters / 1609.34;
            case KILOMETERS:
                return meters / 1000;
            case METERS:
                return meters;
            case YARDS:
                return meters / 0.9144;
        }        
        throw new IllegalArgumentException();
    }
}
