public class Main {
    public static void main(String[] args) {
        // Test case 1
        var circuit1 = new Parallel();
        var circuit1Serial1 = new Serial();
        circuit1.add(new Resistor(100.0));
        circuit1Serial1.add(new Resistor(100.0));
        circuit1Serial1.add(new Resistor(200.0));
        circuit1.add(circuit1Serial1);

        System.out.printf("Combined resistance: %s%n", circuit1);

        // Test case 2
        var circuit2 = new Parallel();
        var circuit2Serial1 = new Serial();
        var circuit2Serial2 = new Serial();
        var circuit2Parallel1 = new Parallel();
        var circuit2Parallel2 = new Parallel();
        var circuit2Parallel3 = new Parallel();

        circuit2Serial1.add(new Resistor(25.0));
        circuit2Serial1.add(new Resistor(60.5));
        circuit2Serial1.add(new Resistor(90.0));

        circuit2Serial2.add(new Resistor(100.0));
        circuit2Serial2.add(new Resistor(16.5));

        circuit2Parallel1.add(circuit2Serial1);
        circuit2Parallel1.add(circuit2Serial2);

        circuit2Parallel2.add(new Resistor(30.0));
        circuit2Parallel2.add(new Resistor(75.0));

        circuit2Parallel3.add(new Resistor(40.0));
        circuit2Parallel3.add(new Resistor(40.0));

        circuit2.add(circuit2Parallel1);
        circuit2.add(circuit2Parallel2);
        circuit2.add(circuit2Parallel3);

        System.out.printf("Combined resistance: %s%n", circuit2);

        // Test case 3
        var circuit3 = new Serial();
        var circuit3Serial1 = new Serial();
        var circuit3Serial2 = new Serial();
        var circuit3Serial3 = new Serial();
        var circuit3Serial4 = new Serial();
        var circuit3Serial5 = new Serial();
        var circuit3Parallel1 = new Parallel();
        var circuit3Parallel2 = new Parallel();
        var circuit3Parallel3 = new Parallel();
        var circuit3Parallel4 = new Parallel();
        var circuit3Parallel5 = new Parallel();

        circuit3Serial1.add(new Resistor(10.0));
        circuit3Serial1.add(new Resistor(15.0));
        circuit3Parallel1.add(new Resistor(12.0));
        circuit3Parallel1.add(circuit3Serial1);

        circuit3Serial2.add(new Resistor(40.0));
        circuit3Serial2.add(new Resistor(9.0));
        circuit3Serial3.add(new Resistor(5.0));
        circuit3Serial3.add(new Resistor(6.75));
        circuit3Parallel2.add(circuit3Serial2);
        circuit3Parallel2.add(circuit3Serial3);

        circuit3Parallel3.add(new Resistor(25.0));
        circuit3Serial4.add(new Resistor(11.5));
        circuit3Serial4.add(new Resistor(33.5));
        circuit3Parallel3.add(circuit3Serial4);

        circuit3Parallel4.add(circuit3Parallel1);
        circuit3Parallel4.add(circuit3Parallel2);
        circuit3Parallel4.add(circuit3Parallel3);

        circuit3Parallel5.add(new Resistor(77.0));
        circuit3Serial5.add(new Resistor(8.25));
        circuit3Serial5.add(new Resistor(6.45));
        circuit3Parallel5.add(circuit3Serial5);

        circuit3.add(circuit3Parallel4);
        circuit3.add(circuit3Parallel5);

        System.out.printf("Combined resistance: %s%n", circuit3);
    }
}
