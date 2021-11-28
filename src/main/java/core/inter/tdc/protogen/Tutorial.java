package core.inter.tdc.protogen;

import core.inter.tdc.protogen.models.AddressBook;
import core.inter.tdc.protogen.models.Person;
import core.inter.tdc.protogen.models.PhoneNumber;
import core.inter.tdc.protogen.models.PhoneType;

public class Tutorial {
    public static void main(String args[])  {
        PhoneNumber phoneNumber = PhoneNumber.newBuilder()
                .setNumber("(41)99999-0000")
                .setType(PhoneType.HOME)
                .build();

        Person person = Person.newBuilder()
                .setEmail("marcel.costa@bancointer.com.br")
                .setName("Marcel Samaruga da Costa")
                .setPhone(phoneNumber)
                .build();

        AddressBook addressBook = AddressBook.newBuilder()
                .addPerson(person)
                .build();

        System.out.println(addressBook);
    }

}
