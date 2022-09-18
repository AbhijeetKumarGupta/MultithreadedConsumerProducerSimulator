package BufferProducer;

/**
*
* @author UNKNOWN
*/
import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

public class Buffer {

	private static final int BUFF_SIZE = 5;

	private Integer[] buff = new Integer[BUFF_SIZE];

	private Mutex mut = new Mutex();

	private int itemsSize = 0;

	/*
	 * Insert an object from buffer placing it in item, return 0 if successful,
	 * otherwise return -1, indicating an error condition
	 */
	public boolean insert_Item(Integer item) {
		try {
			mut.acquire();
			if (itemsSize >= buff.length) {
				mut.release();

				return false;
			}
			buff[itemsSize++] = item;

			mut.release();

		} catch (Exception e) {

			mut.release();

			return false;
		}

		return true;
	}

	/*
	 * Remove an object from buffer placing it in item, return 0 if successful,
	 * otherwise return -1, indicating an error condition
	 */
	public boolean remove_Item(Constant item) {

		try {
			mut.acquire();
			if (itemsSize == 0) {
				mut.release();

				return false;
			}

			item.value = buff[0];

			for (int i = 0; i < itemsSize - 1; i++) {
				buff[i] = buff[i + 1];
			}
			itemsSize--;

			mut.release();

		} catch (Exception e) {

			mut.release();

			return false;
		}
		return true;
	}

}
