package com.galid.commerce.domains.catalog.domain.item;

import com.galid.commerce.domains.catalog.presentation.Sorter;
import com.galid.commerce.domains.catalog.service.ItemSearchForm;
import com.galid.commerce.domains.catalog.service.ItemSummaryInItemList;
import com.galid.commerce.domains.catalog.service.QItemSummaryInItemList;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.galid.commerce.domains.catalog.domain.item.QItemEntity.itemEntity;


@Repository
public class ItemQuery {
    private JPAQueryFactory query;

    public ItemQuery(EntityManager entityManager) {
        this.query = new JPAQueryFactory(entityManager);
    }

    public List<ItemSummaryInItemList> searchItem(ItemSearchForm searchForm)  {
        return query
                .select(new QItemSummaryInItemList(itemEntity.itemId, itemEntity.imagePath, itemEntity.name, itemEntity.price))
                .from(itemEntity)
                .where(nameLike(searchForm.getName()))
                .orderBy(sorter(searchForm.getSorter()))
                .fetch();
    }

    private Predicate nameLike(String name) {
        if (name != null && name.length() > 0)
            return itemEntity.name.like("%" + name + "%");
        return null;
    }

    private OrderSpecifier sorter(Sorter sorter) {
        if (sorter == null)
            return itemEntity.createdDate.desc();

        if (sorter == Sorter.PRICE)
            return itemEntity.price.desc();

        if (sorter == Sorter.LATEST)
            return itemEntity.createdDate.desc();

        return itemEntity.createdDate.desc();
    }

    public List<ItemEntity> findItemsByIds(List<Long> ids) {
        return query.selectFrom(itemEntity)
                .where(itemEntity.itemId.in(ids))
                .fetch();
    }
}
