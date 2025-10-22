package com.example.zalo_manager.repository;

import com.example.zalo_manager.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends BaseRepository<Comment>{
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.issue.id = :issueId and c.isActive = 1")
    long countByIssueId(@Param("issueId") Long issueId);
}
