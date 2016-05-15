package models;

/**
 * @author williamding
 */
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String nickName;
    
    private String address;
    private String email;
    
    private String cardNumber;
    private String cardName;
    private String cardExpireDate;
    private String cvc;
    
    public final static int CUSTOMER = 0;
    public final static int MANAGER  = 10;
    
    /**
     * identity = 0,    Customer
     * identity = 10,   Manager
     */
    private int    userType;
    
    public User() {
        this.userType = CUSTOMER;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = CUSTOMER;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardExpireDate() {
        return cardExpireDate;
    }

    public void setCardExpireDate(String cardExpireDate) {
        this.cardExpireDate = cardExpireDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", nickName=" + nickName + ", address=" + address + ", email=" + email + ", cardNumber=" + cardNumber + ", cardName=" + cardName + ", cardExpireDate=" + cardExpireDate + ", cvc=" + cvc + ", userType=" + userType + '}';
    }

 
}
