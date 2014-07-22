package tmtvo.net;

public class DriverExecutor extends CommandExecutor {
	public DriverExecutor(Connection connection) {
		super(connection);
	}

	@Override
	public void update(String[] args) {
		if (args.length == 0 || !args[0].equalsIgnoreCase("driver")) {
			return;
		}
		
		switch (args.length) {
		case 5:
			
		}
	}
	
	private void argsLengthFive(String[] args) {
		if (!args[1].equalsIgnoreCase("add"))
			return;
		
		int carIdx = Integer.parseInt(args[2]);
		int carNumber = Integer.parseInt(args[3]);
		String abbrevName = args[4];
		
		
	}
}