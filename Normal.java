import java.util.ArrayList;
import java.util.Calendar;

public class Normal extends Member{
	public Normal(String[] name,String[] address,ArrayList<Book> rentalBook,boolean penalty,Calendar penaltyDate,int id){
		this.name = name;
		this.address = address;
		if(rentalBook == null) {
			this.rentalBook = new ArrayList<Book>();
			this.rentalNumber = 0;
		}else {
			this.rentalBook = rentalBook;
			this.rentalNumber = rentalBook.size();
		}
		this.exist = true;
		this.penalty = penalty;
		this.penaltyDate = penaltyDate;
		this.rentalMax = 3;
		this.rentalLimit = 14;
		this.id = id;
	}
}
