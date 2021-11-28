package core.inter.tdc.protogen.json;

import java.util.List;

public class PersonJson {
    private String name;
    private int id;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PhoneNumberJson> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneNumberJson> phones) {
        this.phones = phones;
    }

    private List<PhoneNumberJson> phones;
}
