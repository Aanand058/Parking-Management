package model;

public class Vehicle {

	private int vehicleId;
	private String type; // car, bike, truck, van
	private String make;
	private String model;
	private String color;
	private String licensePlate;

	public Vehicle(int vehicleId, String type, String make, String model, String color, String licensePlate) {
		super();
		this.vehicleId = vehicleId;
		this.type = type;
		this.make = make;
		this.model = model;
		this.color = color;
		this.licensePlate = licensePlate;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", type=" + type + ", make=" + make + ", model=" + model + ", color="
				+ color + ", licensePlate=" + licensePlate + "]";
	}
}
