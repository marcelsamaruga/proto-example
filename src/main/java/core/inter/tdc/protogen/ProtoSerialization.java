package core.inter.tdc.protogen;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.inter.tdc.protogen.json.AddressBookJson;
import core.inter.tdc.protogen.json.PersonJson;
import core.inter.tdc.protogen.json.PhoneNumberJson;
import core.inter.tdc.protogen.json.PhoneTypeJson;
import core.inter.tdc.protogen.models.AddressBook;
import core.inter.tdc.protogen.models.Person;
import core.inter.tdc.protogen.models.PhoneNumber;
import core.inter.tdc.protogen.models.PhoneType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class ProtoSerialization {
    public static void main(String args[]) throws IOException {
        PhoneNumber phoneNumber = PhoneNumber.newBuilder()
                .setNumber("(41)99999-0000")
                .setType(PhoneType.MOBILE)
                .build();

        Person person = Person.newBuilder()
                .setEmail("marcel.costa@bancointer.com.br")
                .setName("Marcel Samaruga da Costa")
                .setPhone(phoneNumber)
                .build();

        AddressBook addressBook = AddressBook.newBuilder()
                .addPerson(person)
                .build();

        // save file
        Files.write(Path.of("address_book"), addressBook.toByteArray());

        // example for Kafka
        // org.apache.kafka.clients.producer.ProducerRecord<String, byte[]>

        byte[] addressBookBytesSerialized = Files.readAllBytes(Path.of("address_book"));

        AddressBook addressBookSerialized = AddressBook.parseFrom(addressBookBytesSerialized);
        System.out.println(addressBookSerialized);
        System.out.println("Proto byte[]: " + addressBookBytesSerialized.length + " elements");


        // JSON
        ObjectMapper mapper = new ObjectMapper();

        PhoneNumberJson phoneNumberJson = new PhoneNumberJson();
        phoneNumberJson.setNumber("(41)99999-0000");
        phoneNumberJson.setType(PhoneTypeJson.MOBILE);

        PersonJson personJson = new PersonJson();
        personJson.setEmail("marcel.costa@bancointer.com.br");
        personJson.setName("Marcel Samaruga da Costa");
        personJson.setPhones(Collections.singletonList(phoneNumberJson));

        AddressBookJson addressBookJson = new AddressBookJson();
        addressBookJson.setPerson(Collections.singletonList(personJson));

        byte[] addressBookJsonSerialized = mapper.writeValueAsBytes(addressBookJson);
        Files.write(Path.of(("address_book_json")), addressBookBytesSerialized);
        System.out.println("Json byte[]: "+ addressBookJsonSerialized.length + " elements");
    }

}
