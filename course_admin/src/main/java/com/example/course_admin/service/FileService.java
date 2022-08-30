package com.example.course_admin.service;

import com.example.course_admin.exception.BadRequestException;
import com.example.course_admin.exception.NotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    private Path rootPath = Paths.get("uploads");

    public FileService(){
        createFolder(rootPath.toString());
    }
    // Tao folder
    private void createFolder(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    // Upload file
    public String uploadFile(int id, MultipartFile file) {
//        // Tao folder tuong ung voi CourseId
//        Path userPath = rootPath.resolve(String.valueOf(id));
//        createFolder(userPath.toString());
//
//        // Validate file
//        validateFile(file);
//
//        // Tao fileId
//        String fileId = String.valueOf(System.currentTimeMillis());
//        File serverFile = new File(userPath + "/" + fileId);
        File serverFile = new File(rootPath + "/" + id);

        try {
            // Sử dụng Buffer để lưu dữ liệu từ file
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

            stream.write(file.getBytes());
            stream.close();

            return "/course-edit/" + id + "/files";
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi upload file");
        }
    }

    // Check validate
    public void validateFile(MultipartFile file){
        // Kiem tra ten
        String fileName = file.getOriginalFilename();
        if(fileName == null || file.equals("")){
            throw new BadRequestException("File's name can not blank");
        }

        // Kiem tra extension
        // avatar.png -> png
        // avatar.jpg -> jpg
        String fileExtension = getExtensionFile(fileName);
        if(!checkFileExtension(fileExtension)){
            throw new BadRequestException("File khong hop le");
        }

        // Kiem tra size
        // Upload <= 3MB
        double size = file.getSize()/Math.pow(1024,2);
        if(size > 3){
            throw new BadRequestException("Kich thuoc file khong vuot qua 3MB");
        }
    }

    // Lay duoi file
    public String getExtensionFile(String fileName){
        int lastIndex = fileName.lastIndexOf(".");
        if(lastIndex == -1){
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }

    // Kiem tra duoi file
    public boolean checkFileExtension(String fileExtension){
        List<String> extensions = new ArrayList<>(List.of("png", "jpeg", "jpg"));
        return extensions.contains(fileExtension);
    }

    // Xem file
    public byte[] readFile(int id) {
//        // Lấy ra đường dẫn file tương ứng với user_id
//        Path userPath = rootPath.resolve(String.valueOf(id));
//
//        // Kiểm tra xem đường dẫn file có tồn tại hay không
//        if(!Files.exists(userPath)) {
//            throw new NotFoundException("Không thể đọc file " + fileId);
//        }

        try {
            // Lấy ra đường dẫn file tương ứng với course_id
            Path file = rootPath.resolve(String.valueOf(id));
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                InputStream stream = resource.getInputStream();
                byte[] bytes = StreamUtils.copyToByteArray(stream);

                stream.close();
                return bytes;
            } else {
                throw new RuntimeException("Không thể đọc file ");
            }

        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc file ");
        }
    }
}
