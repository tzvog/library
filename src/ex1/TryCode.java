package ex1;

class TryCode {

	void ma() {
		
		Library lib = new Library(4,10,8);
		
		Book book1 = new Book("my", "fair", 5, 6, 7, 9);
		Book book2 = new Book("my", "fair", 5, 6, 7, 9);
		Patron pat = new Patron("t", "vog", 1, 2, 3, 1000);
		
		System.out.println(lib.addBookToLibrary(book1));
		System.out.println(lib.addBookToLibrary(book2));
		System.out.println(lib.addBookToLibrary(book1));
		System.out.println(lib.addBookToLibrary(book1));
		System.out.println(lib.addBookToLibrary(book1));
		
		System.out.println(lib.registerPatronToLibrary(pat));
		System.out.println(lib.getPatronId(pat));
		System.out.println((lib.suggestBookToPatron(lib.getPatronId(pat))));
	}
}
