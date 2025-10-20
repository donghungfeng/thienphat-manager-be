package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Comment;
import com.example.zalo_manager.entity.Issue;
import com.example.zalo_manager.entity.User;
import com.example.zalo_manager.model.dto.CommentDto;
import com.example.zalo_manager.model.request.CommentCreateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.CommentRepository;
import com.example.zalo_manager.repository.IssueRepository;
import com.example.zalo_manager.repository.UserRepository;
import com.example.zalo_manager.service.CommentService;
import com.example.zalo_manager.util.ContextUtil;
import com.example.zalo_manager.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ContextUtil contextUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Override
    protected BaseRepository<Comment> getRepository() {
        return commentRepository;
    }

    @Override
    public BaseResponse create(CommentCreateReq req) {
        User user = userRepository.findAllByUsername(contextUtil.getUserName()).get();
        Issue issue = issueRepository.findAllByIdAndIsActive(req.getIssueId(), 1);
        if (issue == null){
            return BaseResponse.fail("Issue không tồn tại");
        }
        Comment comment = Comment
                .builder()
                .assign(user)
                .issue(issue)
                .text(req.getText())
                .status(req.getStatus())
                .build();
        return BaseResponse.success(MapperUtil.map(commentRepository.save(comment), CommentDto.class));
    }
}
