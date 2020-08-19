package com.galid.commerce;

import com.galid.commerce.common.value.Address;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.member.domain.QMemberEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.galid.commerce.domains.member.domain.QMemberEntity.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class QueryDslTest {
    @Autowired
    private EntityManager em;

    private JPAQueryFactory query;

    @BeforeEach
    public void init() {
        this.query = new JPAQueryFactory(em);

        MemberEntity m1 = MemberEntity.builder()
                .address(new Address("seoul", "seoul"))
                .authId("T1")
                .authPw("T1")
                .build();

        MemberEntity m2 = MemberEntity.builder()
                .address(new Address("busan", "busan"))
                .authId("T2")
                .authPw("T2")
                .build();

        MemberEntity m3 = MemberEntity.builder()
                .address(new Address("incheon", "incheon"))
                .authId("T3")
                .authPw("T3")
                .build();

        em.persist(m1);
        em.persist(m2);
        em.persist(m3);
    }

    @Test
    public void 동적쿼리_1() throws Exception {
        //given
        String id = "T1";
        String pw = "T1";

        //when
        List<MemberEntity> result = searchMember1(null, pw);

        //then
        assertEquals(result.size(), 1);
    }

    private List<MemberEntity> searchMember1(String id, String pw) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (id != null)
            booleanBuilder.and(memberEntity.authId.eq(id));

        if (pw != null)
            booleanBuilder.and(memberEntity.authPw.eq(pw));

        return query.selectFrom(memberEntity)
                .where(booleanBuilder)
                .fetch();
    }

    @Test
    public void 동적쿼리_2() throws Exception {
        //given
        String id = "T1";
        String pw = "T1";

        //when
        List<MemberEntity> result = searchMember2(null, pw);

        //then
        assertEquals(result.size(), 1);
    }

    private List<MemberEntity> searchMember2(String id, String pw) {

        return query.selectFrom(memberEntity)
                .where(idEq(id), pwEq(pw))
                .fetch();
    }

    private Predicate idEq(String id) {
        return id == null
                ?
                    null
                :
                    memberEntity.authId.eq(id);
    }

    private Predicate pwEq(String pw) {
        return pw == null
                ?
                    null
                :
                    memberEntity.authPw.eq(pw);
    }

    @Test
    public void like_조회() throws Exception {
        List<MemberEntity> fetch = query.selectFrom(memberEntity)
                .where(memberEntity.authId.like("T%"))
                .fetch();

        System.out.println("size : " + fetch.size());
        fetch.stream()
                .forEach(m -> System.out.println("member : " + m.getAuthId()));
    }

    @Test
    public void in_조회() throws Exception {
        //given
        List<MemberEntity> fetch = query.selectFrom(memberEntity)
                .where(memberEntity.authId.in("T1", "T2"))
                .fetch();

        assertEquals(fetch.size(), 2);
        //when
        //then
    }
}
