import java.io.*;
import java.net.*;
import java.util.*;

public class Serveur
{
	private static final int MAX_CLIENTS = 2;
	private static List<PrintWriter> clients = new ArrayList<>();
	private static List<String> colors = Arrays.asList("Blanc", "Noir");
	private static int currentPlayer = 0; // Indique le joueur actuel

	public static void main(String[] args)
	{
		try
		{
			ServerSocket serverSocket = new ServerSocket(6666);
			System.out.println("Serveur en attente des connexions...");

			for (int i = 0; i < MAX_CLIENTS; i++)
			{
				Socket clientSocket = serverSocket.accept();
				System.out.println("Nouvelle connexion: " + clientSocket);

				// Attribuer la couleur au joueur
				String color = colors.get(i);
				PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
				output.println(color);
				clients.add(output);

				Thread clientHandler = new Thread(new ClientHandler(clientSocket, color));
				clientHandler.start();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static class ClientHandler implements Runnable
	{
		private Socket clientSocket;
		private String color;

		public ClientHandler(Socket clientSocket, String color)
		{
			this.clientSocket = clientSocket;
			this.color = color;
		}

		@Override
		public void run()
		{
			try
			{
				BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String message;
				while ((message = input.readLine()) != null)
				{
					
					System.out.println("Coordonnees recues : " + message);

					// Verifier si c'est le tour du joueur actuel
					if (color.equals(colors.get(currentPlayer)))
					{

						for (PrintWriter client : clients)
						{
							if (client != null)
							{
								client.println(message);
							}
						}
						// Changer le joueur actuel
						currentPlayer = (currentPlayer + 1) % MAX_CLIENTS;
					}
					else
					{

						PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
						output.println("Ce n'est pas encore votre tour de jouer");
					}
				}

				/*input.close();
				clientSocket.close();*/
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
