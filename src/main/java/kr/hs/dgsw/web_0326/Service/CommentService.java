package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Protocol.CommentUsernameProtocol;

import java.util.List;

public interface CommentService {
    List<CommentUsernameProtocol> listAllComments();
    CommentUsernameProtocol updateComment(Long id, Comment comment);
    CommentUsernameProtocol addComment(Comment comment);
    List<CommentUsernameProtocol> viewByUserId(Long id);
    boolean delComment(Long id);
}
