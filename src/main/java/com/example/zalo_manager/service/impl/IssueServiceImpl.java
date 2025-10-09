package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Issue;
import com.example.zalo_manager.entity.User;
import com.example.zalo_manager.model.request.IssueCreateReq;
import com.example.zalo_manager.model.request.IssueUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.IssueRepository;
import com.example.zalo_manager.repository.UserRepository;
import com.example.zalo_manager.service.IssueService;
import com.example.zalo_manager.service.UserService;
import com.example.zalo_manager.util.ContextUtil;
import com.example.zalo_manager.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class IssueServiceImpl extends BaseServiceImpl<Issue> implements IssueService {
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ContextUtil contextUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected BaseRepository<Issue> getRepository() {
        return issueRepository;
    }

    @Override
    public BaseResponse create(IssueCreateReq req) {
        User user = userRepository.findAllByUsername(contextUtil.getUserName()).get();
        Issue issue = MapperUtil.map(req, Issue.class);
        issue.setCreate(user);
        if (req.getAssignId() != null){
            User assignedUser = userRepository.findAllByIdAndIsActive(req.getAssignId(), 1);
            if (assignedUser == null){
                return BaseResponse.success(req, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Assigned không tồn tại");
            }
            if (assignedUser.getStatus() == -1){
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài khoản đã bị khóa không thể assign");
            }
            issue.setAssign(assignedUser);
        }
        return BaseResponse.success(issueRepository.save(issue));
    }

    @Override
    public BaseResponse update(IssueUpdateReq req) {
        Issue issue = issueRepository.findAllByIdAndIsActive(req.getId(), 1);
        issue = MapperUtil.mapValue(req, issue);
        if (issue == null){
            return BaseResponse.success(req, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Issue không tồn tại");
        }
        if (req.getCreateId() != null){
            User createUser = userRepository.findAllByIdAndIsActive(req.getCreateId(), 1);
            if (createUser == null){
                return BaseResponse.success(req, HttpStatus.INTERNAL_SERVER_ERROR.value(),"createUser không tồn tại");
            }
            if (createUser.getStatus() == -1){
                return BaseResponse.fail(createUser, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài khoản đã bị khóa không thể assign");
            }
        }
        if (req.getAssignId() != null){
            User assignedUser = userRepository.findAllByIdAndIsActive(req.getAssignId(), 1);
            if (assignedUser == null){
                return BaseResponse.success(req, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Assigned không tồn tại");
            }
            if (assignedUser.getStatus() == -1){
                return BaseResponse.fail(assignedUser, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài khoản đã bị khóa không thể assign");
            }
            issue.setAssign(assignedUser);
        }
        return BaseResponse.success(issueRepository.save(issue));
    }
}
