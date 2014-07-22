package tmtvo.data;

import java.util.ArrayList;
import java.util.List;

public class DriverManager {
	private List<Driver> drivers;

	public DriverManager() {
		drivers = new ArrayList<Driver>();
	}

	public void addDriver(Driver driver) {
		drivers.add(driver);
	}

	public Driver removeDriver(int index) {
		return drivers.remove(index);
	}

	public boolean removeDriver(Driver driver) {
		return drivers.remove(driver);
	}

	public class Driver {
		public final int carIdx;
		public final int carNumber;
		public final String abbrevName;

		public Driver(int carIdx, int carNumber, String name) {
			this.carIdx = carIdx;
			this.carNumber = carNumber;
			this.abbrevName = name;
		}

		@Override
		public String toString() {
			return new StringBuilder().append(carNumber).append(" - ")
					.append(abbrevName).toString();
		}
	}
}
