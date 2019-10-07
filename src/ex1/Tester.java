package ex1;


public class Tester {

    public static void main(String[] args) {

        // set up
        Book book1 = new Book("book1", "author1", 2001,
                2, 3, 1);
        Book book2 = new Book("book2", "author2", 2002,
                8, 3, 6);
        Book book3 = new Book("book3", "author2", 2002,
                8, 3, 6);
        Book book4 = new Book("book4", "author4", 2004,
                10, 10, 10);

        int book1ID, book2ID, book3ID, book4ID;

        Patron patron1 = new Patron("patron1", "last1", 3,
                5, 1, 22);
        Patron patron2 = new Patron("patron1", "last1", 2,
                1, 1, 0);
        Patron patron3 = new Patron("patron3", "last3", 2,
                1, 1, 0);

        int patron1ID1, patron2ID1, patron1ID2, patron2ID2;

        Library lib = new Library(3, 2, 2);

//        return;


        // test book
        System.out.println("test Book methods");
        System.out.println();

        System.out.println(book1.stringRepresentation().equals("[book1,author1,2001,6]"));

        System.out.println(book1.getLiteraryValue() == 2 + 3 + 1);

        System.out.println(book1.getCurrentBorrowerId() < 0);

        book1.setBorrowerId(5);
        System.out.println(book1.getCurrentBorrowerId() == 5);
        book1.returnBook();


        // test patron
        System.out.println();

        System.out.println("test Patron methods");
        System.out.println();

        System.out.println("0.1:" + patron1.stringRepresentation().equals("patron1 last1"));

        System.out.println("0.2:" + (patron1.getBookScore(book1) == 3 * 2 + 5 * 3 + 1 * 1));

        System.out.println("0.3:" + patron1.willEnjoyBook(book1)); // 22=22

        System.out.println("0.4:" + patron1.willEnjoyBook(book2));


        // test library
        System.out.println();

        System.out.println("test Library methods");
        System.out.println();

        System.out.println("1: " + (!lib.isBookIdValid(1)));
        System.out.println("1.1: " + (!lib.isBookIdValid(88)));
        System.out.println("1.2: " + (!lib.isBookAvailable(1)));

        book1ID = lib.addBookToLibrary(book1);
        System.out.println("2: " + (book1ID >= 0));
        System.out.println("3: " + lib.isBookIdValid(book1ID));

        book2ID = lib.addBookToLibrary(book2);
        System.out.println("4: " + (book2ID >= 0));
        System.out.println("4.1: " + (book2ID != book1ID));
        System.out.println("4.2: " + (lib.isBookIdValid(book2ID)));
        System.out.println("4.3: " + (lib.addBookToLibrary(book2) == book2ID)); // add the same book, should
        // return the index of original and shouldn't save another copy.

        book3ID = lib.addBookToLibrary(book3); // book with same  arguments
        System.out.println("5: " + (book3ID >= 0 && book3ID != book1ID && book3ID != book2ID));

        System.out.println("6: " + (lib.addBookToLibrary(book4) < 0)); // no room
        System.out.println("6.1: " + (lib.addBookToLibrary(book2) == book2ID)); // add the same book when
        // there is no room.

        System.out.println("7: " + (!lib.isPatronIdValid(1)));
        System.out.println("7.1: " + (!lib.isPatronIdValid(8)));

        patron1ID1 = lib.registerPatronToLibrary(patron1);
        System.out.println("8:" + (patron1ID1 >= 0));
        System.out.println("9:" + (lib.registerPatronToLibrary(patron1) == -1));
        System.out.println("10: " + (lib.isPatronIdValid(patron1ID1)));

        patron2ID1 = lib.registerPatronToLibrary(patron2);
        System.out.println("11: " + (lib.registerPatronToLibrary(patron2) == -1));
        System.out.println("12: " + (lib.isPatronIdValid(patron2ID1)));

        System.out.println("13: " + (lib.registerPatronToLibrary(patron3) < 0));
        System.out.println("14: " + (lib.registerPatronToLibrary(patron1) == -1));

        System.out.println("15: " + (lib.getPatronId(patron2) == patron2ID1));

        // tests for 'borrowBook'
        System.out.println("16: " + (lib.isBookAvailable(book1ID)));
//        System.out.println("wiil enjoy?" + patron1.willEnjoyBook(book1));
        System.out.println("17: " + (lib.borrowBook(book1ID, patron1ID1))); // will not enjoy
        System.out.println("18: " + (!lib.borrowBook(book1ID, patron2ID1)));
        System.out.println("19: " + (!lib.isBookAvailable(book1ID)));
        System.out.println("20: " + (!lib.borrowBook(80, patron2ID1))); // book id is not valid
        System.out.println("21: " + (!lib.borrowBook(book2ID, 80))); // patron id is not
        // valid
        System.out.println("22: " + (lib.borrowBook(book2ID, patron2ID1)));
        System.out.println("23: " + (lib.borrowBook(book3ID, patron2ID1))); // too many booksArray for one patron
        System.out.println("24: " + (!lib.borrowBook(book2ID, patron1ID1))); // the book is taken
        lib.returnBook(book1ID);
        System.out.println("25: " + (!lib.borrowBook(book3ID, patron2ID1)));
        System.out.println("26: " + (book1.getCurrentBorrowerId() < 0));


        for (Book book : lib.bookShelf)
            if (book != null)
                System.out.println(book.stringRepresentation());
        System.out.println();

        System.out.println("27: " + (lib.getBookId(lib.suggestBookToPatron(patron2ID1)) == book1ID));
        System.out.println("28: " + (lib.suggestBookToPatron(patron1ID1) != null)); // null because book2 and
        // book3 are taken and he will not enjoy book1

        // two libraries tests
        Library lib2 = new Library(1, 1, 2);
        System.out.println();
        System.out.println("two libraries tests");
        System.out.println();

        System.out.println("29: " + (!lib2.isBookIdValid(0)));

        book4ID = lib2.addBookToLibrary(book4);
        System.out.println("30: " + (book4ID >= 0));


        patron1ID2 = lib2.registerPatronToLibrary(patron1);
        patron2ID2 = lib2.registerPatronToLibrary(patron2);

        System.out.println("31: " + (patron1ID2 >= 0));
        System.out.println("32: " + (patron2ID2 >= 0));
        System.out.println("33: " + (patron1ID2 != patron2ID2));

        System.out.println("34: " + (lib2.borrowBook(book4ID, patron1ID2))); // patron1 has the max number
        // of booksArray in library1, should be able to borrow in another library.

    }
}

