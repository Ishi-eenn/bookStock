import java.util.ArrayList;
import java.util.Calendar;

public abstract class Member implements MemberInterface {

	int id;
	String[] name = new String[2];
	int rentalNumber;
	String[] address = new String[3];
	ArrayList<Book> rentalBook = new ArrayList<Book>();
	int rentalMax;
	int rentalLimit;
	boolean penalty;
	Calendar penaltyDate;

	public Calendar getPenaltyDate() {
		return penaltyDate;
	}

	boolean exist;
}
