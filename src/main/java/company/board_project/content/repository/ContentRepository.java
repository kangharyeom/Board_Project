package company.board_project.content.repository;

import company.board_project.content.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findAll();
    @Query(value = "select * from contents where user_id = :userId", nativeQuery = true)
    List<Content> findByUserId(@Param("userId") long userId);
    Optional<Content> findByContentId(long contentId);

    @Query(value = "select * from contents where title like %:keyword% or content like %:keyword% ", nativeQuery = true)
    List<Content> findAllSearch(@Param(value = "keyword")String keyword);

    @Query(value = "select * from contents where category_type like %:category%", nativeQuery = true)
    List<Content> findAllByCategoryType(@Param(value = "category")String category);

    @Query(value = "select * from contents where name like :name", nativeQuery = true)
    List<Content> findAllSearchByUserName(@Param(value = "name")String name);

    @Query(value = "select * from contents order by created_at desc", nativeQuery = true)
    List<Content> findContentsNewest();

    @Query(value = "select * from contents order by created_at asc", nativeQuery = true)
    List<Content> findContentsLatest();

}
