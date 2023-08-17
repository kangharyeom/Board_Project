package company.board_project.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class HomeController {
    @RequestMapping(value = "/3000/homepage", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);

        return "homepage";
    }

    @RequestMapping(value = "/3000/contents", method = RequestMethod.GET)
    public String contentList() {


        return "contentList";
    }

    @RequestMapping(value = "/3000/contents/contentDetail", method = RequestMethod.GET)
    public String contentDetail() {


        return "contentDetail";
    }

    @RequestMapping(value = "/3000/contents/contentRegister", method = RequestMethod.GET)
    public String contentRegister() {

        return "contentRegister";
    }
}