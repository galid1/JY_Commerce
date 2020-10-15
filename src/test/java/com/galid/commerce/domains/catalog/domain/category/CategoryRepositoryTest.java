package com.galid.commerce.domains.catalog.domain.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@DataJpaTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void Category_생성_조회() throws Exception {
        //given
        CategoryEntity category1 = new CategoryEntity("c1", 1l);

        //when
        CategoryEntity savedCategory1 = categoryRepository.save(category1);

        //then
        assertTrue("생성 시 반환된 값이 null이 아니다", savedCategory1 != null);
    }

    @Test
    public void id_자동증가() throws Exception {
        //given
        CategoryEntity c1 = new CategoryEntity("c1", 1l);
        CategoryEntity c2 = new CategoryEntity("c2", 1l);
        categoryRepository.saveAll(List.of(c1, c2));

        //when
        CategoryEntity findC1 = categoryRepository.findById(1l)
                .get();
        CategoryEntity findC2 = categoryRepository.findById(2l)
                .get();

        //then
        assertThat(findC1.getCategoryId(), is(1l));
        assertThat(findC2.getCategoryId(), is(2l));
    }
}