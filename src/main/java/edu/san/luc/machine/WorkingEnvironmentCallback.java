package edu.san.luc.machine;

import org.drools.core.FactException;
import org.drools.core.WorkingMemory;

public interface WorkingEnvironmentCallback {

	void initEnvironment(WorkingMemory workingMemory) throws FactException;
}
