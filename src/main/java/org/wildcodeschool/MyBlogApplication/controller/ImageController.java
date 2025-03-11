package org.wildcodeschool.MyBlogApplication.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wildcodeschool.MyBlogApplication.dto.ImageDTO;
import org.wildcodeschool.MyBlogApplication.model.Article;
import org.wildcodeschool.MyBlogApplication.model.Image;
import org.wildcodeschool.MyBlogApplication.repository.ArticleRepository;
import org.wildcodeschool.MyBlogApplication.repository.ImageRepository;
import org.wildcodeschool.MyBlogApplication.service.ImageService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;


    public ImageController( ImageService imageService){
        this.imageService = imageService;
    }


    @GetMapping
    public ResponseEntity<List<ImageDTO>> getAllImages() {
        List<ImageDTO> images = imageService.getAllImages();
        if (images == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(images);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> getImageById(@PathVariable Long id){
        ImageDTO image = imageService.getImageById(id);
        if(image == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(image);
    }

    @PostMapping
    public ResponseEntity<ImageDTO> createImage(@RequestBody Image image){
        ImageDTO savedImage = imageService.createImage(image);
        return ResponseEntity.status(201).body(savedImage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageDTO> updateImage(@PathVariable Long id,@RequestBody Image imageDetails){
        ImageDTO image = imageService.updateImage(id, imageDetails);
        if(image == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(image);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id){
        if(!imageService.deleteImage(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }
}
