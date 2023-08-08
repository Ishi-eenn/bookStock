import java.util.ArrayList;
import java.util.Calendar;

interface BookInterface {

	String name = "";
	String author = "";
	String publisher = "";
	int num = 1;
	int numMax = 10;
	Calendar cal = Calendar.getInstance();
	ArrayList<Member> rentalMember = new ArrayList<Member>();
	int id = 0;;
	boolean exist = true;

}
