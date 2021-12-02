package core.inter.tdc.protogen;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import core.inter.tdc.protogen.json.AddressBookJson;
import core.inter.tdc.protogen.json.PersonJson;
import core.inter.tdc.protogen.json.PhoneNumberJson;
import core.inter.tdc.protogen.json.PhoneTypeJson;
import core.inter.tdc.protogen.models.AddressBook;
import core.inter.tdc.protogen.models.Person;
import core.inter.tdc.protogen.models.PhoneNumber;
import core.inter.tdc.protogen.models.PhoneType;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
import java.util.function.Supplier;

public class PerformanceComparison {
    public static void main(String args[])  {
        System.out.println("Proto:");
        runPerformanceTest(
                protoConsumer()
        );

        System.out.println("Json:");
        runPerformanceTest(
                jsonConsumer()
        );
    }

    private static void runPerformanceTest(Supplier consumer) {
        long begin = System.currentTimeMillis();

        for (int i=0; i < 10_000; i++) {
            consumer.get();
        }

        System.out.println(System.currentTimeMillis() - begin + " milliseconds.");
    }

    private static Supplier<AddressBookJson> jsonConsumer() {
        return () -> {
            ObjectMapper mapper = new ObjectMapper();
            AddressBookJson addressBookJson = buildAddressBookJson();
            try {
                byte[] addressBookJsonSerialized = mapper.writeValueAsBytes(addressBookJson);
                mapper.readValue(addressBookJsonSerialized, AddressBookJson.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return addressBookJson;
        };
    }

    private static Supplier<AddressBook> protoConsumer()  {
        return () -> {
            AddressBook addressBook = buildAddressBookProto();
            try {
                byte[] addressBookSerialized = addressBook.toByteArray();
                AddressBook.parseFrom(addressBookSerialized);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }

            return addressBook;
        };
    }

    private static AddressBook buildAddressBookProto() {
        PhoneNumber phoneNumber = PhoneNumber.newBuilder()
                .setNumber(UUID.randomUUID().toString())
                .setType(PhoneType.MOBILE)
                .build();

        Person person = Person.newBuilder()
                .setEmail(UUID.randomUUID().toString())
                .setName(UUID.randomUUID().toString())
                .setPhone(phoneNumber)
                .build();

        AddressBook addressBook = AddressBook.newBuilder()
                .addPerson(person)
                .build();

        return addressBook;
    }

    private static AddressBookJson buildAddressBookJson() {
        PhoneNumberJson phoneNumberJson = new PhoneNumberJson();
        phoneNumberJson.setNumber(UUID.randomUUID().toString());
        phoneNumberJson.setType(PhoneTypeJson.MOBILE);

        PersonJson personJson = new PersonJson();
        personJson.setEmail(UUID.randomUUID().toString());
        personJson.setName(UUID.randomUUID().toString());
        personJson.setPhones(Collections.singletonList(phoneNumberJson));

        AddressBookJson addressBookJson = new AddressBookJson();
        addressBookJson.setPerson(Collections.singletonList(personJson));

        return addressBookJson;
    }

}
