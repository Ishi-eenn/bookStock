import java.util.Scanner;

public class MemberManagement implements MemberManageInterface {
	public MemberManagement(MemberList ml) {
		boolean memberContinue = true;

		while (memberContinue) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("メンバー");
			System.out.println("何を行いますか。");
			System.out.println("1.追加");
			System.out.println("2.変更");
			System.out.println("3.メンバー確認");
			System.out.println("4.延滞確認");
			System.out.println("5.戻る");
			int worknum = scanner.nextInt();

			if (worknum == 1) {
				System.out.println("追加");
				memberRegister(ml);
			} else if (worknum == 2) {
				System.out.println("変更");
				memberEdit(ml);
			} else if (worknum == 3) {
				System.out.println("メンバー確認");
				int memberCheck = idManagement(ml);
			} else if (worknum == 4) {
				System.out.println("延滞確認");
				DelayCheck(ml);
			} else if (worknum == 5) {
				memberContinue = false;
			} else {
				System.out.println("入力した数値は正しくありません。");
				System.out.println("");
			}
		}
	}

	// メンバー追加
	public void memberRegister(MemberList ml) {
		Scanner scanner = new Scanner(System.in);
		int check = 2;
		String[] name = new String[2];
		String[] address = new String[3];
		while (check == 2) {
			name = memberName();
			address = memberAddress();
			System.out.println("入力が正しければ1を、やり直す場合は2を入力してください。");
			System.out.println("名前:" + name[0] + name[1]);
			System.out.println("住所:" + address[0] + address[1] + address[2]);
			check = scanner.nextInt();
		}
		Member m = MemberInterface.createMember(name, address, null, false, null, ml.member.size());
		ml.member.add(m);
		System.out.println("会員登録が完了しました。");
		System.out.println("ID:" + (ml.member.size() - 1));
		System.out.println("");
	}

	// 変更
	public void memberEdit(MemberList ml) {
		Scanner scanner = new Scanner(System.in);
		int id = -1;
		boolean workContinue = true;
		while (workContinue) {
			id = idManagement(ml);
			if (id < 0) {
				workContinue = false;
			} else {
				Member m = ml.member.get(id);
				System.out.println("何を行いますか。");
				System.out.println("1.名前の変更");
				System.out.println("2.住所の変更");
				int worknum = scanner.nextInt();
				if (worknum == 1) {
					int nameflag = 2;
					String[] name = new String[2];
					while (nameflag == 2) {
						name = memberName();
						System.out.println("入力が正しければ1を、やり直す場合は2を入力してください。");
						System.out.println("名前:" + name[0] + name[1]);
						nameflag = scanner.nextInt();
					}
					m.name = name;
					System.out.println("名前の変更が完了しました。");
					System.out.println("");
					workContinue = false;
				} else if (worknum == 2) {
					int addressflag = 2;
					String[] address = new String[3];
					while (addressflag == 2) {
						address = memberAddress();
						System.out.println("入力が正しければ1を、やり直す場合は2を入力してください。");
						System.out.println("住所:" + address[0] + address[1] + address[2]);
						addressflag = scanner.nextInt();
					}
					m = MemberInterface.createMember(m.name, address, m.rentalBook, m.penalty, m.getPenaltyDate(),
							m.id);
					ml.member.set(id, m);
					System.out.println("住所の変更が完了しました。");
					System.out.println("");
					workContinue = false;
				}
			}
		}
	}

	// 名前の入力
	public String[] memberName() {
		Scanner scanner = new Scanner(System.in);
		String[] name = new String[2];
		System.out.println("名字と名前を空白区切で入力してください。");
		name[0] = scanner.next();
		name[1] = scanner.next();
		return name;
	}

	// 住所の入力
	public String[] memberAddress() {
		Scanner scanner = new Scanner(System.in);
		String[] address = new String[3];
		System.out.println("住所を以下にしたがって入力してください。");
		System.out.println("都道府県を入力してください。");
		address[0] = scanner.next();
		System.out.println("市町村区を入力してください。");
		address[1] = scanner.next();
		System.out.println("それ以降の住所を入力してください。");
		address[2] = scanner.next();
		return address;
	}

	// メンバー確認
	public static int idManagement(MemberList ml) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("会員IDを入力してください。");
		int id = scanner.nextInt();
		if (id < 0) {
			System.out.println("入力された値は正しい値ではありません。");
			System.out.println("");
			return -1;
		} else if (ml.member.size() - 1 < id || ml.member.get(id).exist == false) {
			System.out.println("会員が存在しません。");
			System.out.println("");
			return -1;
		} else {
			Member m = ml.member.get(id);
			System.out.println("ID:" + m.id);
			System.out.println("名前:" + m.name[0] + m.name[1]);
			System.out.println("住所:" + m.address[0] + m.address[1] + m.address[2]);
			System.out.println("貸し出し中の書籍の数:" + m.rentalNumber);
			if (m.rentalNumber != 0) {
				for (int i = 0; i < m.rentalNumber; i++) {
					System.out.println(i + 1 + ".");
					System.out.println(m.rentalBook.get(i).name);
					System.out.println(m.rentalBook.get(i).cal.getTime());
				}
			}
			System.out.println("");
			return id;
		}
	}

	// 延滞確認
	public static void DelayCheck(MemberList ml) {
		boolean dl = false;
		for (int i = 0; i < ml.member.size(); i++) {
			dl = Deadline.deadlineCheck(ml.member.get(i));
		}
		if (dl == false) {
			System.out.println("延滞はありません");
			System.out.println("");
		}
	}
}
