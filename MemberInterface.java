import java.util.ArrayList;
import java.util.Calendar;

interface MemberInterface {
	int id = 0;
	String[] name = new String[2];
	int rentalNumber = 0;
	String[] address = new String[3];
	ArrayList<Book> rentalBook = new ArrayList<Book>();
	int rentalMax = 10;
	int rentalLimit = 10;
	boolean penalty = true;
	Calendar penaltyDate = Calendar.getInstance();

	static Member createMember(String[] name, String[] address, ArrayList<Book> rentalBook, boolean penalty,
			Calendar penaltyDate, int id) {

		return new Normal(name, address, rentalBook, penalty, penaltyDate, id);
	}
}
