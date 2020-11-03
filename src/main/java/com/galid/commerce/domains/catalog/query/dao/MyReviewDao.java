package com.galid.commerce.domains.catalog.query.dao;

import com.galid.commerce.domains.catalog.query.dto.MyReviewSummaryLine;
import com.galid.commerce.domains.catalog.query.dto.QMyReviewSummaryLine;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.galid.commerce.domains.catalog.domain.item.QItemEntity.itemEntity;
import static com.galid.commerce.domains.catalog.domain.review.QReviewEntity.reviewEntity;

@Repository
public class MyReviewDao {
    private JPAQueryFactory query;

    public MyReviewDao(EntityManager em) {
        query = new JPAQueryFactory(em);
    }

    public Page<MyReviewSummaryLine> myReviewSummary(Long memberId, Pageable pageable) {
        QueryResults<MyReviewSummaryLine> result = query.select(new QMyReviewSummaryLine(itemEntity.itemId, itemEntity.name, itemEntity.imagePath, reviewEntity.reviewId, reviewEntity.createdDate, reviewEntity.review.comment, reviewEntity.review.rating))
                .from(reviewEntity)
                .join(itemEntity)
                .on(reviewEntity.product.productId.eq(itemEntity.itemId))
                .where(reviewEntity.reviewerId.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(reviewEntity.createdDate.desc())
                .fetchResults();

        List<MyReviewSummaryLine> contents = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }
}
