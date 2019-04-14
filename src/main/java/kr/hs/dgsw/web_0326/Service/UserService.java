package kr.hs.dgsw.web_0326.Service;

        import kr.hs.dgsw.web_0326.Domain.User;
        import kr.hs.dgsw.web_0326.Protocol.AttachmentProtocol;

        import java.util.List;

public interface UserService {
    List<User> listAllUser();
    User findUser(Long id);
    User init(User user);
    User updateUser(Long id, User user);
    boolean delUser(Long id);
    AttachmentProtocol getUserImgById(Long id);
}
