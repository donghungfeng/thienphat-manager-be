package com.example.zalo_manager.controller;

import com.example.zalo_manager.entity.Issue;
import com.example.zalo_manager.model.dto.IssueDto;
import com.example.zalo_manager.model.request.IssueCreateReq;
import com.example.zalo_manager.model.request.IssueUpdateReq;
import com.example.zalo_manager.model.request.SearchReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.service.BaseService;
import com.example.zalo_manager.service.IssueService;
import com.example.zalo_manager.util.MapperUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("issue")
public class IssueController extends BaseController<Issue, IssueDto>{
    @Autowired
    IssueService issueService;

    public IssueController() {
        super(IssueDto.class);
    }

    @Override
    protected BaseService<Issue> getService() {
        return issueService;
    }

    @PostMapping("create")
    public BaseResponse create(@RequestBody @Valid IssueCreateReq req){
        return issueService.create(req);
    }

    @PutMapping("update")
    public BaseResponse update(@RequestBody @Valid IssueUpdateReq req){
        return issueService.update(req);
    }

    @Override
    @GetMapping("/search")
    public BaseResponse search(SearchReq req) {
        return issueService.customSearch(req);
    }

}
