package kr.hs.dgsw.web_0326.Controller;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_0326.Repository.CommentRepository;
import kr.hs.dgsw.web_0326.Repository.UserRepository;
import kr.hs.dgsw.web_0326.Service.CommentService;
import kr.hs.dgsw.web_0326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController {

    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @PostMapping("/attachment")
    public AttachmentProtocol upload(@RequestPart MultipartFile srcFile){
        String destFilename
                = "C:\\Users\\ijh0329\\IdeaProjects\\web_0326\\upload\\"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"))
                + UUID.randomUUID().toString() + "_"
                + srcFile.getOriginalFilename();

        try{
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            srcFile.transferTo(destFile);
            return new AttachmentProtocol(destFilename, srcFile.getOriginalFilename());
        } catch(Exception e){
            return null;
        }
    }

    @PostMapping("/profile/{username}")
    public AttachmentProtocol uploadProfile(@PathVariable String username, @RequestPart MultipartFile srcFile){

        String profileDestFilename
                = "C:\\Users\\ijh0329\\IdeaProjects\\web_0326\\profile\\"
                + username + "\\"
                + UUID.randomUUID().toString() + "_"
                + srcFile.getOriginalFilename();

        try{
            File profileDestFile = new File(profileDestFilename);
            profileDestFile.getParentFile().mkdirs();
            srcFile.transferTo(profileDestFile);
            return new AttachmentProtocol(profileDestFilename, srcFile.getOriginalFilename());
        } catch(Exception e){
            return null;
        }
    }

    @GetMapping("/attachment/{type}/{id}")
    public void download(@PathVariable String type, @PathVariable Long id, HttpServletRequest req, HttpServletResponse resp){
        try{
            AttachmentProtocol attachmentProtocol = null;

            if(type.equals("comment")){
                attachmentProtocol = commentService.getCommentImgById(id);
            } else if (type.equals("user")){
                attachmentProtocol = userService.getUserImgById(id);
            }

            String filePath = attachmentProtocol.getStoredPath();
            String fileName = attachmentProtocol.getOriginalName();

            File file = new File(filePath);
            if(file.exists() == false) return;

            String mineType = URLConnection.guessContentTypeFromName(file.getName());
            if (mineType == null) mineType = "application/octet-stream";

            resp.setContentType(mineType);
            resp.setHeader("Content-Disposition", "inline; filename = \"" + fileName + "\"");
            resp.setContentLength((int) file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, resp.getOutputStream());

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }
}
