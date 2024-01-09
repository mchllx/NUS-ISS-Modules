package sg.edu.iss.nus.day21.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import sg.edu.iss.nus.day21.models.Book;
import sg.edu.iss.nus.day21.repositories.BookRepository;
import sg.edu.iss.nus.day21.services.BookService;

@Controller
@RequestMapping(path={"/","/index"})

public class BookController {

    @Autowired
    private BookService bookSvc;

    // GET
    @GetMapping
    public ModelAndView showListing(
        @RequestParam(defaultValue="39") int limit,
        @RequestParam(defaultValue="0") int offset) {
        // limit and offset are queries

        List<Book> bookList = bookSvc.findBooksOrderByTitle(limit, offset);
        // System.out.println("----Book List Controller-----" + bookList);

        List<Integer> pages = new LinkedList<>();
        int totalPageNum = (int)Math.floor(bookSvc.count()/limit);
        System.out.println("------Total Page Num------" + totalPageNum);

        for (int i=1; i <= totalPageNum; i++) {
            pages.add(i);
        }

        ModelAndView mav = new ModelAndView("view0");
        mav.setStatus(HttpStatusCode.valueOf(200));
        mav.addObject("books", bookList);
        mav.addObject("count", bookSvc.count());
        mav.addObject("pages", pages);

        return mav;
    }

    // GET /index/{bookId}

    @GetMapping(path={"/book/{bookId}"})
    public ModelAndView showBook(@PathVariable ("bookId") String bookId) {
        Optional<Book> opt = bookSvc.findBooksById(bookId);

        // Optional does not contain getters/methods for specific fields
        Book book = opt.get();
        // System.out.println("-------Book Id Controller------\n" + opt.get());

        ModelAndView mav = new ModelAndView("view1");
        mav.setStatus(HttpStatusCode.valueOf(200));
        mav.addObject("books", book);

        return mav;
    }
    
    //Json/API
    @GetMapping(path={"/book/{bookId}"}, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getBookAsJson(@PathVariable String bookId) {
        Optional<Book> opt = bookSvc.findBooksById(bookId);
        Book book = opt.get();

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            arrBuilder.add(book.toJSON());
        JsonArray array = arrBuilder.build();

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(array.toString());

    }
}
