import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// 処理を選択する
		MemberList ml = new MemberList();
		BookList bl = new BookList();

		boolean workContinue = true;
		while (workContinue == true) {
			System.out.println("番号を選択してください。");
			System.out.println("1.メンバー");
			System.out.println("2.書籍");
			System.out.println("3.貸し出し");
			System.out.println("4.返却");
			System.out.println("5.終了");

			Scanner scanner = new Scanner(System.in);
			int worknum = scanner.nextInt();

			if (worknum == 1)
				new MemberManagement(ml);
			else if (worknum == 2)
				new BookManagement(bl);
			else if (worknum == 3)
				new RentalManagement(ml, bl);
			else if (worknum == 4)
				new ReturnManagement(ml, bl);
			else if (worknum == 5)
				workContinue = false;
			else
				System.out.println("入力した数値は正しくありません。");
		}
	}
}
