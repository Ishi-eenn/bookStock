import java.util.Scanner;

// 書籍
public class BookManagement implements BookManageInterface {
	public BookManagement(BookList bl) {
		boolean bookContinue = true;
		while (bookContinue) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("書籍");
			System.out.println("何を行いますか。");
			System.out.println("1.書籍の追加");
			System.out.println("2.書籍一覧");
			System.out.println("3.検索");
			System.out.println("4.戻る");
			int worknum = scanner.nextInt();
			if (worknum == 1) {
				System.out.println("書籍の追加");
				bookRegister(bl);
			} else if (worknum == 2) {
				System.out.println("書籍一覧");
				for (int i = 0; i < bl.book.size(); i++) {
					if (bl.book.get(i).exist == true) {
						System.out.println(bl.book.get(i).name);
					}
				}
				System.out.println("");
			} else if (worknum == 3) {
				System.out.println("検索");
				bookSearch(bl);
			} else if (worknum == 4) {
				bookContinue = false;
			} else {
				System.out.println("入力した数値は正しくありません。");
				System.out.println("");
			}
		}
	}

	// 書籍の追加
	public void bookRegister(BookList bl) {
		Scanner scanner = new Scanner(System.in);
		int check = 2;
		String name = null;
		String author = null;
		String publisher = null;
		int num = 0;
		while (check == 2) {
			name = bookName();
			author = bookAuthor();
			publisher = bookPublisher();
			num = bookNum();
			System.out.println("入力が正しければ1を、やり直す場合は2を入力してください。");
			System.out.println("題名:" + name);
			System.out.println("著者:" + author);
			System.out.println("出版社:" + publisher);
			System.out.println("冊数:" + num);
			check = scanner.nextInt();
		}
		Book b = new Book(name, author, publisher, num, bl.book.size());
		bl.book.add(b);
		System.out.println("追加が完了しました。");
		System.out.println("");
	}

	// 題名の入力
	public String bookName() {
		Scanner scanner = new Scanner(System.in);
		String name = null;
		System.out.println("題名を入力してください。");
		name = scanner.next();
		return name;
	}

	// 著者名の入力
	public String bookAuthor() {
		Scanner scanner = new Scanner(System.in);
		String author = null;
		System.out.println("著者名を入力してください。");
		author = scanner.next();
		return author;
	}

	// 出版社の入力
	public String bookPublisher() {
		Scanner scanner = new Scanner(System.in);
		String publisher = null;
		System.out.println("出版社名を入力してください。");
		publisher = scanner.next();
		return publisher;
	}

	// 書籍の冊数の入力
	public int bookNum() {
		Scanner scanner = new Scanner(System.in);
		int num = 0;
		System.out.println("冊数を入力してください。");
		num = scanner.nextInt();
		return num;
	}

	// 検索
	public void bookSearch(BookList bl) {
		Scanner scanner = new Scanner(System.in);
		boolean searchContinue = true;
		while (searchContinue) {
			System.out.println("1.題名から検索");
			System.out.println("2.著者名から検索");
			System.out.println("3.出版社から検索");
			System.out.println("4.戻る");
			int searchNum = scanner.nextInt();
			String keyword = null;
			int result = 0;

			if (searchNum == 1) {
				System.out.println("題名から検索");
				keyword = scanner.next();
				for (int i = 0; i < bl.book.size(); i++) {
					Book b = bl.book.get(i);
					if (b.exist == true && b.name.contains(keyword) == true) {
						result = result + 1;
						System.out.println(result + ".");
						searchResult(b);
					}
				}
				if (result == 0) {
					System.out.println("該当する書籍は見つかりませんでした。");
					System.out.println("");
				}
			} else if (searchNum == 2) {
				System.out.println("著者名から検索");
				keyword = scanner.next();
				for (int i = 0; i < bl.book.size(); i++) {
					Book b = bl.book.get(i);
					if (b.exist == true && b.author.contains(keyword) == true) {
						result = result + 1;
						System.out.println(result + ".");
						searchResult(b);
					}
				}
				if (result == 0) {
					System.out.println("該当する書籍は見つかりませんでした。");
					System.out.println("");
				}
			} else if (searchNum == 3) {
				System.out.println("出版社から検索");
				keyword = scanner.next();
				for (int i = 0; i < bl.book.size(); i++) {
					Book b = bl.book.get(i);
					if (b.exist == true && b.publisher.contains(keyword) == true) {
						result = result + 1;
						System.out.println(result);
						searchResult(b);
					}
				}
				if (result == 0) {
					System.out.println("該当する書籍は見つかりませんでした。");
					System.out.println("");
				}
			} else if (searchNum == 4) {
				searchContinue = false;
			} else {
				System.out.println("入力された値は正しい値ではありません。");
				System.out.println("");
			}
		}
	}

	public void searchResult(Book b) {
		System.out.println("題名:" + b.name);
		System.out.println("著者名:" + b.author);
		System.out.println("出版社:" + b.publisher);
		System.out.println("在庫数:" + b.num);
		System.out.println("");
	}

	// id管理
	public static int idManagement(BookList bl) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("書籍のIDを入力してください。");
		int id = scanner.nextInt();
		if (id < 0) {
			System.out.println("入力された値は正しい値ではありません。");
			return -1;
		} else if (bl.book.size() - 1 < id || bl.book.get(id).exist == false) {
			System.out.println("書籍が存在しません。");
			return -1;
		} else {
			Book b = bl.book.get(id);
			System.out.println("題名:" + b.name);
			System.out.println("著者:" + b.author);
			System.out.println("出版社:" + b.publisher);
			System.out.println("");
			return id;
		}
	}
}
