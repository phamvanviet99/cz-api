package com.phamvanviet.demo.domain.services;

import com.phamvanviet.demo.app.dtos.CommentDTO;
import com.phamvanviet.demo.domain.entities.comment.Comment;
import com.phamvanviet.demo.domain.entities.product.Product;
import com.phamvanviet.demo.domain.entities.user.User;
import com.phamvanviet.demo.domain.exception.NotFoundException;
import com.phamvanviet.demo.domain.model.TokenInfo;
import com.phamvanviet.demo.domain.repositories.CommentRepository;
import com.phamvanviet.demo.domain.repositories.ProductRepository;
import com.phamvanviet.demo.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CommentService extends BaseService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity create(CommentDTO commentDTO, String accessToken) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        Product product = productRepository.getOne(commentDTO.getProductId());
        if (Objects.isNull(product))
            throw new NotFoundException("Product not found Id: "+commentDTO.getProductId());

        User user = userRepository.getOne(tokenInfo.getUserId());
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setProduct(product);
        comment.setCreatedBy(user);
        commentRepository.save(comment);
        return new ResponseEntity<>("created!", HttpStatus.OK);
    }

    public ResponseEntity update(CommentDTO commentDTO, String accessToken, Integer id) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        Comment comment = commentRepository.findCommentByIdAndCreatedById(id,tokenInfo.getUserId());
        if (Objects.isNull(comment))
            throw new NotFoundException("Comment not found Id: "+id);
        User user = userRepository.getOne(tokenInfo.getUserId());

        comment.setContent(commentDTO.getContent());
        comment.setUpdatedBy(user);
        commentRepository.save(comment);
        return new ResponseEntity<>("updated!", HttpStatus.OK);
    }

    public ResponseEntity delete(String accessToken, Integer id) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        Comment comment = commentRepository.findCommentByIdAndCreatedById(id,tokenInfo.getUserId());
        if (Objects.isNull(comment))
            throw new NotFoundException("Comment not found Id: "+id);
        commentRepository.deleteById(id);
        return new ResponseEntity<>("deleted!", HttpStatus.OK);
    }

}
