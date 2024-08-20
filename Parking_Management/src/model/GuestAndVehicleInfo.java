package model;

public class GuestAndVehicleInfo {
	
	 private String name;
	    private String email;
	    private String phone;
	    private String address;
	    private String type;
	    private String make;
	    private String model;
	    private String color;
	    private String licensePlate;

	    // Constructor
	    public GuestAndVehicleInfo(String name, String email, String phone, String address, String type, String make, String model, String color, String licensePlate) {
	        this.name = name;
	        this.email = email;
	        this.phone = phone;
	        this.address = address;
	        this.type = type;
	        this.make = make;
	        this.model = model;
	        this.color = color;
	        this.licensePlate = licensePlate;
	    }

	    // Getters and Setters
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
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
	        return "GuestVehicleInfo{" +
	                "name='" + name + '\'' +
	                ", email='" + email + '\'' +
	                ", phone='" + phone + '\'' +
	                ", address='" + address + '\'' +
	                ", type='" + type + '\'' +
	                ", make='" + make + '\'' +
	                ", model='" + model + '\'' +
	                ", color='" + color + '\'' +
	                ", licensePlate='" + licensePlate + '\'' +
	                '}';
	    }

}
