package org.wildcodeschool.MyBlogApplication.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wildcodeschool.MyBlogApplication.dto.ImageDTO;
import org.wildcodeschool.MyBlogApplication.model.Article;
import org.wildcodeschool.MyBlogApplication.model.Image;
import org.wildcodeschool.MyBlogApplication.repository.ArticleRepository;
import org.wildcodeschool.MyBlogApplication.repository.ImageRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageRepository imagerepository;
    private final ArticleRepository articleRepository;

    public ImageController(ImageRepository imageRepository, ArticleRepository articleRepository){
        this.imagerepository = imageRepository;
        this.articleRepository = articleRepository;
    }

    private ImageDTO convertToDTO(Image image) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setUrl(image.getUrl());
        if (image.getArticles() != null) {
            imageDTO.setArticleIds(image.getArticles().stream().map(Article::getId).collect(Collectors.toList()));
        }
        return imageDTO;
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> getAllImages() {
        List<Image> images = imagerepository.findAll();
        if (images == null) {
            return ResponseEntity.noContent().build();
        }
        List<ImageDTO> imageDto = images.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(imageDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> getImageById(@PathVariable Long id){
        Image image = imagerepository.findById(id).orElse(null);
        if(image == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDTO(image));
    }

    @PostMapping
    public ResponseEntity<ImageDTO> createImage(@RequestBody Image image){
        Image savedImage = imagerepository.save(image);
        return ResponseEntity.status(201).body(convertToDTO(savedImage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageDTO> updateImage(@PathVariable Long id,@RequestBody Image imageDetails){
        Image image = imagerepository.findById(id).orElse(null);
        if(image == null){
            return ResponseEntity.notFound().build();
        }
        image.setUrl(imageDetails.getUrl());
        Image updatedImage = imagerepository.save(image);
        return ResponseEntity.ok(convertToDTO(updatedImage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id){
        Image image = imagerepository.findById(id).orElse(null);
        if(image == null){
            return ResponseEntity.notFound().build();
        }
        imagerepository.delete(image);
        return ResponseEntity.notFound().build();
    }
}
