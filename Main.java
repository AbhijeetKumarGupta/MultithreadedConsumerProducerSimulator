package BufferProducer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		/* Getting the command line arguments */

		System.out.println("How long to sleep before terminating?: ");
		int sleepTime = scan.nextInt();

		System.out.println("The number of producer threads: ");
		int noOfProd = scan.nextInt();

		System.out.println("The number of consumer threads: ");
		int noOfCons = scan.nextInt();

		/* Initializing the buffer */
		Buffer buffer = new Buffer();

		/* ArrayList for producer and consumer */

		List<Producer> producers = new ArrayList<Producer>();
		ArrayList<Consumer> consumers = new ArrayList<Consumer>();

		/* Producer threads */

		for (int i = 0; i < noOfProd; i++) {
			Producer prod = new Producer(buffer);
			producers.add(prod);

			new Thread(prod).start();
		}

		/* Consumer threads */

		for (int i = 0; i < noOfCons; i++) {

			Consumer con = new Consumer(buffer);
			consumers.add(con);

			new Thread(con).start();

		}
		/* Sleep mode */
		try {
			Thread.sleep(sleepTime);

		} catch (Exception e) {
		}

		/* Exit mode */
		for (Producer prod : producers) {
			prod.stop = true;
		}

		for (Consumer cons : consumers) {
			cons.stop = true;
		}
		
		scan.close();

	}
}
