import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PcServer {

	public static void main(String[] args) {
		try
		(
				ServerSocket ss = new ServerSocket(5000);
				Socket socket = ss.accept();
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		)
		{
			System.out.println("Client Connected");
			boolean done = false;
			while(!done)
			{
				String message = dis.readUTF();
				System.out.println("Client says: " + message);
				
				if(message.equalsIgnoreCase("quit"))
				{
					done = true;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
