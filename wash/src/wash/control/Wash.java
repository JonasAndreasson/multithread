package wash.control;

import wash.io.WashingIO;
import wash.simulation.WashingSimulator;

public class Wash {
	static Thread current;
	
    public static void main(String[] args) throws InterruptedException {
        WashingSimulator sim = new WashingSimulator(Settings.SPEEDUP);
        
        WashingIO io = sim.startSimulation();

        TemperatureController temp = new TemperatureController(io);
        WaterController water = new WaterController(io);
        SpinController spin = new SpinController(io);

        temp.start();
        water.start();
        spin.start();

        while (true) {
            int n = io.awaitButton();
            System.out.println("user selected program " + n);
            switch(n) {
            case 0:
            	current.interrupt();
            	break;
            case 1:
            	current = new WashingProgram1(io,temp,water,spin);
            	current.start();
            	break;
            case 2:
            	current = new WashingProgram2(io,temp,water,spin);
            	current.start();
            	break;
            case 3: 
            	
             current = new WashingProgram3(io,temp,water,spin);
             current.start();
             break;
            }
            
            
            // TODO:
            // if the user presses buttons 1-3, start a washing program
            // if the user presses button 0, and a program has been started, stop it
        }
    }
};
