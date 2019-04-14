package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_0326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_0326.Repository.CommentRepository;
import kr.hs.dgsw.web_0326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init() {
        User u = this.userRepository.save(new User("aaa", "aaa@dgsw.hs.kr"));
    }

    @Override
    public List<CommentUsernameProtocol> listAllComments(){
        List<Comment> commentList = this.commentRepository.findAll();
        return createList(commentList);
    }

    @Override
    public CommentUsernameProtocol updateComment(Long id, Comment comment) {
        return this.commentRepository.findById(id)
                .map(found -> {
                    found.setContent(comment.getContent());
                    return new CommentUsernameProtocol(commentRepository.save(found), getUsername(comment));
                })
                .orElse(null);
    }

    @Override
    public CommentUsernameProtocol addComment(Comment comment) {
        return new CommentUsernameProtocol(this.commentRepository.save(comment), getUsername(comment));
    }

    @Override
    public List<CommentUsernameProtocol> viewByUserId(Long id) {
        List<Comment> commentList = this.commentRepository.findByUserId(id);
        return createList(commentList);
    }

    @Override
    public boolean delComment(Long id) {
        try{
            this.commentRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    private String getUsername(Comment comment){
        Optional<User> found = this.userRepository.findById(comment.getUserId());
        return (found.map(User::getUsername).orElse(null));
    }

    private List<CommentUsernameProtocol> createList(List<Comment> commentList){
        List<CommentUsernameProtocol> cupList = new ArrayList<>();
        commentList.forEach( comment -> {
            cupList.add(new CommentUsernameProtocol(comment, getUsername(comment)));
        });

        return cupList;
    }

    @Override
    public AttachmentProtocol getCommentImgById(long id){
        return this.commentRepository
                .findById(id)
                .map(found -> new AttachmentProtocol(
                        found.getImgPath(),
                        found.getImgName()))
                .orElse(null);
    }
}