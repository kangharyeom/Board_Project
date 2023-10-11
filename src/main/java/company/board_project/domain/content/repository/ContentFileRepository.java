package company.board_project.domain.content.repository;

import company.board_project.domain.content.entity.ContentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentFileRepository extends JpaRepository<ContentFile, Long> {
    List<ContentFile> findByContentId(long contentId);
    @Query(value = "select * from content_file where user_id = :userId", nativeQuery = true)
    List<ContentFile> findByUserId(@Param("userId") long userId);
    @Modifying
    @Query(value = "delete from content_file where content_id=:contentId", nativeQuery = true)
    void deleteAllByContentId(@Param("contentId")Long contentId);
}