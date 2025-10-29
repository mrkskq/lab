package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@Component
public class BookReservationServlet extends HttpServlet {

    //Овој сервлет треба да зависи од BookReservationService
    private final BookReservationService bookReservationService;
    private final SpringTemplateEngine springTemplateEngine;

    public BookReservationServlet(BookReservationService bookReservationService, SpringTemplateEngine springTemplateEngine) {
        this.bookReservationService = bookReservationService;
        this.springTemplateEngine = springTemplateEngine;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String bookTitle = req.getParameter("bookTitle");
        int numberOfCopies = Integer.parseInt(req.getParameter("numCopies"));
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");

        String clientIpAddress = req.getRemoteAddr();

        BookReservation reservation = bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numberOfCopies);

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("readerName", reservation.getReaderName());
        context.setVariable("clientIpAddress", clientIpAddress);
        context.setVariable("bookTitle", reservation.getBookTitle());
        context.setVariable("numberOfCopies", reservation.getNumberOfCopies());

        springTemplateEngine.process("reservationConfirmation.html", context, resp.getWriter());
    }
}
