import java.io.Serializable;

public class PhoneBookEntry implements Serializable {
    public String firstname = "";
    public String lastname = "";
    public String phone = "";

    public PhoneBookEntry(String firstname, String lastname, String phone) {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setPhone(phone);
    }

    public String getFisrtname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setFirstname(String fisrtname) {
        this.firstname = fisrtname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PhoneBookEntry{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
