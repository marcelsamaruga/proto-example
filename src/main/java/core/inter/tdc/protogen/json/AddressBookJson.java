package core.inter.tdc.protogen.json;

import java.util.List;

public class AddressBookJson {
    private List<PersonJson> person;

    public List<PersonJson> getPerson() {
        return person;
    }

    public void setPerson(List<PersonJson> person) {
        this.person = person;
    }
}
