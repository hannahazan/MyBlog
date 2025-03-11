package org.wildcodeschool.MyBlogApplication.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.wildcodeschool.MyBlogApplication.dto.AuthorDTO;
import org.wildcodeschool.MyBlogApplication.mapper.AuthorMapper;
import org.wildcodeschool.MyBlogApplication.model.Author;
import org.wildcodeschool.MyBlogApplication.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

        private AuthorRepository authorRepository;
        private AuthorMapper authorMapper;

        public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper){
            this.authorRepository = authorRepository;
            this.authorMapper = authorMapper;
        }

        public List<AuthorDTO> getAllAuthors(){
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(authorMapper::convertToDTO).collect(Collectors.toList());
        }

        public AuthorDTO getAuthoById(Long id){
            Author author = authorRepository.findById(id).orElse(null);
            return authorMapper.convertToDTO(author);
        }

        public AuthorDTO createAuthor(Author author){
            Author savedAuthor = authorRepository.save(author);
            return authorMapper.convertToDTO(savedAuthor);
        }

        public AuthorDTO updateAuthor(Long id,Author authorDetails){
            Author author = authorRepository.findById(id).orElse(null);

            author.setFirstname(authorDetails.getFirstname());
            author.setLastname(authorDetails.getLastname());

            Author updatedAuthor = authorRepository.save(author);

            return authorMapper.convertToDTO(updatedAuthor);
        }

        public Boolean deleteAuthor(Long id){
            Author author = authorRepository.findById(id).orElse(null);
            if (author == null) {
                return false;
            }
            authorRepository.deleteById(id);
            return true;
        }

}
