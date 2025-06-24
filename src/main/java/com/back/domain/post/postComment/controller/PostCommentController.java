package com.back.domain.post.postComment.controller;

import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostCommentController {
    private final PostService postService;

    @AllArgsConstructor
    @Getter
    public static class WriteForm {
        @NotBlank
        @Size(min = 2, max = 100)
        private String content;
    }

    @PostMapping("/posts/{postId}/comments/write")
    @Transactional
    public String write(
            @PathVariable int postId,
            @Valid WriteForm writeForm
    ) {
        Post post = postService.findById(postId).get();

        postService.writeComment(post, writeForm.getContent());

        return "redirect:/posts/" + postId;
    }
}
