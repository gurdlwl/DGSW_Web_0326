package kr.hs.dgsw.web_0326.Controller;

import kr.hs.dgsw.web_0326.Protocol.AttachmentProtocol;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController {

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

    @PostMapping("/profile")
    public AttachmentProtocol uploadProfile(@RequestBody MultipartFile srcFile){

        String profileDestFilename
                = "C:\\Users\\ijh0329\\IdeaProjects\\web_0326\\profile\\"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"))
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
}
