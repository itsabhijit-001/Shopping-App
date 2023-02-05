public class Customer {
    String cName, cEmailId;
    long cPhoneNumber;

    public void setCustomerDetails(String name, String emailId, long phoneNumber) {
        this.cName = name;
        this.cEmailId = emailId;
        this.cPhoneNumber = phoneNumber;
    }

    public void displayUserDetails() {
        System.out.println("Customer name : " + this.cName + " \nEmail id: " +
                this.cEmailId + "\nPhone number: " +
                this.cPhoneNumber);
    }
}