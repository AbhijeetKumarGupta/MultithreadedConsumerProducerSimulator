package BufferProducer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

	private Buffer buff;
	public boolean stop = false;

//Constructor
	public Producer(Buffer b) {
		buff = b;
	}

	@Override
	public void run() {
		Random rand = new Random();

		while (!stop) {

			try {
				TimeUnit.MICROSECONDS.sleep(rand.nextInt(Integer.MAX_VALUE) % 100000);
			} catch (Exception e) {
			}

//Generating a random number
			int num = rand.nextInt(3000) + 1;

// Inserting a number to the buffer
			if (buff.insert_Item(num)) {
				System.out.println("How many items were produced? : " + num);
			} else {
				System.out.println("Sorry. Buffer full, therefore producer cannot insert number");
			}
		}

	}
}
