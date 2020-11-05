package com.galid.commerce.domains.catalog.query.application;

import com.galid.commerce.domains.catalog.query.dao.MyReviewDao;
import com.galid.commerce.domains.catalog.query.dto.MyReviewSummary;
import com.galid.commerce.domains.catalog.query.dto.MyReviewSummaryLine;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyReviewService {
    private final MyReviewDao myReviewDao;

    public MyReviewSummary myReviewSummary(Long memberId, Pageable pageable) {
        Page<MyReviewSummaryLine> myReviewSummaryLienList = myReviewDao.myReviewSummary(memberId, pageable);

        List<MyReviewSummaryLine> contents = myReviewSummaryLienList.getContent();
        int size = contents.size();

        return new MyReviewSummary(contents, size);
    }
}
