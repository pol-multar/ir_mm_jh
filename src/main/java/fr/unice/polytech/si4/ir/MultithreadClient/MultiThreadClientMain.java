package fr.unice.polytech.si4.ir.MultithreadClient;

public class MultiThreadClientMain {
	public static void main(String args[]) {
		MultiThreadClient directoryClient = new MultiThreadClient();

		if (args.length != 2) {
			System.out
					.println("Launching client with default server address and port");
			directoryClient.launchClient("127.0.0.1", 9999);
			// directoryClient.launchClient("192.168.56.101",9999);

		} else {
			String hostName = args[0];
			int portNumber = Integer.parseInt(args[1]);
			System.out.println("Launching client, server address : " + hostName
					+ " port number " + portNumber);
			directoryClient.launchClient(hostName, portNumber);

		}
		System.out.println("Ending client ");
	}
}
