package kr.hs.dgsw.web_0326.Controller;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_0326.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public List<CommentUsernameProtocol> listComments(){
        return this.commentService.listAllComments();
    }

    @GetMapping("/findComment/{id}")
    public List<CommentUsernameProtocol> viewByUserId(@PathVariable Long id){
        return this.commentService.viewByUserId(id);
    }

    @PutMapping("/update/{id}")
    public CommentUsernameProtocol updateComment(@PathVariable Long id, @RequestBody Comment comment){
        return this.commentService.updateComment(id, comment);
    }

    @PostMapping("/insert")
    public CommentUsernameProtocol addComment(@RequestBody Comment comment){
        return this.commentService.addComment(comment);
    }

    @DeleteMapping("/delComment/{id}")
    public boolean delComment(@PathVariable Long id){
        return this.commentService.delComment(id);
    }

}
