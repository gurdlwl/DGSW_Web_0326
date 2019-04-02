package kr.hs.dgsw.web_0326.Repository;

import kr.hs.dgsw.web_0326.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUserId(Long id);
}
