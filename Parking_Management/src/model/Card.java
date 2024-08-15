package model;

public class Card {

	private String cardNumber;
	private int expiryMonth;
	private int expiryYear;
	private int cvv;
	private String fullNameOnCard;
	private String cardType;

	// Constructor to initialize the card details
	public Card(String cardType,String cardNumber, int expiryMonth, int expiryYear, int cvv, String fullNameOnCard) {
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.cvv = cvv;
		this.fullNameOnCard = fullNameOnCard;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public int getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getFullNameOnCard() {
		return fullNameOnCard;
	}

	public void setFullNameOnCard(String fullNameOnCard) {
		this.fullNameOnCard = fullNameOnCard;
	}

	@Override
	public String toString() {
		return "Card [cardNumber=" + cardNumber + ", expiryMonth=" + expiryMonth + ", expiryYear=" + expiryYear
				+ ", cvv=" + cvv + ", fullNameOnCard=" + fullNameOnCard + ", cardType=" + cardType + "]";
	}


}
