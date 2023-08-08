interface BookManageInterface {
	void bookRegister(BookList bl);
	String bookName();
	String bookAuthor();
	String bookPublisher();
	int bookNum();
	void bookSearch(BookList bl);
	void searchResult(Book b);
}
