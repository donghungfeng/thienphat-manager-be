package com.example.zalo_manager.repository;

import com.example.zalo_manager.entity.Issue;
import com.example.zalo_manager.model.dto.statistic.issue.StatisticQuantityIssueByDateDto;
import com.example.zalo_manager.model.dto.statistic.issue.StatisticQuantityIssueByUserDto;
import com.example.zalo_manager.model.dto.statistic.issue.StatisticSumIssueByUserDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IssueRepository extends BaseRepository<Issue> {
    @Query("""
                SELECT new com.example.zalo_manager.model.dto.statistic.issue.StatisticQuantityIssueByUserDto(
                    u.id,
                    u.username,
                    u.role,
                    u.fullName,
                    u.email,
                    u.phone,
                    u.address,
                    u.status,
                    u.isActive,
                    u.deviceName,
                    u.deviceCode,
                    u.note,
                    u.identityCardNumber,
                    COUNT(i)
                )
                FROM Issue i
                JOIN i.assign u
                WHERE u IS NOT NULL
                AND i.resolveDate BETWEEN :startDate AND :endDate
                AND u.isActive = 1
                AND i.status >= 3
                AND i.isActive = 1
                GROUP BY u.id, u.username, u.role, u.fullName, u.email, u.phone,
                         u.address, u.status, u.deviceName, u.deviceCode, u.note,
                         u.department, u.identityCardNumber
            """)
    List<StatisticQuantityIssueByUserDto> countIssueByAssign(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("""
                SELECT new com.example.zalo_manager.model.dto.statistic.issue.StatisticQuantityIssueByDateDto(
                    i.resolveDate,
                    COUNT(i)
                )
                FROM Issue i
                WHERE i.resolveDate BETWEEN :startDate AND :endDate
                AND i.status >= 3
                AND i.isActive = 1
                GROUP BY i.resolveDate
                ORDER BY i.resolveDate
            """)
    List<StatisticQuantityIssueByDateDto> countIssuesByResolveDate(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("""
            SELECT new com.example.zalo_manager.model.dto.statistic.issue.StatisticSumIssueByUserDto(
                u.id,
                u.username,
                u.role,
                u.fullName,
                u.email,
                u.phone,
                u.address,
                u.status,
                u.isActive,
                u.deviceName,
                u.deviceCode,
                u.note,
                u.identityCardNumber,
                CAST(SUM(i.estimate) AS big_decimal)
            )
            FROM Issue i
            JOIN i.assign u
            WHERE u IS NOT NULL
            AND i.resolveDate BETWEEN :startDate AND :endDate
            AND u.isActive = 1
            AND i.status >= 3
            AND i.isActive = 1
            GROUP BY u.id, u.username, u.role, u.fullName, u.email, u.phone,
                     u.address, u.status, u.deviceName, u.deviceCode, u.note,
                     u.department, u.identityCardNumber
            """)
    List<StatisticSumIssueByUserDto> sumEstimateByAssign(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}
