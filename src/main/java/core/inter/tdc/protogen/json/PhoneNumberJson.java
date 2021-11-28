package core.inter.tdc.protogen.json;

public class PhoneNumberJson {
    private String number;
    private PhoneTypeJson type;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneTypeJson getType() {
        return type;
    }

    public void setType(PhoneTypeJson type) {
        this.type = type;
    }
}
