package socket;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.net.Socket;

public class ClientSocketApplication {
   public static void main(String[] args) throws Exception {

   Socket clientSocket = new Socket("localhost", 3001);
			System.out.println(">>> Connected to port 3001");  
   
   try (InputStream is = clientSocket.getInputStream()) {
				BufferedInputStream bis = new BufferedInputStream(is);
				DataInputStream dis  = new DataInputStream(bis);
				String line = dis.readUTF(dis);
				System.out.println(">>>Reading from server :: " + line);
			} catch (EOFException e) { //End of file
				clientSocket.close();
			}
   }
}
