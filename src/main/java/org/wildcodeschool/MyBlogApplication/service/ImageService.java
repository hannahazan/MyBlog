package org.wildcodeschool.MyBlogApplication.service;

import org.springframework.stereotype.Service;
import org.wildcodeschool.MyBlogApplication.dto.ImageDTO;
import org.wildcodeschool.MyBlogApplication.mapper.ImageMapper;
import org.wildcodeschool.MyBlogApplication.repository.ImageRepository;
import org.wildcodeschool.MyBlogApplication.model.Image;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {
    public final ImageRepository imageRepository;
    public final ImageMapper  imageMapper;

    public ImageService(ImageRepository imageRepository, ImageMapper imageMapper){
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    public List<ImageDTO> getAllImages(){
        List<Image> images = imageRepository.findAll();

        return images.stream().map(imageMapper::convertToDTO).collect(Collectors.toList());
    }

    public ImageDTO getImageById(Long id){
        Image image = imageRepository.findById(id).orElse(null);

        return imageMapper.convertToDTO(image);
    }

    public ImageDTO createImage(Image image){
        Image savedImage = imageRepository.save(image);

        return imageMapper.convertToDTO(savedImage);
    }

    public ImageDTO updateImage(Long id, Image imageDetails){
        Image image = imageRepository.findById(id).orElse(null);

        image.setUrl(imageDetails.getUrl());
        Image updateImage = imageRepository.save(image);

        return imageMapper.convertToDTO(updateImage);
    }

    public Boolean deleteImage(Long id){
        Image image = imageRepository.findById(id).orElse(null);
        if(image == null){
            return false;
        }
        imageRepository.deleteById(id);
        return true;
    }

}
