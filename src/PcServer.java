import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class PcServer {

	public static void main(String[] args) {
		System.out.println("Server Started");
		JFrame frame = new JFrame();
		
		frame.setTitle("Dab on the Map");
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		
		MapMaker mk = new MapMaker();
		frame.add(mk);
		frame.setVisible(true);
		
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
				if(message.contains(","))
				{
					String[] sArray = message.split(",");
					double x = Double.parseDouble(sArray[0]);
					double y = Double.parseDouble(sArray[1]);
					mk.addPosition(x, y);
				}
				if(message.equalsIgnoreCase("quit"))
				{
					done = true;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
