package _ITTicketSystem.utils;

import java.util.List;


public interface TerminalFormatter<T> {

	
	String format(T obj);
	String format(List<T> list);
	
}
