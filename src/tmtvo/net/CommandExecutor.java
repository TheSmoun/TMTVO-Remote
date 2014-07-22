package tmtvo.net;

import java.util.Observable;
import java.util.Observer;

public class CommandExecutor implements Observer {
	
	public CommandExecutor(Connection connection) {
		connection.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Parse Lines.
	}
}