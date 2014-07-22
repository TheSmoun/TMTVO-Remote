package tmtvo.net;

import java.util.Observable;
import java.util.Observer;

public abstract class CommandExecutor implements Observer {
	public CommandExecutor(Connection connection) {
		connection.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg == null || !(arg instanceof String))
			return;
		
		String line = (String) arg;
		if (!line.startsWith("#") || !line.endsWith("#"))
			return;
		
		line = line.substring(1, line.length() - 2);
		String[] args = line.split(":");
		update(args);
	}
	
	public abstract void update(String[] args);
}