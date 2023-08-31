package company.board_project.board;

import company.board_project.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    @RequestMapping("/")
    public String home(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

        // 세션이 없으면 home
        if(session == null) {
            return "home";
        }

        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser == null) {
            return "home";
        }

        model.addAttribute("member",loginUser);
        return "login";
    }
    @RequestMapping("/join")
    public String join(){
        return "join";
    }

    @RequestMapping("/users")
    public String users(){
        return "users";
    }

    @RequestMapping("/board")
    public String board(){
        return "board";
    }

    @RequestMapping("/board/post")
    public String boardPost(){
        return "/board/boardPost";
    }

    @RequestMapping("/board/detail")
    public String boardDetail(){
        return "/board/boardDetail";
    }

    @RequestMapping("/contents/search")
    public String homeKeyword(){
        return "/home/homeKeyword";
    }
    @RequestMapping("/contents/search/username")
    public String homeSearchByName(){
        return "/home/homeSearchByName";
    }
    @RequestMapping("/contents/search/username/result")
    public String homeSearchByNameResult(){
        return "/home/homeSearchByName/result";
    }
    @RequestMapping("/contents/newest")
    public String homeNewest(){
        return "/home/homeNewest";
    }

    @RequestMapping("/contents/latest")
    public String homeLatest(){
        return "/home/homeLatest";
    }

    @RequestMapping("/contents/delete")
    public String contentDelete(){
        return "/board/boardDelete";
    }

    @RequestMapping("/comments/delete")
    public String commentDelete(){
        return "/comment/commentDelete";
    }

    @RequestMapping("/users/delete")
    public String userDelete(){
        return "/users/userDelete";
    }

    @RequestMapping("/contents/update")
    public String contentUpdate(){
        return "/board/boardUpdate";
    }

    @RequestMapping("/comments/update")
    public String commentUpdate(){
        return "/comment/commentUpdate";
    }

    @RequestMapping("/users/update")
    public String userUpdate(){
        return "/users/userUpdate";
    }

    @RequestMapping("/users/mypage")
    public String userMypage(){
        return "/users/userMypage";
    }

    @RequestMapping("/boot/strap")
    public String bootStrap(){
        return "/bootStrap";
    }
}