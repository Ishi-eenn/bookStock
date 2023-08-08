import java.util.Calendar;
import java.util.Objects;
import java.util.Scanner;

public class ReturnManagement {
	public ReturnManagement(MemberList ml, BookList bl) {
		System.out.println("返却");
		Scanner scanner = new Scanner(System.in);
		int memberID = MemberManagement.idManagement(ml);

		if (!Objects.isNull(memberID)) {
			System.out.println("書籍のIDを入力してください。");
			int bookID = scanner.nextInt();
			Book b = bl.book.get(bookID);

			Calendar now = Calendar.getInstance();
			int rentalMemberID = 0;
			int rentalBookID = 0;
			if (memberID >= 0) {
				boolean correctPair = false;
				Member m = ml.member.get(memberID);
				for (int i = 0; i < b.rentalMember.size(); i++) {
					if (b.rentalMember.get(i).id == memberID) {
						rentalMemberID = i;
						break;
					}
				}
				for (int i = 0; i < m.rentalNumber; i++) {
					if (m.rentalBook.get(i).id == b.id) {
						correctPair = true;
						rentalBookID = i;
						break;
					}
				}
				if (correctPair == false) {
					System.out.println("この書籍はこの会員に借りられていません。");
					System.out.println("");
				} else if (DeadlineOverCheack(now, m, rentalBookID)) {
					DeadlineOver(b, m, rentalBookID, rentalMemberID);
				} else {
					returnBook(b, m, rentalBookID, rentalMemberID);
				}
			} else {
				System.out.println("正しい値を入力してください");
				System.out.println("");
			}
		}
	}

	private boolean DeadlineOverCheack(Calendar now, Member m, int rentalBookID) {
		return (Deadline.diffDays(now.getTimeInMillis() -
				m.rentalBook.get(rentalBookID).cal.getTimeInMillis()) > m.rentalLimit);
	}

	private void DeadlineOver(Book b, Member m, int rentalBookID, int rentalMemberID) {
		System.out.println("貸出期間が過ぎています。");
		System.out.println("次回のご利用は一週間後からとなります。");
		m.penaltyDate = Calendar.getInstance();
		m.penalty = true;
		returnBook(b, m, rentalBookID, rentalMemberID);
	}

	private void returnBook(Book b, Member m, int rentalBookID, int rentalMemberID) {
		b.num++;
		m.rentalBook.remove(rentalBookID);
		m.rentalNumber--;
		b.rentalMember.remove(rentalMemberID);
		System.out.println("返却処理が完了しました。");
		System.out.println("");
	}
}
