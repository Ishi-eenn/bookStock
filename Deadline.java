import java.util.Calendar;
import java.util.Objects;

public class Deadline {
	public static boolean deadlineCheck(Member m) {
		Calendar now = Calendar.getInstance();
		int delayflag = 0;
		if (m.rentalBook == null) {
			return true;
		} else {
			delayflag = deadlineFlag(now, m);
			if (Objects.equals(delayflag, 0)) {
				return true;
			} else {
				return false;
			}
		}
	}

	private static int deadlineFlag(Calendar now, Member m) {
		for (int i = 0; i < m.rentalNumber; i++) {
			if (check(now, m, i)) {
				System.out.println(m.name[0] + m.name[1] + ":");
				System.out.println(m.rentalBook.get(i).name);
				System.out.println("貸し出し日時:" + m.rentalBook.get(i).cal.getTime());
				System.out.println("");
				return 1;
			}
		}
		return 0;
	}

	private static boolean check(Calendar now, Member m, int i) {
		if (diffDays(now.getTimeInMillis() -
				m.rentalBook.get(i).cal.getTimeInMillis()) > m.rentalLimit) {
			return true;
		}
		return false;
	}

	public static int diffDays(long diffTime) {
		int diffDaysMillis = 1000 * 60 * 60 * 24;
		return (int) (diffTime / diffDaysMillis);
	}

}
