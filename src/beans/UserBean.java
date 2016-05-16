package beans;

import java.io.Serializable;
import java.util.Date;

public class UserBean implements Serializable{
	
	public UserBean(int id) {
		super();
		this.id = id;
		this.firstName = "Ken";
		this.lastName ="Ng";
		this.address = "";
		this.email = "";
	}
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String nickName;
	private String address;
	private String email;
	private String cardNumber;
	private String cardName;
	private String cardExpire;
	private String cvc;
	private int userType;
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getNickName() {
		return nickName;
	}
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public String getCardName() {
		return cardName;
	}
	public String getCardExpire() {
		return cardExpire;
	}
	public String getCvc() {
		return cvc;
	}
	public int getUserType() {
		return userType;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public void setCardExpire(String cardExpire) {
		this.cardExpire = cardExpire;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	
}
