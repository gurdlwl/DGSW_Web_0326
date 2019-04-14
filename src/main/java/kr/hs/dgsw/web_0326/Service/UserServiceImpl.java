package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_0326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listAllUser(){
        return userRepository.findAll();
    }

    @Override
    public User findUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User init(User user){
        Optional<User> found = this.userRepository.findByEmail(user.getEmail());
        if(found.isPresent()) return null;

        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user){
        Optional<User> found = this.userRepository.findById(id);
        if(found.isPresent()){
            User u = found.get();
            u.setEmail(user.getEmail());
            u.setUsername(user.getUsername());
            u.setPath(user.getPath());
            u.setFilename(user.getFilename());

            return this.userRepository.save(u);
        }
        return null;
    }

    @Override
    public boolean delUser(Long id) {
        try {
            this.userRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public AttachmentProtocol getUserImgById(Long id) {
        return this.userRepository.findById(id)
                .map(found -> new AttachmentProtocol(
                        found.getPath(),
                        found.getFilename()
                ))
                .orElse(null);
    }
}
