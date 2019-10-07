package ex1;

/*
 * This class represents a library, which hold a collection of books.
 *  Patrons can register at the library to be able to check out books, 
 *  if a copy of the requested book is available.
 */
class Library {

	/** The maximal number of books this library can hold. */
	final int maxBookCapacity;
	
	/** The maximal number of books this library allows a single patron to borrow at the same time. */
	final int maxBorrowedBooks;
	
	/** The maximal number of registered patrons this library can handle. */
	final int maxPatronCapacity;

	/** The book shelf to hold all the books. */
	Book[] bookShelf;
	
	/** The amount of books we currently have. */
	int bookCounter;
	
	/** The list to hold all the patrons. */
	Patron[] patronlist;
	
	/** a list to count the amount of books every patron took. */
	int [] patronBookCount;
	
	/** The amount of patrons we currently have. */
	int patronCounter; 
	
	/*----=  Constructors  =-----*/
	
	/**
	 * Creates a new patron with the given characteristics.
	 * 
	 * @param maxBookCapacity		The maximal number of books this library can hold.
	 * @param maxBorrowedBooks      The maximal number of books this library allows a 
	 * 								single patron to borrow at the same time
	 * @param maxPatronCapacity		The maximal number of registered patrons this library can handle.
	 */
	public Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity) {
		this.maxBookCapacity = maxBookCapacity;
		this.maxBorrowedBooks = maxBorrowedBooks;
		this.maxPatronCapacity = maxPatronCapacity;
		this.bookShelf = new Book[this.maxBookCapacity];
		this.bookCounter = 0;
		this.patronlist = new Patron[this.maxPatronCapacity];
		this.patronCounter = 0;
		this.patronBookCount = new int[this.maxPatronCapacity];
	}
	
	/*----=  Instance Methods  =-----*/
	
	/**
	 * Adds the given book to this library, if there is place available, and it isn't already in the library.
	 * @param book 		The book to add to this library
	 * @return a 		non-negative id number for the book if there was a spot and the book was successfully added,
	 *  or if the book was already in the library; a negative number otherwise
	 */
	int addBookToLibrary(Book book) {
		
		// checks if the book is already in our library 
		int checkBookId = this.getBookId(book);

		if (checkBookId != -1) {
			return checkBookId;
		}
		
		// checks if passed the amount of books we can hold
		if (this.bookCounter >= this.maxBookCapacity) {
			return -1; 
		}
			
		// adds the book to the bookshelf
		this.bookShelf[this.bookCounter] = book; 
		
		// update the counter
		this.bookCounter ++; 
		
		return (this.bookCounter - 1); 
	}
	
	/**
	 * Returns true if the given number is an id of some book in the library, false otherwise.
	 * @param 		bookId The id to check
	 * @return 		true if the given number is an id of some book in the library, false otherwise.
	 */
	boolean isBookIdValid(int bookId) {
		
		// checks if the id is to big for the book case or if that location is not taken 
		if (bookId >= this.maxBookCapacity || this.bookShelf[bookId] == null || bookId < 0) {
			return false; 
		}
		
		return true; 
	}
	
	
	/**
	 * Returns the non-negative id number of the given book if he is owned by this library, -1 otherwise.
	 * @param 		The book for which to find the id number
	 * @return		a non-negative id number of the given book if he is owned by this library, -1 otherwise.
	 */
	int getBookId(Book book) {
		
		// goes through all our books
		for (int index = 0; index < this.bookCounter; index++) {

			// checks if the book is already in the library
			if (book == this.bookShelf[index]) {
				return index;
			}
		}
		
		// we could not find the book 
		return -1; 
	}
	
	/**
	 * Returns true if the book with the given id is available, false otherwise.
	 * @param bookId	The id number of the book to check
	 * @return			true if the book with the given id is available, false otherwise
	 */
	boolean isBookAvailable(int bookId) {
		
		// checks if the books id is valid
		if (!this.isBookIdValid(bookId)) {
			return false; 
		}
		
		// checks if the book is not taken 
		if(this.bookShelf[bookId].currentBorrowerId == -1) {
			return true;
		}
		
		// the book is taken 
		return false;
	}
	
	/**
	 * Registers the given Patron to this library, if there is a spot available.
	 * @param patron	The patron to register to this library.
	 * @return			a non-negative id number for the patron if there was a spot and the patron 
	 * 					was successfully registered, a negative number otherwise.
	 */
	int registerPatronToLibrary(Patron patron) {
		
		int checkCurrnetPatronId = this.getPatronId(patron);
		
		// checks if that patron is already in the list and returns the patrons id 
		if (checkCurrnetPatronId != -1) {
			return checkCurrnetPatronId; 
		}
		
		// checks if there are to many patrons in the list 
		if (this.patronCounter >= this.maxPatronCapacity)
		{
			return -1; 
		}
		
		// add patrons to the list and update the counter
		this.patronlist[this.patronCounter] = patron;		
		this.patronCounter++; 
		
		return (this.patronCounter - 1); 
	}
	
	/**
	 * Returns true if the given number is an id of a patron in the library, false otherwise
	 * @param patronId		bookId - The id to check.
	 * @return				true if the given number is an id of a patron in the library, false otherwise.
	 */
	boolean isPatronIdValid(int patronId) {
		
		// checks if the id is to big or to small or if that location is not taken
		if (patronId >= this.maxPatronCapacity || this.patronlist[patronId] == null || patronId < 0) { 
			return false;
		}

		return true;
	}
	
	/**
	 * Returns the non-negative id number of the given patron if he is registered to this library, 
	 * -1 otherwise.
	 * @param patron		The patron for which to find the id number.
	 * @return				a non-negative id number of the given patron if he 
	 * 						is registered to this library, -1 otherwise.
	 */
	int getPatronId(Patron patron) {
		
		// goes through all our patrons 
		for (int id = 0; id < this.patronCounter; id++) {			
			// checks if this patron is our needed patron 
			if (this.patronlist[id] == patron) {
				return id; 
			}
		}
		
		// we could not get an id for that patron 
		return -1; 
	}
	
	/**
	 * Marks the book with the given id number as borrowed by the patron with the given patron id,
	 * if this book is available, the given patron isn't already borrowing the maximal number of books 
	 * allowed, and if the patron will enjoy this book.
	 * @param bookId		The id number of the book to borrow.
	 * @param patronId		The id number of the patron that will borrow the book.
	 * @return				true if the book was borrowed successfully, false otherwise.
	 */
	boolean borrowBook(int bookId, int patronId) {
		
		// checks if the book and patron id are not valid or the book is not available  
		if (!this.isBookIdValid(bookId) || !this.isPatronIdValid(patronId) ||
				!this.isBookAvailable(bookId)) {
			return false; 
		}
		
		// checks if the patron took out too many books or wont like that book 
		if (this.patronBookCount[patronId] > this.maxBorrowedBooks ||
		!this.patronlist[patronId].willEnjoyBook(this.bookShelf[bookId])) {
			return false; 
		}
		
		// sets that book to taken 
		this.bookShelf[bookId].setBorrowerId(patronId);
		
		// adds one to the patrons counter
		this.patronBookCount[patronId]++; 
		
		// it was a successful borrow
		return true; 
	}
	
	/**
	 * Return the given book.
	 * @param bookId		The id number of the book to return.
	 */
	void returnBook(int bookId) {
		
		// check if the book id is not valid 
		if (!this.isBookIdValid(bookId)){
			return;
		}
		
		// checks if that book was never taken out
		if (this.isBookAvailable(bookId))
		{
			return; 
		}
				
		// sets the counter on that patron as one less  
		this.patronBookCount[this.bookShelf[bookId].getCurrentBorrowerId()]--;
		
		// sets the current borrower to none
		this.bookShelf[bookId].returnBook();
	}
	
	/**
	 * Suggest the patron with the given id the book he will enjoy the most, 
	 * out of all available books he will enjoy, if any such exist.
	 * @param patronId			The id number of the patron to suggest the book to.
	 * @return					The available book the patron with the given will enjoy the most.
	 * 							Null if no book is available.
	 */
	Book suggestBookToPatron(int patronId) {
	
		// checks if the patron id is invalid 
		if (!this.isPatronIdValid(patronId))
		{
			return null; 
		}
		
		Patron currentPatron = this.patronlist[patronId];
		int currentMaxScore = 0; 
		int maxBookId = -1;
		
		for (int bookId = 0; bookId < this.bookCounter; bookId++) {

			// checks if the book is available and the patron will like it
			if (this.isBookAvailable(bookId) && (currentPatron.willEnjoyBook(this.bookShelf[bookId]))) {

				// gets the patrons score on a book
				int currentBookScore = currentPatron.getBookScore(this.bookShelf[bookId]);

				// if its bigger then the max score set the new max
				if (currentBookScore > currentMaxScore) {
					maxBookId = bookId;
					currentMaxScore = currentBookScore;
				}
			}
		}
		
		// checks if exists a book that the patron will like
		if (maxBookId == -1) {
			return null;
		}
		
		// returns the book with the highest score
		return this.bookShelf[maxBookId]; 
	}
	
}
