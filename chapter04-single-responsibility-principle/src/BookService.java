import java.time.LocalDate;
import java.util.*;

interface BookRepo {
    Book findByAuthorIdAndBookName(int authorId, String bookName);

    void save(Book book);

    List<Book> findByBookIds(List<Integer> bookIds);
}

interface AuthorRepo {
    boolean checkAuthorId(int authorId);

    Author save(Author author);
}

class BookRepository implements BookRepo {
    private Map<Integer, Book> books = new HashMap<>();
    private static int counter = 0;

    @Override
    public Book findByAuthorIdAndBookName(int authorId, String bookName) {
        for (Book book : books.values()) {
            if (book.getAuthorId() == authorId && book.getName().equals(bookName)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void save(Book book) {
        int id = generateUniqueId();
        book.setId(id);
        books.put(id, book);
    }

    @Override
    public List<Book> findByBookIds(List<Integer> bookIds) {
        List<Book> foundBooks = new ArrayList<>();
        for (Integer bookId : bookIds) {
            Book book = books.get(bookId);
            if (book != null) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    private int generateUniqueId() {
        return ++counter;
    }
}

class AuthorRepository implements AuthorRepo {
    private Map<Integer, Author> authors = new HashMap<>();

    @Override
    public boolean checkAuthorId(int authorId) {
        return authors.containsKey(authorId);
    }

    @Override
    public Author save(Author author) {
        authors.put(author.getAuthorId(), author);
        return author;
    }
}

class Book {
    private int id;
    private int authorId;
    private String name;
    private String publisherName;
    private LocalDate released;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    @Override
    public String toString() {
        return "Buku{" +
                "id=" + id +
                ", idPenulis=" + authorId +
                ", judul='" + name + '\'' +
                ", penerbit='" + publisherName + '\'' +
                ", tanggalRilis=" + released +
                '}';
    }
}

class Author {
    private int authorId;
    private String name;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class BookService {
    private final BookRepo bookRepo;
    private final AuthorService authorService;

    public BookService(BookRepo bookRepo, AuthorService authorService) {
        this.bookRepo = bookRepo;
        this.authorService = authorService;
    }

    public void saveBook(int authorId, String bookName, String publisher) throws Exception {
        validateBook(authorId, bookName);
        authorService.saveIfNotExist(authorId);
        String publisherName = getPublisherName(publisher);

        Book book = new Book();
        book.setAuthorId(authorId);
        book.setName(bookName);
        book.setPublisherName(publisherName);
        bookRepo.save(book);
    }

    private String getPublisherName(String publisher) {
        return publisher != null ? publisher : "Anonim";
    }

    private void validateBook(int authorId, String bookName) throws Exception {
        if (bookName == null)
            throw new Exception("Judul Buku kosong");
        Book bookByAuthorIdAndBookName = bookRepo.findByAuthorIdAndBookName(authorId, bookName);
        if (bookByAuthorIdAndBookName != null) {
            throw new Exception("Buku Duplikat");
        }
    }

    public Map<Integer, List<Book>> rilisBukuBerdasarkanPenulis(List<Integer> bookIds) {
        List<Book> books = updateRilisBuku(bookIds);
        return grupBukuBerdasarkanPenulis(books);
    }

    private Map<Integer, List<Book>> grupBukuBerdasarkanPenulis(List<Book> books) {
        Map<Integer, List<Book>> booksByAuthor = new HashMap<>();
        for (Book book : books) {
            int authorId = book.getAuthorId();
            List<Book> bookList = booksByAuthor.getOrDefault(authorId, new ArrayList<>());
            bookList.add(book);
            booksByAuthor.put(authorId, bookList);
        }
        return booksByAuthor;
    }

    private List<Book> updateRilisBuku(List<Integer> bookIds) {
        List<Book> books = bookRepo.findByBookIds(bookIds);
        for (Book book : books) {
            book.setReleased(LocalDate.now());
        }
        return books;
    }
}

class AuthorService {
    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public void saveIfNotExist(int authorId) {
        boolean existedAuthor = authorRepo.checkAuthorId(authorId);
        if (!existedAuthor) {
            Author author = new Author();
            author.setName("Tidak Diketahui");
            author.setAuthorId(authorId);
            authorRepo.save(author);
        }
    }
}
class Main {
    public static void main(String[] args) {
        BookRepo bookRepo = new BookRepository();
        AuthorRepo authorRepo = new AuthorRepository();
        AuthorService authorService = new AuthorService(authorRepo);
        BookService bookService = new BookService(bookRepo, authorService);

        try {
            bookService.saveBook(1, "Cantik Itu Luka", "Eka Kurniawan");
            bookService.saveBook(2, "Laut Bercerita", "Leila S. Chudori");
            bookService.saveBook(3, "Bumi Manusia", "Pramoedya Ananta Toer");
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Integer> bookIds = List.of(1, 2, 3);
        Map<Integer, List<Book>> booksByAuthor = bookService.rilisBukuBerdasarkanPenulis(bookIds);

        for (Map.Entry<Integer, List<Book>> entry : booksByAuthor.entrySet()) {
            int authorId = entry.getKey();
            List<Book> books = entry.getValue();
            System.out.println("Penulis ID: " + authorId);
            for (Book book : books) {
                System.out.println("\t" + book);
            }
        }
    }
}