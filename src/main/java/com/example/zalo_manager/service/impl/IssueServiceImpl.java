package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Issue;
import com.example.zalo_manager.entity.User;
import com.example.zalo_manager.model.request.IssueCreateReq;
import com.example.zalo_manager.model.request.IssueUpdateReq;
import com.example.zalo_manager.model.request.SearchReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.query.CustomRsqlVisitor;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.IssueRepository;
import com.example.zalo_manager.repository.UserRepository;
import com.example.zalo_manager.service.IssueService;
import com.example.zalo_manager.util.ContextUtil;
import com.example.zalo_manager.util.MapperUtil;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class IssueServiceImpl extends BaseServiceImpl<Issue> implements IssueService {
    private static final String DELETED_FILTER = ";isActive>-1";
    private static final String USER_FILTER = ";id==";
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

    @Override
    public Page<Issue> search(SearchReq req) {
        User user = userRepository.findAllByUsername(contextUtil.getUserName()).get();
        if (Objects.equals(user.getRole(), "user")){
            req.setFilter(req.getFilter().concat(";id==" + user.getId()));
        }
        req.setFilter(req.getFilter().concat(DELETED_FILTER));
        Node rootNode = new RSQLParser().parse(req.getFilter());
        Specification<Issue> spec = rootNode.accept(new CustomRsqlVisitor<Issue>());
        Pageable pageable = getPage(req);
        return this.getRepository().findAll(spec, pageable);
    }
}
