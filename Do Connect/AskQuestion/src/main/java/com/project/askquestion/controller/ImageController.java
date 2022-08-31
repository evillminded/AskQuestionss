package com.project.askquestion.controller;

/*Author: Rajasree
 * Modified Date :25-08-2022
 * Description : Created contorller for image
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.project.askquestion.repository.IOwnerRepo;
import com.project.askquestion.repository.ImageRepository;
import com.project.askquestion.service.IEndUserService;
import com.project.askquestion.util.ImageUtility;
import com.project.askquestion.dto.*;
import com.project.askquestion.entity.Image;
import com.project.askquestion.entity.Owner;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin() 
@RequestMapping("/user")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;
    
    @Autowired
    IEndUserService endUserService;
    
    @Autowired
    IOwnerRepo adminRepo;
    

    @PostMapping("/upload/image")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file)
            throws IOException {

        imageRepository.save(Image.builder() 
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build());
        
        List<Owner> admins = adminRepo.findAll();
		for (Owner admin : admins) {
			endUserService.sendMail(admin.getEmail(), "Answer Added");
		}
		
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @GetMapping(path = {"/get/image/info/{name}"})
    public Image getImageDetails(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }

    @GetMapping(path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }
}