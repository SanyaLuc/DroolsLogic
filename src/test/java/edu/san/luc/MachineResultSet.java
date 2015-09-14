package edu.san.luc;

import edu.san.luc.machine.Machine;

public interface MachineResultSet {
	
	boolean next();
	
	Machine getMachine();

}
