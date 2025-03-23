import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileTransferClient {
    public static void main(String[] args) {
        try {
            // Specify the full path to the file
            String filePath = "C:/FileTransferProject/src/files/sample.txt";
            FileInputStream fileInputStream = new FileInputStream(filePath);

            // Calculate the MD5 checksum of the file
            String checksum = calculateMD5Checksum(filePath);
            System.out.println("Calculated MD5 Checksum: " + checksum);

            // Simulate sending the checksum to the server
            // In a real scenario, this would involve network operations
            System.out.println("Sending checksum to the server...");

            // Simulate sending the file to the server
            System.out.println("Sending file to the server...");
            int data;
            while ((data = fileInputStream.read()) != -1) {
                // Here you would write the data to the output stream connected to the server
                System.out.print((char) data); // Just for demonstration
            }

            // Close the stream
            fileInputStream.close();
            System.out.println("\nFile sent successfully!");

            // Simulate server-side checksum validation
            String serverChecksum = calculateMD5Checksum(filePath); // In real-world, this would be done on the server
            System.out.println("Server-side MD5 Checksum: " + serverChecksum);

            // Compare checksums to verify integrity
            if (checksum.equals(serverChecksum)) {
                System.out.println("File integrity verified successfully!");
            } else {
                System.out.println("File integrity verification failed!");
            }

        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // Method to calculate the MD5 checksum of a file
    private static String calculateMD5Checksum(String filePath) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream(filePath);

        byte[] dataBytes = new byte[1024];
        int bytesRead;

        while ((bytesRead = fis.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, bytesRead);
        }

        byte[] mdBytes = md.digest();

        // Convert the byte array into a hexadecimal string
        StringBuilder sb = new StringBuilder();
        for (byte b : mdBytes) {
            sb.append(String.format("%02x", b));
        }

        fis.close();
        return sb.toString();
    }
}

