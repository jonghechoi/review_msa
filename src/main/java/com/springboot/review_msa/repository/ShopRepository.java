package com.springboot.review_msa.repository;

import com.querydsl.core.types.Projections;
import com.springboot.review_msa.config.jpa.CustomJPAQueryFactory;
import com.springboot.review_msa.domain.QShop;
import com.springboot.review_msa.web.dto.ShopDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ShopRepository {
    private final CustomJPAQueryFactory jpaQueryFactory;
    private final QShop qShop;

    public List<ShopDTO> getShopAdvertisementGrade() {
        System.out.println("#".repeat(100));
        System.out.println("###### getShopAdvertisementGrade 실행 ######");
        return jpaQueryFactory
                .select(
                        Projections.fields(ShopDTO.class,
                        qShop.sid,
                        qShop.adgrade)
                )
                .from(qShop)
                .fetch();
    }
}
