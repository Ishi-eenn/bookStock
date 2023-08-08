import java.util.Calendar;
import java.util.Scanner;

public class RentalManagement {
	public RentalManagement(MemberList ml, BookList bl) {
		System.out.println("貸し出し");
		Scanner scanner = new Scanner(System.in);
		Calendar now = Calendar.getInstance();
		int memberID = MemberManagement.idManagement(ml);
		if (memberID >= 0) {
			Member m = ml.member.get(memberID);
			if (m.penalty == true) {
				int availableDay = calcAvailableDay(now, m);
				if (availableDay > 0) {
					printMessage("只今ご利用できません。 ご利用再開は" + availableDay + "日後からとなります。");
				} else {
					m.penalty = false;
				}
			}
			if (m.rentalNumber >= m.rentalMax) {
				printMessage("これ以上は借りることができません");
			} else if (Deadline.deadlineCheck(m) == false) {
				printMessage("お貸ししている書籍の中に期限を過ぎているものがあるため、"
						+ "貸し出しを行えません。");
			} else if (m.penalty == false) {
				System.out.println("書籍のIDを入力してください。");
				int bookID = scanner.nextInt();
				Book b = bl.book.get(bookID);
				if (b.num <= 0) {
					printMessage("この書籍は借りることができません。");
				} else {
					checkOut(b, m);
				}
			}
		}
	}

	private void printMessage(String str) {
		System.out.println(str);
		System.out.println("");
	}

	private int calcAvailableDay(Calendar now, Member m) {
		return 7 - Deadline.diffDays(now.getTimeInMillis() - m.penaltyDate.getTimeInMillis());
	}

	private void checkOut(Book b, Member m) {
		b.num--;
		Book copy = new Book(b.name, b.author, b.publisher, b.id);
		copy.cal = Calendar.getInstance();

		m.rentalBook.add(copy);
		m.rentalNumber++;
		b.rentalMember.add(m);
		System.out.println("貸出処理が完了しました。");
		printMessage("返却期限は" + m.rentalLimit / 7 + "週間です");
	}
}
