package BufferProducer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

	private Buffer buff;

	public boolean stop = false;

	/* Constructor */
	public Consumer(Buffer b) {
		buff = b;
	}

	@Override
	public void run() {
		Random rand = new Random();
		/* Generate a random number */

		while (!stop) {

			try {

				TimeUnit.MICROSECONDS.sleep(rand.nextInt(Integer.MAX_VALUE) % 100000);

			} catch (Exception e) {

			}

			Constant num = new Constant();

			/* Inserting a number to the buffer */
			if (buff.remove_Item(num)) {
				System.out.println("How many items were consumed? : " + num.value);
			} else {
				System.out.println("Sorry. Buffer empty, therefore consumer cannot insert number");
			}
		}

	}
}
