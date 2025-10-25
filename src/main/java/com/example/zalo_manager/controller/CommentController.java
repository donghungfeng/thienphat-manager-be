package com.example.zalo_manager.controller;

import com.example.zalo_manager.entity.Comment;
import com.example.zalo_manager.model.dto.CommentDto;
import com.example.zalo_manager.model.request.CommentCreateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.service.BaseService;
import com.example.zalo_manager.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("comment")
public class CommentController extends BaseController<Comment, CommentDto>{
    @Autowired
    private CommentService commentService;

    public CommentController() {
        super(CommentDto.class);
    }

    @Override
    protected BaseService<Comment> getService() {
        return commentService;
    }

    @PostMapping("create")
    public BaseResponse create(@RequestBody @Valid CommentCreateReq req){
        return commentService.create(req);
    }

    @DeleteMapping("/delete")
    @Override
    public BaseResponse deleteById(@RequestParam(name = "id") Long id) {
        return commentService.deleteCustom(id);
    }
}
