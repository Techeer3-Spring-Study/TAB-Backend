//package com.techeeresc.tab.domain.member.respository;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import static com.techeeresc.tab.domain.member.entity.QMember.member;
//
//@RequiredArgsConstructor
//@Repository
//public class MemberQueryDSLRepository {
//    private final JPAQueryFactory queryFactory;
//
//    public int id InacitveMember(int id) {
////        return queryFactory.selectFrom(academy)
////                .where(academy.name.eq(name))
////                .fetch();
//        return queryFactory
//                .update(member)
//                .set(member.isActive, false)
//                .where(member.id)
//                .execute();
//    }
//    }
//}