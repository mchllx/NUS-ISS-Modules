package socket;
import java.util.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketApplication {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(3001);
			System.out.println(">>> Awaiting connection");
			Socket serverSocket = server.accept(); //blocked until connection arrives
			System.out.println(">>> Connected successfully");
		
			OutputStream os = serverSocket.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			DataOutputStream dos = new DataOutputStream(bos);
			dos.writeUTF("hello, world");
			dos.flush();

			} catch (EOFException e) {
				System.out.println(">>>Failed to connect EOF" + e.getMessage()); 	
			} catch (IOException e) {
				System.out.println(">>>Failed to connect IO" + e.getMessage()); 
			}
	}
}

