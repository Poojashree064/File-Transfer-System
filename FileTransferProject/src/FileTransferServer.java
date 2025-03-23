import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;

public class FileTransferServer {
    public static void main(String[] args) {
        try {
            // Create a server socket listening on port 8080
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server is listening on port 8080...");

            // Server will continue to accept clients in an infinite loop
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");

                InputStream inputStream = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                // Read checksum sent by the client
                String expectedChecksum = reader.readLine();
                System.out.println("Expected checksum received from client: " + expectedChecksum);

                // Prepare to receive the file
                File receivedFile = new File("C:/FileTransferProject/src/files/received_sample.txt");
                FileOutputStream fileOutputStream = new FileOutputStream(receivedFile);

                // Buffer for reading file data
                byte[] buffer = new byte[4096];
                int bytesRead;

                // Receive the file and write it to disk
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                // Close the file output stream and socket
                fileOutputStream.close();
                socket.close();
                System.out.println("File received and saved successfully.");

                // Calculate the checksum of the received file
                String actualChecksum = calculateChecksum(receivedFile);
                System.out.println("Calculated MD5 Checksum of received file: " + actualChecksum);

                // Verify the integrity of the received file by comparing checksums
                if (expectedChecksum.equals(actualChecksum)) {
                    System.out.println("File integrity verified. The file was received correctly.");
                } else {
                    System.out.println(
                            "File integrity check failed. The file may have been altered during transmission.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to calculate the MD5 checksum of a file
    public static String calculateChecksum(File file) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream(file);

        // Buffer to read file data
        byte[] buffer = new byte[1024];
        int bytesRead;

        // Read through the file and update the MessageDigest with file data
        while ((bytesRead = fis.read(buffer)) != -1) {
            md.update(buffer, 0, bytesRead);
        }

        // Close the file input stream
        fis.close();

        // Generate the MD5 digest
        byte[] digest = md.digest();

        // Convert the byte array into a hexadecimal string
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }

        // Return the checksum as a string
        return sb.toString();
    }
}
