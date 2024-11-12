class Library {
    constructor(bookList, borrowLimit) {
        this.bookList = bookList       
        this.borrowLimit = borrowLimit     
    }

    addBook(newBook) {
        this.bookList.push(newBook)
        return "Book added:" + newBook
    }

    getAvailableBooks() {
        if(this.bookList.length > 0) {        
            return "Available books: " + this.bookList; 
        } else {
            return "No Books Left";       
        }
    }

    borrowBook(bookName) {
        if(this.bookList.length > this.borrowLimit && bookName in this.bookList) {       
            return "You borrowed " + bookName
        } else {
            return "You cannot borrow " + bookName
        }
    }

    overdueBook(overLimit) {
        "You have to return " + overLimit + " books out of this list:" + this.bookList
        
    }
}

    var library = new Library(["The Lord of the Rings", "The Hobbit", "The Silmarillion"], 2)
    library.addBook("The Fellowship of the Ring")
    console.log(library.getAvailableBooks())
    console.log(library.borrowBook("The Hobbit"))
