

import java.util.UUID;

public class RandomStringUUID {
    public static void main(String[] args) {
        // Creating a random UUID (Universally unique identifier).
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        System.out.println("Random UUID String1 = " + randomUUIDString);
        System.out.println("UUID version       = " + uuid.version());
        System.out.println("UUID variant       = " + uuid.variant());
        
       uuid = UUID.randomUUID();
        randomUUIDString = uuid.toString();
        System.out.println("Random UUID String2 = " + randomUUIDString);
    }
}