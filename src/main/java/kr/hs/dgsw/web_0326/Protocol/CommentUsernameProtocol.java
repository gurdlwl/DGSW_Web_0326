package kr.hs.dgsw.web_0326.Protocol;

import kr.hs.dgsw.web_0326.Domain.Comment;
import lombok.Data;

@Data
public class CommentUsernameProtocol extends Comment {
    private String username;

    public CommentUsernameProtocol(Comment c, String username){
        super(c);
        this.username = username;
    }
}
