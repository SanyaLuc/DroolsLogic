package edu.san.luc.machine;

import java.util.Collection;

public interface TestDAO {

	Test findByKey(Integer id);

	Collection findAll();

}
