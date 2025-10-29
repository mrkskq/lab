package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@Component
public class BookListServlet extends HttpServlet {

    //Овој сервлет треба да зависи од BookService
    private final BookService bookService;
    private final SpringTemplateEngine springTemplateEngine;

    public BookListServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String filterTitle = req.getParameter("filterTitle");
        String filterRating = req.getParameter("filterRating");
        Double rating = null;

        if (filterRating != null && !filterTitle.isEmpty()){
            try{
                rating = Double.parseDouble(filterRating);
            } catch (NumberFormatException e){
                rating = null;
            }
        }


        //ako se prazni zemi gi site knigi
        if ((filterTitle == null || filterTitle.isEmpty()) && rating == null){
            context.setVariable("books", bookService.listAll());
        }
        //ako ne se prazni filtriraj spored title i rating
        else{
            context.setVariable("books", bookService.searchBooks(filterTitle, rating));
        }

//      bookService.listAll().forEach(b -> System.out.println(b.getTitle() + " - " + b.getGenre() + " - " + b.getAverageRating()));

        springTemplateEngine.process("listBooks.html", context, resp.getWriter());
    }
}
