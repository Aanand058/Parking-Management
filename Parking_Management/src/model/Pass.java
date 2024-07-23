package model;

import java.time.LocalDateTime;

public class Pass {
	
	private int id; 
	private LocalDateTime startDateTime;
    private LocalDateTime validTill;
    private int hours;
    private double tax;
    private double subTotal;
    private double total;
    
    public Pass(int id, LocalDateTime startDateTime, int hours, double subTotal, double tax, double total) {
    	this.id= id;
        this.startDateTime = startDateTime;
        this.hours = hours;
        this.validTill = startDateTime.plusHours(hours);
        this.subTotal = subTotal;
        this.tax = tax;
        this.total = total;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getValidTill() {
		return validTill;
	}

	public void setValidTill(LocalDateTime validTill) {
		this.validTill = validTill;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
    

}
